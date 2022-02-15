授权服务:认证服务  
服务访问令牌URL：/oauth/token  
服务认证请求URL:/oauth/authorize  

资源服务：保护资源，校验令牌  

OAuth2AuthenticationProcessingFilter用来给请求给出的令牌解析鉴权  
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/%E6%8E%88%E6%9D%83%E6%9C%8D%E5%8A%A1%E4%B8%8E%E8%B5%84%E6%BA%90%E6%9C%8D%E5%8A%A1.jpg)  

![image](https://github.com/cocodx/spring-security-learn/blob/master/image/security%E6%9E%B6%E6%9E%84%E5%9B%BE.jpg)  

authenticationManager:
userDetailsService:
authorizationCodeServices:
implicitGrantService:
tokenGranter:


/oauth/authorize:
/oauth/token:
/oauth/token:

###授权码模式：第三方微信登录例子  

1、浏览器GET访问 http://localhost:53020/uaa/oauth/authorize?client_id=c1&redirect_uri=http://www.baidu.com&response_type=code&scope=all    
2、输入用户名和密码  
4、获取code，5GqdCO  
3、拿着code，POST请求调用token接口 http://localhost:53020/uaa/oauth/token?client_id=c1&client_secret=secret&grant_type=authorization_code&code=5GqdCO&redirect_uri=http://www.baidu.com  

###隐藏式：没有后端，简化模式：只要认证成功，就返回token  

1、浏览器GET访问 http://localhost:53020/uaa/oauth/authorize?response_type=token&client_id=c1&redirect_uri=http://www.baidu.com&scope=all  

###密码式：直接发username和password，拿到以后，直接向目标请求令牌  

1、 POST请求访问 http://localhost:53020/uaa/oauth/token?client_id=c1&grant_type=password&username=zhangsan&password=1q2w3e&client_secret=secret  

###凭证式client credentials，针对第三方应用，多个用户公用一个

1、POST请求访问 http://localhost:53020/uaa/oauth/token?client_id=c1&&client_secret=secret&grant_type=client_credentials  

###刷新token

当用户token过期之后，再重来一遍，很可能体验不好，也没必要，所以，可以带着就的token，调用刷新token的节点  

1、POST请求访问 http://localhost:53020/uaa/oauth/token?client_id=c1&client_secret=secret&grant_type=refresh_token&refresh_token=19d503b3-17ab-416c-b7dd-be4292305fe2    