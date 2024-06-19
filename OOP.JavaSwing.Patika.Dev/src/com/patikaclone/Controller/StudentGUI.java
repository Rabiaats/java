package com.patikaclone.Controller;

import com.patikaclone.Helper.Config;
import com.patikaclone.Helper.Helper;
import com.patikaclone.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * contentguı yi düzenle yorum tablosu yorum ve content id si ile
 * sonra puan tablosu olsun content id ve 1-5 arası puan ile
 * sorular contente quize git butonu olsun panel açılsın soru ve altında fld gönder butonuna basınca fld kontrollri ve ortalama al
 *sonra o iççeriği ortalama fldsi dolu ise shwMesaj yazsın content quize gitte
 */
public class StudentGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_student;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_info;
    private JTable tbl_patika_info_course_list;
    private JButton btn_singup;
    private JTextField fld_signup_course_name;
    private JScrollPane scrl_patika_list;
    private JPanel pnl_course_info;
    private JTable tbl_course_content_list;
    private JScrollPane scrl_patika_info;
    private JButton btn_comeback_course;
    private JLabel lbl_course_info_name;
    private JLabel lbl_patika_info_name;
    private JScrollPane scrl_course_info;
    private JPanel pnl_patika_list;
    private JTextField fld_patika_name;
    private JButton btn_patika_open;
    private JScrollPane scrl_course_list;
    private JScrollPane scrl_content_list;
    private JTable tbl_course_list;
    private JTable tbl_content_list;
    private JPanel pnl_content_list;
    private JPanel pnl_course_list;
    private JTextField fld_course_name;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private DefaultTableModel mdl_patika_info_course_list;
    private Object[] row_patika_info_course_list;
    private DefaultTableModel mdl_course_content_list;
    private Object[] row_course_content_list;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    private JPopupMenu courseMenu;
    private JPopupMenu singinCourseMenu;
    private JPopupMenu contentMenu;
    private int patika_id;
    private int course_id;
    private int content_id;
    private Student student;
    public StudentGUI(Student student){
        Helper.setLayout();
        this.student = student;
        add(this.wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        pnl_patika_info.setVisible(false);
        pnl_course_info.setVisible(false);
        pnl_content_list.setVisible(false);

        // CREATE TABLES
        tablePatika();
        popUpPatikaInfo();
        tablePatikaInfo();
        tableCourseInfo();
        popUpCourse();
        tableCourse();
        popUpContent();
        tableContent();
        // ##

        // PATIKA BUTTON
        // OPEN -> PATIKA nın course leri açılır
        btn_patika_open.addActionListener(e -> {
            if (!Course.isCourse(patika_id).isEmpty()){
                pnl_patika_list.setVisible(false);
                pnl_patika_info.setVisible(true);
            }else {
                Helper.showMsg("Bu patikaya ders eklenmemiş!!");
            }
        });
        // PANEL COURSE INFO - Come Back -> content ten course ye doner
        btn_comeback_course.addActionListener(e -> {
            pnl_course_info.setVisible(false);
            pnl_patika_info.setVisible(true);
            loadCourseModel(patika_id);
        });
        // PANEL COURSE INFO - Sing Up
        btn_singup.addActionListener(e1 -> {
            CourseSignIn.singInCourse(course_id,this.student.getId());
            loadCourseList();
        });
        // ## PATIKA BUTTON

        // STUDENTGUI BUTTON
        // Log Out -> Close
        btn_logout.addActionListener(e -> {
            dispose(); //varolduğu, çağrıldığı frame i kapatır
            LoginGUI login = new LoginGUI();
        });
        // ## STUDENTGUI BUTTON
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

    private void loadCourseModel(int patika_id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_info_course_list.getModel();
        clearModel.setRowCount(0);
        for (Course obj : Course.isCourse(patika_id)){ //getList ArrayList<Course> donduruyor
            int i = 0;
            row_patika_info_course_list[i++] = obj.getId();
            row_patika_info_course_list[i++] = obj.getName();
            row_patika_info_course_list[i++] = obj.getLang();
            row_patika_info_course_list[i++] = obj.getPatika().getName();
            row_patika_info_course_list[i] = obj.getEducator().getName();
            mdl_patika_info_course_list.addRow(row_patika_info_course_list); // tablomuza ekleriz
        }
    }

    private void loadCourseList() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);

        for (Course obj : CourseSignIn.getList(this.student.getId())){ //getList ArrayList<Course> donduruyor
            int i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLang();
            row_course_list[i++] = obj.getPatika().getName();
            row_course_list[i] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list); // tablomuza ekleriz
        }
    }
    private void loadContentModel(int course_id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_content_list.getModel();
        clearModel.setRowCount(0);

        for (Content obj : Content.searchContentList(Content.searchQuery("", Course.getFetch(course_id).getName()))){ //getList ArrayList<Content> donduruyor
            int i = 0;
            row_course_content_list[i++] = obj.getId();
            row_course_content_list[i++] = obj.getTitle();
            row_course_content_list[i++] = obj.getDescription();
            mdl_course_content_list.addRow(row_course_content_list); // tablomuza ekleriz
        }
    }
    private void loadContentList(int course_id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);

        for (Content obj : Content.searchContentList(Content.searchQuery("", Course.getFetch(course_id).getName()))){ //getList ArrayList<Content> donduruyor
            int i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getDescription();
            row_content_list[i++] = obj.getLink();
            row_content_list[i] = obj.getCourse().getName();
            mdl_content_list.addRow(row_content_list); // tablomuza ekleriz
        }
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
        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75); //id alanı çok büyüktü küçülttük
        tbl_patika_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                fld_patika_name.setText(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 1).toString());
                lbl_patika_info_name.setText(fld_patika_name.getText().toUpperCase() + " PATİKASI DERSLERİ");
                patika_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
            }catch (Exception exception){}
            loadCourseModel(patika_id);
        });
    }
    private void popUpPatikaInfo(){
        courseMenu = new JPopupMenu();
        JMenuItem seeContent = new JMenuItem("İçeriği Gör");
        JMenuItem comeBackPatika = new JMenuItem("Patika Listesine Dön");
        courseMenu.add(seeContent);
        courseMenu.add(comeBackPatika);
        comeBackPatika.addActionListener(e ->{
            pnl_patika_info.setVisible(false);
            pnl_patika_list.setVisible(true);
            loadPatikaModel();
            fld_patika_name.setText(null);
        });
        seeContent.addActionListener(e ->{
            course_id = Integer.parseInt(tbl_patika_info_course_list.getValueAt(tbl_patika_info_course_list.getSelectedRow(),0).toString());
            lbl_course_info_name.setText(tbl_patika_info_course_list.getValueAt(tbl_patika_info_course_list.getSelectedRow(),1).toString().toUpperCase() + " DERS İÇERİKLERİ");
            fld_signup_course_name.setText(tbl_patika_info_course_list.getValueAt(tbl_patika_info_course_list.getSelectedRow(),1).toString());
            loadContentModel(course_id);
            pnl_patika_info.setVisible(false);
            pnl_course_info.setVisible(true);
        });
    }
    private void tablePatikaInfo(){
        mdl_patika_info_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Object[] col_patika_info_course_list = {"ID","Name","Language","Patika","Educator"};
        mdl_patika_info_course_list.setColumnIdentifiers(col_patika_info_course_list); //tablo baslıklarını ekledik
        row_patika_info_course_list = new Object[col_patika_info_course_list.length];
        lbl_patika_info_name.setForeground(Color.BLUE);
        tbl_patika_info_course_list.setModel(mdl_patika_info_course_list);
        tbl_patika_info_course_list.setComponentPopupMenu(courseMenu);
        tbl_patika_info_course_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_patika_info_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_info_course_list.getSelectionModel().addListSelectionListener(e ->{
            try {
                fld_signup_course_name.setText(tbl_patika_info_course_list.getValueAt(tbl_patika_info_course_list.getSelectedRow(),1).toString());
                course_id = Integer.parseInt(tbl_patika_info_course_list.getValueAt(tbl_patika_info_course_list.getSelectedRow(),0).toString());
            }catch (Exception exception){
            }
            loadContentList(course_id);
        });
    }
    private void tableCourseInfo(){
        mdl_course_content_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Object[] col_course_content_list = {"ID","Title","Description"};
        mdl_course_content_list.setColumnIdentifiers(col_course_content_list); //tablo baslıklarını ekledik
        row_course_content_list = new Object[col_course_content_list.length];
        lbl_course_info_name.setForeground(Color.BLUE);
        tbl_course_content_list.setModel(mdl_course_content_list);
        tbl_course_content_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_content_list.getTableHeader().setReorderingAllowed(false);
    }
    private void popUpCourse(){
        singinCourseMenu = new JPopupMenu();
        JMenuItem seeSinginContent = new JMenuItem("İçerik");
        singinCourseMenu.add(seeSinginContent);
        seeSinginContent.addActionListener(e ->{
            if (Content.isContent(course_id)){
                pnl_course_list.setVisible(false);
                pnl_content_list.setVisible(true);
                loadContentList(course_id);
            }else {
                Helper.showMsg("Bu derse içerik eklenmemiş");
            }
        });
    }
    private void tableCourse(){
        mdl_course_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Object[] col_course_list = {"ID","Name","Language","Patika","Educator"};
        mdl_course_list.setColumnIdentifiers(col_course_list); //tablo baslıklarını ekledik
        row_course_list = new Object[col_course_list.length];
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.setComponentPopupMenu(singinCourseMenu);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadCourseList();
        tbl_course_list.getSelectionModel().addListSelectionListener(event -> {
            try {
                course_id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString());
            } catch (Exception e){}
            loadContentList(course_id);
        });
    }
    private void popUpContent(){
        contentMenu = new JPopupMenu();
        JMenuItem openContent = new JMenuItem("Aç");
        JMenuItem comeBackCourse = new JMenuItem("Derslere Geri Dön");
        contentMenu.add(openContent);
        contentMenu.add(comeBackCourse);
        openContent.addActionListener(e ->{
            ContentGUI contentGUI = new ContentGUI(Content.getFetch(this.content_id), this.student);
        });
        comeBackCourse.addActionListener(e -> {
            pnl_content_list.setVisible(false);
            pnl_course_list.setVisible(true);
            loadCourseList();
        });
    }
    private void tableContent(){
        mdl_content_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Object[] col_content_list = {"ID","Title","Description","Youtube Link","Course"};
        mdl_content_list.setColumnIdentifiers(col_content_list); //tablo baslıklarını ekledik
        row_content_list = new Object[col_content_list.length];
        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.setComponentPopupMenu(contentMenu);
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);
        tbl_content_list.getSelectionModel().addListSelectionListener(event -> {
            try {
                content_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
            } catch (Exception e){}
        });
    }
}
