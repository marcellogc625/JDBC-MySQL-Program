package Application;

import Model.DAO.DAOFactory;
import Model.DAO.DepartmentDAO;
import Model.Entities.Department;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class DepartmentMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final DepartmentDAO departmentDAO = DAOFactory.createDepartmentDAO();

    public static void menu() throws ParseException {
        System.out.println("--------- || Department menu || ------------");
        System.out.println("\n1- Find by...\n2- Insert new department\n3- Update department" +
                "\n4- Delete department\n\n0- Return to main menu");
        System.out.print("\nSelect option: ");
        Integer option = sc.nextInt();
        subMenu(option);
        sc.close();
    }

    public static void subMenu(Integer option) throws ParseException {
        String name;
        int id;
        List <Department> departmentList = departmentDAO.findAll();

        Department dep;

        switch (option) {
            case 1:
                clearScreen();
                System.out.println("Select criteria:\n1- By Id\n2- By Name\n3- Find all" +
                        "\n\n0- Return to main menu.");
                System.out.print("\nOption: ");
                option = sc.nextInt();
                //Switch-case for define search criteria//
                switch (option) {
                    //Search by department id//
                    case 1:
                        clearScreen();
                        System.out.print("\nInsert the id: ");
                        id = sc.nextInt();
                        dep = departmentDAO.findById(id);
                        System.out.println("Search result:\n" + dep);
                        anotherUse();
                        break;
                    //Search by department name//
                    case 2:
                        clearScreen();
                        System.out.print("\nInsert department name: ");
                        name = sc.next();
                        dep = departmentDAO.findByDepartment(name);
                        System.out.println("Search result:\n" + dep);
                        anotherUse();
                        break;
                    //Search all departments//
                    case 3:
                        clearScreen();
                        System.out.println("Search results:\n");
                        for (Department depart : departmentList) {
                            System.out.println(depart);
                        }
                        anotherUse();
                        break;
                    //Return to main menu//
                    case 0:
                        clearScreen();
                        menu();
                        break;
                }
                while (option > 3 || option < 0) {
                    clearScreen();
                    System.out.println("Wrong option.\nSelect criteria:\n1- Id\n2- Name\n3- Find all" +
                            "\n\n0- Return to main menu.");
                    System.out.print("\nOption: ");
                    option = sc.nextInt();
                }
            case 2:
                clearScreen();
                System.out.println("Insert new department:\n");
                System.out.print("\nInsert department name: ");
                name = sc.next();
                Department newDep = new Department(null, name);
                departmentDAO.insert(newDep);
                anotherUse();
                break;
            case 3:
                clearScreen();
                System.out.println("Update existing department:\n");
                for (Department depart : departmentList) {
                    System.out.println(depart);
                }
                System.out.print("\nInsert id for update: ");
                id = sc.nextInt();
                dep = departmentDAO.findById(id);
                System.out.print("\nInsert new department name: ");
                name = sc.next();
                dep.setName(name);
                departmentDAO.update(dep);
                anotherUse();
                break;
            case 4:
                clearScreen();
                System.out.println("Delete department\n\nSelect criteria:\n1- By Id\n2- By Name " +
                        "\n\n0- Return to main menu.");
                System.out.print("\nOption: ");
                option = sc.nextInt();
                //Switch-case for define delete criteria//
                switch (option) {
                    //Delete by department id//
                    case 1:
                        clearScreen();
                        System.out.println("Departments:\n");
                        for (Department depart : departmentList) {
                            System.out.println(depart);
                        }
                        System.out.print("\nEnter department id for delete: ");
                        id = sc.nextInt();
                        departmentDAO.deleteById(id);
                        anotherUse();
                        break;
                    //Delete by department name//
                    case 2:
                        clearScreen();
                        System.out.println("Departments:\n");
                        for (Department depart : departmentList) {
                            System.out.println(depart);
                        }
                        System.out.print("\nEnter department name for delete: ");
                        name = sc.next();
                        departmentDAO.deleteByName(name);
                        anotherUse();
                        break;
                    //Return to main menu//
                    case 0:
                        clearScreen();
                        menu();
                        break;

                }
                while (option > 2 || option < 0) {
                    clearScreen();
                    System.out.println("Wrong option.\nSelect criteria:\n1- Id\n2- Name\n3- Find all" +
                            "\n\n0- Return to main menu.");
                    System.out.print("\nOption: ");
                    option = sc.nextInt();
                }
                break;

            case 0:
                clearScreen();
                MainProgram.mainMenu();
                break;
        }

        while (option > 4 || option < 0) {
            System.out.println("--------- || Department menu || ------------");
            System.out.println("\n1- Find by...\n2- Insert new department\n3- Update department" +
                    "\n4- Delete department");
            System.out.print("\nSelect option: ");
            option = sc.nextInt();
            subMenu(option);
        }
        sc.close();
    }

    public static void anotherUse() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWant to use again this program (Y/N)?");
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