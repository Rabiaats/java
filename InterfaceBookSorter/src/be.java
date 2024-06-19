public class be {
    /*
    import java.io.*; import java.util.*;
class Student {
    private int id;
    private String name;
    private double cgpa;

    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }

    public double getCGPA(){
        return cgpa;
    }

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

}

class Stu implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getCGPA() != o2.getCGPA())
        return Double.compare(o2.getCGPA(), o1.getCGPA());
    else if (!o1.getName().equals(o2.getName()))
    return o1.getName().compareTo(o2.getName());
    else return o1.getID() - o2.getID();
}
}

class Priorities{

public List<Student> getStudents(List<String> events){
    int len=events.size();
    PriorityQueue<Student> ps=new PriorityQueue<>(len, new Stu());
    for(int i=0;i<len;i++){
        String[] line=events.get(i).split("\\s+");
        if(line[0].equals("ENTER")){
            Student s=new Student(Integer.parseInt(line[3]),line[1],Double.valueOf(line[2]));
            ps.add(s);
        }else if(!ps.isEmpty()){
            ps.poll();
        }

    }
    List<Student> li=new ArrayList<>();
    while(!ps.isEmpty()){
        li.add(ps.poll());
    }
    return li;
}
}
     */
}
