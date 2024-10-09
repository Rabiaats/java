package business;

import core.Helper;
import dao.UserDao;
import entity.User;

public class UserController {
    private final UserDao userDao = new UserDao(); // userDao ya bu sınıf erisebilir

    public User findByLogin(String mail, String password){ // veritabanından once burada da kontrol yapilmali
        // bazen ui ı hackleyip gecebiliyorlar
        if(!Helper.isEmailValid(mail)) return null;
        return this.userDao.findByLogin(mail,password);
    }
}
