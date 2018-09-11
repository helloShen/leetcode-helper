
# Usage
build.xml是ant的配置文件，这里列举了几个最常用的用法，

### 配置问题名称
打开`build.properties`配置文件，修改`problem`项的值为想要的问题名称，
```
problem = two_sum
```

### 自动生成问题骨架代码
自动构建问题解，以及测试源代码骨架，
```
ant generate
```
或缺省`generate`，因为是默认项，
```
ant
```
如果懒得修改`build.properties`里项目名称的配置，可以直接在命令行中用`-D`设置`problem`参数，
```
ant generate -Dproblem three_sum
```
同样可以缺省`generate`，
```
ant -Dproblem three_sum
```

### 自动编译
写完题解代码以及单元测试代码，自动编译
```
ant compile
```
同样可以用`-D`编译特定问题，
```
ant compile -Dproblem three_sum
```

### 自动单元测试
自动运行JUnit单元测试，
```
ant test
```
同样可以用`-D`测试特定问题，
```
ant test -Dproblem three_sum
```

### 清除问题
清除所有问题相关代码，项目回到初始状态。
```
ant clean
```
同样可以用`-D`清除特定问题，
```
ant clean -Dproblem three_sum
```

### 自动构建，测试`two_sum`问题的例子

#### 自动构建前
项目主目录如下，
```
.
├── README.md
├── bin
│   └── com
│       └── ciaoshen
│           └── leetcode
│               └── helper
├── build-release.xml
├── build.properties
├── build.xml
├── lib
│   ├── commons-lang3-3.8.jar
│   ├── gson-2.8.5.jar
│   ├── junit-4.10.jar
│   ├── slf4j-api-1.7.25.jar
│   ├── slf4j-log4j12-1.7.25.jar
│   ├── slf4j-simple-1.7.25.jar
│   └── velocity-engine-core-2.0.jar
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── ciaoshen
    │   │           └── leetcode
    │   │               └── helper
    │   │                   └── ProblemBuilder.java
    │   └── resources
    │       ├── Solution.vm
    │       ├── Solution1.vm
    │       ├── Solution2.vm
    │       ├── Solution3.vm
    │       ├── Tester.vm
    │       └── TesterRunner.vm
    └── test
        ├── java
        │   └── com
        │       └── ciaoshen
        │           └── leetcode
        │               └── helper
        │                   ├── ProblemBuilderTest.java
        │                   └── TestRunner.java
        └── resources
            ├── reader_test.txt
            └── writer_test.txt
```

#### 自动构建
运行自动构建命令，
```
ant generate
```
或默认缺省简写，
```
ant
```
自动构建后项目主目录如下，问题解以及测试源代码骨架都自动生成。
```
.
├── README.md
├── bin
│   └── com
│       └── ciaoshen
│           └── leetcode
│               └── helper
│                   └── ProblemBuilder.class
├── build-release.xml
├── build.properties
├── build.xml
├── lib
│   ├── commons-lang3-3.8.jar
│   ├── gson-2.8.5.jar
│   ├── junit-4.10.jar
│   ├── slf4j-api-1.7.25.jar
│   ├── slf4j-log4j12-1.7.25.jar
│   ├── slf4j-simple-1.7.25.jar
│   └── velocity-engine-core-2.0.jar
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── ciaoshen
    │   │           └── leetcode
    │   │               ├── helper
    │   │               │   └── ProblemBuilder.java
    │   │               └── two_sum
    │   │                   ├── Solution.java
    │   │                   ├── Solution1.java
    │   │                   ├── Solution2.java
    │   │                   └── Solution3.java
    │   └── resources
    │       ├── Solution.vm
    │       ├── Solution1.vm
    │       ├── Solution2.vm
    │       ├── Solution3.vm
    │       ├── Tester.vm
    │       └── TesterRunner.vm
    └── test
        ├── java
        │   └── com
        │       └── ciaoshen
        │           └── leetcode
        │               ├── helper
        │               │   ├── ProblemBuilderTest.java
        │               │   └── TestRunner.java
        │               └── two_sum
        │                   ├── Tester.java
        │                   └── TesterRunner.java
        └── resources
            ├── reader_test.txt
            └── writer_test.txt
```

