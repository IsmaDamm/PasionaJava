package Classs;

public class Monstruo extends Character{

    public int Reward;
    public String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String Description;
    public Monstruo(String Name,double Strenght, double Hp,int lvl, int Reward, String Type, String Description){
        this.setName(Name);
        this.setStrenght(Strenght);
        this.setHp(Hp);
        this.setLvl(lvl);
        this.setReward(Reward);
        this.setType(Type);
        this.setDescription(Description);
    }
    public int getReward() {
        return Reward;
    }

    public void setReward(int Reward) {
        this.Reward = Reward;
    }

    @Override
    public double calcul_fuerza() {
        return getStrenght() * Math.random() + 0.6;
    }

    @Override
    public void Stats() {
        System.out.println("Nombre : " + getName());
        System.out.println("Fuerza : " + getStrenght());
        System.out.println("Tipo : " + getType());
        System.out.println("Nivel : " + getLvl());
        System.out.println("Vida : " + getHp());
        System.out.println("Descripcion : " + getDescription());
    }
}
