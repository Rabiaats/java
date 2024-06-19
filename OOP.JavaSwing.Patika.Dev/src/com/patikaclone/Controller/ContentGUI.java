package com.patikaclone.Controller;

import com.patikaclone.Helper.Config;
import com.patikaclone.Helper.Helper;
import com.patikaclone.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ContentGUI extends JFrame{
    private JPanel wrapper;
    private JLabel lbl_content_title;
    private JPanel pnl_content_link;
    private JPanel pnl_content_description;
    private JPanel pnl_comment_list;
    private JTable tbl_comment_list;
    private JTextField fld_content_comment;
    private JTextField fld_content_point;
    private JTabbedPane tab_comment;
    private JButton btn_content_comment;
    private JButton btn_content_point;
    private JLabel lbl_content_link;
    private JLabel lbl_content_description;
    private JLabel lbl_content_point;
    private JPanel pnl_top;
    private JPanel pnl_question_list;
    private JPanel pnl_button;
    private JButton btn_quiz_send;
    private JTable tbl_question_list;
    private JPanel pnl_quiz_result;
    private JLabel lbl_ouiz_result;
    private JPanel pnl_quiz_list;
    private JPanel pnl_statement_list;
    private DefaultTableModel mdl_comment_list;
    private Object[] row_comment_list;
    private DefaultTableModel mdl_question_list;
    private Object[] row_question_list;
    private Content content;
    private Student student;

    public ContentGUI(Content content, Student student){
        Helper.setLayout();
        this.content = content;
        this.student = student;
        add(this.wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        pnl_quiz_result.setVisible(false);
        lbl_content_title.setText(this.content.getTitle().toUpperCase());
        lbl_content_link.setText(this.content.getLink());
        lbl_content_description.setText(this.content.getDescription());
        loadPointLabel();

        // CREATE TABLES
        tableComment();
        tableQuestion();
        // ##

        // STATEMENT BUTTON
        //Comment
        btn_content_comment.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_content_comment)){
                Helper.showMsg("fill");
            }else {
                if (ContentOfContent.add(this.student.getId(), this.content.getId(), fld_content_comment.getText(), "comment")){
                    Helper.showMsg("done");
                    loadCommentModel();
                    fld_content_comment.setText(null);
                }
            }
            loadCommentModel();
        });
        //Point
        btn_content_point.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_content_point)){
                Helper.showMsg("fill");
            }else if (Integer.parseInt(fld_content_point.getText()) > 5){
                Helper.showMsg("1 ve 5 arası bir değer giriniz.");
            }else {
                if (ContentOfContent.add(this.student.getId(), this.content.getId(), fld_content_point.getText(), "point")){
                    Helper.showMsg("done");
                    loadCommentModel();
                    fld_content_point.setText(null);
                }
            }
            loadPointLabel();
        });
        // ## STATEMENT BUTTON

        // QUIZ BUTTON
        // Send
        btn_quiz_send.addActionListener(e -> {
            pnl_button.setVisible(false);
            pnl_quiz_result.setVisible(true);
            int correct = 0;
            int wrong = 0;
            for (int i = 0; i < mdl_question_list.getRowCount(); i++){
                if (!Question.getFetch(Integer.parseInt(tbl_question_list.getValueAt(i,0).toString())).getAnswer().equals(tbl_question_list.getValueAt(i,2))){
                    tbl_question_list.setValueAt(tbl_question_list.getValueAt(i,2).toString() + " YANLIŞ", i,2);
                    wrong++;
                }else {
                    correct++;
                }
            }
            int point = (correct * 10);
            lbl_ouiz_result.setForeground(Color.BLUE);
            lbl_ouiz_result.setText(" Toplam puanınız : " + point);
            if (!QuizSolved.add(this.content.getId(), this.student.getId(), point)){
                Helper.showMsg("error");
            }
        });
        // ## QUIZ BUTTON
    }
    public void loadCommentModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_comment_list.getModel();
        clearModel.setRowCount(0);

        for (ContentOfContent obj : ContentOfContent.getList(this.content.getId(), "comment")){
            int i = 0;
            row_comment_list[i++] = obj.getStudent().getName();
            row_comment_list[i++] = obj.getContentofcontent();
            mdl_comment_list.addRow(row_comment_list); // tablomuza ekleriz
        }
    }
    public void loadPointLabel(){
        lbl_content_point.setText(null);
        int total = 0;
        int count = 0;
        for (ContentOfContent obj : ContentOfContent.getList(this.content.getId(), "point")){
            total += Integer.parseInt(obj.getContentofcontent());
            count++;
        }
        if (count == 0){
            lbl_content_point.setText("Content Point : 0");
        }else {
            lbl_content_point.setText("Content Point : " + String.valueOf(total/count));
        }
    }
    private void loadQuestionModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_question_list.getModel();
        clearModel.setRowCount(0);
        for (Question obj : Question.getList(this.content.getId())){ //getList ArrayList<Content> donduruyor
            int i = 0;
            row_question_list[i++] = obj.getId();
            row_question_list[i++] = obj.getTitle();
            row_question_list[i] = "";
            mdl_question_list.addRow(row_question_list); // tablomuza ekleriz
        }
    }
    private void solvedQuiz(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_question_list.getModel();
        clearModel.setRowCount(0);
        for (Question obj : Question.getList(this.content.getId())){ //getList ArrayList<Content> donduruyor
            int i = 0;
            row_question_list[i++] = obj.getId();
            row_question_list[i++] = obj.getTitle();
            row_question_list[i] = obj.getAnswer();
            mdl_question_list.addRow(row_question_list); // tablomuza ekleriz
        }
    }
    private void tableComment(){
        mdl_comment_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Object[] col_comment_list = {"User","Comment"};
        mdl_comment_list.setColumnIdentifiers(col_comment_list); //tablo baslıklarını ekledik
        row_comment_list = new Object[col_comment_list.length];
        tbl_comment_list.setModel(mdl_comment_list);
        tbl_comment_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_comment_list.getTableHeader().setReorderingAllowed(false);
        loadCommentModel();
    }
    private void tableQuestion(){
        if (QuizSolved.getFetch(this.content.getId(),this.student.getId()) == -1){
            mdl_question_list = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column){
                    if (column == 0){
                        return false;
                    }
                    return super.isCellEditable(row,column);
                }
            };
            Object[] col_question_list = {"ID","Question","Answer"};
            mdl_question_list.setColumnIdentifiers(col_question_list); //tablo baslıklarını ekledik
            row_question_list = new Object[col_question_list.length];
            tbl_question_list.setModel(mdl_question_list);
            tbl_question_list.getColumnModel().getColumn(0).setMaxWidth(75);
            tbl_question_list.getTableHeader().setReorderingAllowed(false);
            tbl_question_list.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
            tbl_question_list.getActionMap().put("Enter", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tbl_question_list.getCellEditor().stopCellEditing();
                    tbl_question_list.getSelectionModel().clearSelection(); // Clear selection after editing
                }
            });
            loadQuestionModel();
        }else {
            mdl_question_list = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            Object[] col_question_list = {"ID","Question","Answer"};
            mdl_question_list.setColumnIdentifiers(col_question_list); //tablo baslıklarını ekledik
            row_question_list = new Object[col_question_list.length];
            tbl_question_list.setModel(mdl_question_list);
            tbl_question_list.getColumnModel().getColumn(0).setMaxWidth(75);
            tbl_question_list.getTableHeader().setReorderingAllowed(false);
            solvedQuiz();
            pnl_button.setVisible(false);
            pnl_quiz_result.setVisible(true);
            lbl_ouiz_result.setForeground(Color.BLUE);
            lbl_ouiz_result.setText(" Toplam puanınız : " + QuizSolved.getFetch(this.content.getId(), this.student.getId()));
        }
    }
}
