package com.patikaclone.Controller;

import com.patikaclone.Helper.Config;
import com.patikaclone.Helper.Helper;
import com.patikaclone.Model.Patika;

import javax.swing.*;

public class UpdatePatikaGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_patika_name;
    private JButton btn_patika_update;
    private Patika patika;

    public UpdatePatikaGUI(Patika patika) {
        this.patika = patika;
        add(wrapper);
        setSize(300,150);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        // UPDAREPATIKAGUI BUTTON
        fld_patika_name.setText(patika.getName());
        btn_patika_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_patika_name)){
                Helper.showMsg("fill");
            }else {
                if (Patika.update(patika.getId(), fld_patika_name.getText())){
                    Helper.showMsg("done");
                }
                dispose();
            }
        });
        // ## UPDATEPATIKAGUI BUTTON
    }
}
