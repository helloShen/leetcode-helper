
# To Do List

0. (DONE) 优化JUnit测试用例。

1. (DONE) 把部署的所有.jar包中的库抽出来，再加上我的`helper`和`util`库，打包成单个.jar包发布。
关键要在MANIFEST里把每个.jar包都加到class-path里，否则找不到库。
Class-Path: . P:/Tools/xstream/1.4.2/lib/kxml2-2.3.0.jar P:/Tools/xstr
 eam/1.4.2/lib/xstream-1.4.2.jar P:/Tools/StringTemplate/4.0.5/lib/ant
 lr-3.3-complete.jar P:/Tools/StringTemplate/4.0.5/lib/ST-4.0.5.jar P:
 /Tools/Jdbc/lib/sqljdbc4.jar

2. (DONE) 准备发布。---------------------------------------------- [v0.3]

3. (DONE) 清理一下build.xml

4. (DONE) 优化代码，尤其是：
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

5. (DONE) 重新部署
    * (DONE) 解决jar里拿不到`Path`的问题
    https://stackoverflow.com/questions/15713119/java-nio-file-path-for-a-classpath-resource
    * (DONE) 屏蔽掉项目内部debug级别的log
    * (DONE) 规范化jar打包

6. (DONE) 重新发布
    * (DONE) `.tar`
    * (DONE) `.tar.gz`
    * (DONE) `.zip`
    * (DOEN) `.rar` (ant doesn't support rar)

7. (DONE) 更新项目README.md ---------------------------------------------- [v0.4]
    * (DONE) 先写一篇加入发布压缩包的《给用户的README》

8. (DONE) github上正式发布全部4个版本：0.1, 0.2, 0.3, 和 0.4

9. 清理`com.ciaoshen.leetcode.util`包
    * 只留最基本的数据结构
        * (DONE) ListNode
        * (DONE) TreeNode
        * (DONE) NestedInteger
        * (DONE) TreeLinkedNode
        * (DONE) UndirectedGraphNode
        * Interval
        * RandomListNode

10. 更好的github项目README.md ------------------------------------- [v0.5]
    * 加入徽章：shields.io
    * travis、coverage、npm等等

10. 写一篇”怎么用ClassLoader从classpath读取资源“
https://stackoverflow.com/questions/6608795/what-is-the-difference-between-class-getresource-and-classloader-getresource
https://stackoverflow.com/questions/3861989/preferred-way-of-loading-resources-in-java

11. 写一篇关于nio的心得

12. 上传一个通用的ant`build.xml`到代码库。

13. 写一个ant怎么打包jar，tar, tar.gz, zip的笔记,
https://stackoverflow.com/questions/515428/clean-way-to-combine-multiple-jars-preferably-using-ant
https://ant.apache.org/manual/Tasks/tar.html
https://ant.apache.org/manual/Tasks/zip.html
https://www.pharmasoft.be/7z/
https://www.zhihu.com/question/26026741

14. log4j最佳实践（或者slf4j + logback）
日志库的前世今生
https://zhuanlan.zhihu.com/p/24272450
https://zhuanlan.zhihu.com/p/24275518
lsf4j和log4j的关系（桥接）
http://blog.51cto.com/woshixy/1371420

15. 记录：怎么在github上面release，打tag:
https://stackoverflow.com/questions/1028649/how-do-you-rename-a-git-tag/5719854#5719854
https://stackoverflow.com/questions/18216991/create-a-tag-in-github-repository

16. properties怎么写多个参数值
https://stackoverflow.com/questions/9737812/properties-file-with-a-list-as-the-value-for-an-individual-key

17. 总结一篇生产环境的布局（最好先参考IDE的默认项目布局）

18. 考虑用javascript在leetcode网页上加个按钮。抓取问题信息调用`leetcode-helper`创建项目骨架。