import java.util.Scanner;

class Student {
    Course c1, c2, c3, c4, c5, c6;
    String name;
    String stuNo;
    String classes;
    double average;
    boolean isPass = false;
    Student(String name, String stuNo, String classes, Course c1, Course c2, Course c3, Course c4){
        this.name = name;
        this.stuNo = stuNo;
        this.classes = classes;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }
    void addBulkExam(int c1Note, int c2Note, int c3NOte, int c4NOte) {
        if (c1Note >= 0 && c1Note <= 100) this.c1.examNote = c1Note;
        if (c2Note >= 0 && c2Note <= 100) this.c2.examNote = c2Note;
        if (c3NOte >= 0 && c3NOte <= 100) this.c3.examNote = c3NOte;
        if (c4NOte >= 0 && c4NOte <= 100) this.c4.examNote = c4NOte;
    }
    void addBulkOralExam(int c1OralExam, int c2OralExam, int c3OralExam, int c4OralExam) {
        if (c1OralExam >= 0 && c1OralExam <= 100) {
            this.c1.oralExamNote = c1OralExam;
        }

        if (c2OralExam >= 0 && c2OralExam <= 100) {
            this.c2.oralExamNote = c2OralExam;
        }


        if (c3OralExam >= 0 && c3OralExam <= 100) {
            this.c3.oralExamNote = c3OralExam;
        }

        if (c4OralExam >= 0 && c4OralExam <= 100) {
            c4.oralExamNote = c4OralExam;
        }
    }
    double calcAvarage(){
        this.average = (c1.courseAverage() + c2.courseAverage() + c3.courseAverage() + c4.courseAverage()) / 4;
        if (this.average > 50) this.isPass = true;
        return this.average;
    }
    void printNote(){
        System.out.println();
        System.out.println(this.c1.name + "'s exam note: " + this.c1.examNote);
        System.out.println(this.c1.name + "'s oral exam note: " + this.c1.oralExamNote);
        System.out.println("The effect of " + this.c1.name + "oral exam grade on average: "
                           + this.c1.courseAverage());

        System.out.println(this.c2.name + "'s exam note: " + this.c2.examNote);
        System.out.println(this.c2.name + "'s oral exam note: " + this.c2.oralExamNote);
        System.out.println("The effect of " + this.c2.name + "oral exam grade on average: "
                           + this.c2.courseAverage());

        System.out.println(this.c3.name + "'s exam note: " + this.c3.examNote);
        System.out.println(this.c3.name + "'s oral exam note: " + this.c3.oralExamNote);
        System.out.println("The effect of " + this.c3.name + "oral exam grade on average: "
                           + this.c3.courseAverage());


        System.out.println(this.c4.name + "'s exam note: " + this.c4.examNote);
        System.out.println(this.c4.name + "'s oral exam note: " + this.c4.oralExamNote);
        System.out.println("The effect of " + this.c4.name + "oral exam grade on average: "
                           + this.c4.courseAverage());
        System.out.println("==============================================================");
    }
    void isPass() {
        System.out.println();
        if (this.isPass) System.out.println(this.name + " passed the class");
        else System.out.println(this.name + " failed the class");
        System.out.println(this.name + "'s Average: " + calcAvarage());
    }
}
