package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    /*
        // singleton design pattern
        // çok fazla veritabanına bağlanacağız her bağlandığımızda nesne üretmeyelim ihtiyaç duyduğumuzda
            bir kere oluşturup ve bunu hafızamda depoda tutup gerektiğinde kullanalım
        // config ayarlar ya classta final olarak ya da core/config cllassında statik olarak tutulurlar

         private final String DB_URL = "jdbc:mysql://localhost/patika";
         private final String DB_USERNAME = "root";
         private final String DB_PASSWORD = "mysql";
    */
    private static Database instance = null;
    private Connection connection = null;

    private Database(){ // private bir constructer -> d,rek erisilsin istemiyorum, nesne uretmek icin static bir method kullanacagim
        try {
            this.connection = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        return connection; // sadece degere erisilebilsin
    }
    public static Connection getInstance(){
        // daha once olusturulmmus olabilir diye try catch icine aldik
        try {
            if (instance == null || instance.getConnection().isClosed()){
                instance = new Database();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instance.getConnection();
    }
}
