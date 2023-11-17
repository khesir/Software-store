import java.io.File;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

import src.BST;
import src.Software;

/**
 * MainApp
 * Author : Aj R. Tollo
 * Course: BS Computer Science
 */
public class MainApp {
    static BST tree = new BST();
    public static void main(String[] args)  {
        try(Scanner sc = new Scanner(System.in)){
            GenerateData(new FileReader("Software.txt"));
            while (true) {
                DisplayMenu();
                try{
                    System.out.print("Input: ");
                    int input = Integer.valueOf(sc.nextLine().trim());
                    boolean hasExited = MenuChoices(input, sc);
                    if(!hasExited){
                        break;
                    }
                } catch(Exception e){
                    System.err.println("Invalid Input");
                }     
            }
        } catch(Exception e){
            System.err.println(e);
        }
    }

    public static boolean MenuChoices(int x, Scanner sc){
        String name;
        String version;
        int quantity;
        int price;
        Software sf;
        try{
            switch (x) {
            case 1:
                System.out.println("Show Softwares: ");
                System.out.printf("%-30s%-10s%-10s%-10s\n", "Name", "Version", "Quantity", "Price");
                System.out.println("----------------------------------------------------------");
                tree.breadthFirst();
                System.out.println("----------------------------------------------------------");
                return true;
            case 2:
                System.out.println("Add Software: ");
                System.out.print("Name: ");
                name = sc.nextLine().trim();
                System.out.print("Version: ");
                version = sc.nextLine().trim();
                System.out.print("Quantity: ");
                quantity = Integer.valueOf(sc.nextLine().trim());
                System.out.print("Price: ");
                price = Integer.valueOf(sc.nextLine().trim());

                System.out.println("----------------------------------------------------------");
                sf = tree.search(new Software(name, version, quantity, price));
                if (sf == null){
                    sf = new Software(name, version, quantity, price);
                    tree.insert(sf);
                    System.out.println("Software "+ sf.getName() + " has been added to the tree");
                }else{
                    try{
                        
                        sf.setQuantity(sf.getQuantity() + quantity);
                        tree.deleteByCopying(sf);
                        System.out.println(sf);
                        tree.insert(sf);
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
                System.out.println("----------------------------------------------------------");
                return true;
            case 3:
                System.out.println("Sell Software: ");
                System.out.print("Name: ");
                name = sc.nextLine().trim();
                System.out.print("Version: ");
                version = sc.nextLine().trim();
                System.out.print("Quantity: ");
                quantity = Integer.valueOf(sc.nextLine().trim());
                System.out.print("Price: ");
                price = Integer.valueOf(sc.nextLine().trim());

                System.out.println("----------------------------------------------------------");
                sf = tree.search(new Software(name, version, quantity, price));
                if (sf == null){
                    System.out.println("No Specified Software Found");
                }else{
                    try{
                        sf.setQuantity(sf.getQuantity() - quantity);
                        tree.deleteByCopying(sf);
                        tree.insert(sf);
                        System.out.println("Software "+ sf.getName() +" data has been updated");
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
                System.out.println("----------------------------------------------------------");
                return true;
            case 4:
                tree.printData();
                return false;
            default:
                return true;
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return true;
    }
    public static void GenerateData(FileReader fr){
        try(Scanner sc = new Scanner(fr)){
            
            while(sc.hasNextLine()){
                
                String name = "", version = "";
                int quantity = 0, price = 0;

                try{
                    name = sc.nextLine().trim();
                    version = sc.nextLine().trim();
                    quantity = Integer.parseInt(sc.nextLine().trim());
                    price = Integer.parseInt(sc.nextLine().trim());
                    
                } catch(NoSuchElementException e){
                    // This error will occur when version is missing
                    // eg.
                    /* 
                     * Mac OS <- name
                     * 12     <- quantity
                     * 2032   <- price
                    */

                    price = Integer.valueOf(quantity);
                    quantity = Integer.valueOf(version);
                    version = "-";
                } catch(Exception e){
                    System.err.println("Exception: " + e);
                }

                if(quantity == 0){
                    continue;
                }
                else{    
                    Software sf = new Software(name, version, quantity, price);
                    tree.insert(sf);
                }
            }
            
        } catch(Exception e){
            
            System.err.println(e);
        }
    }
    public static void DisplayMenu(){
        System.out.printf("%-60s\n","Software Store");
        System.out.println("----------------------------------------------------------");
        System.out.println("1. Show Softwares");
        System.out.println("2. Add Software");
        System.out.println("3. Sell Software");
        System.out.println("4. Exit");
    }
}
