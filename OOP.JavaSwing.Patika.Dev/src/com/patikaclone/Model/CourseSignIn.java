package com.patikaclone.Model;

import com.patikaclone.Helper.DBConnector;
import com.patikaclone.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseSignIn {
    private int id;
    private int course_id;
    private int user_id;
    private Course course;
    private Student student;

    public CourseSignIn(int course_id, int user_id) {
        this.course_id = course_id;
        this.user_id = user_id;
        this.course = Course.getFetch(course_id);
        this.student = (Student) User.getFetch(user_id);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public static boolean singInCourse(int course_id, int student_id){
        if (!CourseSignIn.getFetch(course_id,student_id)){
            try {
                String query = "INSERT INTO coursesingin (course_id,user_id) VALUES (?,?)";
                PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
                pr.setInt(1,course_id);
                pr.setInt(2, student_id);
                return pr.executeUpdate() != -1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            Helper.showMsg("Bu derse kayıtlısınız!");
            return false;
        }
    }
    public static boolean getFetch(int course_id, int student_id){
        Course obj = null;
        String query = "SELECT * FROM coursesingin WHERE course_id = ? AND user_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,course_id);
            pr.setInt(2,student_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static ArrayList<Course> getList(int user_id){
        ArrayList<CourseSignIn> courseSingInList = new ArrayList<>();
        ArrayList<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM coursesingin WHERE user_id = " + user_id;
        CourseSignIn obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new CourseSignIn(rs.getInt("course_id"),rs.getInt("user_id"));
                courseSingInList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (CourseSignIn c : courseSingInList){
            courseList.add(c.getCourse());
        }
        return courseList;
    }
    public static boolean delete(int course_id){
        String query = "DELETE FROM coursesingin WHERE course_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,course_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
