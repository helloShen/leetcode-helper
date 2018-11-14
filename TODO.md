
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

9. (DONE) 清理`com.ciaoshen.leetcode.util`包
    * (DONE) 只留最基本的数据结构
        * (DONE) ListNode
        * (DONE) TreeNode
        * (DONE) NestedInteger
        * (DONE) TreeLinkedNode
        * (DONE) UndirectedGraphNode
        * (DONE) Interval
        * (DONE) RandomListNode

10. (DONE) 更好的github项目README.md ------------------------------------- [v0.5]
    * (DONE) 加入徽章：shields.io
    * (DONE) travis、coverage、npm等等

11. (DONE) 兼容Windows，解决斜杠和反斜杠的问题 -------------------------------[v0.51]
https://stackoverflow.com/questions/8075373/file-separator-vs-filesystem-getseparator-vs-system-getpropertyfile-separato
jar包中的路径分隔符默认为"/"，就算到了Windows系统也是。不需要换。我只需要管jar文件外面的系统路径。
https://stackoverflow.com/questions/24749007/how-to-use-file-separator-for-a-jar-file-resource
zip压缩包必须用正斜杠，不能用反斜杠，
https://stackoverflow.com/questions/13846000/file-separators-of-path-name-of-zipentry
Windows `cmd.exe`不兼容UNIX风格的斜杠分隔符"/"，必须用`PowerShell`运行，
https://stackoverflow.com/questions/10523708/why-does-the-cmd-exe-shell-on-windows-fail-with-paths-using-a-forward-slash

12. (DONE) `v0.55` 完善自动化流程 ----------------------------------------[v0.55]
    * (DONE) 利用ant的<junit>task，去掉`TesterRunner`类。发布模板的TesterRunner类不用去掉，我不喜欢log4j自己到默认位置找`log4j.properties`配置文件的方式，很容易出问题。PropertyScanner类可靠地多。
    * (DONE) 清理一下`Tester`类。给每个Solution类加上Logger。
    * (DONE) 项目库和产品库一般是可以混在一起的，但我可以设子目录稍微区分一下。`resources/dev/`和`resources/release/`子目录互为镜像。
    * (DONE) 我的库包和第三方供应商的库包分开。`lib/`放我的`leetcode-helper.jar`，`vendor/lib`放第三方库包。
    * (DONE) 原本执行`ProblemBuilder`的测试也纳入junit单元测试 (没必要，junit要运行main，必须转向`System.out`，`System.in`和`System.err`)
    * (DONE) 加入`demo`样板，用户可以快速上手。
    * (DONE) 生成新问题骨架之前，先检查工作区有没有同一个问题的老代码。避免覆盖掉用户之前的工作。
    * (DONE) 发布包的测试方法可以利用开发时候的单元测试。做法不是测试jar包，而是把jar解压出来测试。具体参考《项目自动化》P92的布局。全部解压到`install/`目录底下。测试完`install/`目录全部清空。
    * (DONE) 改完之后，项目布局就是以后可以参考的经典布局。`build.xml`就是以后`build.xml`的模板。
     

13. (DONE) `v0.60` 再加几个可执行shell脚本。`bin/`底下是可执行shell脚本，`lib/`底下是`.jar`库。具体参考`ant`和`maven`发布包的构架。 -------------------------------------[v0.60]
    * (DONE) 把`build.xml`的工作分摊到`generate`,`compile`,`run`3个程序里。因为jar内部的文件解压出来会自动将换行符改成空格，就不太好解析。目前的解决办法是把layout.properties再留一份在jar外面，方便读取。但是把配置文件留在打包好的jar外面的方案不太完美。把应该向用户隐藏的配置细节暴露给用户，一来增加了客户使用的难度，第二也不安全。而且用户目录下的layout.properties的内容和jar中的layout.properties内容完全一致，也不方便维护。因此暂不将这些shell脚本包含到发布的`v.60`版本里。只是作为一次尝试留在项目工程目录里。
    https://stackoverflow.com/questions/10929453/read-a-file-line-by-line-assigning-the-value-to-a-variable
    https://www.shellscript.sh/case.html
    https://stackoverflow.com/questions/4774054/reliable-way-for-a-bash-script-to-get-the-full-path-to-itself
    https://stackoverflow.com/questions/59895/getting-the-source-directory-of-a-bash-script-from-within/246128#246128
    https://stackoverflow.com/questions/369758/how-to-trim-whitespace-from-a-bash-variable
    https://stackoverflow.com/questions/7066063/how-to-read-manifest-mf-file-from-jar-using-bash
    https://stackoverflow.com/questions/7066063/how-to-read-manifest-mf-file-from-jar-using-bash
    * (DONE) 看一下`ANT_HOME`是怎么回事。考虑一下像`ant`这样发布，我的包放在`usr/local`目录底下。`leetboot`到处可以运行。（不需要这么麻烦，我要彻底扁平化，解压以后, 全部在根目录底下）
    * (DONE) Windows底下可能是`.bat`批处理文件
    * (DONE) 测试包可以分开发布，单独`zip`包。用户软件出问题可以让他运行测试包，把错误发过来。（我不需要发布测试包）
    * (DONE) 程序安装软件有Windows系统的NSIS，或者Mac的PackageMaker。（我不安装！这个项目就是以jar库包形式发布，安装对用户是一种负担。）
    * (DONE) WAR部署模块了解下（我不是网络项目，不需要打包成WAR）

