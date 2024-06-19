import java.util.Scanner;

public class Screen {
    private static Scanner scan = new Scanner(System.in);
    private static String choice;
    PatikaStore patikaStore;
    public Screen(){
        this.patikaStore = new PatikaStore();
    }
    public void start(){
        do {
            System.out.println("-".repeat(20) + " ENTRANCE " + "-".repeat(20));

            System.out.print("""
                    
                    Welcome to the Patika Store.
                    
                    1)Sign Up
                    2)Log In
                    3)Exit The Patika Store
                    Enter number:\s""");
            choice = scan.nextLine();
            switch (choice){
                case "1":
                    signUpInterface();
                    break;
                case "2":
                    logInInterface();
                    break;
                case "3":
                    System.out.println("You have logged out to the Product Panel.\nGoodbye.");
                    return;
                default:
                    System.out.println("You entered incorrectly! Again enter.");
            }

        }while (!choice.equals("3"));
    }
    public void signUpInterface(){
        System.out.print("\n" + "-".repeat(20) + " SING UP " +  "-".repeat(21) +
                "\n\nHello, new user" +
                "\nEnter your name: ");
        String name = scan.nextLine();
        this.patikaStore.setUsers(new User(name));
        System.out.println("\n" + name + " have registered in the Patika Store." +
                "\nYou are being redirected to the login.");
        System.out.println();
        logInInterface();
    }
    public void logInInterface(){
        System.out.println("-".repeat(20) + " LOGIN " +  "-".repeat(23));
        System.out.println();

        int wrong = 3;

        if (this.patikaStore.getUsers().isEmpty()){
            System.out.println("There are no users in the system\n");
            return;
        }

        do{
            System.out.println("! Log in to continue !");
            System.out.print("Enter user name: ");
            String name = scan.nextLine();

            for (int i = 0; i < this.patikaStore.getUsers().size(); i++){
                if (this.patikaStore.getUsers().get(i).getName().equalsIgnoreCase(name)){
                    loggedInterface(i); // user information
                    return;
                }
            }

            wrong --;

            if (wrong == 0){
                System.out.println("You have entered too many incorrectly." +
                        "\nYou will return to the login screen\n");
                return;
            }

            System.out.println("There is no user by that name in the system." +
                    "\nİf you enter incorrectly " + wrong +
                    " more times, you will be returned to the login screen.\n");

        }while (wrong > 0);
    }
    public void loggedInterface(int index){
        do {
            System.out.println("\n" + "-".repeat(20) + " PRODUCT PANEL " + "-".repeat(15));
            System.out.println();

            System.out.println(this.patikaStore.getUsers().get(index).getName() +
                    " have logged in to the Product Panel.\n" +
                    "\nWhat do you want to do?");
            System.out.print("""
                            1)Add A Brand
                            2)Remove A Brand
                            3)Add A Product
                            4)Remove A Product
                            5)List
                            6)Return To The Start Screen
                            
                            Enter number:\s""");
            choice = scan.nextLine();
            switch (choice){
                case "1":
                    System.out.print("\nEnter the name of the brand you want to add: ");
                    this.patikaStore.addBrand(scan.nextLine());
                    break;
                case "2":
                    System.out.print("\nEnter the name of the brand you want to remove: ");
                    this.patikaStore.removeBrand(scan.nextLine());
                    break;
                case "3":
                    selectProduct("add");
                    break;
                case "4":
                    selectProduct("remove");
                    break;
                case "5":
                    this.list();
                    break;
                case "6":
                    System.out.println("\n" + this.patikaStore.getUsers().get(index).getName() +
                            " have logged out to the Product Panel.\nGoodbye.:)\n");
                    return;
                default:
                    System.out.println("\nYou entered incorrectly! Again enter.");
            }

        }while (!choice.equals("6"));
        return;
    }
    public void selectProduct(String select) {
        do {
            System.out.println("\n" + "-".repeat(20) + " SELECT PRODUCT " + "-".repeat(14));

            System.out.print("""
                    
                    Which product will you select?
                    1)Mobile Phone
                    2)Notebook
                    3)Exit
                    
                    Enter number:\s""");
            choice = scan.nextLine();
            String brandName;
            switch (choice) {
                case "1", "2":// products with features similar to mobile phone and notebook ,new cases can be added/removed
                    if (select.equals("add")){
                        System.out.println("\n" + "-".repeat(20) + " ADD PRODUCT " + "-".repeat(17));
                        System.out.print("\nEnter the Brand Name: ");
                        brandName = scan.nextLine();
                        System.out.print("\nEnter product stock: ");
                        int stock = Integer.parseInt(scan.nextLine());
                        this.patikaStore.addProduct(brandName, stock, informationProduct(Integer.parseInt(choice)));
                    }else {
                        System.out.println("\n" + "-".repeat(20) + " REMOVE PRODUCT " + "-".repeat(14));
                        System.out.print("""
                                
                                According to which feature you will find the product that you will remove?
                                1)Id
                                2)Brand Name
                                
                                Select:\s""");
                        String entry = scan.nextLine();
                        if (entry.equals("1")){
                            System.out.print("\nEnter id: ");
                            entry = scan.nextLine();
                            this.patikaStore.removeProduct(Integer.parseInt(entry));
                        }else {
                            System.out.print("\nEnter the Brand Name: ");
                            brandName = scan.nextLine();
                            this.patikaStore.removeProduct(brandName, informationProduct(Integer.parseInt(choice)));
                        }
                    }
                    return;
                case "3":
                    System.out.println("\nYou have left the add product section.");
                    return;
                default:
                    System.out.println("\nEnter only from the given numbers!");
            }

        } while (!choice.equals("3"));
    }
    public <T extends Product> Product informationProduct(int i){
        System.out.println("\n" + "-".repeat(20) + " ENTER PRODUCT INFORMATION " + "-".repeat(3));

        System.out.print(choice.equals("1") ? "\nEnter the Mobile Phone Name: " : "\nEnter the Notebook Name: ");
        String productName = scan.nextLine();
        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scan.nextLine());
        System.out.print("Enter product screen size: ");
        double screenSize = Double.parseDouble(scan.nextLine());
        System.out.print("Enter product ram: ");
        int ram = Integer.parseInt(scan.nextLine());
        System.out.print("Enter product storage: ");
        int storage = Integer.parseInt(scan.nextLine());

