
# To Do List

(DONE) 0. 优化JUnit测试用例。

(DONE) 1. 把部署的所有.jar包中的库抽出来，再加上我的`helper`和`util`库，打包成单个.jar包发布。
关键要在MANIFEST里把每个.jar包都加到class-path里，否则找不到库。
Class-Path: . P:/Tools/xstream/1.4.2/lib/kxml2-2.3.0.jar P:/Tools/xstr
 eam/1.4.2/lib/xstream-1.4.2.jar P:/Tools/StringTemplate/4.0.5/lib/ant
 lr-3.3-complete.jar P:/Tools/StringTemplate/4.0.5/lib/ST-4.0.5.jar P:
 /Tools/Jdbc/lib/sqljdbc4.jar

(DONE) 2. 准备发布。

3. 优化代码，尤其是：
    * 异常处理，别抛Runtime，用printStackTrace()，尝试用日志文件。
    * 尽量用nio替代io。
    * JUnit单元测试不需要手动创建目标对象，而且构造函数里也不应该有单元测试。

4. 上传一个通用的ant`build.xml`到代码库。
