public class Main {
    public static void main(String[] args) {
        Fighter f1 = new Fighter("Jeff");
        Fighter f2 = new Fighter("John");
        Ring ring = new Ring(f1, f2, 90, 120);
        ring.run();
    }
}