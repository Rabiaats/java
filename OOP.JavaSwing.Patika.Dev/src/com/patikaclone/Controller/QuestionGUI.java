package com.patikaclone.Controller;

import com.patikaclone.Helper.Config;
import com.patikaclone.Helper.Helper;
import com.patikaclone.Helper.Item;
import com.patikaclone.Model.Content;
import com.patikaclone.Model.Question;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class QuestionGUI extends JFrame{
    private JPanel wrapper;
    private JTable tbl_question_list;
    private JPanel pnl_top;
    private JPanel pnl_question;
    private JPanel pnl_question_form;
    private JTextField fld_question_title;
    private JTextField fld_question_answer;
    private JComboBox cmb_question_content;
    private JButton btn_question_add;
    private JButton btn_question_delete;
    private JTextField fld_question_delete_id;
    private JButton btn_logout;
    private JButton btn_question_update;
    private DefaultTableModel mdl_question_list;
    private Object[] row_question_list;
    private Content content;

    public QuestionGUI(Content content){
        Helper.setLayout();
        this.content = content;
        add(this.wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        mdl_question_list = new DefaultTableModel();
        Object[] col_question_list = {"ID","Question","Answer","Content"};
        mdl_question_list.setColumnIdentifiers(col_question_list); //tablo baslıklarını ekledik
        row_question_list = new Object[col_question_list.length];
        loadQuestionModel();

        // CREATE TABLE
        tableQuestion();
        // ##
        Item item = new Item(this.content.getId(),this.content.getTitle());
        cmb_question_content.addItem(item);

        // QUESTIONGUI BUTTON
        //Add
        btn_question_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_question_title) || Helper.isFieldEmpty(fld_question_answer)){
                Helper.showMsg("fill");
            }else {
                if (Question.add(fld_question_title.getText(),fld_question_answer.getText(),this.content.getId())){
                    Helper.showMsg("done");
                    loadQuestionModel();
                    fld_question_title.setText(null);
                    fld_question_title.setText(null);
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        //Delete
        btn_question_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_question_delete_id)){
                Helper.showMsg("fill");
            }else {
                int question_id = Integer.parseInt(fld_question_delete_id.getText());
                if (Helper.confirm("sure")){
                    if (Question.delete(question_id)){
                        Helper.showMsg("done");
                        loadQuestionModel();
                        fld_question_delete_id.setText(null);
                        fld_question_title.setText(null);
                        fld_question_answer.setText(null);
                        cmb_question_content.getModel().setSelectedItem(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        //Update
        btn_question_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_question_delete_id)){
                Helper.showMsg("fill");
            }else {
                int question_id = Integer.parseInt(fld_question_delete_id.getText());
                if (Helper.confirm("sure")){
                    if (Question.update(question_id, fld_question_title.getText(), fld_question_answer.getText(), item.getKey())){
                        Helper.showMsg("done");
                        loadQuestionModel();
                        //loadUserModel();
                        //loadUserCombo();
                        //loadCourseModel();
                        fld_question_delete_id.setText(null);
                        fld_question_answer.setText(null);
                        fld_question_title.setText(null);
                        cmb_question_content.getModel().setSelectedItem(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        //Log Out
        btn_logout.addActionListener(e -> {
            dispose(); //varolduğu, çağrıldığı frame i kapatır
        });
        // ## QUESTION BUTTON
    }

    public void setBtn_question_add_enable() {
        this.btn_question_add.setEnabled(true);
    }

    private void loadQuestionModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_question_list.getModel();
        clearModel.setRowCount(0);
        for (Question obj : Question.getList(this.content.getId())){ //getList ArrayList<Content> donduruyor
                int i = 0;
                row_question_list[i++] = obj.getId();
                row_question_list[i++] = obj.getTitle();
                row_question_list[i++] = obj.getAnswer();
                row_question_list[i] = obj.getContent().getTitle();
                mdl_question_list.addRow(row_question_list); // tablomuza ekleriz
        }
    }
    private void tableQuestion(){
        tbl_question_list.setModel(mdl_question_list);
        tbl_question_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_question_list.getTableHeader().setReorderingAllowed(false);

        /*
        tabloda tıkladığı dersin bilgileri yazılsın şeklinde bir kural yazıyoruz
        seçtiğimiz satırın sütununları isim language patika educator
         */
        tbl_question_list.getSelectionModel().addListSelectionListener(e -> {
            /*
            try catch içine aldık tablomuz yenilenmiyor ve eleman seçili kalıyor
            seçili ama böyle bir eleman yok ya da güncellenmiş
            bu hatayı almamamız için
             */
            try {
                fld_question_title.setText(tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 1).toString());
                fld_question_answer.setText(tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 2).toString());
                fld_question_delete_id.setText(tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(),0).toString());
            } catch (Exception exception) {
            }
        });
    }
}
