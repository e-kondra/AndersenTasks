package andersen.AndersenTasks.collections;

public class CheckCustomCollections {
    public static void main(String[] args) {
        System.out.println("------------------CustomArrayList---------------");
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>();

        for(int i = 0; i < 6; i++){
            customArrayList.put(i);
        }

        customArrayList.forEach(System.out::println);
        System.out.println(customArrayList.get(0));
        customArrayList.delete(3);
        System.out.println(customArrayList.getSize());
        customArrayList.forEach(System.out::println);

        System.out.println("------------------CustomHashSet---------------");
        CustomHashSet<Integer> customHashSet = new CustomHashSet<>();
        customHashSet.put(23);
        customHashSet.put(10);
        customHashSet.put(12);
        customHashSet.put(23);
        customHashSet.put(20);

        customHashSet.forEach(System.out::println);

        System.out.println(customHashSet.contains(43));
        System.out.println(customHashSet.contains(10));

        customHashSet.delete(10);
        System.out.println(customHashSet.contains(10));

        customHashSet.forEach(System.out::println);

    }
}
