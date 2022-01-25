# spring-security-learn
spring security整合oauth2的学习实践

### oauth2-server-demo1
介绍：spring security整合oauth2，实现授权码模式服务器的功能  

1、项目启动，浏览器访问下面的uri  
http://localhost:8888/oauth/authorize?client_id=clientApp&redirect_uri=http://localhost:8001/payment/get/1&response_type=code&scope=read_userInfo  
2、此时跳转登录校验界面，输入WebSecurityConfig的配置，获取到code
3、到postman调用接口，获取到token
4、然后根据token，去调用/api的controller
