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

## 애노테이션 컨트롤러

1. 웹플럭스에서 제공하는 `애노테이션 컨트롤러(Annotation Controller)` 모델은 전통적인 스프링 MVC에서 사용하던 애노테이션 기반의 요청, 응답을 그대로 사용할 수 있다.
2. 컨트롤러 함수의 반환타입이 Mono, Flux인 경우, WebFlux에서 자동으로 subscribe를 호출해준다.

## 웹클라이언트

1. RestTemplate
   1. `RestTemplate`은 스프링에서 제공하는 블로킹 방식의 HttpClient이다.
   2. Spring 5부터 Deprecated되어 스프링 MVC, 스프링 WebFlux 모두 이후 소개할 WebClient를 사용하길 권고한다.
   3. RestTemplate의 문제는 요청을 보낸 서버로부터 응답을 받을 때까지 스레드가 블로킹되어 다른 일을 하지 못한다.
      1. 만약 하나의 API에서 여러 서버의 응답을 받아 결합하는 기능이 있는 경우라면 하나씩 처리하므로 응답이 느려지는 문제가 발생할 수 있다.
   4. 이런 문제점 때문에 복수 개의 응답을 처리하는 경우라면 CompletableFuture와 가은 방식을 사용해야 한다.
2. WebClient
   1. `WebClient`는 스프링에서 제공하는 리액티브 기반의 **논블로킹** HttpClient이다.
   2. 스프링 5 이후부터 RestTemplate을 대체하여 논블로킹 방식, 블로킹 방식 모두 사용이 가능하다.
   3. **WebClient를 사용하면 스레드가 응답을 기다릴 필요 없이 처리할 수 있으므로 RestTemplate보다 부하를 줄일 수 있고, 여러 서버의 응답을 받아서 처리하는 경우 동시에 여러 서버로 호출이 가능하므로 빠르게 처리가 가능하다.**
