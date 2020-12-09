# Converter 사용해보기

Converter<S, T> 인터페이스를 구현해서 사용하면된다. S는 소스, T는 타겟을 의미한다.  
WebConfigurer를 구현한 다음 `addFormatters`에서 `addConverter`에 추가해 주면 `@PathVariable`로 들어온 값을 바로 변환해서 사용할 수 있다.

그리고 RequestParam으로 들어온 경우에도 이름만 일치시키면 변환이 되는걸 확인할 수 있었다! Dto로 받는 경우에도 멤버변수의 이름만 일치시면 된다!

만약 동일 소스 -> 타겟 으로 변환하는 컨버터가 존재한다면 나중에 등록한 컨버터가 작동하게 된다. 
내가 작성한 코드에서 StringToLocalDateTimeConverter가 먼저 등록되었고 StringToLocalDateTimeConverter2가 후에 등록되었는데  
StringToLocalDateTimeConverter2 가 작동하는 것을 볼 수 있다.