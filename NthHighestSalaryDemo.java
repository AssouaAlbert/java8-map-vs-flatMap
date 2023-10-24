package com.javatechie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class NthHighestSalaryDemo {

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

        System.out.println(getDynamicNthHighestSalary(3, map1));

    }

    public static Map.Entry<String, Integer> getNthHighestSalary(int num, Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList())
                .get(num);
    }

    /**
     * 
     * @param n   : Entry at position to retrieve from sorted map.
     * @param map : Map to sort in reverse oder.
     * @return : Return the sorted map.
     */

    public static Map.Entry<String, Integer> getNthHighestSalaryCustomComparator(int n,
            Map<String, Integer> map) {
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
                int Value1 = entry1.getValue();
                int Value2 = entry1.getValue();
                return Integer.compare(Value2, Value1);
            }
        };
        return map.entrySet().stream()
                .sorted(Collections.sort(customComparator))
                .collect(Collectors.toList())
                .get(n - 1);
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
