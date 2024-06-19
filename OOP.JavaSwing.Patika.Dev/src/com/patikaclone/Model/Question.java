package com.patikaclone.Model;

import com.patikaclone.Helper.DBConnector;
import com.patikaclone.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Question {
    private String title;
    private String answer;
    private int id;
    private int content_id;
    private Content content;

    public Question() {
    }

    public Question(String title, String answer, int id, int content_id) {
        this.title = title;
        this.answer = answer;
        this.id = id;
        this.content_id = content_id;
        this.content = Content.getFetch(this.content_id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public static ArrayList<Question> getList(){
        ArrayList<Question> questionList = new ArrayList<>();
        String query = "SELECT * FROM question";
        Question obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Question(rs.getString("question"),rs.getString("answer"),rs.getInt("id"),rs.getInt("content_id"));
                questionList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questionList;
    }

    public static boolean add(String title, String answer, int content_id){
        String query = "INSERT INTO question (question,answer,content_id) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,title);
            pr.setString(2,answer);
            pr.setInt(3,content_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean delete(int id){
        String query = "DELETE FROM question WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean update(int id, String title, String answer, int content_id){
        String query = "UPDATE question SET question = ?, answer = ?, content_id = ? WHERE id = ?";
        Question findQuestion = Question.getFetch(id);
        if (findQuestion.getContent_id() == content_id && findQuestion.getTitle().equals(title) && findQuestion.getAnswer().equals(answer)){
            Helper.showMsg("Bir değişiklik yapmadınız");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            pr.setString(2,answer);
            pr.setInt(3,content_id);
            pr.setInt(4,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public static Question getFetch(int id){
        Question obj = null;
        String query = "SELECT * FROM question WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Question(rs.getString("question"),rs.getString("answer"),rs.getInt("id"),rs.getInt("content_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    public static ArrayList<Question> getList(int content_id){
        ArrayList<Question> questionList = new ArrayList<>();
        Question obj = null;
        String query = "SELECT * FROM question WHERE content_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,content_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new Question(rs.getString("question"),rs.getString("answer"),rs.getInt("id"),rs.getInt("content_id"));
                questionList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return questionList;
    }
}
