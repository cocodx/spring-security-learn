此项目就是一个简单的http session认证的项目

1、启动项目，如下图操作
![image](https://github.com/cocodx/spring-security-learn/blob/master/image/%E5%90%AF%E5%8A%A8%E9%A1%B9%E7%9B%AE.png)

2、浏览器访问链接：http://localhost:80/session-auth-server  

3、账号（张三&1q2w3e）（李四&1q2w3e）

4、url链接介绍：  
*  http://localhost:80/session-auth-server/logout 退出登录  
*  http://localhost:80/session-auth-server/r/r1 权限，可以通过张三正常访问  
*  http://localhost:80/session-auth-server/r/r2 权限，可以通过李四正常访问  
