package com.patikaclone.Model;

import com.patikaclone.Helper.DBConnector;
import com.patikaclone.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {
    private String title;
    private String description;
    private String link;
    private int id;
    private int course_id;
    private Course course;

    public Content() {
    }
    public Content(String title, String description, String link, int id, int course_id) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.id = id;
        this.course_id = course_id;
        this.course = Course.getFetch(this.course_id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static ArrayList<Content> getList(){
        ArrayList<Content> contentList = new ArrayList<>();
        String query = "SELECT * FROM content";
        Content obj;
        try {
            /*
                connect = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASSWORD);
                st = connect.createStatement()
             */

            // DBConnector.getInstance() == connect yani Connection gondurur
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Content(rs.getString("title"),rs.getString("description"),rs.getString("link"),rs.getInt("id"),rs.getInt("course_id"));
                contentList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contentList;
    }

    public static Content getFetch(int id){
        Content obj = null;
        String query = "SELECT * FROM content WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Content(rs.getString("title"),rs.getString("description"),rs.getString("link"),rs.getInt("id"),rs.getInt("course_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static boolean isContent(int course_id){
        String query = "SELECT * FROM content WHERE course_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,course_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
            return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean add(String title, String description, String link, int course_id){
        String query = "INSERT INTO content (course_id,title,description,link) VALUES (?,?,?,?)";
        for (Course c : Course.getList()){
            if (c.getId() == course_id){
                try {
                    PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
                    pr.setInt(1,course_id);
                    pr.setString(2,title);
                    pr.setString(3,description);
                    pr.setString(4,link);
                    return pr.executeUpdate() != -1;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Helper.showMsg("Bir ders seçmediniz");
        return false;
    }

    public static boolean delete(int id){
        String query = "DELETE FROM content WHERE id = ?";
        for (Question q : Question.getList()){
            if (q.getContent().getId() == id){
                Question.delete(q.getId());
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
    public static boolean update(int id, int course_id, String title, String description, String link){
        String query = "UPDATE content SET course_id = ?, title = ?, description = ?, link = ? WHERE id = ?";
        Content findContent = Content.getFetch(id);
        if (findContent.getCourse().getId() == course_id && findContent.getTitle().equals(title) && findContent.getDescription().equals(description) && findContent.getLink().equals(link)){
            Helper.showMsg("Bir değişiklik yapmadınız");
            return false;
        }
        for (Course c : Course.getList()) {
            if (c.getId() == course_id) {
                try {
                    PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
                    pr.setInt(1, course_id);
                    pr.setString(2, title);
                    pr.setString(3, description);
                    pr.setString(4, link);
                    pr.setInt(5, id);
                    return pr.executeUpdate() != -1;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        Helper.showMsg("Bir ders seçmediniz!");
        return false;
    }
    public static String searchQuery(String title, String course){
        String query = "SELECT * FROM content WHERE title LIKE '{{title}}%'";
        query = query.replace("{{title}}",title);
        if (!course.isEmpty()){
            for (Course c : Course.getList()){
                if (c.getName().equals(course)){
                    query += " AND course_id = " + c.getId();
                }
            }
        }
        return query;
    }
    public static ArrayList<Content> searchContentList(String query){
        ArrayList<Content> contentList = new ArrayList<>();
        Content obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Content(rs.getString("title"),rs.getString("description"),rs.getString("link"),rs.getInt("id"),rs.getInt("course_id"));
                contentList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contentList;
    }
}
