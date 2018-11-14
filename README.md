# Only Focus on Solutions!
![velocity](https://img.shields.io/badge/velocity-2.0-blue.svg)
![ant](https://img.shields.io/badge/ant-1.10.5-brightgreen.svg)
![junit](https://img.shields.io/badge/junit-4.10-brightgreen.svg)
![log4j](https://img.shields.io/badge/log4j-1.2.17-brightgreen.svg)
![slf4j](https://img.shields.io/badge/slf4j-1.7.25-brightgreen.svg)

**leetcode-helper** is a single-jar library that liberate you from setting up solution-test scaffold for each problem. Generate solution/test skeletons, compile, test by commands on one line. `Junit 4`, `log4j`, `slf4j` are integrated. `com.ciaoshen.leetcode.util` package provide simplest implementations for 7 commonly used data structures such as `TreeNode`, `ListNode`, etc.

### Configure a problem
![leetcode-helper-config](src/main/resources/dev/gif/leetcode-helper-config.gif)

### Command on one line to generate Solution/Test skeleton
![leetcode-helper-generate](src/main/resources/dev/gif/leetcode-helper-generate.gif)

The generated skeletons:
![leetcode-helper-skeleton](src/main/resources/dev/gif/leetcode-helper-skeleton.gif)

### Commend on one line to compile and run JUnit tests
![leetcode-helper-compile-test](src/main/resources/dev/gif/leetcode-helper-compile-test.gif)

### Commend on one line to remove sources
![leetcode-helper-clean](src/main/resources/dev/gif/leetcode-helper-clean.gif)



# The newest version is v0.60
![travis](https://travis-ci.com/helloShen/leetcode-helper.svg?branch=master)

* [leetcode-helper-v0.60.tar](https://github.com/helloShen/leetcode-helper/releases/download/v0.6/leetcode-helper-v0.6.tar)

* [leetcode-helper-v0.60.tar.gz](https://github.com/helloShen/leetcode-helper/releases/download/v0.6/leetcode-helper-v0.6.tar.gz)
* [leetcode-helper-v0.60.zip](https://github.com/helloShen/leetcode-helper/releases/download/v0.6/leetcode-helper-v0.6.zip)


### Updates
1. add shell script to generate, compile, test leetcode solution just as what ant does.
2. some other finishing touch.
3. leetcode-helper project completed.

# Installation
Unarchieve the `.tar`or `.tar.gz` or `.zip` file. Copy everything in `leetcode-helper-vX.X` folder to your work directory. If your work directory was clean, it will look like,

```
root
├── README.md
├── build.xml
├── demo/
│   └── two_sum/
│       └── ...
├── lib/
│   └── leetcode-helper.jar
└── problem.properties
```

### Need Ant
Make sure you have installed `Ant`. Use `-version` option to check,
```
ant -version
```

If not, get the last version here --> [【Installing Apache Ant】](https://ant.apache.org/manual/install.html)

# Usage

### Windows User please use `PowerShell` to launch the script
Do NOT use `cmd.exe`. It doesn't work with the paths using forward-slash("/").

### Configure problem information
To solve for example the problem "two sum", open `problem.properties` file, configure the "problem.name" field as below,
```
problem.name = two_sum
```

Give your solutions a parent package name,
```
package.name = com.leetcode
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
├── demo/
│   └── two_sum/
│       └── ...
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
├── demo/
│   └── two_sum/
│       └── ...
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

# `com.ciaoshen.leetcode.util` library
`com.ciaoshen.leetcode.util` library provide only fundamental features,
* member fields
* constructor
* toString() serialization

### TreeLinkNode
```
/**
 * A binary tree with next pointer
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * EX:
 *           1 -> NULL
 *         /  \
 *        2 -> 3 -> NULL
 *       / \  / \
 *      4->5->6->7 -> NULL
 *
 * DEFINITION:
 * ======================================
 *
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 *
 * ======================================
 *
 */
```

Related Problems:
* #116 - Populating Next Right Pointers in Each Node

### UndirectedGraphNode

```
/**
 * Each node in the graph contains a label (int)
 * and a list (List[UndirectedGraphNode]) of its neighbors.
 * EX:
 *          1
 *         / \
 *        /   \
 *       0 --- 2
 *            / \
 *            \_/
 *
 * DEFINITION:
 * ===========================================================
 *
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<UndirectedGraphNode>();
 *     }
 * };
 *
 * ===========================================================
 */
```

Related Problems:
* #133 - Clone Graph

### TreeNode
```
/**
 * TreeNode refers to the node of a binary tree.
 * Composed of it's value and left child and right child.
 *              1
 *             /  \
 *            2    3
 *           / \  / \
 *          4  5  6  7
 *     
 * DEFINITION:
 * =================================
 *
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 * =================================
 */
 ```

 ### ListNode
```
/**
 * ListNode is Singly-Linked List
 * ex: 1->2->3->4->5
 *     
 * DEFINITION:
 * =================================
 *
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 * =================================
 */
 ```

 Related Problems:
 * #148 - Sort List


 ### NestedInteger

 ```
 /**
 * NestedInteger can hold a single integer, or a nested list, which looks like:
 *    [[1,1],2,[1,1]]
 *    [1,[4,[6]]]
 *
 * INTERFACE that allows for creating nested lists.
 * ===================================================================================================
 *
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 *
 * ===================================================================================================
 *
 * Note:
 * Constructor is not exposed to user to create new instances.
 */
 ```

 Related Problems:
 * #341 - Flatten Nested List Iterator
 * #385 - Mini Parser

 ### Interval

```
/**
 * Always used to represent a period of time
 *
 * DEFINITION
 * ===================================================
 *
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 *
 * ===================================================
 */
```

Related Problems:
* #56 - Merge Intervals

### RandomListNode

```
/**
 * The random linked list is a singly linked list given
 * such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 *
 * DEFINITION
 * ===================================================
 *
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 *
 * ===================================================
 */
```

Related Problem:
* #138 - Copy List with Random Pointer

### Node

```
/**
 * Binary Search Tree looks like:
 *           4
 *          / \
 *         2   5
 *        / \
 *       1   3
 *
 * DEFINITION
 * ===================================================
 *
 *  class Node {
 *      public int val;
 *      public Node left, right;
 *
 *      public Node() {}
 *      public Node(int v, Node l, Node r) {
 *          val = v; left = l; right = r;
 *      }
 *  }
 *
 * ===================================================
 */
```
Related Problems:
* #426 - Convert Binary Search Tree to Sorted Doubly Linked List

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

### v0.5
1. Simplify `com.ciaoshen.leetcode.util` package

### v0.51
1. Compatible with Windows

### v0.55
1. Prompt for user input before generating skeleton if previous works for this problem exist.
2. Add a simple demo for new user to getting started.
3. Put some effort in continuous integrating and continuous delivery.

### v0.6
1. add shell script to generate, compile, test leetcode solution just as what ant does.
2. some other finishing touch.
3. leetcode-helper project completed.