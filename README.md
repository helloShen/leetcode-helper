# Installation
Unachieve the `.tar`or `.tar.gz` or `.zip` file. Copy everything in `leetcode-helper-vX.X` folder to your work directory. If your work directory was clean, it will look like,

```
root
├── README.md
├── build.xml
├── lib
│   └── leetcode-helper.jar
└── problem.properties
```

# Usage
Make sure you have installed `Ant`. Use `-version` option to check,
```
ant -version
```

If not, get the last version here --> [【Installing Apache Ant】](https://ant.apache.org/manual/install.html)

Suppose you want to solve the problem "two sum", open `problem.properties` file, configure the "problem.name" field  and `package.name` field as below,
```
problem.name = two_sum

package.name = com.ciaoshen.leetcode
```

You can also assign a method prototype to `problem.members` argument (optional). If you are lazy, just keep this field blank.
```
problem.members = private int add(int a, int b) {}
```

### Generate solution skeleton
After typing command line in console,
```
ant
```

or,
```
ant generate
```

you'll get 5 skeletons, 3 under `./src/main/java` directory, 2 in `./src/test/java` folder.
```
root
├── README.md
├── build.xml
├── lib
│   └── leetcode-helper.jar
├── problem.properties
└── src
    ├── main
    │   └── java
    │       └── your
    │           └── package
    │               └── name
    │                   └── two_sum
    │                       ├── Solution.java
    │                       ├── Solution1.java
    │                       └── Solution2.java
    └── test
        └── java
            └── your
                └── package
                    └── name
                        └── two_sum
                            ├── Tester.java
                            └── TesterRunner.java
```

`Solution.java` is an interface, while `Solution1.java` and `Solution2.java` are two implementations. `Tester.java` class works oriented to `Solution` interface, thus you can easily add `Solution3.java` or `Solution4.java`. Make sure that they implement `Solution` interface.

`Tester.java` and `TesterRunner.java` are JUnit module. Add your unit test in `Tester.java` and launch `TesterRunner` to execute JUnit test (you can launch TesterRunner in one line command).

`Tester` tests `Solution1` as default. To test `Solution2`, just assign an instance of Solution2 to the `solution` field in Tester class (do it in the Constructor of Tester class) as follow,

```
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
        // solution = new Solution1();
        solution = new Solution2();
    }
```

### Compile & Test solutions
To compile all above sources by one command line,
```
ant compile
```

and launch TesterRunner with another command,
```
ant test
```

or, you can do both the same time,
```
ant compile test
```

The compiled `.class` files will locate in `./bin` directory as usual,
```
root
├── README.md
├── bin
│    └── main
│        └── java
│            └── your
│                └── package
│                    └── name
│                        └── two_sum
│                            ├── Solution.class
│                            ├── Solution1.class
│                            ├── Solution2.class
│                            ├── Tester.class
│                            └── TesterRunner.class
├── build.xml
├── lib
│   └── leetcode-helper.jar
├── problem.properties
└── src
    ├── main
    │   └── java
    │       └── your
    │           └── package
    │               └── name
    │                   └── two_sum
    │                       ├── Solution.java
    │                       ├── Solution1.java
    │                       └── Solution2.java
    └── test
        └── java
            └── your
                └── package
                    └── name
                        └── two_sum
                            ├── Tester.java
                            └── TesterRunner.java
```

### Clean a specific problem
The following command can delete all source files, as well as `.class` byte code for a specific problem,
```
ant clean
```

Make sure that you really want to do so before using this command.


### Use `-D` option to pass argument
If you don't want to edit the `problem.proberties` for every problems, you can edit the `package.name` field at the first time, and pass the `problem.name` argument from the command line,
```
ant generate -Dproblem.name three_sum
```

```
ant compile -Dproblem.name three_sum
```

```
ant test -Dproblem.name three_sum
```

```
ant clean -Dproblem.name three_sum
```


# Version History

### v0.2
1. Use Apache Velocity to separate skeleton templates and skeleton generator.
2. Add `com.ciaoshen.leetcode.util` library to support the commonly used data structures in leetcode.

### v0.3
1. Combine multiple jar libraries into one
2. Support log4j
3. Reinforce ant + JUnit scaffold to enable test-driven development and continuous delivery.

### v0.4
1. Include templates and properties files into jar
2. Load all resources from classpath
3. Migrate from `java.io` to `java.nio`
4. Support slf4j facade for log4j
5. Reinforce JUnit test template
6. Update `build.xml` scaffold