#### 编译问题解的源码，以及测试代码
```
ant compile
```

题解以及测试代码都正确编译，
```
.
├── README.md
├── bin
│   └── com
│       └── ciaoshen
│           └── leetcode
│               ├── helper
│               └── two_sum
│                   ├── Solution.class
│                   ├── Solution1.class
│                   ├── Solution2.class
│                   ├── Solution3.class
│                   ├── Tester.class
│                   └── TesterRunner.class
├── build-release.xml
├── build.properties
├── build.xml
├── lib
│   ├── commons-lang3-3.8.jar
│   ├── gson-2.8.5.jar
│   ├── junit-4.10.jar
│   ├── slf4j-api-1.7.25.jar
│   ├── slf4j-log4j12-1.7.25.jar
│   ├── slf4j-simple-1.7.25.jar
│   └── velocity-engine-core-2.0.jar
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── ciaoshen
    │   │           └── leetcode
    │   │               ├── helper
    │   │               │   └── ProblemBuilder.java
    │   │               └── two_sum
    │   │                   ├── Solution.java
    │   │                   ├── Solution1.java
    │   │                   ├── Solution2.java
    │   │                   └── Solution3.java
    │   └── resources
    │       ├── Solution.vm
    │       ├── Solution1.vm
    │       ├── Solution2.vm
    │       ├── Solution3.vm
    │       ├── Tester.vm
    │       └── TesterRunner.vm
    └── test
        ├── java
        │   └── com
        │       └── ciaoshen
        │           └── leetcode
        │               ├── helper
        │               │   ├── ProblemBuilderTest.java
        │               │   └── TestRunner.java
        │               └── two_sum
        │                   ├── Tester.java
        │                   └── TesterRunner.java
        └── resources
            ├── reader_test.txt
            └── writer_test.txt
```

#### 运行单元测试
```
ant test
```

如果题解正确，即能通过所有Junit单元测试。
```
➜  helper git:(master) ✗ ant test
Buildfile: /Users/Wei/github/leetcode/helper/build.xml

compile.problem:
     [echo] Compile .java for problem two_sum ...

test.problem:
     [echo] Test generated problem two_sum ...
     [java] true

BUILD SUCCESSFUL
Total time: 0 seconds
```

#### 清除问题
运行清除命令，
```
ant clean
```
清除所有和`two_sum`有关的代码，项目回到初始状态。
```
.
├── README.md
├── bin
│   └── com
│       └── ciaoshen
│           └── leetcode
│               └── helper
├── build-release.xml
├── build.properties
├── build.xml
├── lib
│   ├── commons-lang3-3.8.jar
│   ├── gson-2.8.5.jar
│   ├── junit-4.10.jar
│   ├── slf4j-api-1.7.25.jar
│   ├── slf4j-log4j12-1.7.25.jar
│   ├── slf4j-simple-1.7.25.jar
│   └── velocity-engine-core-2.0.jar
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── ciaoshen
    │   │           └── leetcode
    │   │               └── helper
    │   │                   └── ProblemBuilder.java
    │   └── resources
    │       ├── Solution.vm
    │       ├── Solution1.vm
    │       ├── Solution2.vm
    │       ├── Solution3.vm
    │       ├── Tester.vm
    │       └── TesterRunner.vm
    └── test
        ├── java
        │   └── com
        │       └── ciaoshen
        │           └── leetcode
        │               └── helper
        │                   ├── ProblemBuilderTest.java
        │                   └── TestRunner.java
        └── resources
            ├── reader_test.txt
            └── writer_test.txt
```
