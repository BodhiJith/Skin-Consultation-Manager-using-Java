import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        WestminsterSkinConsultationManager westminsterSkinConsultationManager = new WestminsterSkinConsultationManager();
        Scanner input = new Scanner(System.in);
        //Create an arraylist for Doctor
        ArrayList<Doctor> doc = new ArrayList<>();
        //Create an arraylist for Consultation
        ArrayList<Consultation> consul = new ArrayList<>();

        //Console a menu
        for (int x = 0; x < 500; x++) {
            System.out.println("_Menu_");
            System.out.println("Choose from here");
            System.out.println("  ");
            System.out.println("A - Add a new Doctor to the menu");
            System.out.println("D - Delete a Doctor from the menu");
            System.out.println("P - Print the Doctor list ");
            System.out.println("S - Save Doctor details in a file ");
            System.out.println("G - Open the GUI");
            System.out.println("X - Exit the menu ");
            System.out.println("  ");

            System.out.println("Choose what you want -> ");
            String selectoption = input.nextLine();
            System.out.println("  ");

            // Select an option from the menu
            switch (selectoption) {
                case "A" -> westminsterSkinConsultationManager.adddoc(doc);
                case "D" -> westminsterSkinConsultationManager.dltdoc(doc);
                case "P" -> westminsterSkinConsultationManager.printdoc(doc);
                case "S" -> westminsterSkinConsultationManager.savedoc(doc);
                case "G" -> westminsterSkinConsultationManager.gui(doc,consul);
                case "X" -> westminsterSkinConsultationManager.exit(doc);
                default -> System.out.println("Invalid Input");
            }
        }
    }}


