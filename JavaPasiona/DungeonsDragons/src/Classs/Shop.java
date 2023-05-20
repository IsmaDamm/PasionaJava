package Classs;

import java.util.ArrayList;

public class Shop {
    public int Precio;
    public String Nombre;
    public String Descripcion;

    public Shop(int precio, String nombre, String descripcion) {
        Precio = precio;
        Nombre = nombre;
        Descripcion = descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public void ShowItems(){
        System.out.println("Nombre: " + getNombre() + "");
        System.out.println("        Precio: " + getPrecio());
        System.out.println("        Descripcion: " + getDescripcion());
    }
    public void BuyItem(Human buyer){
        if (buyer.getMoney() > getPrecio()){
            System.out.println("Has comprado " + getNombre());
            buyer.setMoney(buyer.getMoney() - getPrecio());

            ArrayList <Shop>lista = buyer.getInventory();
            lista.add(this);
            buyer.setInventory(lista);
            System.out.println("Compra realizada correctamente <:), Objeto añadido al inventario");
        }
        else{
            System.out.println("Te falta dinero mi pequeño padawan");
        }

    }

}
