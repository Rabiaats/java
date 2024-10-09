package com.patikaclone.Controller;

import com.patikaclone.Helper.Config;
import com.patikaclone.Helper.Helper;
import com.patikaclone.Helper.Item;
import com.patikaclone.Model.Content;
import com.patikaclone.Model.Course;
import com.patikaclone.Model.Educator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class EducatorGUI extends JFrame{

    private JPanel wrapper;
    private JButton btn_logout;
    private JTabbedPane tab_educator;
    private JPanel pnl_course_list;
    private JPanel pnl_top;
    private JTable tbl_course_list;
    private JPanel pnl_content_list;
    private JTable tbl_content_list;
    private JPanel pnl_content_search;
    private JTextField fld_sh_content_title;
    private JTextField fld_content_title;
    private JTextField fld_content_desc;
    private JComboBox cmb_content_course;
    private JButton btn_content_add;
    private JTextField fld_content_delete_id;
    private JButton btn_content_delete;
    private JTextField fld_content_link;
    private JButton btn_content_update;
    private JComboBox cmb_sh_content_course;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    private JPopupMenu contentMenu;
    private Educator educator;
    public EducatorGUI(Educator educator){
        Helper.setLayout();
        this.educator = educator;
        add(this.wrapper);
        setSize(1000,550);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        // CREATE TABLES
        tableCourse();
        popUpContent();
        tableContent();
        // ##

        // CONTENT BUTTON
        //Add
        btn_content_add.addActionListener(e -> {
            Item courseItem = (Item) cmb_content_course.getSelectedItem();
            if (Helper.isFieldEmpty(fld_content_title) || Helper.isFieldEmpty(fld_content_desc) || Helper.isFieldEmpty(fld_content_link)){
                Helper.showMsg("fill");
            }else {
                if (Content.add(fld_content_title.getText(),fld_content_desc.getText(),fld_content_link.getText(),courseItem.getKey())){
                    Helper.showMsg("done");
                    loadContentModel();
                    fld_content_delete_id.setText(null);
                    fld_content_title.setText(null);
                    fld_content_link.setText(null);
                    fld_content_desc.setText(null);
                    cmb_content_course.getModel().setSelectedItem(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });

        //Delete
        btn_content_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_content_delete_id)){
                Helper.showMsg("fill");
            }else {
                int content_id = Integer.parseInt(fld_content_delete_id.getText());
                if (Helper.confirm("sure")){
                    if (Content.delete(content_id)){
                        Helper.showMsg("done");
                        loadContentModel();
                        fld_content_delete_id.setText(null);
                        fld_content_title.setText(null);
                        fld_content_link.setText(null);
                        fld_content_desc.setText(null);
                        cmb_content_course.getModel().setSelectedItem(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        //Update
        btn_content_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_content_delete_id)){
                Helper.showMsg("fill");
            }else {
                Item courseItem = (Item) cmb_content_course.getSelectedItem();
                int content_id = Integer.parseInt(fld_content_delete_id.getText());
                if (Helper.confirm("sure")){
                    if (Content.update(content_id,courseItem.getKey() ,fld_content_title.getText(), fld_content_desc.getText(),fld_content_link.getText())){
                        Helper.showMsg("done");
                        loadContentModel();
                        fld_content_delete_id.setText(null);
                        fld_content_title.setText(null);
                        fld_content_link.setText(null);
                        fld_content_desc.setText(null);
                        cmb_content_course.getModel().setSelectedItem(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        // ## CONTENT BUTTON

        // EDUCATORGUI BUTTON
        btn_logout.addActionListener(e -> {
            dispose(); //varolduğu, çağrıldığı frame i kapatır
            LoginGUI login = new LoginGUI();
        });
        // ## EDUCATORGUI BUTTON
    }
    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);

        for (Course obj : Course.getList()){ //getList ArrayList<Course> donduruyor
            if (obj.getEducator().getId() == this.educator.getId()){
                int i = 0;
                row_course_list[i++] = obj.getId();
                row_course_list[i++] = obj.getName();
                row_course_list[i++] = obj.getLang();
                row_course_list[i++] = obj.getPatika().getName();
                row_course_list[i] = obj.getEducator().getName();
                mdl_course_list.addRow(row_course_list); // tablomuza ekleriz
            }
        }
    }
    private void loadContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);

        for (Course c : Course.getList()){
            if (c.getEducator().getId() == this.educator.getId()){
                for (Content obj : Content.getList()){ //getList ArrayList<Content> donduruyor
                    if (obj.getCourse().getId() == c.getId()){
                        int i = 0;
                        row_content_list[i++] = obj.getId();
                        row_content_list[i++] = obj.getTitle();
                        row_content_list[i++] = obj.getDescription();
                        row_content_list[i++] = obj.getLink();
                        row_content_list[i] = obj.getCourse().getName();
                        mdl_content_list.addRow(row_content_list); // tablomuza ekleriz
                    }
                }
            }
        }
    }
    public void loadCourseCombo(){
        cmb_content_course.removeAllItems();
        cmb_sh_content_course.removeAllItems();
        cmb_content_course.addItem(new Item(0,""));
        cmb_sh_content_course.addItem(new Item(0,""));
        for (Course obj : Course.getList()){
            if (obj.getEducator().getId() == this.educator.getId()){
                cmb_content_course.addItem(new Item(obj.getId(), obj.getName()));
                cmb_sh_content_course.addItem(new Item(obj.getId(),obj.getName()));
            }
        }
    }
    public void loadContentModel(ArrayList<Content> list){ //search de kullandık
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);

        for (Course c : Course.getList()){
            if (c.getEducator().getId() == this.educator.getId()){
                for (Content obj : list){ //list ArrayList<Content> donduruyor
                    if (obj.getCourse().getId() == c.getId()){
                        int i = 0;
                        row_content_list[i++] = obj.getId();
                        row_content_list[i++] = obj.getTitle();
                        row_content_list[i++] = obj.getDescription();
                        row_content_list[i++] = obj.getLink();
                        row_content_list[i] = obj.getCourse().getName();
                        mdl_content_list.addRow(row_content_list); // tablomuza ekleriz
                    }
                }
            }
        }
    }
    private void tableCourse(){
        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Object[] col_course_list = {"ID","Name","Language","Patika","Educator"};
        mdl_course_list.setColumnIdentifiers(col_course_list); //tablo baslıklarını ekledik
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
    }
    private void popUpContent(){
        contentMenu = new JPopupMenu();
        // menüyü oluşturuyoruz
        JMenuItem deleteContentMenu = new JMenuItem("DELETE");
        JMenuItem quizMenu = new JMenuItem("Question");
        // popup ımıza ekliyoruz
        contentMenu.add(quizMenu);
        // menü oluştu tabloya eklememiz gerek setComponentPopupMenu methodu ile yapcağız
        //UpdatePatikaGUI yi bağlayacağız
        quizMenu.addActionListener(e->{
            // seçilen row un id isini alıyoruz
            int select_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0).toString());
            QuestionGUI questionGUI = new QuestionGUI(Content.getFetch(select_id));
            questionGUI.setBtn_question_add_enable();
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
                return false;
            }
        };
        Object[] col_content_list = {"ID","Title","Description","Youtube Link","Course"};
        mdl_content_list.setColumnIdentifiers(col_content_list); //tablo baslıklarını ekledik
        row_content_list = new Object[col_content_list.length];
        loadContentModel();
        loadCourseCombo();

        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.setComponentPopupMenu(contentMenu); // popupmenusünü tabloya ekledik
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);

        fld_sh_content_title.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String  title = fld_sh_content_title.getText();
                String course = cmb_sh_content_course.getSelectedItem().toString();
                String query = Content.searchQuery(title, course);
                loadContentModel(Content.searchContentList(query));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String  title = fld_sh_content_title.getText();
                String course = cmb_sh_content_course.getSelectedItem().toString();
                String query = Content.searchQuery(title, course);
                loadContentModel(Content.searchContentList(query));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        cmb_sh_content_course.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String  title = fld_sh_content_title.getText();
                String course = cmb_sh_content_course.getSelectedItem().toString();
                String query = Content.searchQuery(title, course);
                loadContentModel(Content.searchContentList(query));
            }
        });

        tbl_content_list.getSelectionModel().addListSelectionListener(e ->{
            try{
                fld_content_title.setText(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),1).toString());
                fld_content_desc.setText(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),2).toString());
                fld_content_link.setText(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),3).toString());
                for (Course obj : Course.getList()){
                    if (obj.getName().equals(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),4).toString())){
                        cmb_content_course.getModel().setSelectedItem(new Item(obj.getId(),obj.getName()));
                        break;
                    }
                }
                fld_content_delete_id.setText(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0).toString());
            }catch (Exception exception){

            }
        });
    }
}
