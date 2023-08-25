package ch.th;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketMachine {
    private double balance;
    private double total;
    private int zoneID;
    private boolean halfTicket;
    private int ticketClass;
    private double zonePrice;
    private int firstClassPrice;
    private int secondClassPrice;

    List<Double> coins = new ArrayList<>();
    Scanner scannerIn = new Scanner(System.in);

    TicketMachine(){
        this.balance = 0;
        this.total = 0;
        this.halfTicket = false;
        this.ticketClass = 2;
        this.zoneID = 0;

        this.firstClassPrice = 2;
        this.secondClassPrice = 1;
        this.zonePrice = 3;

        this.coins = new ArrayList<>(List.of(0.10,0.20,0.50,1.00,2.00,5.00));
    }
    public void reset() {
        balance = 0;
        zoneID = 0;
        halfTicket = false;
        ticketClass = 0;
    }

    public void insertCoin(double value) {
        if(coins.contains(value)){
            balance = balance + value;
        } else {
            System.out.printf("Not accepted coin... %.2f throwed back",value);
        }

    }

    public void zoneIDSetter(){
        System.out.println("Write the Zone Number (1000-9999): ");
        int postID = scannerIn.nextInt();
        int confirm = 0;
        boolean check = false;
        while(confirm != 1){
            while(!check){
                if(postID >= 1000 && postID <= 9999 ){
                    System.out.printf("You selected the zone: %d \n", postID);
                    this.zoneID = postID;
                    check = true;
                } else if(postID != 1) {
                    System.out.println("Wrong value inserted!");
                    System.out.println("Write the Zone Number (1000-9999): ");
                    postID = scannerIn.nextInt();
                } else if(postID == 1){
                    System.out.println("Write the New Zone Number (1000-9999): ");
                    postID = scannerIn.nextInt();
                }
            }
            System.out.println("Is the zone Right?\nYes - 1\nNo - 2");
            confirm = scannerIn.nextInt();
            if(confirm == 2){
                postID = 1;
                check = false;
            }
        }

    }

    public void ticketSetter() {
        boolean checkTicket = false;
        boolean checkHalfTicket = false;
        int confirmTicketClass = 2;
        while (!checkTicket) {
            System.out.println("+----------------------------+");
            System.out.println("|     Select your ticket     |");
            System.out.println("+----------------------+-----+");
            System.out.println("|  First Class         |  1  |");
            System.out.println("|  Second Class        |  2  |");
            System.out.println("+----------------------+-----+");
            System.out.println("Your Ticker: ");
            int ticketClass = scannerIn.nextInt();

            switch (ticketClass) {
                case 1:
                    System.out.println("You chose the First Class");
                    this.ticketClass = 1;
                    checkTicket = true;
                    break;
                case 2:
                    System.out.println("You chose the Second Class");
                    this.ticketClass = 2;
                    checkTicket = true;
                    break;
                default:
                    System.out.println("Wrong value inserted!");
                    break;
            }
        }

        while (!checkHalfTicket){

            System.out.println("+----------------------------+");
            System.out.println("|         HalfTicket         |");
            System.out.println("+----------------------+-----+");
            System.out.println("|  Yes                 |  1  |");
            System.out.println("|  No                  |  2  |");
            System.out.println("+----------------------+-----+");
            int ticketClass = scannerIn.nextInt();

            switch (ticketClass) {
                case 1:
                    System.out.println("You chose Half ticket");
                    this.halfTicket = true;
                    checkHalfTicket = true;
                    break;
                case 2:
                    System.out.println("You chose the Normal ticket");
                    this.halfTicket = false;
                    checkHalfTicket = true;
                    break;

                default:
                    System.out.println("Wrong value inserted!");
                    break;
            }
        }



    }

    private boolean payValidation(){
        if(zoneID == 0){
            System.out.println("You need to chose a Zone");
            return false;
        }
        return true;
    }

    public boolean pay(){
        boolean verify = payValidation();
        if(verify){
            int calcZone = zoneID/1000;
            double calcValue;//return true;
            if(ticketClass == 1){
                calcValue = calcZone * firstClassPrice * zonePrice;
            } else {
                calcValue = calcZone * secondClassPrice * zonePrice;
            }
            if(halfTicket){
                calcValue = calcValue/2;
                this.total = calcValue;
                System.out.printf("You need to pay: %.2f\n", calcValue);
                //return true;
            } else {
                this.total = calcValue;
                System.out.printf("You need to pay: %.2f\n", calcValue);
            }

            while(balance != total){
                System.out.println("Insert a coin: ");
                double insertedCoin = scannerIn.nextDouble();
                insertCoin(insertedCoin);
                System.out.printf("Balance: %.2f\n", balance);
            }
            return true;
        }
        return false;


        //return false;
    }

    @Override
    public String toString() {
        return "TicketMachine{" +
                "balance=" + balance +
                ", total=" + total +
                ", zoneID=" + zoneID +
                ", halfTicket=" + halfTicket +
                ", ticketClass=" + ticketClass +
                '}';
    }
}
