# spring-security-learn
spring security整合oauth2的学习实践  
授权码模式、简易模式、用户名密码password模式、客户端模式  
要实现如下的扩展功能：  
1、支持刷新令牌Refresh Token  
2、使用关系数据库存储令牌和客户信息  
3、使用缓存Cache存储令牌提升性能  
4、授权服务器和资源服务器拆分  
5、Revoke端点  
6、Introspection端点  

### oauth2-server-demo1
介绍：spring security整合oauth2，实现授权码模式服务器的功能  

1、项目启动，浏览器访问下面的uri  
http://localhost:8888/oauth/authorize?client_id=clientApp&redirect_uri=http://localhost:8001/payment/get/1&response_type=code&scope=read_userInfo  
2、此时跳转登录校验界面，输入WebSecurityConfig的配置，获取到code  
3、到postman调用接口，获取到token,获取token:http://localhost:8888/oauth/token?grant_type=authorization_code&code=ZzBmFY&client_id=clientApp&client_secret=112233&redirect_uri=http://localhost:8001/payment/get/1  
4、然后根据token，去调用/api的controller,调用接口示例：http://localhost:8888/api/userInfo?access_token=ee963d54-a3dc-42aa-a424-81199c47aed4

### oauth2-server-demo2 
介绍：简易模式  
1、项目启动，浏览器访问下面的uri 
http://localhost:8888/oauth/authorize?client_id=clientApp&redirect_uri=http://localhost:8001/payment/get/1&response_type=token&scope=read_userInfo&state=123456  
2、输入账号密码：zhangsan 123456  
3、点击左侧box，确定，可以看到uri上面已经返回了token  
