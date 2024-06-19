import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Brand {
    private static Scanner scan = new Scanner(System.in);
    private ArrayList<MobilePhone> phones;
    private ArrayList<Notebook> notebooks;
    private String name;
    private int id;
    public Brand(String name) {
        this.phones = new ArrayList<>();
        this.notebooks = new ArrayList<>();
        this.name = name;
    }
    public Brand(){}
    public ArrayList<MobilePhone> getPhones() {
        return phones;
    }
    public <T extends Product> void produceProduct(T product){
        product.setBrand(this);
        if(product.getClass().getName().equals("MobilePhone")){
            this.phones.add((MobilePhone) product);
        } else if (product.getClass().getName().equals("Notebook")) {
            this.notebooks.add((Notebook) product);
        }
    }

    public ArrayList<Notebook> getNotebooks() {
        return notebooks;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}