package business;

import core.Helper;
import core.Item;
import dao.ProductDao;
import entity.Customer;
import entity.Product;

import java.util.ArrayList;

public class ProductController {
    private final ProductDao productDao = new ProductDao();

    public ArrayList<Product> findAll(){
        return this.productDao.findAll();
    }
    public boolean save(Product product){
        return  this.productDao.save(product);
    }

    public Product getById(int id){
        return this.productDao.getById(id);
    }
    public boolean update(Product product){
        if(this.getById(product.getId()) == null){
            Helper.showMsg((product.getId() + " ID kayıtlı ürün bulunamadı!"));
            return false;
        }
        return this.productDao.update(product);
    }

    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id + " ID kayıtlı müşteri bulunamadı");
            return false;
        } else {
            return this.productDao.delete(id);
        }
    }
    public ArrayList<Product> filter(String name, String code, Item isStock){
        String query = "SELECT * FROM product ";

        ArrayList<String> whereList = new ArrayList<>();
        System.out.println(code);

        if (!name.isEmpty()){
            whereList.add("name LIKE '%" + name + "%'");
        }

        if (!code.isEmpty()){
            whereList.add("code LIKE '%" + code + "%'");
        }

        if (isStock != null){
            if (isStock.getKey() == 1){
                whereList.add("stock > 0");
            }else {
                whereList.add("stock <= 0");
            }
        }

        if (!whereList.isEmpty()){
            String whereQuery = String.join(" AND ", whereList);
            query += " WHERE " + whereQuery;
        }

        System.out.println(query);

        return this.productDao.query(query);
    }

}
