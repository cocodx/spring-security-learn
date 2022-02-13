spring security所解决的问题就是安全访问控制，而安全访问控制功能其实就是对所有进入系统的请求进行拦截，校验每个请求是否能够访问它所期望的资源。  
根据前边的知识，可以通过Filter或AOP等技术来实现  
Spring security对Web资源的保护是靠Filter实现的，初始化的时候，创建一个SpringSecurityFilterChain的Servlet过滤器，它实现了javax.servlet.Filter  
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/SpringSecurityChain.jpg)  
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/chain2.jpg)

认证器和授权器  

spring security认证流程
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/spring-security%E8%AE%A4%E8%AF%81%E6%B5%81%E7%A8%8B.jpg)

Spring Security中进行身份认证的是AuthenticationManager接口，ProviderManager是它的一个默认实现，
但它并不用处理身份认证，而是委托给配置好的AuthenticationProvider，每个AuthenticationProvider会轮流
检查身份认证。检查后或者返回Authentication对象或者抛出异常。

验证身份，就是加载响应的UserDetails，看看是否和用户输入的账号、密码、权限等信息匹配
此步骤由DaoAuthenticationProvider（它获取UserDetailService来验证）

UserDetailsService  
获取用户信息（密码：加密后的密码）

PasswordEncoder  
常用：BCryptPasswordEncoder