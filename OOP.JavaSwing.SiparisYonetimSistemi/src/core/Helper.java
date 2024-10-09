package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    /**
     *
     * @param axis
     * @param size
     * @return koordinat
     * ekranımızın ortasında açılsın UI
     */
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

    /**
     * arayüz görünümü
     */
    public static void setTheme(){
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
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fields){
        for (JTextField field : fields){
            if (isFieldEmpty(field)){
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailValid(String mail){
        // info@patika.dev
        // @ olacak, @'ten once bir deger, @'ten sonra . olacak ve bir deger olaacak
        if (mail == null || mail.trim().isEmpty()) return false;

        if (!(mail.contains("@"))) return false;

        String[] parts = mail.split("@");

        if (parts.length != 2) return false;

        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) return false;

        if (!parts[1].contains(".")) return false;

        return true;
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
        if (str.equals("sure")){
            msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
        }else {
            msg = str;
        }

        return JOptionPane.showConfirmDialog(null,msg,"Emin misin ?",JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPaNeTR(){
        UIManager.put("OptionPane.okButtonText", "TAMAM"); // showMsg buttonunda yazan
        UIManager.put("OptionPane.yesButtonText", "EVET"); //confirm
        UIManager.put("OptionPane.noButtonText", "HAYIR");
    }
}
