package Classs;

import java.util.ArrayList;
import java.util.Scanner;

public class Human extends Character{

    public int money;
    public ArrayList<Shop> Inventory;


    public ArrayList<Shop> getInventory() {
        return Inventory;
    }

    public void setInventory(ArrayList<Shop> inventory) {
        Inventory = inventory;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Human(String Name, int Strenght, int Hp, int lvl, int money, ArrayList<Shop> inventory){
    this.setName(Name);
    this.setStrenght(Strenght);
    this.setHp(Hp);
    this.setLvl(lvl);
    this.setMoney(money);
    Inventory = inventory;
    }
    @Override
    public double calcul_fuerza() {
        return getStrenght() * Math.random()*1 + 0.5;
    }

    @Override
    public void Stats() {
        System.out.println("Nombre : " + getName());
        System.out.println("Fuerza : " + getStrenght());
        System.out.println("Dinero : " + getMoney());
        System.out.println("Nivel : " + getLvl());
        System.out.println("Vida : " + getHp());
    }
    public void PrintInventory(Human humano){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Este es tu inventario");
        System.out.println("------------------------------------------------------");
        getInventory().forEach((objeto)->{
            System.out.println("Nombre = " + objeto.getNombre() + ", Descripcion = " + objeto.getDescripcion());
        });
        System.out.println("------------------------------------------------------");
        if (Inventory.toArray().length == 0){
            System.out.println("No tienes objetos disponibles");
        }
        else {
            System.out.println("Quieres usar un objeto (s/n)");
            String pregunta = entrada.next();
            if (pregunta.equalsIgnoreCase("s")) {
                System.out.println("Selecciona el numero del objeto");
                int num = entrada.nextInt();
                if (num < Inventory.size()) {
                    //metodo de efecto, aplicar pocion, campo de vision ...
                    if (Inventory.get(num).getNombre().equalsIgnoreCase("Pocion de vida")) {
                        System.out.println("Se ha usado " + Inventory.get(num).getNombre());
                        humano.setHp(getHp() + 10);
                        Inventory.remove(num);
                    }
                    //si no es un arma -->
                }
            }
        }
    }
}
