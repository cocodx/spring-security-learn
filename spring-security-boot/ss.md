spring security所解决的问题就是安全访问控制，而安全访问控制功能其实就是对所有进入系统的请求进行拦截，校验每个请求是否能够访问它所期望的资源。  
根据前边的知识，可以通过Filter或AOP等技术来实现  
Spring security对Web资源的保护是靠Filter实现的，初始化的时候，创建一个SpringSecurityFilterChain的Servlet过滤器，它实现了javax.servlet.Filter  
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/SpringSecurityChain.jpg)  
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/chain2.jpg)

认证器和授权器  

spring security认证流程
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/spring-security%E8%AE%A4%E8%AF%81%E6%B5%81%E7%A8%8B.jpg)

UserDetailsService  

PasswordEncoder  
常用：BCryp