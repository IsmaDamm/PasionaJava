import Classs.Board;
import Classs.Human;
import Classs.Monstruo;
import Classs.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //Viva el spanglish <3
    public static void main(String[] args)
    {
        Scanner entrada = new Scanner(System.in);
        System.out.println();
        System.out.println("Jugadoooor, Bienvenido a mi juego, te ves capaz de superar mis retos y seguir adelante?, HOHOHO");
        System.out.println("Comencemos!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Human MainCharacter = CreateHuman();
        MainCharacter.Stats();
        System.out.println("Bueno estas listo pa empezar?");
        entrada.next();

        Board Mapa = CreateBoard();

        boolean evento = false;
        int TipoEvento;
        Mapa.Paint();

        while(!evento){
            if (Mapa.RegenerateWorld()){
                System.out.println("Felicidades, acabaste este mundo");
                System.out.println("Muy facil no?, ahora juguemos de verdad hasta el infinito");
                System.out.println("MundosInfinitos Desbloqueados, nuevos monstruos apareceran");
                Mapa = CreateBoard();
            }
            System.out.println("A donde quieres moverte?(U,D,L,R),(I,E)");
            TipoEvento = Mapa.Move(entrada.next(), MainCharacter);
            if (TipoEvento == 1){
                EncuentroTesoro(MainCharacter);
            }
            if (TipoEvento == 2){
                Fight(MainCharacter);
                Mapa.Paint();
            }
            if (TipoEvento == 3){
                Shopping(MainCharacter);
                Mapa.Paint();
            }
        }
    }

    public static Board CreateBoard(){
        //hacer Los scanners
        int tamaño = 10;
        int tesoros = 6;
        int monstruos = 3;
        Board a = new Board(tamaño, tesoros, monstruos);
        return a;
    }

    public static Human CreateHuman(){
        Scanner entrada = new Scanner(System.in);
        String Name;
        int Money;
        int Strenght;
        System.out.println("Que tal Manito, soy Dios responde estas preguntas para transformarte en un personaje");
        System.out.println("Como te llamas?");
        Name = entrada.next();
        System.out.println("Lo siento de verdad, ya me joderia tener un nombre tan feo");
        System.out.println("Pisas el gimnasio mucho hermano o eres mas de casita? del 1 al 10 di quan gymbro eres");
        Strenght = Integer.parseInt(entrada.next());
        if (Strenght > 5){
            System.out.println("Que loco hermano, estas mamadisimo, una pena que no puedas entrenar esa cara");
        }
        else{
            System.out.println("Tronco, no tienes nada bueno, nombre de mierda, gordito y feo");
        }
        System.out.println("Ahora hablando de lo serio, tus padres manejan verde del bueno o vives debajo de un puente");
        System.out.println("Cuanta pasta tienes? (1-10)");
        Money = Integer.parseInt(entrada.next());
        if (Money > 5 || Money < 0){
            Money = 5;
            System.out.println("Esa cifra no te la crees ni tu, baja de esa nube " + Name);
        }
        else{
            System.out.println("De la clase media tirando pa pobre, la que te espera chavalin");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Shop> lista = new ArrayList<Shop>();

        Human a = new Human(Name, Strenght,15,1,Money, lista);

        System.out.println("Bueno, los he visto de peores, tus estadisticas son decentes :");
        return a;
    }
    public static void EncuentroTesoro(Human a) {
        int probabilidad;
        int monedas = (int) (Math.random() * 10) + 1;
        String Arma = "Ninguna";
        String Pocion = "Ninguna";
        probabilidad = (int) (Math.random() * 10) + 1;

        if (probabilidad >= 8){

            Arma = "Arco"; // random armas

        }else if (probabilidad >= 5){
            Shop potiVida = new Shop(10, "Pocion de Vida", "Elixir magico curativo, aumenta tu salud en 10");
            Pocion = "Pocion de Vida";
            /*
            ArrayList <Shop>lista = a.getInventory();
            lista.add(potiVida);
            a.setInventory(lista);
            */
            a.getInventory().add(potiVida);
        }

        System.out.println("Has encontrado un tesoro, dentro has encontrado");
        System.out.println("Armas : " + Arma); // clase armas?
        System.out.println("Pociones : " + Pocion ); // Inventario
        System.out.println("Monedas : " + monedas);
        a.setMoney(a.getMoney() + monedas);
    }

    public static void Fight(Human a){
        Scanner entrada = new Scanner(System.in);
        Monstruo Monster = PickRandomMonster();
        double vida = Monster.getHp();
        System.out.println("Te has encontrado con " + Monster.getName() + ", preparate para pelear!");
        int opciones;
        boolean finalizar = false;

        do {
            System.out.println("1 -- Pelear");
            System.out.println("2 -- Caracteristicas");
            System.out.println("3 -- Huir");
            System.out.println("4 -- Llamar a dios por ayuda");
            System.out.println("5 -- Suicidarse");
            opciones = Integer.parseInt(entrada.next());
            switch (opciones) {
                case 1: {
                    double reward = Monster.getStrenght() / 2;
                    double reward2 = (vida - (vida / 2))/2;
                    double golpe = a.calcul_fuerza();
                    double golpe2 = Monster.calcul_fuerza();
                    System.out.println("Atacas a " + Monster.getName() + " Con tus puños, le has hecho " + golpe);
                    Monster.setHp(Monster.getHp() - golpe);
                    if (Monster.getHp() <= 0) {
                        System.out.println(a.getName() + " ha ganado la batalla, obtienes " + reward + " puntos de fuerza y " + reward2 + " de vida");
                        a.setHp(a.getHp() + reward2);
                        a.setStrenght(a.getStrenght() + reward);
                        a.setLvl(a.getLvl() + 1);
                        opciones = 5;
                    } else {
                        System.out.println(Monster.getName() + " Tiene una vida restante de " + Monster.getHp());
                        System.out.println(Monster.getName() + " te ataca con una fuerza de " + golpe2);
                        a.setHp(a.getHp() - golpe2);
                        if (a.getHp() <= 0) {
                            System.out.println("DIOS : Vaya paliza te ha dado nene, te dije que no aguantabas, mas gym y menos donuts coleguita");
                            System.out.println(a.getName() + " ha ganado la batalla");
                            opciones = 5;

                        } else if (Monster.getHp() >= 0) {
                            System.out.println("Aguantas el golpe, vida restante: " + a.getHp());
                        }
                    }

                    break;
                }
                case 2: {
                    Monster.Stats();
                    break;
                }
                case 3: {
                    System.out.println("Dios no te deja huir");
                    break;
                }
                case 4: {
                    System.out.println("Te voy a ayudar de una ostia si me vuelves a llamar, calla y no molestes cagón");
                    System.out.println("Pelea como buen mono de feria que eres");
                    System.out.println("DIOS TE HA PROHIBIDO LA HUIDA");
                    break;
                }
            }
        }while(opciones != 5);
    }

    //JSON a objeto meter
    public static Monstruo PickRandomMonster(){
        Monstruo m1 = new Monstruo("Goblo", 4, 8, 1, 1, "Goblin","El Señor Goblo, Padre goblin de 6 hijos al cual le encanta comer novatos");
        Monstruo m2 = new Monstruo("MikeTyson-Zombie", 10, 10, 1, 1, "Zombie","Humano que fue transformado en zombie, vivia de la cafeina y las drogas, Ex-Boxeador professional");
        Monstruo m3 = new Monstruo("JP-B0T", 5, 15, 1, 1, "Humanoide","Humano Maligno que migro a las maquinas para saber como serà el fin del mundo, no le gusta las peleas, prefiere la lectura");
        Monstruo m4 = new Monstruo("Albert-Maligno", 13, 7, 1, 1, "Human","Albert llegado de otra dimension preparado para enseñarte quien manda, amigo de JP-BOT");

        Monstruo [] monsters= {m1,m2,m3,m4};
        int Nmonsters = (int) (Math.random() * monsters.length);

        return monsters[Nmonsters];
    }
    public static Shop[] CreateShopItems(){
        Shop potiVida = new Shop(20, "Pocion de Vida", "Elixir magico curativo, aumenta tu salud en 10");
        Shop potiVision = new Shop(50, "DronVolador", "Dron volador el cual te da la vision de todo el mapa");
        Shop Magnolia = new Shop(60, "Magnolia", "Flor bonita, vendecida por nuestro querido Dios, efecto desconocido");
        Shop MasterBall = new Shop(100, "MasterBall", "Cansado de Pelear contra monstruos mas fuertes que tu, no te preocupes, atrapalos >:)!");

        Shop [] shop= {potiVida,potiVision,Magnolia,MasterBall};

        return shop;
    }
    public static void Shopping(Human MainCharacter){
        Scanner entrada = new Scanner(System.in);
        Shop[] items = CreateShopItems();
        int opcion = 0;
        System.out.println("Que tal nene, soy el comerciante ambulante PaPePiPoPu");

        do {
        System.out.println("Tienes" + MainCharacter.getMoney() + "Monedas, Que deseas Hacer?");
        System.out.println(" 1 - Comprar en la tienda");
        System.out.println(" 2 - Vender");
        System.out.println(" 3 - Salir");
        opcion = entrada.nextInt();

        switch(opcion) {
            case 1:
                System.out.println("Estos son los objetos disponibles con sus precios");
                System.out.println("");
                for(int i = 0; i < items.length; i++){
                    items[i].ShowItems();
                }
                System.out.println("Deseas comprar alguno?(s/n)");
                String pregunta = entrada.next();
                if (pregunta.equalsIgnoreCase("s")){
                    System.out.println("Diga su posicion");
                    int posicion = entrada.nextInt();
                    items[posicion].BuyItem(MainCharacter);
                }
                else{
                    System.out.println("vaya @#%&$ estas hecho, compra o vete.");
                }
                break;
            case 2:
                System.out.println("Vender? a un vendedor como yo? JAJA");
                System.out.println("Que va a venderme un humano que sigue estancado en el mundo 1? JAJAJAJAJAJ, compra y calla.");
                break;
        }

    }while (opcion != 3);
    }


}