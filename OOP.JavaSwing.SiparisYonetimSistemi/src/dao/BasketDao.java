package dao;

import core.Database;
import entity.Basket;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketDao {
    private Connection connection;
    private ProductDao productDao;

    public BasketDao() {
        this.connection = Database.getInstance(); // veritabanı baglantisi
        this.productDao = new ProductDao();
    }

    public boolean save(Basket basket){
        String query = "INSERT INTO basket " +
                "(" +
                "product_id" +
                ")" +
                " VALUES (?)";

        try {
            // soru işaretlerini degistirecegim
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,basket.getProductId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    private Basket match(ResultSet rs) throws SQLException { // user tablosundakikolonlari ve degerleri aliyoruz
        Basket basket = new Basket();
        basket.setId(rs.getInt("id"));
        basket.setProductId(rs.getInt("product_id"));
        basket.setProduct(this.productDao.getById(rs.getInt("product_id")));
        return basket;
    }


}
