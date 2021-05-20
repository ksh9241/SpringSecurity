# SpringSecurity

### Authentication(인증) 과 Authorization(권한)
- 스프링 시큐리티가 애플리케이션 보안을 구성하는 두가지 영역은 인증과 권한입니다. 인증은 애플리케이션의 작업을 수행할 수 있는 주체(사용자) 라고 주장할 수 있는 것을 말하며 권한은 인증된 주체가 애플리케이션의 동작을 수행할 수 있도록 허락되있는지를 결정하는 것을 말한다. 따라서 권한 승인이 필요한 부분으로 접근하려면 인증 과정을 통해 주체가 증명 되어야만 한다는 것이다.

### Authorization(권한)의 종류
- 웹 요청권한
- 메소드 호출 및 인스턴스에 대한 접근 권한

```
스프링 시큐리티 dependency 관련 설명
https://godekdls.github.io/Spring%20Security/springsecuritydependencies/
```

### WebSecurityConfigurerAdapter 내장함수의 기능
- anonymous() : 인증되지 않은 사용자가 접근할 수 있습니다.
- authenticated() : 인증된 사용자만 접근할 수 있다.
- fullyAuthenticated() : 완전히 인증된 사용자만 접근할 수 있습니다.
- hasRole() || hasAnyRole() : 특정 권한을 가지는 사용자만 접근할 수 있습니다.
- hasAuthority() || hasAnyAuthority() : 특정 권한을 가지는 사용자만 접근할 수 있습니다.
- hasIpAddress() : 특정 아이피 주소를 가지는 사용자만 접근할 수 있습니다.
- access() : SpEL 표현식에 의한 결과에 따라 접근할 수 있습니다.
- not() : 접근 제한 기능을 해제합니다.
- permitAll() || denyAll() : 접근을 전부 허용하거나 제한합니다.
- rememberMe() : 리멤버 기능을 통해 로그인한 사용자만 접근할 수 있습니다.
