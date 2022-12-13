package Application;

import java.text.ParseException;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) throws ParseException {

        System.out.println("Hello!");
        System.out.println("\nWith this application, you can access the database and" +
                " access the tables from them.\n" +
                "\nThese tables are: \n1- Department: Store all department datas " +
                "like their names and ids." +
                "\n2- Seller: Store all seller datas like their names, salaries, birthdates " +
                "and their departments.");

        mainMenu();
    }

    public static void mainMenu() throws ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nChoose option: \n\n1- Department\n2- Seller\n\n0- Exit");

        System.out.print("\nOption: ");
        int option = sc.nextInt();

        switch(option){
            case 1:
                clearScreen();
                DepartmentMenu.menu();
            case 2:
                clearScreen();
                SellerMenu.menu();
            case 0:
                System.exit(0);
        }

        while(option > 2 || option < 0){
            clearScreen();
            System.out.println("Wrong option!");
            System.out.println("\nChoose option:\n\n1- Department\n2- Seller\n\n0- Exit");
            System.out.print("\nOption: ");
            option = sc.nextInt();
        }

        sc.close();
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}