package com.patikaclone.Controller;

import com.patikaclone.Helper.Config;
import com.patikaclone.Helper.Helper;
import com.patikaclone.Model.Educator;
import com.patikaclone.Model.Operator;
import com.patikaclone.Model.Student;
import com.patikaclone.Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_password;
    private JButton btn_login;
    private JButton btn_singup;

    public LoginGUI(){
        add(this.wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        // LOGINGUI BUTTON
        // Log In
        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_password)){
                Helper.showMsg("fill");
            }else {
                User u = User.getFetch(fld_user_uname.getText(), fld_user_password.getText());
                if (u == null){
                    Helper.showMsg("Kullanıcı bulunamadı");
                }else {
                    dispose();
                    switch (u.getUser_type()){
                        case "operator":
                            OperatorGUI opeGUI = new OperatorGUI((Operator) u);
                            break;
                        case "educator":
                            EducatorGUI eduGUI = new EducatorGUI((Educator) u);
                            break;
                        case "student":
                            StudentGUI stuGUI = new StudentGUI((Student) u);
                            break;
                    }
                }
            }
        });
        // Sing Up
        btn_singup.addActionListener(e -> {
            dispose();
            SingupGUI singup = new SingupGUI();
        });
        // ## LOGINGUI BUTTON
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI login = new LoginGUI();
    }
}
