# Spring Security 연습하기

## 요구사항
- [x] 홈 페이지
- [ ] 마이 페이지
- [x] 가입 페이지
- [x] 로그인 페이지
- [x] 로그아웃 버튼
- [ ] 관리자 페이지
- [ ] 게시판 구현
    - [ ] 공지 사항 게시판
    - [ ] 자유 게시판

---
- [ ] 권한에 따른 기능/페이지 노출 목록 제어
    - 관리자
        - [ ] 관리자 페이지 접근 가능
            - [ ] 가입한 유저의 목록 보기 가능
            - [ ] 유저 블랙리스트 등록
            - [ ] 유저 블랙리스트 해제
        - [ ] 공지 사항 게시판 글 작성 가능
    
    - 일반 유저
        - [ ] 회원 탈퇴 가능
        - [ ] 자유 게시판 글 작성 가능
        - [ ] 공지 사항 게시판 게시글 작성 불가
     
    - 블랙 리스트 유저
        - [ ] 회원 탈퇴 가능
        - [ ] 자유 게시판 접근 가능 
        - [ ] 자유 게시판 글 작성 불가능 
        - [ ] 공지 사항 게시판 글 작성 불가
        - [ ] 자유 게시판 글 조회 불가
        - [ ] 공지사항 게시판 접근 가능
        - [ ] 공지사항 게시판 조회 가능
        
---
## Form Login

CSRF 방어를 위해서 X-XSRF-TOKEN 값을 헤더로 받아야한다.  
이를 위해서는 `.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())` 를 설정해주면 된다. 설정하고 허가된 url로 요청을 하면 XSRF 토큰을 쿠키로 구울수 있게 헤더에 값을 넣어서 내려준다.

Form 로그인 방식을 사용하는 만큼 Form Login 방식으로 인증을 처리할 때는 Form 데이터를 전달해줘야한다. 이때 기본적으로 id 는 username 으로 password는 password 라는 이름으로 전달해줘야한다.
내가 여기서 실수한 것은 JSON으로 값을 전달하려 했던 것인데 Form 데이터 그대로 넘겨주면 된다.

이후에는 UserDetailService를 구현한 인증용 CustomService를 Spring Bean으로 제공해줘야한다.  
인터페이스의 api는 하나로 UserDetails 를 반환하면 되는데 구현체로 User가 있으니 해당 구현체에 DB에 저장된 값을 username에 로그인에 사용한 id, password에 비밀번호, `Collection<? extends GrantedAuthority> authorities` 에 사용자의 권한을 GrantedAuthority로 구현해서 넘겨주면 된다.

결과를 반환하면 `DaoAuthenticationProvider`에서 Form으로 전달받은 password를 passwordEncoder를 이용해 인코딩한 결과와 UserDetails 로 전달받은 password 를 비교해서 success, fail 처리를 한다.
세션값을 키로 SecurityContextHolder -> SecurityContext -> Authentication 에 인증된 상태 값을 저장하고 있는것 같다.

기본적으로 사용하는 세션 스토리지가 있는 것 같다. 따라서 따로 세션 스토리지를 구현하지 않으면 메모리기반의 세션 스토리지에 세션 아이디로 로그인되어있는 지에 대한 값이 저장되는 것으로 보인다. 