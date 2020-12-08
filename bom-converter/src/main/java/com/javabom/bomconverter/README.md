# Converter 사용해보기

Converter<S, T> 인터페이스를 구현해서 사용하면된다. S는 소스, T는 타겟을 의미한다.  
WebConfigurer를 구현한 다음 addFormatters에서 addConverter에 추가해 주면 @PathVarialbes로 들어온 값을 바로 변환해서 사용할 수 있다.
