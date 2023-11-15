import java.io.FileReader;
import java.util.Scanner;

import src.BST;
import src.Software;

/**
 * MainApp
 */
public class MainApp {
    static BST tree = new BST();
    public static void main(String[] args)  {

        try(Scanner sc = new Scanner(new FileReader("Software.txt"))){
            
            while(sc.hasNextLine()){
                
                String name = sc.nextLine();
                System.out.println(name);
                double version = Double.parseDouble(sc.nextLine());
                System.out.println(version);
                int quantity = Integer.parseInt(sc.nextLine());
                System.out.println(quantity);
                double price = Double.parseDouble(sc.nextLine());
                System.out.println(price);
                
                Software sf = new Software(name, version, quantity, price);
                System.out.println(sf);
                tree.insert(sf);
            }
            
        } catch(Exception e){
            System.err.println(e);
        }
        

        try(Scanner sc = new Scanner(System.in)){
            
            int input;
            while (true) {
                DisplayMenu();
                
                try{
                    System.out.print("input: ");
                    input = sc.nextInt();
                    MenuChoices(input);
                } catch(Exception e){
                    System.err.println(e);
                }
                
            }
        } catch(Exception e){
            System.err.println(e);
        }
    }

    public static boolean MenuChoices(int x){
        System.out.println("x");
        switch (x) {
            case 1:
                tree.breadthFirst();
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return false;
            default:
                return true;
        }
    }

    public static void DisplayMenu(){
        System.out.printf("\t%s\n","Software Store");
        System.out.println("------------------------------------");
        System.out.println("1. Show Softwares");
        System.out.println("2. Add Software");
        System.out.println("3. Sell Software");
        System.out.println("4. Exit");
    }
}