        if (choice.equals("1")) {
            System.out.print("Enter product camera: ");
            int camera = Integer.parseInt(scan.nextLine());
            System.out.print("Enter product battery power: ");
            int batteryPower = Integer.parseInt(scan.nextLine());
            System.out.print("Enter product colour: ");
            String colour = scan.nextLine();
            return new MobilePhone(productName, colour, price, screenSize, ram, storage, camera, batteryPower);
        }else {
            return new Notebook(productName, price, screenSize, ram, storage);
        }
    }
    public void list(){
        do {
            System.out.println("\n" + "-".repeat(20) + " LIST " + "-".repeat(24));

            System.out.print("""
                    
                    Which one will you list?
                    
                    1)Product
                    2)Brand
                    3)Exit list
                    
                    Enter number:\s""");
            choice = scan.nextLine();
            switch (choice){
                case "1":

                    if (this.patikaStore.getProducts().isEmpty()){
                        System.out.println("\nThere is not product in the Patika Store");
                    }else {
                        System.out.print("""
                                
                                Which type of product will you list?
                                
                                1)Mobile Phone
                                2)Notebook
                                
                                Select:\s""");
                        choice = scan.nextLine();
                        System.out.print("""
                                
                                According to which feature you will list the product?
                                
                                1)List By Id
                                2)List By Brand Name
                                3)List Them All
                                
                                Select:\s""");
                        String entry = scan.nextLine();
                        if (choice.equals("1")) {
                            choice = "MobilePhone";
                        } else if (choice.equals("2")) {
                            choice = "Notebook";
                        }
                        if (entry.equals("1")){
                            System.out.print("\nEnter id: ");
                            entry = scan.nextLine();
                            System.out.println();
                            this.patikaStore.printfMobilePhone(this.patikaStore.listProduct(choice, Integer.parseInt(entry)));
                        } else if (entry.equals("2")){
                            System.out.print("\nEnter brandName: ");
                            entry = scan.nextLine();
                            System.out.println();
                            this.patikaStore.printfNotebook(this.patikaStore.listProduct(choice, entry));
                        } else if (entry.equals("3")) {
                            if (choice.equals("MobilePhone")) {
                                this.patikaStore.printfMobilePhone(this.patikaStore.listProduct(choice));
                            } else if (choice.equals("Notebook")) {
                                this.patikaStore.printfNotebook(this.patikaStore.listProduct(choice));
                            }
                        }
                    }

                    break;
                case "2":
                    System.out.println();
                    this.patikaStore.listBrand();
                    break;
                case "3":
                    System.out.println();
                    return;
                default:
                    System.out.println("\nEnter only from the given numbers!");
            }

        }while(choice.equals("3"));
    }

}