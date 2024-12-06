### What is a Collection?

A collection is simply an object that represents a group of objects, known as its elements.

### What is a Collection Framework?

It provides a set of interfaces and classes that help in managing groups of objects.

Before the introduction of the Collection Framework in JDK 1.2, Java used to rely on a variety of classes like Vector, Stack, HashTable, and arrays to manipulate groups of objects.

Problems with the above mentioned classes -

- **_Inconsistency:_** Each class had a different way of managing collections, leading to confusion and a steep learning curve.
- **_Lack of inter-operability:_** These classes were not designed to work together seamlessly.
- **_No common interface:_** There was no common interface for all these classes, which meant you couldn't write generic algorithms that could operate on different types of collections.
- **_No common interface:_** There was no common interface for all these classes, which meant you couldn't write generic algorithms that could operate on different types of collections.

To solve these problems, the Collection Framework was introduced.

- **_Unified architecture:_** A consistent set of interfaces for all collections.
- **_Inter-operability:_** Collections can be easily interchanged and manipulated in a uniform way.
- **_Reusability:_** Generic algorithms can be written that work with any collection.
- **_Efficiency:_** The framework provides efficient algorithms for basic operations like searching, sorting, and manipulation.

Key Interfaces in the Collection Framework

The Collection Framework is primarily built around a set of interfaces. Important ones are:

- **_Collection:_** The root interface for all the other collection types.
- **_List:_** An ordered collection that can contain duplicate elements (e.g., ArrayList, LinkedList).
- **_Set:_** A collection that cannot contain duplicate elements (e.g., HashSet, TreeSet).
- **_Queue:_** A collection designed for holding elements prior to processing (e.g., PriorityQueue, LinkedList when used as a queue).
- **_Deque:_** A double-ended queue that allows insertion and removal from both ends (e.g., ArrayDeque).
- **_Map:_** An interface that represents a collection of key-value pairs (e.g., HashMap, TreeMap).

## **Collection Hierarchy**

The Collection Framework is organized into a hierarchy where the core interfaces are at the top, and the specific implementations extend these interfaces.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/846a8e07-a8e6-477e-af20-b000ef81ae94/768371ba-521b-42db-8e87-dd3853ca9280/image.png)

### The Collection Interface

The Collection interface is the root interface of the Java Collection Framework. It is the most basic interface that defines a group of objects known as elements. The Collection interface is a part of the java.util package, and It is a parent interface that is extended by other collection interfaces like List, Set, and Queue.

Since Collection is an interface, it cannot be instantiated directly; rather, it provides a blueprint for the basic operations that are common to all collections.

The Collection interface defines a set of core methods that are implemented by all classes that implement the interface. These methods allow for basic operations such as adding, removing, and checking the existence of elements in a collection.

### # List Interface

The List interface in Java is a part of the java.util package and is a sub-interface of the Collection interface. It provides a way to store an ordered collection of elements (known as a sequence). Lists allow for precise control over where elements are inserted and can contain duplicate elements.

The List interface is implemented by several classes in the Java Collection Framework, such as ArrayList, LinkedList, Vector, and Stack.

**Key Features of the List Interface**

- Order Preservation
- Index-Based Access
- Allows Duplicates

  ### ## ArrayList

  An ArrayList is a resizable array implementation of the List interface. Unlike arrays in Java, which have a fixed size, an ArrayList can change its size dynamically as elements are added or removed. This flexibility makes it a popular choice when the number of elements in a list isn't known in advance.

  ```java
  import java.util.ArrayList;

  public class Main {
  	public static void main(String[] args) {
  		ArrayList<Integer> list = new ArrayList<>();
  		list.add(1);
  		list.add(5);
  		list.add(80);

  		list.set(2, 50);

  		System.out.println(list);
  	}
  }
  ```

  **Internal Working**

  - Unlike a regular array, which has a fixed size, and ArrayList can grow and shrink as elements are added or removed. This dynamic resizing is achieved by creating a new array when the current array is full and copying the elements to the new array.
  - Internally, the ArrayList is implemented as an array of Object references. When you add elements to an ArrayList, you're essentially storing these elements in this internal array.
  - When you create an ArrayList, it has an initial capacity (default is 10). The capacity refers to the size of the internal array that can hold elements before needing to resize.

  ### How Adding Elements Work Internally

  When we add an element to an ArrayList, the following steps occur

  **_Check Capacity:_** Before adding the new element, ArrayList checks if there is enough space in the internal array (elementData). If the array is full, it needs to be resized.

  **_Resize if Necessary:_** If the internal array is full, the ArrayList will create a new array with a larger capacity `(usually 1.5 times the current capacity)` and copy the elements from the old array to the new array.

  **_Add the Element:_** The new element is then added to the internal array at the appropriate index, and the size is incremented.

  ```java
  import java.util.ArrayList;

  public class Main {
  	public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(11); // 11 is the initial internal capacity i.e. upto 11 elements the array will not resize
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1); // till here the capacity will not change i.e. capacity = 11
        list.add(1); // array will resize and the internal capacity will change to 11 * 1.5 = 16
  	}
  }
  ```

  **Is There any way to print the internal capacity?**

  There isn't any built in method to do so but there is the following workaround for that using reflection

  ```Java
  import java.lang.reflect.Field;
  import java.util.ArrayList;

  public class Main {
  	public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>(11); // 11 is the initial internal capacity i.e. upto 11 elements the array will not resize
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1); // till here the capacity will not change i.e. capacity = 11
        list.add(1); // array will resize and the internal capacity will change to 11 * 1.5 = 16

        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] elementData = (Object[]) field.get(list);
        System.out.println("ArrayList capacity: " + elementData.length); // Output: ArrayList capacity: 11

        list.add(1);

        elementData = (Object[]) field.get(list);
        System.out.println("ArrayList capacity: " + elementData.length); // Output: ArrayList capacity: 16

        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);

        elementData = (Object[]) field.get(list);
        System.out.println("ArrayList capacity: " + elementData.length); // Output: ArrayList capacity: 16

        list.trimToSize(); // The size of the arraylist wont shrink autometically when we remove elements so we do it manually by using list.trimToSize() method

        elementData = (Object[]) field.get(list);
        System.out.println("ArrayList capacity: " + elementData.length); // Output: ArrayList capacity: 4
  	}
  }
  ```
#### **Ways to create ArrayList**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>(); // Method 1
        System.out.println(list.getClass().getName()); // Output: java.util.ArrayList

        // Can't add or remove elements from this list only modification is allowed (creates a fixed size list)
        List<String> list1 = Arrays.asList("Monday", "Tuesday");
        System.out.println(list1.getClass().getName()); // Output: java.util.Arrays$ArrayList
        list1.set(1, "Wednesday");
        System.out.println(list1.get(1));

        String[] array = { "Apple", "Banana", "Cherry" };
        List<String> list2 = Arrays.asList(array);
        System.out.println(list2.getClass().getName()); // Output: java.util.Arrays$ArrayList

        // we can add elements to the above collection by creating an arraylist from it
        List<String> list3 = new ArrayList<>();
        list3.add("mango");
        System.out.println(list3);

        List<Integer> list4 = List.of(1, 2, 3, 4);
        // list4.set(1, 33); throws exceptions not allowed
    }
}
```
