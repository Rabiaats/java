package com.patikaclone.Model;

import com.patikaclone.Helper.DBConnector;
import com.patikaclone.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContentOfContent {
    private int id;
    private int user_id;
    private int content_id;
    private String contentofcontent;
    private String type;
    private Student student;
    private Content content;

    public ContentOfContent(int id, int user_id, int content_id, String contentofcontent, String type) {
        this.id = id;
        this.user_id = user_id;
        this.content_id = content_id;
        this.contentofcontent = contentofcontent;
        this.type = type;
        this.student = (Student) User.getFetch(user_id);
        this.content = Content.getFetch(content_id);
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

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getContentofcontent() {
        return contentofcontent;
    }

    public void setContentofcontent(String contentofcontent) {
        this.contentofcontent = contentofcontent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public static ArrayList<ContentOfContent> getList(int content_id, String type){
        ArrayList<ContentOfContent> ofcontentList = new ArrayList<>();
        String query = "SELECT * FROM contentofcontent WHERE content_id = ? AND type = ?";
        ContentOfContent obj = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,content_id);
            pr.setString(2,type);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = switch (type) {
                    case "comment" ->
                            new ContentComment(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("content_id"), rs.getString("contentofcontent"));
                    case "point" ->
                            new ContentPoint(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("content_id"), rs.getString("contentofcontent"));
                    default -> obj;
                };
                ofcontentList.add(obj);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ofcontentList;
    }
    public static boolean add(int user_id, int content_id, String contentofcontent, String type){
        String query = "INSERT INTO contentofcontent (user_id,content_id,contentofcontent, type) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,content_id);
            pr.setString(3,contentofcontent);
            pr.setString(4,type);
            int response = pr.executeUpdate();
            if (response == -1){
                Helper.showMsg("error");
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
