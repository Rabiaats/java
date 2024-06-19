package com.patikaclone.Model;

import com.patikaclone.Helper.DBConnector;
import com.patikaclone.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class Patika {
    private int id;
    private String name;

    public Patika(){};
    public Patika(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static ArrayList<Patika> getList(){
        ArrayList<Patika> patikaList = new ArrayList<>();
        String query = "SELECT * FROM patika";
        Patika obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new Patika();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                patikaList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patikaList;
    }

    public static boolean add(String name){
        String query = "INSERT INTO patika (name) VALUES (?)";
        for (Patika p : Patika.getList()){
            if (p.getName().equalsIgnoreCase(name)){
                Helper.showMsg("Bu isimde bir patika önceden eklenmiştir!");
                return false;
            }
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean update(int id, String name){
        String query = "UPDATE patika SET name = ? WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setInt(2,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static Patika getFetch(int id){
        Patika obj = null;
        String query = "SELECT * FROM patika WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Patika(rs.getInt("id"),rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    public static boolean delete(int id){
        String query = "DELETE FROM patika WHERE id = ?";
        for (Course c : Course.getList()){
            if (c.getPatika_id() == id){
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
                Course.delete(c.getId());
            }
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //patika tablosundaki kolon isimlerini almak
    public static Object[] colName() throws SQLException {
        // Kolon bilgilerini almak için sorgu hazırlama
        ResultSet resultSet = DBConnector.getInstance().getMetaData().getColumns(null, null, "patika", null);

        // Sütun sayısını al
        int columnCount = 0;
        while (resultSet.next()) {
            columnCount++;
        }

        // Kolon başlıklarını depolamak için bir dizi oluşturma
        Object[] columnNames = new String[columnCount];

        // ResultSet'i tekrar başa al
        resultSet.beforeFirst();

        int index = 0;
        // Sütun başlıklarını diziye ekleme
        while (resultSet.next()) {
            columnNames[index] = resultSet.getString("COLUMN_NAME").toUpperCase();
            index++;
        }

        // Bağlantıyı kapatma
        DBConnector.getInstance().close();
        return columnNames;
    }
}
