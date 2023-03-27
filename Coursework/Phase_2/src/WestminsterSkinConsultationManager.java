import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;


public class WestminsterSkinConsultationManager implements SkinConsultationManager {


    @Override
    //Add a new doctor method
    public ArrayList<Doctor> adddoc(ArrayList<Doctor> doc) {
        Scanner input = new Scanner(System.in);

        //Try catch statement for error handling
        try {

            // Maximum 10 doctors
            for (int x = 0; x < 10; x++) {

                Doctor doct = new Doctor();

                //Enter doctor first name
                System.out.println("First name of the doctor");
                String fname = input.nextLine();
                doct.setFname(fname);

                //Enter doctor surname
                System.out.println("Last name of the doctor");
                String lname = input.nextLine();
                doct.setLname(lname);

                //Enter doctor license number
                System.out.println("License Number of the doctor");
                String liceno = input.nextLine();
                doct.setLiceno(liceno);

                //Enter doctor phone number
                System.out.println("Phone Number of the doctor");
                String phoneno = input.nextLine();
                doct.setPhoneno(Integer.parseInt(phoneno));

                //Enter doctor specialization
                System.out.println("Doctor Specialisation");
                String spec = input.nextLine();
                doct.setSpec(spec);

                doc.add(doct);
                System.out.println(doc);

                //Continuation of the add doctor
                System.out.println(" 1 - Continue , 2 - Exit ");
                int exit = Integer.parseInt(input.nextLine());
                if (exit == 2) {
                    break;
                }
            }
        } catch (Exception e){
            System.out.println("Incorrect Input");
        }

        return doc;
    }

    @Override
    //Delete a doctor method
    public ArrayList<Doctor> dltdoc(ArrayList<Doctor> doc) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the License number of the doctor that want to delete - ");

        try {

            String delID = input.nextLine();

            Doctor doct = null;
            for (Doctor doctor : doc) {
                if (Objects.equals(doctor.getLiceno(), delID)) {
                    doct = doctor;
                    break;
                }
            }
            //Checking the license number
            if (doct == null) {
                System.out.println("Incorrect License number");
            } else {
                doc.remove(doct);
                System.out.println("Licence number " + doct.getLiceno() + " with name " + doct.getFname() + " " + doct.getLname() + " has been deleted");
                //Calculate the remaining number of doctors
                System.out.println(doc.size() + " doctors are in the center now");
            }
        } catch (Exception e){
            System.out.println("Incorrect Input");
        }
        return doc;
    }

    @Override
    //Print the doctor list method
    public ArrayList<Doctor> printdoc(ArrayList<Doctor> doc) {
        try{

            //Sort doctors alphabetically
            //Reference https://www.infoworld.com/article/3323403/java-challengers-5-sorting-with-comparable-and-comparator-in-java.html
            doc.sort(new Comparator<Doctor>() {
                @Override
                public int compare(Doctor d1, Doctor d2) {
                    return d1.getFname().compareTo(d2.getLname());
                }
            });
            for (Doctor doct : doc) {
                System.out.println("Doctor name: " + doct.getFname() + " " + doct.getLname());
                System.out.println("Medical license number: " + doct.getLiceno());
                System.out.println("Specialization: " + doct.getSpec());
                System.out.println();

            }
        } catch (Exception e){
            System.out.println("Incorrect Input");
        }
        return doc;
    }

    @Override
    //Save doctor method
    public ArrayList<Doctor> savedoc(ArrayList<Doctor> doc) throws FileNotFoundException {
    //throws FileNotFoundException for error handling
        //File path
        File docinfo = new File("C:DoctorsInformation.txt");
        PrintStream print = new PrintStream(docinfo);

        for (Doctor doct : doc) {
            print.println("Full Name of the Doctor - " + doct.getFname() + " " + doct.getLname());
            print.println("License Number of the Doctor - " + doct.getLiceno());
            print.println("Specialization of the Doctor - " + doct.getSpec());
            print.println("  ");

        }

        System.out.println("Doctor information saved");
        print.close();


        return doc;
    }

    @Override
    //Exit doctor menu
    public ArrayList<Doctor> exit(ArrayList<Doctor> doc) {
        System.exit(0);
        return doc;
    }
}