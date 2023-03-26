package it.module;

import it.module.DAO.CoffeesRepository;
import it.module.DAO.SellsRepository;
import it.module.DAO.interfaces.CoffeesDAO;
import it.module.DAO.interfaces.SellsDAO;
import it.module.entities.Coffee;
import it.module.entities.Sell;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import java.util.Scanner;



import static java.lang.Math.round;

public class Main {
    private static final String DB_NAME = "coffeebreak";
    private static final String USER_NAME = "javauser";
    private static final String PASSWORD = "stresa1965";

    public static void main(String[] args) {
        SellsDAO sellsDAO;
        CoffeesDAO coffeesDAO;
        try {

            sellsDAO = new SellsRepository(DB_NAME, USER_NAME, PASSWORD);
            coffeesDAO = new CoffeesRepository(DB_NAME, USER_NAME, PASSWORD);
            /*Coffee coffee = new Coffee("Lavazza", 1.5);
            coffeesDAO.insert(coffee)
            Sell sell = new Sell("2023-2-2", 120,1);
            sellsDAO.insert(sell);*/
            manageSells(coffeesDAO, sellsDAO);



        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Vendor code: " + e.getErrorCode());
            for (StackTraceElement ste : e.getStackTrace()) {
                System.err.println("at " + ste.toString());
            }
        }
    }
    public static void manageSells(CoffeesDAO coffeesDAO, SellsDAO sellsDAO) throws SQLException {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        LocalDate today = LocalDate.now();


        do {
            System.out.println("Cosa vuoi fare ?");
            System.out.println("[1] - Inserisci un nuovo tipo di caffè");
            System.out.println("[2] - Inserisci una nuova vendita");
            System.out.println("[3] - Stampare tutte le tipologie di caffè con relative vendite");
            System.out.println("[4] - Stampare tutte le tipologie di caffè con relative vendite maggiori di 100 pezzi");
            System.out.println("[5] - Cambiare il nome a una tipologia di caffè");
            System.out.println("[6] - Eliminare una vendita");
            System.out.println("[7] - Eliminare una tipologia di caffè e tutte le vendite associate (non finito)");
            System.out.println("[0] - Esci");

            System.out.print("Risposta: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Come vuoi che si chiami:");
                    String coffeeName = input.nextLine();
                    System.out.println("imposta il prezzo:");
                    int coffeePrice = input.nextInt();
                    Coffee coffee = new Coffee(coffeeName, coffeePrice);
                    coffeesDAO.insert(coffee);
                    break;
                case 2:
                    List<Coffee> coffees = coffeesDAO.getAll();
                    for(Coffee i:coffees){
                        System.out.println(i.toString());
                    }
                    System.out.println("Che caffè(id) di quelli sopra elencati vorresti aggiungere come vendita:");
                    int coffeeId = input.nextInt();
                    System.out.println("Inserisci la quantità che vorresti vendere:");
                    int sellsQuantity = input.nextInt();
                    Sell sell = new Sell(today.toString(), sellsQuantity, coffeeId);
                    sellsDAO.insert(sell);
                    break;
               case 3:
                    printCoffeeAndSell(coffeesDAO, sellsDAO);
                    break;
                case 4:
                    printCoffeeAndSellOver100(coffeesDAO, sellsDAO);
                    break;
                case 5:


                    System.out.println("Che nome gli vorresti dare:");
                    String coffeeName2 = input.nextLine();
                    List<Coffee> coffees2 = coffeesDAO.getAll();
                    for(Coffee i:coffees2){
                        System.out.println(i.toString());
                    }
                    System.out.println("A quale di questi caffè(id) vorresti aggiornare il nome:");
                    int coffeeId2 = input.nextInt();
                    coffeesDAO.update( coffeeName2, coffeeId2);
                    break;
                case 6:
                    int j = 0;
                    List<Sell> sells = sellsDAO.getAll();
                    for(Sell i:sells){
                        j ++;
                        System.out.println(i.toString()+ "\tseleziona " + j +" per rimuovere questa");


                    }
                    System.out.println("Quale di queste vendite vorresti rimuovere:");
                    int choice2 = input.nextInt();
                    sellsDAO.delete(choice2);
                    break;
                case 7:
                    exit = true; /*non finito il caso 7 per mancanza di tempo*/
                    break;


                case 0:
                    exit = true;
                    break;
                default:
                    System.err.println("Opzione non valida");

            }

        } while (!exit);


    }

    public static void printCoffeeAndSell(CoffeesDAO coffeesDAO, SellsDAO sellsDAO) throws SQLException {


        List<Coffee> coffees = coffeesDAO.getAll();


        for (Coffee coffee : coffees) {
            System.out.println("------------------");
            System.out.println(coffee);
            List<Sell> sells = sellsDAO.getByIdCoffee(coffee.getId());



            if (!sells.isEmpty()) {
                for (Sell sell : sells) {
                    System.out.println("\t  " + "Vendite:");
                    System.out.println("\t  \t \t" + sell.getQuantity() +" pezzi \t "+  round(sell.getQuantity()* coffee.getPrice()) +" Euro");
                }
            } else {
                System.out.println("\t [Nessun vendita disponibile]");
            }
            System.out.println("  ");

        }
    }
    public static void printCoffeeAndSellOver100(CoffeesDAO coffesDAO, SellsDAO sellsDAO) throws SQLException {


        List<Coffee> coffees = coffesDAO.getAll();
        System.out.println("------------------");
        System.out.println("vendite 100 pezzi o superiore");

        for (Coffee coffee : coffees) {

            List<Sell> sells = sellsDAO.getAllOver100(coffee.getId());

            long ricavo = 0;


            if (!sells.isEmpty()) {
                for (Sell sell : sells) {

                    ricavo = ricavo + round(sell.getQuantity() * coffee.getPrice());
                    System.out.println("\t   \t" + sell.getDateSell() + "   " + coffee.getName() + "   " + sell.getQuantity() + " Pezzi \t" + round(sell.getQuantity() * coffee.getPrice()) + " Euro");

                }
            } else {
                System.out.println("\t [Nessun vendita disponibile con quantita maggiore a 100]");
            }
            System.out.println("Ricavo totale: " + ricavo +" Euro");

        }
    }
}