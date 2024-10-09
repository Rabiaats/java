import business.UserController;
import controller.DashboardUI;
import controller.LoginUI;
import core.Helper;
import entity.User;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        //LoginUI loginUI =new LoginUI();
        UserController userController = new UserController();
        User user = userController.findByLogin("mustafa@patika.dev", "123123");
        DashboardUI dashboardUI = new DashboardUI(user);

        /*
        Connection connection = Database.getInstance();
        Connection connection1 = Database.getInstance();
        System.out.println(connection);
        System.out.println(connection1);

        ikisi de aynı location ı dondurur
        ben aynı database i tum rpoje boyunca kullanacagimm farklı bir database yok burada bu yuzden signleton ile tek
        bir kere olusturuluyor ve hep o kullaniliyor
        */
    }
}