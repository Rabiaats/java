package dao;

import core.Database;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection = Database.getInstance(); // veritabanı baglantisi
    }

    public User findByLogin(String mail, String password){ // login olmaya calisan user database de var mı
        User user = null;
        String query = "SELECT * from user WHERE mail = ? AND password = ?"; // soru isaretlerini degistirecegiz
        try {
            PreparedStatement pr = this.connection.prepareStatement(query); // query yi hazirlayacagiz
            pr.setString(1,mail); // 1. soru isareti
            pr.setString(2,password); // 2. soru isareti
            // string olarakta birlestirebilirdik ama bu guvenlik acisindan daha dogru
            ResultSet rs = pr.executeQuery(); // bir sonuc listesi dondur
            if (rs.next()){ // fetch islemi
                user = this.match(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public ArrayList<User> findAll(){ // butun user lari dondur
        ArrayList<User> users = new ArrayList<>();
        try {
            //PreparedStatement e gerek yok zaten tek bir sorgumuz var * başka bir parametre yok, query baska bir parametre almayacak
            ResultSet rs = this.connection.createStatement().executeQuery("SELECT * FROM user");
            while (rs.next()){ // rs null olmadigi surece
                users.add(this.match(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private User match(ResultSet rs) throws SQLException { // user tablosundakikolonlari ve degerleri aliyoruz
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setMail(rs.getString("mail"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
