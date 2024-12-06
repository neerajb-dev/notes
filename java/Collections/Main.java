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
        List<String> list3 = new ArrayList<>(list2);
        list3.add("mango");
        System.out.println(list3);
        // List<Integer> list4 = List.of(1, 2, 3, 4);
        // list4.set(1, 33); throws exceptions not allowed
    }
}
