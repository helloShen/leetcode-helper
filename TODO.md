
# To Do List

(DONE) 0. 优化JUnit测试用例。

1. 把部署的所有.jar包中的库抽出来，再加上我的`helper`和`util`库，打包成单个.jar包发布。

2. 优化代码，尤其是：
    * 异常处理，别抛Runtime，用printStackTrace()，尝试用日志文件。
    * 尽量用nio替代io。
    * JUnit单元测试不需要手动创建目标对象，而且构造函数里也不应该有单元测试。

3. 准备发布。

4. 上传一个通用的ant`build.xml`到代码库。
