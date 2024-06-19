package com.patikaclone.Controller;

import com.patikaclone.Helper.Config;
import com.patikaclone.Helper.Helper;
import com.patikaclone.Model.User;

import javax.swing.*;

public class SingupGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_password;
    private JButton btn_signup;

    public SingupGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        // SINGUPGUI BUTTON
        btn_signup.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_password)){
                Helper.showMsg("fill");
            }else {
                String name = fld_user_name.getText();
                String username = fld_user_uname.getText();
                String password = fld_user_password.getText();
                String usertype = "student";
                if (User.add(name, username, password, usertype)){
                    Helper.showMsg("done");
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_password.setText(null);
                }
                dispose();
                LoginGUI loginGUI = new LoginGUI();
                }
            }
        );
        // ## SINGUP BUTTON
    }
}
