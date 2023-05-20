package Classs;

public abstract class Character {
    private double Hp;
    private double  Strenght;
    private int  lvl;
    private String Name;
    public int getLvl() {
        return lvl;
    }
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public double getHp() {
        return Hp;
    }
    public void setHp(double hp) {
        Hp = hp;
    }
    public double getStrenght() {
        return Strenght;
    }
    public void setStrenght(double strenght) {
        Strenght = strenght;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    abstract public double calcul_fuerza();
    abstract public void Stats();
}