14. (DONE) `com.ciaoshen.leetcode.util`加一个`Node`。#426. Convert Binary Search Tree to Sorted Doubly Linked List
https://stackoverflow.com/questions/1880198/how-to-execute-shell-command-in-javascript

15. (DONE) - 放弃。浏览器调用本地shell脚本，需要用户安装nodejs + babel，对用户太不友好。) 考虑用javascript在leetcode网页上加个按钮。抓取问题信息调用`leetcode-helper`创建项目骨架。 

16. (DONE) 推广项目：leetcode, 知乎，一亩三分地

101. (DONE) 写一篇”怎么用ClassLoader从classpath读取资源“
https://stackoverflow.com/questions/6608795/what-is-the-difference-between-class-getresource-and-classloader-getresource
https://stackoverflow.com/questions/3861989/preferred-way-of-loading-resources-in-java

102. (DONE) 写一篇关于nio的心得

103. (DONE) 上传一个通用的ant`build.xml`到代码库。

104. (DONE) 写一个ant怎么打包jar，tar, tar.gz, zip的笔记,
https://stackoverflow.com/questions/515428/clean-way-to-combine-multiple-jars-preferably-using-ant
https://ant.apache.org/manual/Tasks/tar.html
https://ant.apache.org/manual/Tasks/zip.html
https://www.pharmasoft.be/7z/
https://www.zhihu.com/question/26026741

105. (DONE) log4j最佳实践（或者slf4j + logback）
日志库的前世今生
https://zhuanlan.zhihu.com/p/24272450
https://zhuanlan.zhihu.com/p/24275518
lsf4j和log4j的关系（桥接）
http://blog.51cto.com/woshixy/1371420

106. (DONE) 记录：怎么在github上面release，打tag, 以及merge pull request
https://stackoverflow.com/questions/1028649/how-do-you-rename-a-git-tag/5719854#5719854
https://stackoverflow.com/questions/18216991/create-a-tag-in-github-repository
github上怎么删掉前一个commit
https://stackoverflow.com/questions/448919/how-can-i-remove-a-commit-on-github
github上删除一个tag
* git push origin :tagname
github上上传本地tag
* git push origin --tags
三种常用的merge策略，1.普通merge 2.压缩路径squash 3.剪枝嫁接rebase
https://stackoverflow.com/questions/2427238/in-git-what-is-the-difference-between-merge-squash-and-rebase

107. (DONE) properties怎么写多个参数值
https://stackoverflow.com/questions/9737812/properties-file-with-a-list-as-the-value-for-an-individual-key

108. (DONE) 总结一篇生产环境的布局（最好先参考IDE的默认项目布局）
一种Java开发技术栈
https://cloud.tencent.com/developer/article/1173354

109. (DONE) 怎么美化github项目
* shields.io: https://shields.io/#/
* travis ci: https://travis-ci.com 
* gif录屏: https://giphy.com/apps/giphycapture

不错的文章： https://www.jianshu.com/p/ae6749858f28

110. (DONE) Windows用户编译中文字符：”错误: 编码GBK的不可映射字符”的问题
```
javac -encoding utf-8 MyClass.java
```
https://blog.csdn.net/Leytton/article/details/52740171

111. (DONE) leetcode-helper project completed!