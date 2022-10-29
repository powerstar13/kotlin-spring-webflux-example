# 스프링 WebFlux 이해하기

## 함수형 엔드포인트

1. 함수형 엔드포인트는 요청을 분석해 핸들러로 라우팅하는 라우터 함수(RouterFunction)와 요청 객체를 전달받아 응답을 제공하는 핸들러 함수(HandlerFunction)로 이뤄져 있다.
2. 라우터 함수
   1. 라우터 함수는 클라이언트로부터 전달받은 요청을 해석하고, 그에 맞는 핸들러로 전달하는 역할
   2. 라우터 함수는 @Configuration에 RouterFunction을 반환하는 빈(Bean)으로 등록할 수 있으며, 빈의 이름을 다르게하여 여러 개의 라우터 함수를 등록할 수 있다.
   3. 라우터 함수는 이러한 URI 패턴 매칭에 따른 분배 역할을 위해 RouterFunctions.route()라는 유용한 빌더를 제공하고 있음
   4. RouterFunctions.route()를 사용하면 HTTP 요청 메서드 GET, POST, PUT, DELETE, 등에 대한 매핑을 위한 편리한 함수를 제공하므로 목적에 맞게 URI 패턴을 등록하여 라우팅 룰을 생성할 수 있다.
   5. 중첩 라우터
      1. 중복된 경로를 그룹화하고 싶은 경우 중첩 라우터(Nested Router)를 사용할 수 있다.
3. 핸들러 함수
   1. 핸들러 함수는 라우터 함수로부터 전달받은 요청 객체인 ServerRequest를 이용해 로직을 처리한 후 응답 객체인 ServerResponse를 생성한 뒤 반환한다.
