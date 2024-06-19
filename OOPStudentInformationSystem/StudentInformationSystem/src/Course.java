import java.lang.reflect.Field;

class Course {
    Teacher teach; // oluşturdugumuz teacher nesnesini burada kullanacagiz
    String name;
    String code;
    String prefix;
    int examNote;//%80
    int oralExamNote;//%20
    final double ORALEXAM_PERCENTAGE = 0.20;
    final double EXAM_PERCENTAGE = 0.80;

    Course(String name, String code, String prefix){
        this.name = name;
        this.code = code;
        this.prefix = prefix;
        this.examNote = 0;
        this.oralExamNote = 0;
    }
    void addTeacher(Teacher teach){
        if (teach.branch.equals(prefix + code)){
            this.teach = teach;
        }else {
            System.out.println("Teacher's Branch is not the same as Course's Prefix or Code!!!");
        }
    }
    double courseAverage(){
        return (this.examNote * EXAM_PERCENTAGE) + (this.oralExamNote * ORALEXAM_PERCENTAGE);
    }
    void printTeacher(){
        this.teach.print();
    }
    void oralExamEffect(){}
}
