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