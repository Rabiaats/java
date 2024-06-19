package JavaSwingTraining;

import javax.swing.*;
import java.awt.*;

public class ExampleGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;

    /*

    Bu gorunum karisik gozuktugunden JFrame den inheritance yaptigimizda her yeni nesnede asagidakini kendi yapar

    public static void main(String[] args) {
        JFrame frame = new JFrame("ExampleGUI");
        frame.setContentPane(new ExampleGUI().wrapper);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
     */

    public ExampleGUI(){
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            // eger nimbus varsa yap, bazi bilgisayarlarda olmayabiliyor yoksa hepsinde olsa
            // IUMaganer.setLokkAndFell("javax.swing.plaf.nimbus.NimbusLookAndFeel")
            // yaparakta olur
            if (info.getName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // setContentPane(wrapper); bu da olur
        this.add(wrapper);

        //olusturulacak pencere boyutu
        setSize(300, 250);
        // pencerenin ustundeki isim, uygulama adi
        setTitle("App Name");

        // pencerenin tek boyutu olur, buyutulup kuculmez
        setResizable(false);

        //run ile calıstırınca sol ustte pencere aciliyor, konum ayarlayabiliriz
        // Toolkit.getDefaultToolkit().getScreenSize() bilgisayar ekranımın boyutunu verir
        // getSize() JFrame penceremin boyutu
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x,y);

        //acilan pencereyi kapatsam bile program run da kaliyor
        // kapatma tusunu degistirmis olduk, close butonuna basılınca dispose et/kapat
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //pencere gorunur
        setVisible(true);

        // listener ler kullanicinin ne yaptiginda degisiklik olacagini belirler
        // ActionListener button a tikladigimda demek
        /*
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
         */
        // bir interface de sadece bir method varsa lambda ifadesi ile yazabiliriz
        btn_login.addActionListener(e -> {
            if (fld_username.getText().isEmpty() || fld_password.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Tüm alanları doldurun!", "Hata", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static class Main {
        public static void main(String[] args) {
            ExampleGUI ex = new ExampleGUI();
        }
    }
}
