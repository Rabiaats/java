import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class PatikaStore {
    private static ArrayList<User> users = new ArrayList<>();
    private static TreeSet<Brand> brands =  new TreeSet<>(new Comparator<Brand>() {
        @Override
        public int compare(Brand b1, Brand b2) {
            return b1.getName().compareTo(b2.getName());
        }
    });// they are listed in alphabetical order
    private static ArrayList<Product> products = new ArrayList<>();
    private static int brandId = 1;
    private static int mobilePhoneId = 1;
    private static int notebookId = 1;

    static {
        Brand samsung = new Brand("Samsung");
        MobilePhone sP1 = new MobilePhone("Samsung Galaxy A51", "Black", 3199.0, 6.5,  6, 128, 32,4000);
        MobilePhone sP2 = new MobilePhone("Samsung Galaxy A52", "Black", 3199.0, 6.5,  6, 128, 32,4000);
        Notebook sN1 = new Notebook("Samsung Notebook 11", 5980.99,13.1,  8,512);
        samsung.produceProduct(sP1);
        samsung.produceProduct(sP2);
        samsung.produceProduct(sN1);

        Brand lenovo = new Brand("Lenovo");
        MobilePhone lP1 = new MobilePhone("Lenovo 56", "Black", 2199.0, 8.5,  4, 64, 16,3000);
        Notebook lN1 = new Notebook("Lenovo Notebook L1", 4980.99,14.1,  4,1024);
        lenovo.produceProduct(lP1);
        lenovo.produceProduct(lN1);

        Brand apple = new Brand("Apple");
        MobilePhone aP1 = new MobilePhone("iphone X 128 GB", "Pink", 12199.0, 5.5,  6, 128, 32,5000);
        Notebook aN1 = new Notebook("iPad 9.Nesil 64 GB", 15980.99,10.1,  8,64);
        apple.produceProduct(aP1);
        apple.produceProduct(aN1);

        Brand huawei = new Brand("Huawei");
        MobilePhone hP1 = new MobilePhone("Huawei P60 Pro 64 GB", "Black", 4199.0, 6.5, 6, 64, 16,3000);
        Notebook hN1 = new Notebook("Huawei Matepad 11.5", 9980.99,14.5,  8,64);
        huawei.produceProduct(hP1);
        huawei.produceProduct(hN1);

        Brand casper = new Brand("Casper");
        MobilePhone cP1 = new MobilePhone("Casper Via A40", "Red", 2199.0, 6.5,  4, 64, 32,1000);
        Notebook cN1 = new Notebook("Casper Nirvana C500", 8880.99,17.0,  8,1024);
        casper.produceProduct(cP1);
        casper.produceProduct(cN1);

        Brand asus = new Brand("Asus");
        MobilePhone asP1 = new MobilePhone("Asus Zenfone 5", "Blue", 3199.0, 6.5, 4, 128, 16,3046);
        Notebook asN1 = new Notebook("Asus Vivobook 15", 5680.99,15.0,  8,512);
        asus.produceProduct(asP1);
        asus.produceProduct(asN1);

        Brand hp = new Brand("Hp");
        MobilePhone hpP1 = new MobilePhone("HP Smart Phone", "Blue", 4199.0, 4.5, 6, 512, 8,4000);
        Notebook hpN1 = new Notebook("HP Victus 15-FA1723NT", 9880.99,15.5, 8,512);
        hp.produceProduct(hpP1);
        hp.produceProduct(hpN1);

        Brand xiaomi = new Brand("Xiaomi");
        MobilePhone xP1 = new MobilePhone("Xiaomi Redmi 12", "White", 2199.0, 4.0, 8, 128, 8,3056);
        Notebook xN1 = new Notebook("Xiaomi Pad", 2180.99,14.0, 8,2048);
        xiaomi.produceProduct(xP1);
        xiaomi.produceProduct(xN1);

        Brand monster = new Brand("Monster");
        MobilePhone mP1 = new MobilePhone("Monster Armor 13", "White", 3199.0, 5.5, 8, 128, 16,4000);
        Notebook mN1 = new Notebook("Monster Semruk S7", 13380.99,17.0, 8,1024);
        monster.produceProduct(mP1);
        monster.produceProduct(mN1);

        brands.add(samsung);
        brands.add(lenovo);
        brands.add(apple);
        brands.add(huawei);
        brands.add(casper);
        brands.add(asus);
        brands.add(hp);
        brands.add(xiaomi);
        brands.add(monster);
        addProduct(samsung.getName(), 1, sP1);
        addProduct(samsung.getName(), 1, sP2);
        addProduct(monster.getName(),1, mP1);
        addProduct(apple.getName(), 1,aP1);
        addProduct(xiaomi.getName(), 1, xN1);
        addProduct(hp.getName(), 1,hpN1);
        System.out.println("Some products and some brands was added in the Patika Store for start.");
        System.out.println("Starting the system..." + "\n.".repeat(3));
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(User user) {
        users.add(user);
    }
    public static ArrayList<Product> getProducts() {
        return products;
    }
    public static void setProducts(ArrayList<Product> products) {
        products = products;
    }
    public static TreeSet<Brand> getBrands() {
        return brands;
    }

    public static void setBrands(Brand brand) {
        brands.add(brand);
    }

    public static int getBrandId() {
        return brandId;
    }

    public static void addBrand(String brandName){
        for (Brand brand : brands) {
            if (brand.getName().equalsIgnoreCase(brandName)) {
                System.out.println(brandName + " brand is already available on Patika Store!!");
                return;
            }
        }
        brands.add(new Brand(brandName));
        brands.last().setId(brandId++);
        System.out.println(brandName + " brand has been registered in the Patika Store.");
    }
    public static void removeBrand(String brandName){
        for (Brand brand : brands){
            if (brand.getName().equalsIgnoreCase(brandName)){
                brands.remove(brand);
                System.out.println(brandName + " brand has been removed from Patika Store.");
                for (int i = 0; i < products.size(); i++){
                    if (products.get(i).getBrand().getName().equalsIgnoreCase(brandName)){
                        products.remove(products.get(i));
                        i--;
                    }
                }
                System.out.println(brandName + "'s product has been removed from Patika Store.");
                return;
            }
        }
        System.out.println(brandName + " brand is not already available on Patika Store!!");
    }
    private static  <T extends Product> boolean isProduct(String brandName, int stock, T product){
        boolean available = false;
        for (Product p : products){
            if (p.getName().equalsIgnoreCase(product.getName()) &&
                    p.getBrand().getName().equalsIgnoreCase(brandName) &&
                    p.getRam() == product.getRam() &&
                    p.getScreenSize() == product.getScreenSize() &&
                    p.getStorage() == product.getStorage()){
                if (product.getClass().getName().equals("MobilePhone") &&
                        p.getColour().equalsIgnoreCase(product.getColour()) &&
                        p.getBatteryPower() == product.getBatteryPower() &&
                        p.getCamera() == product.getCamera()){
                    available = true;
                }
                available = true;
                if (available){
                    p.setStock(p.getStock() + stock);
                    System.out.println(p.getName() + " is already available on Patika Store." +
                            p.getName() + "'s stock has been increased by " + stock +
                            ".\n" + p.getName() + "'s stock: " + p.getStock());
                    return true;
                }
            }
        }
        for (Brand brand : brands){
            if (brand.getName().equalsIgnoreCase(brandName)){
                if (product.getClass().getName().equals("MobilePhone")){
                    for (Product p : brand.getPhones()){
                        if (p.getName().equals(product.getName()) &&
                                p.getRam() == product.getRam() &&
                                p.getScreenSize() == product.getScreenSize() &&
                                p.getStorage() == product.getStorage() &&
                                p.getColour().equalsIgnoreCase(product.getColour()) &&
                                p.getBatteryPower() == product.getBatteryPower() &&
                                p.getCamera() == product.getCamera()){
                            product.setBrand(brand);
                            product.setId(mobilePhoneId++);
                            product.setStock(stock);
                            return false;
                        }
                    }
                }else if (product.getClass().getName().equals("Notebook")){
                    for (Product p : brand.getNotebooks()){
                        if (p.getName().equals(product.getName()) &&
                                p.getRam() == product.getRam() &&
                                p.getScreenSize() == product.getScreenSize() &&
                                p.getStorage() == product.getStorage()){
                            product.setId(notebookId++);
                            product.setBrand(brand);
                            product.setStock(stock);
                            return false;
                        }
                    }
                }
                System.out.println("There is not " + product.getName() +
                        " in the " + brandName + " brand!!");
            }
        }
        System.out.println(brandName + " brand is not already available on Patika Store");
        return true;
    }
    public static <T extends Product> void addProduct(String brandName, int stock, T product){// i letter added to determine product type // i=1 mobile i=2 notebook
        if (!isProduct(brandName, stock, product)){
            products.add(product);
            System.out.println(product.getName() +
                    " that is available on " +
                    brandName +
                    " brand has been registered in the Patika Store.");
        }
    }
    public static <T extends Product> void removeProduct(String brandName, T product) {
        if (!isProduct(brandName,1, product)) {
            products.remove(product);
            System.out.println(product.getName() +
                    "that is available on " +
                    brandName +
                    " brand has been removed in the Patika Store.");
        }
    }
    public static void removeProduct(int id){
        for (Product p: products){
            if (p.getId() == id){
                products.remove(p);
                System.out.println(p.getName() +
                        "that is available on " +
                        p.getBrand().getName() +
                        " brand has been removed in the Patika Store.");
                return;
            }
        }
        System.out.println("There is not product that has " + id +
                " in the Patika Store!!");
    }
    public static void listBrand(){
        System.out.println("Brands List\n");
        for (Brand brand: brands){
            System.out.println("- " + brand.getName());
        }
    }
    public static ArrayList<Product> listProduct(String className, int id){
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : products){
            if (p.getClass().getName().equalsIgnoreCase(className) && p.getId() == id){
                list.add(p);
            }
        }
        return list;
    }
    public static ArrayList<Product> listProduct(String className, String brandName){
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : products){
            if (p.getClass().getName().equals(className) && p.getBrand().getName().equalsIgnoreCase(brandName) ) {
                list.add(p);
            }
        }
        return list;
    }
    public static ArrayList<Product> listProduct(String className){
        ArrayList<Product> list = new ArrayList<>();
        for (Product p : products){
            if (p.getClass().getName().equals(className)) {
                list.add(p);
            }
        }
        return list;
    }

    public static void printfNotebook(ArrayList<Product> notebooks){
        System.out.println("NOTEBOOK LİST\n\n" + "-".repeat(102));

        System.out.printf("|  %-2s  | %-30s | %-10s | %-7s | %-7s | %-10s | %-3s | %-5s |", "ID",
                "PRODUCT NAME", "PRİCE", "BRAND", "STORAGE", "SCREEN SIZE", "RAM" , "STOCK");
        System.out.println("\n" + "-".repeat(102));

        for (Product p : notebooks){
            System.out.printf("|  %-2s  | %-30s | %-10.1f | %-7s | %-7d | %-10.1f  | %-3d | %-5d |",
                    p.getId(), p.getName(), p.getPrice(), p.getBrand().getName(),
                    p.getStorage(), p.getScreenSize(), p.getRam(), p.getStock());
            System.out.println();
        }

        System.out.println("-".repeat(102));
        System.out.println("\nThe notebooks are listed.");
    }
    public static void printfMobilePhone(ArrayList<Product> mobilePhones){
        System.out.println("MOBİLE PHONE LİST\n\n" + "-".repeat(136));

        System.out.printf("|  %-2s  | %-30s | %-10s | %-7s | %-7s | %-10s | %-3s | %-6s | %-13s | %-6s | %-5s |",
                "ID", "PRODUCT NAME", "PRİCE", "BRAND", "STORAGE", "SCREEN SIZE",
                "RAM", "CAMERA", "BATTERY POWER", "COLOUR", "STOCK");
        System.out.println("\n" + "-".repeat(136));

        for (Product p : mobilePhones){
            System.out.printf("|  %-2s  | %-30s | %-10.1f | %-7s | %-7d | %-10.1f  | %-3d | %-6d | %-13d | %-6s | %-5d |",
                    p.getId(), p.getName(), p.getPrice(), p.getBrand().getName(), p.getStorage(),
                    p.getScreenSize(), p.getRam(), p.getCamera() ,p.getBatteryPower() , p.getColour(), p.getStock());
            System.out.println();
        }

        System.out.println("-".repeat(136));
        System.out.println("\nThe mobile phones are listed.");
    }
}