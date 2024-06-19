package com.patikaclone.Controller;

import com.patikaclone.Helper.*;
import com.patikaclone.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {
    /*
    tablo ve butonları
     */
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JPasswordField fld_password;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_uname;
    private JComboBox cmb_sh_user_type;
    private JButton btn_user_sh;
    private JPanel pnl_patika_list;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JButton btn_patika_add;
    private JTextField fld_patika_name;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_form;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JButton btn_course_add;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_update;
    private JTextField fld_course_delete_id;
    private JButton btn_course_delete;
    private JPanel fld_content_list;
    private JPanel pnl_user_search;
    private JTable tbl_content_list;
    private JScrollPane scrl_content_list;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    private JPopupMenu contentMenu;

    private final Operator operator;

    public OperatorGUI(Operator operator){
        Helper.setLayout();
        //baslangicta OperatorGUI yu kullanabilmemiz icin bir operatorumuz olmali
        this.operator = operator;
        add(this.wrapper);
        setSize(1000, 500);

        //konumu helper da degistirdigimizde tum projede degisir
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // X Tıkla kapan

        //projemizde title degistiginde sadece config ten yaptigimizda her yerde degisir
        //tum GUI lerde tek tek degistirmeyiz
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        //welcome labeli bize giris yapanin adini yazsin
        lbl_welcome.setText("Welcome " + operator.getName());

        // CREATE TABLES
        tableUser();
        popUpPatika();
        tablePatika();
        tableCourse();
        popUpContent();
        tableContent();
        // ## CREATE TABLES

        // USER BUTTON
        //Add
        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_password)){
                Helper.showMsg("fill");
            }else {
                String name = fld_user_name.getText();
                String username = fld_user_uname.getText();
                String password = fld_password.getText();
                String usertype = cmb_user_type.getSelectedItem().toString();
                if (User.add(name, username, password, usertype)){
                    Helper.showMsg("done");

                    /*aynı kodu iki defa yazmamak için methoda aldık
                    aşağıdaki method ile yeni geleni tablomuza o an ekleyebilriz ancak DefaultTablaModel cleae.setRowCount(0) ile tablomuzu
                    sıfırlamazsak iki tane altalta aynnı tabloyu yazıyor, yenilenmeden altına yazar.
                    biz tabloyu sıfırlayıp sonra hepsini tekrar yazmasını sağlıyoruz
                     */
                    loadUserModel();
                    loadUserCombo();

                    // işlemden sonra field ların içi boşalmıyordu

                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_password.setText(null);
                }
            }
        });
        //Delete
        btn_user_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_id)){
                Helper.showMsg("fill");
            }else {
                int user_id = Integer.parseInt(fld_user_id.getText());
                if (!User.getFetch(user_id).getUser_type().equals("operator")){
                    if (Helper.confirm("sure")){
                        if (User.delete(user_id)){
                            Helper.showMsg("done");
                            loadUserModel();
                            loadUserCombo();
                            loadCourseModel();
                            fld_user_id.setText(null);
                        }else {
                            Helper.showMsg("error");
                        }
                    }
                }else {
                    Helper.showMsg("Bu kişiyi silme yetkiniz yok!");
                }
            }
        });
        //Search
        btn_user_sh.addActionListener(e -> {
            String  name = fld_sh_user_name.getText();
            String username = fld_sh_user_uname.getText();
            String usertype = cmb_sh_user_type.getSelectedItem().toString();
            String query = User.searchQuery(name, username, usertype);
            //ArrayList<User> searchingUser = User.searchUserList(query); // tabloyu yenileyip arananları getirmeliyiz loadUserModel methodu overloading
            //loadUserModel(searchingUser);
            loadUserModel(User.searchUserList(query));
        });
        //## USER BUTTON

        // PatikaButtton
        //Add
        btn_patika_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_patika_name)){
                Helper.showMsg("fill");
            }else {
                if (Patika.add(fld_patika_name.getText())){
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    fld_patika_name.setText(null);
                }
            }
        });
        // delete ve uptade leri fareye sağ tık yaptığımızda menü çıkacak şekilde yapacağız jmenu jpopupmenu
        // ## PATİKA BUTTON

        // COURSE BUTTON
        //Add
        btn_course_add.addActionListener(e -> {
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if (Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)){
                Helper.showMsg("fill");
            }else {
                if (Course.add(fld_course_name.getText(),fld_course_lang.getText(),patikaItem.getKey(),userItem.getKey())){
                    Helper.showMsg("done");
                    loadCourseModel();
                    fld_course_delete_id.setText(null);
                    fld_course_lang.setText(null);
                    fld_course_name.setText(null);
                    cmb_course_user.getModel().setSelectedItem(null);
                    cmb_course_patika.getModel().setSelectedItem(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        //Delete
        btn_course_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_course_delete_id)){
                Helper.showMsg("fill");
            }else {
                int course_id = Integer.parseInt(fld_course_delete_id.getText());
                if (Helper.confirm("sure")){
                    if (Course.delete(course_id)){
                        Helper.showMsg("done");
                        loadUserModel();
                        loadUserCombo();
                        loadCourseModel();
                        loadContentModel();
                        fld_course_delete_id.setText(null);
                        fld_course_lang.setText(null);
                        fld_course_name.setText(null);
                        cmb_course_user.getModel().setSelectedItem(null);
                        cmb_course_patika.getModel().setSelectedItem(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
           }
        });
        //Update
        btn_course_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_course_delete_id)){
                Helper.showMsg("fill");
            }else {
                Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
                Item userItem = (Item) cmb_course_user.getSelectedItem();
                int course_id = Integer.parseInt(fld_course_delete_id.getText());
                if (Helper.confirm("sure")){
                    if (Course.update(course_id, fld_course_name.getText(), fld_course_lang.getText(), patikaItem.getKey(), userItem.getKey())){
                        Helper.showMsg("done");
                        loadUserModel();
                        loadUserCombo();
                        loadCourseModel();
                        fld_course_delete_id.setText(null);
                        fld_course_lang.setText(null);
                        fld_course_name.setText(null);
                        cmb_course_user.getModel().setSelectedItem(null);
                        cmb_course_patika.getModel().setSelectedItem(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        // ## COURSE BUTTON

        // OPERATORGUI BUTTON
        btn_logout.addActionListener(e -> {
            dispose(); //varolduğu, çağrıldığı frame i kapatır
            LoginGUI login = new LoginGUI();
        });
        // ## OPERATORGUI BUTTON
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);

        for (Course obj : Course.getList()){ //getList ArrayList<Course> donduruyor
            int i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLang();
            row_course_list[i++] = obj.getPatika().getName();
            row_course_list[i] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list); // tablomuza ekleriz
        }
    }

    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : User.getList()){ //getList ArrayList<User> donduruyor
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUser_name();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i] = obj.getUser_type();
            mdl_user_list.addRow(row_user_list); // tablomuza ekleriz
        }
    }
    public void loadUserModel(ArrayList<User> list){ //search de kullandık
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for (User obj : list){ //getList ArrayList<User> donduruyor
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUser_name();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i] = obj.getUser_type();
            mdl_user_list.addRow(row_user_list); // tablomuza ekleriz
        }
    }
    public void loadPatikaModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);

        for (Patika obj : Patika.getList()){ //getList ArrayList<Patika> donduruyor patika class ında
            int i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i] = obj.getName();
            mdl_patika_list.addRow(row_patika_list); // tablomuza ekleriz
        }
    }
    public void loadPatikaCombo(){
        cmb_course_patika.removeAllItems();
        cmb_course_patika.addItem(new Item(0,""));
        for (Patika obj : Patika.getList()){
            cmb_course_patika.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    public void loadUserCombo(){
        cmb_course_user.removeAllItems();
        cmb_course_user.addItem(new Item(0,""));
        for (User obj : User.getList()){
            if (obj.getUser_type().equals("educator")){
                cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));
            }
        }
    }
    private void loadContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);

        for (Content obj : Content.getList()){ //getList ArrayList<Content> donduruyor
            int i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getDescription();
            row_content_list[i++] = obj.getLink();
            row_content_list[i] = obj.getCourse().getName();
            mdl_content_list.addRow(row_content_list); // tablomuza ekleriz
        }
    }
    private void tableUser(){

        /*
        tablo sadece başta oluştuğu için o an eklediğimiz veriler veriitabanına ekleniyor ancak buradaki
        tabloya programı tekrar başlattığımızda ekleniyor
        bu yüzden loadUserModel()
         */

        mdl_user_list = new DefaultTableModel() {
            /*
            override ettik çünkü tablodan id leri değiştirebiliyorduk bunu engellemek için
            column u 0 olan değişmesin false
             */
            @Override
            public boolean isCellEditable(int row, int column){
                if (column == 0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };

        Object[] col_user_list = {"ID", "Name", "Username", "Password", "User Type"};
        mdl_user_list.setColumnIdentifiers(col_user_list); //tablo basliklari
        row_user_list = new Object[col_user_list.length];
        loadUserModel();
        loadUserCombo();

        /*
        Object[] firstRow = {"1", "mustafa cetindag", "mustafa", "123", "operator"};
        mdl_user_list.addRow(firstRow);
        */

        /*
        User class ında veritabanından data çekiyoruz

        bu kısmı methoda atadık, birkaç yerde kullandığımız için

        for (User obj : User.getList()){ //getList ArrayList<User> donduruyor
            Object[] row = new Object[col_user_list.length];
            row[0] = obj.getId();
            row[1] = obj.getName();
            row[2] = obj.getUser_name();
            row[3] = obj.getPassword();
            row[4] = obj.getUser_type();
            mdl_user_list.addRow(row); // tablomuza ekleriz
        }

         */


        tbl_user_list.setModel(mdl_user_list); //tabloya yukledik
        tbl_user_list.getTableHeader().setReorderingAllowed(false); //tablo basliklarinin yerini degistiremezsin

        /*
        delete yaparken yanlış girilen id hatası almamak için user id kısmını enable yi kaldırdık.
        oraya kullanıcı bir şeyler yazamıyor
        tabloda tıkladığı kişinin id si yazılsın şeklinde bir kural yazıyoruz
        seçtiğimiz satırın 0.sütunu id
         */
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            /*
            try catch içine aldık çünkü tablodan sildiğimizde tablomuz yenilenmiyor ve sildiğimiz eleman seçili kalıyor
            seçili ama böyle bir eleman yok
            bu hatayı almamamız için
             */
            try {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_user_id.setText(select_user_id);
            } catch (Exception exception) {
            }
        });

        // bu kod değişti
        //### OPERATOR SADECE EĞİTMEN EKLEYEBİLİR, OPERATORLERİ BİZ KENDİMİZ EKLEYECEĞİZ
        //veri tabanındaki enum değerleri cmb_user_type a ekleriz

        ArrayList<String> enumValues = null;
        try {
            enumValues = User.getEnumValues();
            for (String values : enumValues){
                // cmb_user_type.addItem(values);
                cmb_sh_user_type.addItem(values); // sadece search kısmında hepsi olsun
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        cmb_user_type.addItem("educator");

        tbl_user_list.getModel().addTableModelListener(e -> { //tabloyu dinlemek için
            if (e.getType() == TableModelEvent.UPDATE){ //yapılan işlem update mi, dinleyince anlıyor
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                String name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
                String password = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
                String user_type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();

                if (user_type.equals("educator") || user_id == this.operator.getId()){
                    if (User.update(user_id,name,user_name,password,user_type)) {
                        Helper.showMsg("done");
                    }
                }else {
                    Helper.showMsg("Sadece eğitmen bilgilerini ya da kendi bilgilerinizi güncelleyebilirsiniz!");
                }

                // user_type kısmını kontrol etmek için burada cmb de bulunan typeları ArrayList e alıp kontrol etmek için User daki metodu kullanacağım.

                /*boolean isHas = false;
                for (int i = 0; i < cmb_user_type.getItemCount(); i++) { // yazılan user_type uygulamadakilere uyuyor mu bakıyoruz
                    if (cmb_user_type.getItemAt(i).equals(user_type)) {
                        isHas = true;
                        if (User.update(user_id,name,user_name,password,user_type)){
                            Helper.showMsg("done");
                            //loadUserModel();
                            break;
                        }
                    }
                }
                if (!isHas){
                    Helper.showMsg("Uygulamada bulunan bir 'User Type' yazınız!");
                }

                 */

                /*
                Aynı user_name i olunca else giriyordu ve tabloyu güncellemediği için sanki değiştirmiş gibi yazdığımız user_name kalıyordu
                programı kapatıp acınca düzeliyordu
                Bu yüzden sadece doğru iken güncellenmiş tabloyu göstermesin yanlışta olsa göstersin diye loadUserModel i dışarı aldık
                else {
                    Helper.showMsg("error");
                    System.out.println("hata");
                }
                 */
                loadUserModel();
                loadUserCombo();
                loadCourseModel();
            }
        });
    }
    private void popUpPatika(){
        patikaMenu = new JPopupMenu();
        // menüyü oluşturuypruz
        JMenuItem uptadeMenu = new JMenuItem("Uptade");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        // popup ımıza ekliyoruz
        patikaMenu.add(uptadeMenu);
        patikaMenu.add(deleteMenu);
        // menü oluştu tabloya eklememiz gerek setComponentPopupMenu methodu ile yapcağız
        //UpdatePatikaGUI yi bağlayacağız
        uptadeMenu.addActionListener(e->{
            // seçilen row un id isini alıyoruz
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
            UpdatePatikaGUI updatePatikaGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updatePatikaGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                    loadContentModel();
                }
            });
        });

        deleteMenu.addActionListener(e->{
            if (Helper.confirm("sure")){
                int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
                if (Patika.delete(select_id)){
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                    loadContentModel();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }
    private void tablePatika(){
        mdl_patika_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Object[] col_patika_list = {"ID","Name"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list); //tablo baslıklarını ekledik
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();
        loadPatikaCombo();

        tbl_patika_list.setModel(mdl_patika_list);
        // popup ımızı tabloya ekliyoruz
        tbl_patika_list.setComponentPopupMenu(patikaMenu); ///////////////////////
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75); //id alanı çok büyüktü küçülttük

        /*
        addMouseListener ile fareyi dinledik ve tıkladığımız yerin seçili olmasını sağladık
        bunları yaptıktan sonra popup menümüzde çıkan update için yeni bir GUI oluşturuyoruz
         */
        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { // farenin tıklaadığındaki koordinatı verir
                Point point = e.getPoint(); // koordinatları tutan class
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }
    private void tableCourse(){
        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"ID","Name","Language","Patika","Educator"};
        mdl_course_list.setColumnIdentifiers(col_course_list); //tablo baslıklarını ekledik
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);

        /*
        tabloda tıkladığı dersin bilgileri yazılsın şeklinde bir kural yazıyoruz
        seçtiğimiz satırın sütununları isim language patika educator
         */
        tbl_course_list.getSelectionModel().addListSelectionListener(e -> {
            /*
            try catch içine aldık tablomuz yenilenmiyor ve eleman seçili kalıyor
            seçili ama böyle bir eleman yok ya da güncellenmiş
            bu hatayı almamamız için
             */
            try {
                fld_course_name.setText(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 1).toString());
                fld_course_lang.setText(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 2).toString());
                for (Patika obj : Patika.getList()){
                    if (obj.getName().equals(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),3).toString())){
                        cmb_course_patika.getModel().setSelectedItem(new Item(obj.getId(),obj.getName()));
                        break;
                    }
                }
                for (User obj : User.getList()){
                    if (obj.getName().equals(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),4).toString())){
                        cmb_course_user.getModel().setSelectedItem(new Item(obj.getId(),obj.getName()));
                        break;
                    }
                }
                fld_course_delete_id.setText(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString());
            } catch (Exception exception) {
            }
        });
        loadPatikaCombo();
        loadUserCombo();
    }
    private void popUpContent(){
        contentMenu = new JPopupMenu();
        // menüyü oluşturuypruz
        JMenuItem deleteContentMenu = new JMenuItem("DELETE");
        JMenuItem quizMenu = new JMenuItem("Question");
        // popup ımıza ekliyoruz
        contentMenu.add(deleteContentMenu);
        contentMenu.add(quizMenu);
        // menü oluştu tabloya eklememiz gerek setComponentPopupMenu methodu ile yapcağız
        //UpdatePatikaGUI yi bağlayacağız
        quizMenu.addActionListener(e->{
            // seçilen row un id isini alıyoruz
            int select_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0).toString());
            QuestionGUI questionGUI = new QuestionGUI(Content.getFetch(select_id));
        });
        deleteContentMenu.addActionListener(e->{
            if (Helper.confirm("sure")){
                int select_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0).toString());
                if (Content.delete(select_id)){
                    Helper.showMsg("done");
                    loadContentModel();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }
    private void tableContent(){
        mdl_content_list = new DefaultTableModel() {
            /*
            override ettik çünkü tablodan id leri değiştirebiliyorduk bunu engellemek için
            column u 0 olan değişmesin false
             */
            @Override
            public boolean isCellEditable(int row, int column){
                if (column == 0){
                    return false;
                }
                return super.isCellEditable(row,column);
            }
        };
        Object[] col_content_list = {"ID","Title","Description","Youtube Link","Course"};
        mdl_content_list.setColumnIdentifiers(col_content_list); //tablo baslıklarını ekledik
        row_content_list = new Object[col_content_list.length];
        loadContentModel();

        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.setComponentPopupMenu(contentMenu);
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);

        tbl_content_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { // farenin tıklaadığındaki koordinatı verir
                Point point = e.getPoint(); // koordinatları tutan class
                int selected_row = tbl_content_list.rowAtPoint(point);
                tbl_content_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        tbl_content_list.getModel().addTableModelListener(e -> { //tabloyu dinlemek için
            if (e.getType() == TableModelEvent.UPDATE) { //yapılan işlem update mi, dinleyince anlıyor
                int content_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
                String title = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 1).toString();
                String description = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 2).toString();
                String link = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 3).toString();
                String course = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),4).toString();
                for (Course c : Course.getList()){
                    if (c.getName().equals(course)){
                        int course_id = c.getId();
                        if (Content.update(content_id,course_id,title,description,link)){
                            Helper.showMsg("done");
                            loadContentModel();
                            return;
                        }
                    }
                }
                Helper.showMsg("Sadece kayıtlı olan derslerden yazabilirsiniz!");
                loadContentModel();
            }
        });
    }
}
