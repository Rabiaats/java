package com.patikaclone.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setLayout(){ // yeni arayüz görünümü Nimbus
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenterPoint(String axis, Dimension size){
        int point;
        switch (axis){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str){
        optionPaNeTR();
        String msg;
        String title = switch (str) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz!";
                yield "Hata!";
            }
            case "done" -> {
                msg = "İşlem başarılı!";
                yield "Sonuç";
            }
            case "error" -> {
                msg = "Bir hata oluştu!";
                yield "Hata!";
            }
            default -> {
                msg = str;
                yield "Mesaj";
            }
        };

        JOptionPane.showMessageDialog(null,msg,title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(String str){
        optionPaNeTR();
        String msg;
        switch (str){
            case "sure":
                msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
                break;
            default:
                msg = str;
        }

        return JOptionPane.showConfirmDialog(null,msg,"Son kararın mı?",JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPaNeTR(){
        UIManager.put("OptionPane.okButtonText", "OKAY"); // showMsg buttonunda yazan
        UIManager.put("OptionPane.yesButtonText", "YES"); //confirm
        UIManager.put("OptionPane.noButtonText", "NO");
    }
}
