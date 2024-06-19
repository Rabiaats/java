package com.patikaclone.Model;

import com.patikaclone.Helper.DBConnector;
import com.patikaclone.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String user_name;
    private String password;
    private String user_type;

    public User(){};

    public User(int id, String name, String user_name, String password, String user_type) {
        this.id = id;
        this.name = name;
        this.user_name = user_name;
        this.password = password;
        this.user_type = user_type;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUser_type(rs.getString("user_type"));
                userList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    //ekleme işlemi gerçekleşti mi? boolean
    public static boolean add(String name, String username, String password, String usertype){
        String query = "INSERT INTO user (name,user_name,password,user_type) VALUES (?,?,?,?)";
        User findUser = User.getFetch(username); // bu username önceden kullanılmış mı
        if (findUser != null){
            Helper.showMsg("Bu kullanıcı adı daha önceden eklenmiş. Lütfen farklı bir kullanıcı adı giriniz.");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,username);
            pr.setString(3,password);
            pr.setString(4,usertype);

            /*
            hem if de hem de return da 2 defa executeUpdate ettiğimiz için eklediğimiz
            kullanıcıyı iki defa ekliyor
            bunda dolayı response oluşturarak bir defa çalıştırmış oluyoruz
             */
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

    //veritabanından tek bir veri çekme işlemi fetch user_name aynı olmasın
    public static User getFetch(String username){
        User obj = null;
        String query = "SELECT * FROM user WHERE user_name = ?"; // user_name sütununda ? yani aşağıda verceğimiz username varsa döndür

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,username);
            ResultSet rs = pr.executeQuery();
            /*
            while (rs.next()){
                obj =new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUser_type(rs.getString("user_type"));
                break;
            }

             */
            if (rs.next()){ //var mı
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUser_type(rs.getString("user_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static User getFetch(String username, String password){
        User obj = null;
        String query = "SELECT * FROM user WHERE user_name = ? AND password = ?"; // user_name sütununda ? yani aşağıda verceğimiz username varsa döndür

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){ //var mı
                switch (rs.getString("user_type")){
                    case "operator":
                        obj = new Operator();
                        break;
                    case "educator":
                        obj = new Educator();
                        break;
                    case "student":
                        obj = new Student();
                        break;
                    default:
                        obj = new User();
                }
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUser_type(rs.getString("user_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static User getFetch(int id){
        User obj = null;
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                switch (rs.getString("user_type")){
                    case "operator":
                        obj = new Operator();
                        break;
                    case "educator":
                        obj = new Educator();
                        break;
                    case "student":
                        obj = new Student();
                        break;
                    default:
                        obj = new User();
                }
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUser_type(rs.getString("user_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean delete(int id){
        String query = "DELETE FROM user WHERE id = ?";
        for (Course c: Course.getList(id)){
            Course.delete(c.getId());
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
    public static boolean update(int id, String name, String username, String password, String usertype){
        String query = "UPDATE user SET name = ?,user_name = ?,password = ?,user_type = ? WHERE id = ?";
        User findUser = User.getFetch(username);

        /*
        Buraya bir ve koşulu ekledik çünkü diğer şekilde aynı user_name i almaya izin vermiyor tamam sanki her zaman değiştiriyormuşuz gibi
        buradaki sorun user_name dışındakileri değiştirdiğimizde aynı user_name kaldığından sanki onu da değiştiriyormuşuz gibi algılayıp değişime izin vermiyor
        ve koşulu ile -id değişmediğinden- değiştirmek istediğimiz kullanıcı aynı kişi mi değilse değişmesine izin verme user_name in
        ama user_name aynı ve id de aynı o zaman aynı kullanıcının farklı bir parametresini değiştiriyoruz demektir
         */

        if (findUser != null && findUser.getId() != id){
            Helper.showMsg("Bu kullanıcı adı daha önceden eklenmiş. Lütfen farklı bir kullanıcı adı giriniz.");
            return false;
        }
        /*
        getFetch i yaptık yukarıdaki hata mesajını verdi, veritabanında degişmedi ama bizim tablomuzda değişti
        (programı kapatıp açınca veritabanında değişmediği için düzeliyor ama anlık olarak kalıyordu)
        onu düzelmek için OperatorGUI.java da loadUserModel i yazım yerini değiştirdik
        */
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,username);
            pr.setString(3,password);
            pr.setString(4,usertype);
            pr.setInt(5,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public static ArrayList<User> searchUserList(String query){
        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setPassword(rs.getString("password"));
                obj.setUser_type(rs.getString("user_type"));
                userList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static String searchQuery(String name, String username, String usertype){
        String query = "SELECT * FROM user WHERE name LIKE '{{name}}%' AND user_name LIKE '{{username}}%'";
        query = query.replace("{{name}}",name);
        query = query.replace("{{username}}",username);

        /*
        Başlangıçta usertype ı da query i oluştururken yazdık name ve username string sorun çıkarmadı
        ancak usertype ENUM olduğu için sorguyu yanlış kabul etti
        bundan dolayı usertype boş değilse diye bakıyoruz
         */
        if (!usertype.isEmpty()){
            query += " AND user_type LIKE '%{{usertype}}%'";
            query = query.replace("{{usertype}}",usertype);
        }

        return query;
    }

    //veritabanında bulunan user_type enumlarını combobox umuza aktarmak için bu method
    public static ArrayList<String> getEnumValues() throws SQLException {
        ArrayList<String> values = new ArrayList<>();
        /*
        buradaki query miz
        SHOW COLUMNS FROM user ifadesi, user tablosundaki tüm sütunların bilgilerini getirmek için kullanılır.
        WHERE Field = 'user_type' ifadesi ise, sadece user_type sütunun bilgilerini filtrelemek için kullanılır
         */
        String query = "SHOW COLUMNS FROM user WHERE Field = 'user_type'";
        try (Statement st = DBConnector.getInstance().createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                String enumValues = rs.getString("Type");
                enumValues = enumValues.substring(enumValues.indexOf("(") + 1, enumValues.lastIndexOf(")"));
                String[] enumArray = enumValues.split(",");
                for (String value : enumArray) {
                    values.add(value.trim().replaceAll("'", ""));
                }
            }
        }
        return values;
    }
}
