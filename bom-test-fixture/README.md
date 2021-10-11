# Gradle Test Fixture 사용해서 테스트 중복코드 줄이기

## Gradle Test Fixture
테스트코드를 작성하다보면 테스트 코드에서도 중복된 코드가 굉장히 많이 발생하게 된다.  
대표적인 예로 특정 도메인 객체를 생성해야하는 작업이다.  
이러한 코드가 작성하기 어렵지는 않지만 그 양이 적지 않아서 테스트 코드를 작성할 때 시간을 잡아먹는 부분이기도 한다.  

Gradle 에서 제공하는 Test Fixture 기능을 이용하면 이를 쉽게 해결할 수 있다.

코틀린의 경우 gradle 문법만 달라져서 적용하는데 큰 문제 없었다.

예제코드는 [Github](https://github.com/pci2676/Spring-Boot-Lab/tree/master/bom-test-fixture) 에서 확인할 수 있다.

## 문제 상황

테스트를 위해 간단하게 2개의 모듈을 가진 멀티모듈 구조를 만들었다.
application 모듈이 core 모듈 의존성을 사용하는 구조이다.
```
tf-root
⎿ tf-application
  ⎿ src
    ⎿ main
    ⎿ test
⎿ tf-core
  ⎿ src
    ⎿ main
    ⎿ test
```

`core` 모듈에는 `Solider` Entity가 존재한다.

```java
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private SoldierClass soldierClass;

    private SoldierStatus status;

    private LocalDateTime joinDateTime;

    private LocalDateTime modifiedDateTime;

    @Builder
    public Soldier(String name, SoldierClass soldierClass, SoldierStatus status, LocalDateTime joinDateTime, LocalDateTime modifiedDateTime) {
        this.name = name;
        this.soldierClass = soldierClass;
        this.status = status;
        this.joinDateTime = joinDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public Soldier promote(SoldierClass promotionRole) {
        if (status.isInActivate()) {
            throw new RuntimeException("inactivated member");
        }
        if (soldierClass.isHigherThan(promotionRole)) {
            throw new RuntimeException("can not promotion");
        }

        this.soldierClass = promotionRole;
        return this;
    }

}
```
위와 같이 작성한 `Soldier`의 `promote`기능을 테스트 하기 위해 아래와 같은 테스트 코드를 작성했다.

```java
public class SoldierTest {

    private Soldier createSolider(String name, SoldierClass soldierClass, SoldierStatus status) {
        LocalDateTime now = LocalDateTime.now();
        return Soldier.builder()
                .name(name)
                .soldierClass(soldierClass)
                .status(status)
                .joinDateTime(now)
                .modifiedDateTime(now)
                .build();
    }

    @DisplayName("활성화 된 병사는 낮은 등급에서 높은 등급으로 진급이 가능하다.")
    @Test
    void promotionTest1() {
        //given
        Soldier soldier = createSolider("name1", SoldierClass.PRIVATE, SoldierStatus.ENABLE);

        //when
        Soldier promotedSoldier = soldier.promote(SoldierClass.PRIVATE_FIRST_CLASS);

        //then
        assertThat(promotedSoldier.getSoldierClass()).isEqualTo(SoldierClass.PRIVATE_FIRST_CLASS);
    }

    @DisplayName("활성화 된 병사는 높은 등급에서 낮은 등급으로 진급이 불가능하다.")
    @Test
    void promotionTest2() {
        //given
        Soldier soldier = createSolider("name2", SoldierClass.PRIVATE_FIRST_CLASS, SoldierStatus.ENABLE);

        //when
        //then
        assertThatThrownBy(() -> soldier.promote(SoldierClass.PRIVATE));
    }

    @DisplayName("비활성화 된 병사는 진급이 불가능하다.")
    @Test
    void promotionTest3() {
        //given
        Soldier soldier = createSolider("name3", SoldierClass.PRIVATE_FIRST_CLASS, SoldierStatus.DISABLE);

        //when
        //then
        assertThatThrownBy(() -> soldier.promote(SoldierClass.CORPORAL));
    }
}

```

`Soldier` 객체 생성을 쉽게하기 위해 `createSolider`함수를 비공개로 작성해서 유용하게 사용하였다.

이제 `core`모듈에서 `application`모듈로 넘어가도록 한다.

`application`모듈의 `SoldierService`에 아래 코드와 같이 계급으로 `Soldier`를 찾는 기능이 있다.

```java
@Service
@Transactional
@RequiredArgsConstructor
public class SoldierService {
    private final SoldierRepository repository;

    public List<SoldierDto> findActivateSoldierByClass(SoldierClass soldierClass) {
        return repository.findAllByStatusAndSoldierClass(SoldierStatus.ENABLE, soldierClass).stream()
                .map(SoldierDto::of)
                .collect(Collectors.toList());
    }
}
```

그리고 위 코드를 테스트 하기 위해 아래와 같이 테스트 코드를 작성했다.

```java
@EnableAutoConfiguration
@SpringBootTest(classes = {ApplicationConfig.class})
class SoldierServiceTest {

    @Autowired
    private SoldierService service;

    @Autowired
    private SoldierRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    private Soldier createSolider(String name, SoldierClass soldierClass, SoldierStatus status) {
        LocalDateTime now = LocalDateTime.now();
        return Soldier.builder()
                .name(name)
                .soldierClass(soldierClass)
                .status(status)
                .joinDateTime(now)
                .modifiedDateTime(now)
                .build();
    }

    @DisplayName("DISABLE 된 병사는 검색되지 않는다.")
    @Test
    void findActivateSoldierByClassTest1() {
        //given
        Soldier soldier = createSolider("name1", SoldierClass.PRIVATE, SoldierStatus.DISABLE);
        repository.save(soldier);

        //when
        List<SoldierDto> results = service.findActivateSoldierByClass(SoldierClass.PRIVATE);

        //then
        assertThat(results).isEmpty();
    }

    @DisplayName("ENABLE 된 병사중 계급이 일치하지 않으면 검색되지 않는다.")
    @Test
    void findActivateSoldierByClassTest2() {
        //given
        Soldier soldier = createSolider("name1", SoldierClass.SERGENT, SoldierStatus.ENABLE);
        repository.save(soldier);

        //when
        List<SoldierDto> results = service.findActivateSoldierByClass(SoldierClass.PRIVATE);

        //then
        assertThat(results).isEmpty();
    }

    @DisplayName("ENABLE 된 병사중 계급이 일치하면 검색된다.")
    @Test
    void findActivateSoldierByClassTest3() {
        //given
        Soldier soldier = createSolider("name1", SoldierClass.PRIVATE, SoldierStatus.ENABLE);
        repository.save(soldier);

        //when
        List<SoldierDto> results = service.findActivateSoldierByClass(SoldierClass.PRIVATE);

        //then
        assertThat(results).hasSize(1);
    }
}
```

테스트 코드 상단에 위치한 `createSolider`코드가 `core`모듈의 테스트 코드에 있는 것과 동일한 기능을 함을 알 수 있다.  
이 코드를 `core`모듈에 있는 것으로 재활용하고 싶지만 현 상태로는 방법이 없다.

## 해결방법
이제 Gradle 이 제공하는 TestFixture 기능을 이용하면 된다.
테스트 코드를 제공할 모듈의 `plugins` 블럭에 아래와 같이 3개의 플러그인을 추가한다.

이 예제 프로젝트에서는 `core`모듈이 되겠다.
```groovy
plugins{
    // to share test fixture
    id "java-library"
    id "java-test-fixtures"
    id "maven-publish"
}
```

이렇게 플러그인을 적용하면 `core` 모듈 아래 `src` 디렉터리에서 `testFixtures`라는 새로운 Sources 디렉토리를 생성 할 수 있게된다.
```
tf-root
⎿ tf-application
  ⎿ src
    ⎿ main
    ⎿ test
⎿ tf-core
  ⎿ src
    ⎿ main
    ⎿ test
    ⎿ testFixtures // new !!!
```

그리고 이 `testFixtures`디렉터리에 문제의 공유할 코드를 작성한다.
```java
public class SoliderTestFixture {
    public static Soldier createSolider(String name, SoldierClass soldierClass, SoldierStatus status) {
        LocalDateTime now = LocalDateTime.now();
        return Soldier.builder()
                .name(name)
                .soldierClass(soldierClass)
                .status(status)
                .joinDateTime(now)
                .modifiedDateTime(now)
                .build();
    }
}
```

이렇게 했다면 `core` 모듈에서는 별다른 작업 필요없이 `test`에서 `testFixtures`의 코드를 사용 할 수 있다.  
하지만 `application`의 `test`코드에서 해당 코드를 가져다 사용하려면 `dependencies` 블럭에 `testImplementation-testFixtures`를 아래와 같이 코드를 추가해주자.
```groovy
bootJar { enabled = false }
jar { enabled = true }

dependencies {
    implementation(project(":tf-core"))
    testImplementation(testFixtures(project(":tf-core"))) // new !!

    implementation('org.springframework.boot:spring-boot-starter')

    runtime('com.h2database:h2:1.4.199')
}
```

이렇게 하면 `application` 모듈의 `test` 코드에서도 `core` 모듈의 `testFixtures` 코드를 참조하여 사용할 수 있게 된다.

## 참조
[Gradle TestFixtures 공식 문서](https://docs.gradle.org/current/userguide/java_testing.html#sec:java_test_fixtures)
