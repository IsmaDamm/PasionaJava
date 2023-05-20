package Classs;

import java.util.Objects;

public class Board {

    public int tamaño;
    public int Tesoros;
    public int Monstruos;
    public int []human = new int[2];
    public String[][] Map;

    //Constructor donde se crea el tablero
    public Board(int tamaño, int tesoros, int monstruos) {
        this.tamaño = tamaño;
        Tesoros = tesoros;
        Monstruos = monstruos;
        Map = new String[tamaño][tamaño];

        for(int i = 1; i < tamaño; i++){
            for(int j=1; j < tamaño; j++){
                Map[i][j] = "#";
            }
        }
        for(int i = 1; i < tesoros + monstruos + 3; i++){
            int Random = (int) (Math.random()*tamaño);
            int Random2 = (int) (Math.random()*tamaño);
            if (Map[Random][Random2] == "#"){
                if (i == 1){
                    Map[Random][Random2] = "0";
                    human[0] = Random;
                    human[1] = Random2;
                }
                else if (i == 2){
                    Map[Random][Random2] = "S";
                }
                else if (i <= tesoros + 2){
                    Map[Random][Random2] = "T";
                }
                else{
                    Map[Random][Random2] = "M";
                }
            }
            else{
                i--;
            }
        }


    }
    public int getTamaño() {
        return tamaño;
    }
    public int[] getHuman() {
        return human;
    }

    public void setHuman(int[] human) {
        human = human;
    }
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public String[][] getMap() {
        return Map;
    }

    public void setMap(String[][] map) {
        Map = map;
    }

    public int getTesoros() {
        return Tesoros;
    }

    public void setTesoros(int tesoros) {
        Tesoros = tesoros;
    }

    public int getMonstruos() {
        return Monstruos;
    }

    public void setMonstruos(int monstruos) {
        Monstruos = monstruos;
    }
    //Pinta el tablero
    //cambiar tablero que pintas 9 no 10 illo
    public void Paint(){
        String[][] Map2 = new String[tamaño][tamaño];
                   //si tiene la vision podrà ver el mapa entero con la M/S/T
        for(int i = 1; i < tamaño; i++){
            for(int j=1; j < tamaño; j++){
                if (Map[i][j].equalsIgnoreCase("#") || Map[i][j].equalsIgnoreCase("0")){
                    Map2[i][j] = Map[i][j];
                }
                else{
                    Map2[i][j] = "?";
                }
            }
        }

        for(int i = 1; i < tamaño; i++){
            for(int j=1; j < tamaño; j++){
                System.out.print(Map2[i][j] + " ");
            }
            System.out.println();
        }
    }
    public boolean RegenerateWorld(){
        int contador = 1;
        for(int i = 1; i < tamaño; i++){
            for(int j=1; j < tamaño; j++){
                if(Map[i][j].equalsIgnoreCase("#") || Map[i][j].equalsIgnoreCase("0")){
                    contador ++;
                }
            }
        }
        if (contador == (tamaño*tamaño)){
            return true;
        }
        return false;
    }

    //Se encarga del movimiento y enviar las acciones
    //implementar inventario y estats aqui o fuera?

    //Impo implementar tapado de vision
    public int Move(String Movement, Human a) {
        int action = 0;
        if (Movement.equalsIgnoreCase("I")) {
                a.PrintInventory(a);
        } else if (Movement.equalsIgnoreCase("E")) {
                a.Stats();
        } else {
            try {
                if (Movement.equalsIgnoreCase("D")) {
                    if (Map[human[0] + 1][human[1]] != "#") {
                        if (Map[human[0] + 1][human[1]] == "T") {
                            action = 1;
                        } else if (Map[human[0] + 1][human[1]] == "M") {
                            action = 2;
                        } else if (Map[human[0] + 1][human[1]] == "S") {
                            action = 3;
                        }
                    }
                    Map[human[0]][human[1]] = "#";
                    Map[human[0] + 1][human[1]] = "0";
                    human[0] = human[0] + 1;
                } else if (Movement.equalsIgnoreCase("U")) {
                    if (Map[human[0] - 1][human[1]] != "#") {
                        if (Map[human[0] - 1][human[1]] == "T") {
                            action = 1;
                        } else if (Map[human[0] - 1][human[1]] == "M") {
                            action = 2;
                        } else if (Map[human[0] - 1][human[1]] == "S") {
                            action = 3;
                        }
                    }
                    Map[human[0]][human[1]] = "#";
                    Map[human[0] - 1][human[1]] = "0";
                    human[0] = human[0] - 1;
                }
                if (Movement.equalsIgnoreCase("L")) {
                    if (Map[human[0]][human[1] + 1] != "#") {
                        if (Map[human[0]][human[1] + 1] == "T") {
                            action = 1;
                        } else if (Map[human[0]][human[1] + 1] == "M") {
                            action = 2;
                        } else if (Map[human[0]][human[1] + 1] == "S") {
                            action = 3;
                        }
                    }
                    Map[human[0]][human[1]] = "#";
                    Map[human[0]][human[1] + 1] = "0";
                    human[1] = human[1] + 1;
                }
                if (Movement.equalsIgnoreCase("R")) {
                    if (Map[human[0]][human[1] - 1] != "#") {
                        if (Map[human[0]][human[1] - 1] == "T") {
                            action = 1;
                        } else if (Map[human[0]][human[1] - 1] == "M") {
                            action = 2;
                        } else if (Map[human[0]][human[1] - 1] == "S") {
                            action = 3;
                        }
                    }
                    Map[human[0]][human[1]] = "#";
                    Map[human[0]][human[1] - 1] = "0";
                    human[1] = human[1] - 1;
                }
                Paint();
            } catch (Exception e) {
                System.out.println("Movimiento Prohibido, te sales del mapa");
            }
        }
        return action;
    }
}
