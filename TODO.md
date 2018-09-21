
# To Do List

0. (DONE) 优化JUnit测试用例。

1. (DONE) 把部署的所有.jar包中的库抽出来，再加上我的`helper`和`util`库，打包成单个.jar包发布。
关键要在MANIFEST里把每个.jar包都加到class-path里，否则找不到库。
Class-Path: . P:/Tools/xstream/1.4.2/lib/kxml2-2.3.0.jar P:/Tools/xstr
 eam/1.4.2/lib/xstream-1.4.2.jar P:/Tools/StringTemplate/4.0.5/lib/ant
 lr-3.3-complete.jar P:/Tools/StringTemplate/4.0.5/lib/ST-4.0.5.jar P:
 /Tools/Jdbc/lib/sqljdbc4.jar

2. (DONE) 准备发布。

3. (DONE) 清理一下build.xml

4. 优化代码，尤其是：
    * (DONE) 解决log4j设置成INFO级别，但log.info()不能打印的问题。
    * (DONE) 参数调整，不应该让用户看到的参数放到`build.xml`里。用户可以设置的才放在`conf.properties`里。
    * (DONE) 文件路径全是相对路径，因为`java.io`默认以`user.dir`为根目录。可能`ClassLoader.getResourceAsStream()`读取到`InputStream`，然后再解析成一个`Properties`类是比较靠谱的做法。
    * (DONE) 把`.properties`文件专门放到`resources/config`文件夹下
    * (DONE) 把`.vm`文件专门放到`resources/template`文件夹下
    * (DONE) 异常处理，别抛Runtime，用printStackTrace()，尝试用日志文件。
    * (DONE) 用nio替代io。
    * (DONE) JUnit单元测试不需要手动创建目标对象，而且构造函数里也不应该有单元测试。
    * (DONE) 把搜索模板文件的工作独立到一个新的`TemplateSeeker`类里
    * (DONE) 把读取`.properties`配置信息的工作独立到一个新的`PropertyScanner`类里
    * (DONE) 清理JUnit测试代码，尽可能地简化
    * (DONE) 调整log4j和slf4j的一些细节
    * (DONE) 把Log等级将至Debug，禁掉Velocity的Log权限
    * (DONE) 改写Template里的log4j和slf4j的内容
    * (DONE) 把自己项目里的`System.out`全部改成`LOGGER.debug()`

5. 重新部署

6. 重新发布

7. 写一篇”怎么用ClassLoader从classpath读取资源“

8. 写一篇关于nio的心得

9. 上传一个通用的ant`build.xml`到代码库。

10. 写一个ant怎么打包jar的笔记

11. 更新项目README.md

12. log4j最佳实践（或者slf4j + logback）
日志库的前世今生
https://zhuanlan.zhihu.com/p/24272450
https://zhuanlan.zhihu.com/p/24275518
lsf4j和log4j的关系（桥接）
http://blog.51cto.com/woshixy/1371420