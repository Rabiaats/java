public class Har {
    /*
    import java.util.*;
/*
 * Create the Student and Priorities classes here.

    class Student{
        private String name;
        private int id;
        private double cgpa;
        public Student(int id, String name, double cgpa){
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }
        public String getName(){
            return this.name;
        }
        public int getID(){
            return this.id;
        }
        public double getCGPA(){
            return this.cgpa;
        }
    }
    class Priorities{

        public List<Student> getStudents(List<String> events){
            PriorityQueue<Student> stu = new PriorityQueue<>(events.size(), new StudentComparator());
            for(int i = 0; i < events.size(); i++){
                String[] line = events.get(i).split("\\s+");
                if (line[0].equals("ENTER")) {
                    Student student = new Student(Integer.parseInt(line[3]), line[1], Double.parseDouble(line[2]));
                    stu.add(student);
                } else if (!stu.isEmpty()) {
                    stu.poll();
                }
            }
            List<Student> list=new ArrayList<>();
            while(!stu.isEmpty()){
                list.add(stu.poll());
            }
            return list;
        }
    }


    class StudentComparator implements Comparator<Student>{
        @Override
        public int compare(Student s1, Student s2){
            if(s1.getCGPA() != s2.getCGPA()){
                return Double.compare(s2.getCGPA(), s1.getCGPA());
            } else if (!s1.getName().equals(s2.getName())) {
                return s1.getName().compareTo(s2.getName());
            } else { return s1.getID() - s2.getID();}
        }
    }
     */
}
