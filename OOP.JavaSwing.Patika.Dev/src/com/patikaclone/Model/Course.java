package com.patikaclone.Model;

import com.patikaclone.Helper.DBConnector;
import com.patikaclone.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int user_id;
    private int patika_id;
    private String name;
    private String lang;

    private Patika patika;
    private User educator;

    public Course(){};

    public Course(int id, String name, String lang, int patika_id, int user_id) {
        this.id = id;
        this.name = name;
        this.lang = lang;
        this.patika_id = patika_id;
        this.user_id = user_id;
        this.patika = Patika.getFetch(this.patika_id);
        this.educator = User.getFetch(this.user_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }

    public static ArrayList<Course> getList(){
        ArrayList<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM course";
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Course(rs.getInt("id"),rs.getString("name"),rs.getString("lang"),rs.getInt("patika_id"),rs.getInt("user_id"));
                courseList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }
    public static ArrayList<Course> getList(int user_id){
        ArrayList<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM course WHERE user_id = " + user_id;
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Course(rs.getInt("id"),rs.getString("name"),rs.getString("lang"),rs.getInt("patika_id"),rs.getInt("user_id"));
                courseList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }
    public static boolean add(String name, String lang, int patika_id, int user_id){
        String query = "INSERT INTO course (user_id,patika_id,name,lang) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,patika_id);
            pr.setString(3,name);
            pr.setString(4,lang);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean delete(int id){
        String query = "DELETE FROM course WHERE id = ?";
        CourseSignIn.delete(id);
        for (Content i : Content.getList()){
            if (i.getCourse().getId()== id){
                for (Question q : Question.getList()){
                    if (q.getContent().getId() == i.getId()){
                        Question.delete(q.getId());
                    }
                }
                Content.delete(i.getId());
            }
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean update(int id, String name, String lang, int patika_id, int user_id){
        String query = "UPDATE course SET user_id = ?,patika_id = ?,name = ?,lang = ? WHERE id = ?";
        Course findCourse = Course.getFetch(id);
        if (findCourse.getPatika_id() == patika_id && findCourse.getUser_id() == user_id && findCourse.getName().equals(name) && findCourse.getLang().equals(lang)){
            Helper.showMsg("Bir değişiklik yapmadınız");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,patika_id);
            pr.setString(3, name);
            pr.setString(4,lang);
            pr.setInt(5,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public static Course getFetch(int id){
        Course obj = null;
        String query = "SELECT * FROM course WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Course(rs.getInt("id"),rs.getString("name"),rs.getString("lang"),rs.getInt("patika_id"),rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    public static ArrayList<Course> isCourse(int patika_id){
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        String query = "SELECT * FROM course WHERE patika_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,patika_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Course(rs.getInt("id"),rs.getString("name"),rs.getString("lang"),rs.getInt("patika_id"),rs.getInt("user_id"));
                courseList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courseList;
    }

}
