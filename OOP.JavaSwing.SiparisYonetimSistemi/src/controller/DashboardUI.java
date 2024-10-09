package controller;

import business.BasketController;
import business.CustomerController;
import business.ProductController;
import core.Config;
import core.Helper;
import core.Item;
import entity.Basket;
import entity.Customer;
import entity.Product;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class DashboardUI extends JFrame {
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable tbl_customer;
    private JPanel pnl_customer_filter;
    private JTextField fld_f_customer_name;
    private JComboBox<Customer.TYPE> cmb_f_customer_type;
    private JButton btn_customer_filter;
    private JButton btn_customer_filter_reset;
    private JButton btn_customer_new;
    private JLabel lbl_f_customer_name;
    private JLabel lbl_f_customer_type;
    private JPanel pnl_product;
    private JScrollPane scrl_product;
    private JTable tbl_product;
    private JPanel pnl_product_filter;
    private JTextField fld_f_product_name;
    private JTextField fld_f_product_code;
    private JComboBox<Item> cmb_f_product_stock;
    private JButton btn_product_filter;
    private JButton btn_product_reset;
    private JButton btn_product_new;
    private JLabel lbl_f_product_name;
    private JLabel lbl_f_product_code;
    private JLabel lbl_f_product_stock;
    private User user;
    private CustomerController customerController;
    private DefaultTableModel tmdl_customer = new DefaultTableModel();
    private JPopupMenu popup_customer = new JPopupMenu();
    private DefaultTableModel tmdl_product = new DefaultTableModel();
    private ProductController productController;
    private JPopupMenu popup_product = new JPopupMenu();
    private BasketController basketController;

    public DashboardUI(User user){
        this.user = user;
        this.customerController = new CustomerController();
        this.productController = new ProductController();
        this.basketController = new BasketController();

        if(user == null){
            Helper.showMsg("error");
            dispose();
        }

        this.add(this.container);
        this.setTitle(Config.PROJECT_TITLE);
        this.setSize(1000, 500);
        this.setLocation(Helper.screenCenterPoint("x",this.getSize()),Helper.screenCenterPoint("y",this.getSize()));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        this.lbl_welcome.setText("Hoşgeldin : " + this.user.getName());

        this.btn_logout.addActionListener(e -> {
            this.dispose();
            LoginUI loginUI = new LoginUI();
        });

        // CUSTOMER TAB
        loadCustomerTable(null);
        loadCustomerPopupMenu();
        loadCustomerButtonEvent();
        this.cmb_f_customer_type.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));
        this.cmb_f_customer_type.setSelectedItem(null);

        // PRODUCT TAB
        loadProductTable(null);
        loadProductPopupMenu();
        loadProductButtonEvent();
        this.cmb_f_product_stock.addItem(new Item(1, "Stokta Var"));
        this.cmb_f_product_stock.addItem(new Item(2, "Stokta Yok"));
        this.cmb_f_product_stock.setSelectedItem(null);
    }

    private void loadProductButtonEvent(){
        this.btn_product_new.addActionListener(e -> {
            ProductUI productUI = new ProductUI(new Product());
            productUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadProductTable(null); // triger la tetikle
                }
            });
        });

        this.btn_product_filter.addActionListener(e -> {
            ArrayList<Product> filteredProduct = this.productController.filter(
                    this.fld_f_product_name.getText(),
                    this.fld_f_product_code.getText(),
                    (Item) this.cmb_f_product_stock.getSelectedItem()
            );

            loadProductTable(filteredProduct);
        });

        this.btn_product_reset.addActionListener(e -> {
            this.fld_f_product_name.setText(null);
            this.fld_f_product_code.setText(null);
            this.cmb_f_product_stock.setSelectedItem(null);
            loadProductTable(null);
        });
    }
    private void loadProductPopupMenu(){
        this.tbl_product.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_product.rowAtPoint(e.getPoint()); // seçili olan row u aldık
                tbl_product.setRowSelectionInterval(selectedRow, selectedRow); // seçili olan row u gösterdik mavi renk oldu
            }
        });

        this.popup_product.add("Sepete Ekle").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(),0).toString());
            Product basketProduct = this.productController.getById(selectedId);
            if (basketProduct.getStock() <= 0){
                Helper.showMsg("Bu ürün stokta yoktur");
            }else{
                Basket basket = new Basket(basketProduct.getId());

                boolean result = false;

                if (this.basketController.save(basket)){
                    Helper.showMsg("done");
                }else{
                    Helper.showMsg("error");
                }
            }
        });
        this.popup_product.add("Güncelle").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(),0).toString());
            ProductUI productUI = new ProductUI(this.productController.getById(selectedId));
            productUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadProductTable(null);
                }
            });
        });

        this.popup_product.add("Sil").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(),0).toString());
            if (Helper.confirm("sure")){
                if (this.productController.delete(selectedId)){
                    Helper.showMsg("done");
                    loadProductTable(null); // tum hepsini alacak sekilde guncelle
                }else {
                    Helper.showMsg("error");
                }
            }
        });


        this.tbl_product.setComponentPopupMenu(this.popup_product);
    }

    private void loadProductTable(ArrayList<Product> products){
        Object[] columnProduct = {"ID", "Ürün Adı", "Ürün Kodu", "Fiyat", "Stock"};

        if(products == null){
            products = this.productController.findAll();
        }

        // Tablo sifirlama -> tekrar tekrar üstüne yazıyordu
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_product.getModel();
        clearModel.setRowCount(0);

        this.tmdl_product.setColumnIdentifiers(columnProduct); // modeli olusturuyoruz daha tabloya koymadik

        for (Product product : products){
            Object[] rowObject = {
                    product.getId(),
                    product.getName(),
                    product.getCode(),
                    product.getPrice(),
                    product.getStock()
            };
            this.tmdl_product.addRow(rowObject); // row ları ekledik
        }

        this.tbl_product.setModel(tmdl_product); // modeli tabloya aktardik
        this.tbl_product.getTableHeader().setReorderingAllowed(false); // tablonun sütunlarını kaydıramazsın
        this.tbl_product.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_product.setEnabled(false); // duzenlenebilirligi kaldirdik
    }

    private void loadCustomerButtonEvent(){
        this.btn_customer_new.addActionListener(e -> {
            CustomerUI customerUI = new CustomerUI(new Customer());
            customerUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCustomerTable(null);
                }
            });
        });

        this.btn_customer_filter.addActionListener(e -> {
            ArrayList<Customer> filteredCustomer = this.customerController.filter(
                    this.fld_f_customer_name.getText(),
                    (Customer.TYPE) this.cmb_f_customer_type.getSelectedItem()
            );

            loadCustomerTable(filteredCustomer);
        });

        this.btn_customer_filter_reset.addActionListener(e -> {
            loadCustomerTable(null);
            this.fld_f_customer_name.setText(null);
            this.cmb_f_customer_type.setSelectedItem(null);
        });
    }

    private void loadCustomerPopupMenu(){
        this.tbl_customer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_customer.rowAtPoint(e.getPoint()); // seçili olan row u aldık
                tbl_customer.setRowSelectionInterval(selectedRow, selectedRow); // seçili olan row u gösterdik mavi renk oldu
            }
        });

        this.popup_customer.add("Güncelle").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_customer.getValueAt(this.tbl_customer.getSelectedRow(), 0).toString()); // seçilen rowun id sini aldık
            CustomerUI customerUI = new CustomerUI(this.customerController.getById(selectedId));
            customerUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCustomerTable(null);
                }
            });
        });
        this.popup_customer.add("Sil").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_customer.getValueAt(this.tbl_customer.getSelectedRow(), 0).toString()); // seçilen rowun id sini aldık
            if (Helper.confirm("sure")){
                if (this.customerController.delete(selectedId)){
                    Helper.showMsg("done");
                    loadCustomerTable(null); // tum hepsini alacak sekilde guncelle
                }else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_customer.setComponentPopupMenu(this.popup_customer);
    }

    private void loadCustomerTable(ArrayList<Customer> customers){
        Object[] columnCustomer = {"ID", "Müşteri Adı", "Tipi", "Telefon", "Mail", "Adres",};

        if(customers == null){
            customers = this.customerController.findAll();
        }

        // Tablo sifirlama -> tekrar tekrar üstüne yazıyordu
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_customer.getModel();
        clearModel.setRowCount(0);

        this.tmdl_customer.setColumnIdentifiers(columnCustomer); // modeli olusturuyoruz daha tabloya koymadik

        for (Customer customer : customers){
            Object[] rowObject = {
                    customer.getId(),
                    customer.getName(),
                    customer.getType(),
                    customer.getPhone(),
                    customer.getMail(),
                    customer.getAddress()
            };
            this.tmdl_customer.addRow(rowObject); // row ları ekledik
        }

        this.tbl_customer.setModel(tmdl_customer); // modeli tabloya aktardik
        this.tbl_customer.getTableHeader().setReorderingAllowed(false); // tablonun sütunlarını kaydıramazsın
        this.tbl_customer.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_customer.setEnabled(false); // duzenlenebilirligi kaldirdik
    }
}
