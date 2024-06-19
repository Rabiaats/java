package com.patikaclone.Model;

import com.patikaclone.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizSolved {
    private int id;
    private int content_id;
    private int user_id;
    private int point;
    public QuizSolved(int id, int content_id, int user_id, int point) {
        this.id = id;
        this.content_id = content_id;
        this.user_id = user_id;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public static boolean add(int content_id, int user_id, int point){
        String query = "INSERT INTO quizsolved (content_id,user_id,point) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, content_id);
            pr.setInt(2, user_id);
            pr.setInt(3,point);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getFetch(int content_id, int user_id){
        int point = -1;
        String query = "SELECT * FROM quizsolved WHERE content_id = ? AND user_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,content_id);
            pr.setInt(2,user_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                point = rs.getInt("point");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return point;
    }
}
