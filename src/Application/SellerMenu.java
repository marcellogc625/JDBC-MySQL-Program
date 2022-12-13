package Application;

import Model.DAO.DAOFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SellerMenu {

    private static final Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    private static final SellerDAO sellerDAO = DAOFactory.createSellerDAO();

    private static final Department dep = new Department();

    public static void menu() throws ParseException {
        System.out.println("--------- || Seller menu || ------------");
        System.out.println("\n1- Find by...\n2- Insert new seller\n3- Update seller data" +
                "\n4- Delete seller data\n\n0- Return to main menu");
        System.out.print("\nSelect option: ");
        Integer option = sc.nextInt();
        subMenu(option);
        sc.close();
    }

    public static void subMenu(Integer option) throws ParseException {
        Seller seller1;

        int id;
        String name;
        String email;
        Date birthDate;
        double baseSalary;
        int depId;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List <Seller> sellerList = DAOFactory.createSellerDAO().findAll();

        switch(option){
            case 1:
                clearScreen();
                System.out.println("Select criteria:\n1- By Seller id\n2- By name\n3- By email" +
                        "\n4- By department id\n5- Find all sellers" +
                        "\n\n0- Return to main menu");
                System.out.print("\nOption: ");
                option = sc.nextInt();
                //Switch-case for define search criteria//
                switch(option){
                    //Search by seller id//
                    case 1:
                        clearScreen();
                        System.out.print("\nInsert the seller id: ");
                        id = sc.nextInt();
                        seller1 = sellerDAO.findById(id);
                        System.out.println("Search result:\n" + seller1);
                        anotherUse();
                        break;
                    //Search by seller name//
                    case 2:
                        clearScreen();
                        System.out.print("\nInsert the seller name: ");
                        name = sc.next();
                        seller1 = sellerDAO.findByName(name);
                        System.out.println("Search result:\n" + seller1);
                        anotherUse();
                        break;
                    //Search by seller email//
                    case 3:
                        clearScreen();
                        System.out.print("\nInsert the seller email: ");
                        email = sc.next();
                        seller1 = sellerDAO.findByEmail(email);
                        System.out.println("Search result:\n" + seller1);
                        anotherUse();
                        break;
                    //Search by department id//
                    case 4:
                        clearScreen();
                        System.out.print("\nInsert the department id: ");
                        id = sc.nextInt();
                        dep.setId(id);
                        sellerDAO.findByDepartment(dep);
                        for (Seller seller : sellerList) {
                            System.out.println(seller);
                        }
                        anotherUse();
                        break;
                    //Search all sellers//
                    case 5:
                        clearScreen();
                        System.out.println("Search results:\n");
                        for (Seller sellers : sellerList) {
                            System.out.println(sellers);
                        }
                        anotherUse();
                        break;
                    //Return to main menu//
                    case 0:
                        clearScreen();
                        menu();
                        break;
                }
                while(option > 6 || option < 0){
                    clearScreen();
                    System.out.println("Wrong option.Select criteria:\n1- By Seller id" +
                            "\n2- By name\n3- By email\n4- By birth date\n5- By salary" +
                            "\n6- By department id\n\n0- Return to main menu\n");
                    System.out.print("\nOption: ");
                    option = sc.nextInt();
                }
            case 2:
                clearScreen();
                System.out.println("Insert new seller:");
                sc.nextLine();
                System.out.print("Name:");
                name = sc.nextLine();
                System.out.print("Email:");
                email = sc.nextLine();
                System.out.print("Birth date (DD/MM/YYYY): ");
                birthDate = sdf.parse(sc.next());
                System.out.print("Base salary: ");
                baseSalary = sc.nextDouble();
                System.out.print("Department id: ");
                depId = sc.nextInt();
                seller1 = new Seller(null, name, email, birthDate, baseSalary,
                        new Department(depId, null));
                sellerDAO.insert(seller1);
                anotherUse();
                break;
            case 3:
                clearScreen();
                System.out.print("Insert the seller id to be updated: ");
                id = sc.nextInt();
                seller1 = sellerDAO.findById(id);

                sc.nextLine();
                System.out.print("Name:");
                name = sc.nextLine();
                System.out.print("Email:");
                email = sc.nextLine();
                System.out.print("Salary: ");
                baseSalary = sc.nextDouble();
                System.out.print("Birth date (DD/MM/YYYY): ");
                birthDate = sdf.parse(sc.next());
                System.out.print("Department id: ");
                depId = sc.nextInt();

                seller1.setName(name);
                seller1.setEmail(email);
                seller1.setBaseSalary(baseSalary);
                seller1.setBirthDate(birthDate);
                seller1.setDepartment(new Department(depId, null));

                sellerDAO.update(seller1);
                anotherUse();
                break;
            case 4:
                clearScreen();
                System.out.print("Insert the seller id for delete: ");
                id = sc.nextInt();
                sellerDAO.deleteById(id);
                anotherUse();
                break;
            case 0:
                clearScreen();
                MainProgram.mainMenu();
                break;
        }
        while (option > 4 || option < 0) {
            System.out.println("--------- || Seller menu || ------------");
            System.out.println("\n1- Find by...\n2- Insert new seller\n3- Update seller data" +
                    "\n4- Delete seller data\n\n0- Return to main menu");
            System.out.print("\nSelect option: ");
            option = sc.nextInt();
            subMenu(option);
        }
        sc.close();
    }

    public static void anotherUse() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWant to try another operation (Y/N)?");
        char tryUse = sc.nextLine().toUpperCase().charAt(0);
        if(tryUse == 'Y'){
            clearScreen();
            menu();
        }
        else if (tryUse == 'N'){
            System.exit(0);
        }
        while(tryUse != 'Y' && tryUse != 'N'){
            System.out.println("\nWrong option. Choose between 'Y' (Yes) or 'N' (No).");
            System.out.print("Option: ");
            tryUse = sc.next().toUpperCase().charAt(0);
        }
        sc.close();
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}