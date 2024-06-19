import java.util.ArrayList;

public abstract class Product {
    private Brand brand;
    private String name;
    private String colour;
    private double price;
    private double screenSize;
    private int id;
    private int stock;
    private int ram;
    private int storage;
    private int camera;
    private int batteryPower;

    public Product(String name, String colour, double price, double screenSize, int ram, int storage, int camera, int batteryPower) {
        this.name = name;
        this.colour = colour;
        this.price = price;
        this.screenSize = screenSize;
        this.stock = 0;
        this.ram = ram;
        this.storage = storage;
        this.camera = camera;
        this.batteryPower = batteryPower;
        this.brand = new Brand();
    }

    public Product(String name, double price, double screenSize, int ram, int storage) {
        this.name = name;
        this.price = price;
        this.screenSize = screenSize;
        this.stock = 0;
        this.ram = ram;
        this.storage = storage;
        this.brand = new Brand();
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }

    public int getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(int batteryPower) {
        this.batteryPower = batteryPower;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}