# Converter 사용해보기

Converter<S, T> 인터페이스를 구현해서 사용하면된다. S는 소스, T는 타겟을 의미한다.  
WebConfigurer를 구현한 다음 `addFormatters`에서 `addConverter`에 추가해 주면 `@PathVariable`로 들어온 값을 바로 변환해서 사용할 수 있다.

그런데 `@PathVariable` 로 한가지만 들어오는게 아니라면 굳이 Converter를 사용할 필요는 못 느낄것 같다.
그냥 DTO로 받는게 더 편할것 같다는 생각이 든다.