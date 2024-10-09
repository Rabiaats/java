package controller;

import business.UserController;
import core.Config;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame{
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_title;
    private JPanel pnl_bottom;
    private JTextField fld_mail;
    private JButton btn_login;
    private JLabel lbl_mail;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private UserController userController;

    public LoginUI(){
        this.userController = new UserController();
        this.add(this.container);
        this.setTitle(Config.PROJECT_TITLE);
        this.setSize(350,350);
        //sol üste açılıyor, sayfanınortasında olsun;
        this.setLocation(Helper.screenCenterPoint("x",this.getSize()),Helper.screenCenterPoint("y",this.getSize()));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        this.btn_login.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_mail,this.fld_password})){
                Helper.showMsg("fill");
            }else if (!Helper.isEmailValid(this.fld_mail.getText())){
                Helper.showMsg("Geçerli bir email adresi giriniz!");
            }else{
                User user = this.userController.findByLogin(this.fld_mail.getText(), this.fld_password.getText());
                if(user == null){
                    Helper.showMsg("Girdiğiniz bilgilere göre kullanıcı bulunamadı");
                }else {
                    this.dispose();
                    DashboardUI dashboardUI = new DashboardUI(user);
                }
            }
        });
    }
}
