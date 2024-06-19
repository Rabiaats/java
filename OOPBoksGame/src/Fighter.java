public class Fighter {
    String name;
    double damage;
    double health;
    double weight;
    double dodge;
    double startChange;
    public Fighter(String name){
        this.name = name;
        this.health = (Math.random() * 51) + 150;
        this.weight = (Math.random() * 51) + 90;
        this.dodge = (Math.random() * 50) + 1;
    }
    public double hit(Fighter foe){
        System.out.println("----------");
        System.out.println(this.name + " caused " + this.damage  + " damages to " + foe.name);
        if (foe.isDodge()){
            System.out.println(foe.name + " has averted the damage from");
            return foe.health;
        }
        if (foe.health - this.damage < 0){
            return 0;
        }
        return foe.health -this.damage;
    }
    public boolean isDodge(){
        double randomValue = (Math.random() * 50) + 1;
        return randomValue <= this.dodge;
    }
}
