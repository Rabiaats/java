
public class Main {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        System.out.println("Number Of Array Elements : " + list.size());
        System.out.println("Array Capacity : " + list.getCapacity());
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println("Number Of Array Elements : " + list.size());
        System.out.println("Array Capacity : " + list.getCapacity());
        list.add(50);
        list.add(60);
        list.add(70);
        list.add(80);
        list.add(90);
        list.add(100);
        list.add(110);
        System.out.println("Number Of Array Elements : " + list.size());
        System.out.println("Array Capacity : " + list.getCapacity());

        System.out.println("-".repeat(40));

        MyList<Integer> list2 = new MyList<>();
        list2.add(10);
        list2.add(20);
        list2.add(30);
        System.out.println("The element in the second index is : " + list2.get(2));
        list2.remove(0);
        list2.add(40);
        list2.set(0, 100);
        System.out.println("The element in the second index is : " + list2.get(2));
        System.out.println(list2.toString());

        System.out.println("-".repeat(40));

        MyList<Integer> list3 = new MyList<>();
        System.out.println("List Status : " + (list3.isEmpty() ? "Null" : "Full"));
        list3.add(10);
        list3.add(20);
        list3.add(30);
        list3.add(40);
        list3.add(20);
        list3.add(50);
        list3.add(60);
        list3.add(70);

        System.out.println("List Status : " + (list3.isEmpty() ? "Null" : "Full"));

        // Bulduğu ilk indeksi verir
        System.out.println("Index : " + list3.indexOf(20));

        // Bulamazsa -1 döndürür
        System.out.println("Index :" + list3.indexOf(100));

        // Bulduğu son indeksi verir
        System.out.println("Index : " + list3.lastIndexOf(20));

        // Listeyi Object[] dizisi olarak geri verir.
        Object[] arr = list3.toArray();
        System.out.println("The first element of the object array is :" + arr[0]);

        // Liste veri türünde alt bir liste oluşturdu
        MyList<Integer> subMyList = list3.subList(0, 3);
        System.out.println(subMyList.toString());

        // Değerim listedeki olup olmadığını sorguladı
        System.out.println("20 values on my list : " + list3.contains(20));
        System.out.println("120 values on my list : " + list3.contains(120));

        // Listeyi tamamen boşaltır ve varsayılan boyutuna çevirir
        list3.clear();
        System.out.println(list3.toString());

    }
}