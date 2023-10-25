import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class NthHighestSalaryDemo
{

    public static void main(String[] args) {

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("anil", 1000);
        map1.put("bhavna", 1300);
        map1.put("micael", 1500);
        map1.put("tom", 1600);// output
        map1.put("ankit", 1200);
        map1.put("daniel", 1700);
        map1.put("james", 1400);

        Map.Entry<String, Integer> results = getNthHighestSalaryCustomComparator(3, map1);
        System.out.println(results);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("anil", 1000);
        map2.put("ankit", 1200);
        map2.put("bhavna", 1200);
        map2.put("james", 1200);
        map2.put("micael", 1000);
        map2.put("tom", 1300);
        map2.put("daniel", 1300);

        // System.out.println(getNthHighestSalary(2, map2));

        System.out.println(getDynamicNthHighestSalaryCustom(3, map1));

    }

    public static Map.Entry<String, Integer> getNthHighestSalary(int num, Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList())
                .get(num);
    }

    /**
     * @param n   : Entry at position to retrieve from sorted map.
     * @param map : Map to sort in reverse oder.
     * @return : Return the sorted map.
     */
    public static Map.Entry<String, Integer> getNthHighestSalaryCustomComparator(int n, Map<String, Integer> map) {
        Comparator<Entry<String, Integer>> customComparator = new Comparator<Entry<String, Integer>>() {
            /**
             * 
             * @param entry1 : First entry to compare.
             * @param entry2 : Second entry to compare.
             * @return : Return 0 if they are equal, 1 if value1 is greater than value2, or
             *         -1 id value1 is less than value2
             */
            @Override
            public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
                // Compare in descending order of values
                int value1 = entry1.getValue();
                int value2 = entry2.getValue();
                return Integer.compare(value2, value1);
            }
        };

        List<Map.Entry<String, Integer>> sortedEntries = map.entrySet().stream()
                .sorted(customComparator)
                .collect(Collectors.toList());

        if (n <= sortedEntries.size()) {
            return sortedEntries.get(n - 1);
        } else {
            // Handle the case when n is out of bounds
            throw new IllegalArgumentException("The specified rank is out of bounds.");
        }
    }

    public static Map.Entry<Integer, List<String>> getDynamicNthHighestSalaryCustom(int num, Map<String, Integer> map) {
        Comparator<Entry<Integer, List<String>>> customComparator = new Comparator<Entry<Integer, List<String>>>() {
            /**
             * 
             * @param entry1 : First entry to compare.
             * @param entry2 : Second entry to compare.
             * @return : Return 0 if they are equal, 1 if value1 is greater than value2, or
             *         -1 id value1 is less than value2
             */
            @Override
            public int compare(Entry<Integer, List<String>> entry1, Entry<Integer, List<String>> entry2) {
                // Compare in descending order of values
                String value1 = entry1.getValue().get(0);
                String value2 = entry2.getValue().get(0);
                return value2.compareTo(value1);
            }
        };
    List<Entry<Integer, List<String>>> sortedList = map.entrySet().stream()
            .collect(Collectors.groupingBy(entry -> entry.getValue(), 
                     Collectors.mapping(entry -> entry.getKey(), Collectors.toList())))
            .entrySet().stream()
            .sorted(customComparator)
            .collect(Collectors.toList());

    if (num <= sortedList.size()) {
        return sortedList.get(num - 1);
    } else {
        throw new IllegalArgumentException("The specified rank is out of bounds.");
    }
    }


    public static Map.Entry<Integer, List<String>> getDynamicNthHighestSalary(int num, Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList())
                .get(num - 1);
    }

}
