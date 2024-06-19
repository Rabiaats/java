import java.util.Scanner;

public class StudentInformationSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Teacher t1 = new Teacher("Selma Yakut","5376534","MATH1");
        Teacher t2 = new Teacher("Ali Algın","4537264","PHY2");
        Teacher t3 = new Teacher("Deniz Sefer","39487645","CHEM1");
        Teacher t4 = new Teacher("Yavuz Kalkan","9486454","BIO2");

        Course c1 = new Course("Mathematic","1","MATH");
        c1.addTeacher(t1);
        Course c2 = new Course("Physical","2","PHY");
        c2.addTeacher(t2);
        Course c3 = new Course("Chemical","1","CHEM");
        c3.addTeacher(t3);
        Course c4 = new Course("Biology","2","BIO");
        c4.addTeacher(t4);

        Student s1 = new Student("SUNA", "1254", "11", c1, c2, c3, c4);
        s1.addBulkExam(35, 40, 20, 60);
        s1.addBulkOralExam(55,50, 40, 80);
        s1.isPass();
        s1.printNote();
        s1.c5 = c4;
        System.out.println(s1.c5.name);
        System.out.println(s1.c1.name);
        System.out.println("*****************");

        Student s2 = new Student("EREN", "1264", "12", c1, c2, c3, c4);
        s2.addBulkExam(80, 70, 60, 80);
        s2.addBulkOralExam(55, 75, 90, 85);
        s2.isPass();
        s2.printNote();

        System.out.println("---------------------");
        System.out.println(s1.c6);
    }
}
