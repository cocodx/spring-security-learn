# spring-security-learn
spring security整合oauth2的学习实践  

OAuth2.0 认证协议  

认证：用户认证就是判断一个合法身份的过程

会话：系统校验用户身份是否合法，不能每次都让认证，为了避免这种情况，创建会话，基于session方式，基于token方式等。  
在服务端生成用户相关的数据保存在session中，发给客户端的session_id存放在cookie中，这样用户客户端请求带上session_id就可以验证
服务器端是否存在session数据，完成用户的合法校验，当用户退出系统或session过期失效，客户端的session_id也失效了。

基于token：用户认证成功之后，web服务端生成token，给客户端返回过去，客户端保存在自己支持的存储介质中（cookie，localstorage等），解析校验令牌
并获取当前用户数据。不用存储session在服务器端

授权：是用户认证通过根据用户的权限来控制用户访问资源的过程，用户资源的访问权限则可以正常访问，没有权限则拒绝访问

权限的数据模型：
1、主体Subject，用户，程序，访问系统中的资源（用户，账号，密码）  
2、资源Resource，系统商品信息，系统订单信息（资源id，资源名称，资源地址）  
3、权限许可Permission、规定用户对资源的操作许可（权限id，权限标识，权限名称）  

RBAC实现授权： 基于角色的访问控制、基于资源的访问控制

 
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

### oauth2-server-demo3  
介绍：密码模式  

### xxxxxx
介绍：基于session认证的方式  

session认证流程图  
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/%E5%9F%BA%E4%BA%8Esession%E8%AE%A4%E8%AF%81%E7%9A%84%E6%B5%81%E7%A8%8B%E5%9B%BE.png)
基于session认证的方式由Servlet规范定制，Servlet容器已实现，用户通过HttpSession的操作方法即可实现

1、项目启动，post请求    
http://localhost:8888/oauth/token?grant_type=password&username=zhangsan&password=123456&scope=read_userInfo  
并且Basic Auth认证 输入clientId和secret  
  
