package ch.th;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketMachine ticketMachine = new TicketMachine();
        ticketMachine.reset();
        boolean check = false;
        int choice;
        while (!check){
           try {
               System.out.println("+----------------------------+");
               System.out.println("|       Ticket Automat       |");
               System.out.println("+----------------------+-----+");
               System.out.println("|  Zone                |  1  |");
               System.out.println("|  Ticket Class        |  2  |");
               System.out.println("|  Pay                 |  3  |");
               System.out.println("|  Close               |  0  |");
               System.out.println("+----------------------+-----+");
               System.out.println("Chose the action: ");

               choice = scanner.nextInt();

               switch (choice){
                   case 1: ticketMachine.zoneIDSetter(); System.out.println(ticketMachine); break;
                   case 2: ticketMachine.ticketSetter(); System.out.println(ticketMachine); break;
                   case 3: if(ticketMachine.pay()){
                       System.out.println(ticketMachine);
                       ticketMachine.reset();
                   }
                       System.out.println(ticketMachine);
                       break;
                   case 0: check = true; break;
                   default: System.out.println("Inserted wrong Value"); break;
               }
           } catch (InputMismatchException e){
               System.out.println("Invalid input. Please enter a valid number.");
               scanner.next();
           }
        }

        System.out.println(ticketMachine);

    }
}
