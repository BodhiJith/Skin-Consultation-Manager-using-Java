import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            for (int x = 1; x < 11; x++) {

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
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Incorrect Input");
        }
        return doc;
    }

    @Override
    //Print the doctor list method
    public ArrayList<Doctor> printdoc(ArrayList<Doctor> doc) {
        try {
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
        } catch (Exception e) {
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

    @Override
    //Westminister consultation gui
    public void gui(ArrayList<Doctor> doc, ArrayList<Consultation> consul) {
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        jf.getContentPane();
        //Container c = jf.getContentPane(); c.setBackground(Color.red);

        JLabel main = new JLabel("Westminster Consultation");
        main.setFont(new Font("Algerian", Font.BOLD, 50));
        main.setBounds(300, 100, 1000, 60);
        jp.add(main);


        //Adding the Doctor list button to the gui
        JButton butn = new JButton("Doctors List");
        butn.addActionListener(e ->
        {
            viewdoc(doc);
        });

        butn.setBounds(500, 300, 300, 40);
        jp.setLayout(null);
        jp.add(butn);


        //Adding the Book Consultation button to the gui
        JButton jb1 = new JButton("Book a Consultation");
        jb1.addActionListener(e ->
        {
            bookdoc(doc, consul);
        });

        jb1.setBounds(500, 350, 300, 40);
        jp.setLayout(null);
        jp.add(jb1);


        //Adding the Channel list button to the gui
        JButton jb2 = new JButton("Channel list");
        jb2.addActionListener(e ->
        {
            chanldoc(doc, consul);
        });

        jb2.setBounds(500, 400, 300, 40);
        jp.setLayout(null);
        jp.add(jb2);


        jp.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.add(jp);
        jf.setBounds(200, 100, 1200, 1000);
        jf.setVisible(true);

    }

    @Override
    //See the booked consultation
    public void chanldoc(ArrayList<Doctor> doc, ArrayList<Consultation> consul) {
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        jf.getContentPane();
        jp.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jf.add(jp);
        jf.setBounds(200, 100, 1200, 1200);
        jf.setVisible(true);

        JLabel jl = new JLabel("Type the Name");
        jl.setFont(new Font("Century", Font.PLAIN, 20));
        jl.setBounds(150, 150, 300, 40);
        jp.setLayout(null);
        jp.add(jl);

        JTextField jt = new JTextField();
        jt.setBounds(450, 150, 300, 40);
        jp.add(jt);

        JLabel jl2 = new JLabel("Type the Surname");
        jl2.setFont(new Font("Century", Font.PLAIN, 20));
        jl2.setBounds(150, 250, 300, 40);
        jp.setLayout(null);
        jp.add(jl2);

        JTextField jt1 = new JTextField();
        jt1.setBounds(450, 250, 300, 40);
        jp.add(jt1);

        JButton butn = new JButton("Submit details");
        butn.setBounds(350, 350, 200, 30);
        butn.addActionListener(e ->
                {

                    JFrame tbl = new JFrame();
                    JPanel pnl = new JPanel();
                    tbl.getContentPane();
                    pnl.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                    tbl.add(pnl);
                    tbl.setSize(1500, 1000);
                    tbl.setVisible(true);


                    System.out.println(consul.get(0).getPatientfname());
                    String[] colum = {"Channel Date", "Doctor Name", "Start Time", "End Time", "Fullcost", "Other"};
                    DefaultTableModel table = new DefaultTableModel(colum, 0);
                    JTable tabl = new JTable(table);
                    JScrollPane scroll = new JScrollPane(tabl);
                    tbl.add(scroll);

                    ArrayList<Consultation> con = new ArrayList<>();
                    for (Consultation consult : consul) {
                        if (consult.getPatientfname().equals(jt.getText())) {

                            if (consult.getPatientlname().equals(jt1.getText())) {

                                con.add(consult);
                            } else {
                                //JOptionPane.showMessageDialog(null, "No booked consultations for you");
                            }
                        } else {
                            //JOptionPane.showMessageDialog(null, "No booked consultations for you");
                        }

                    }
                    for (Consultation consult : con) {

                        String conDate = consult.getConDate();
                        String docfname = consult.getDocfname();
                        String stime = consult.getStime();
                        String etime = consult.getEtime();
                        int fullcost = consult.getFullcost();
                        String other = consult.getOther();

                        Object[] info = {conDate, docfname, stime, etime, fullcost, other};

                        table.addRow(info);

                    }
                }
        );
        jp.add(butn);
    }

    @Override
    //Get the patient and consultation booking detatils
    public void bookdoc(ArrayList<Doctor> doc, ArrayList<Consultation> consul) {
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        jf.getContentPane();
        jp.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jf.add(jp);
        jf.setBounds(200, 0, 1200, 1200);
        //jf.setLocation(200,0); jf.setSize(1200, 1200);
        jf.setVisible(true);

        JLabel info = new JLabel("Patient Information");
        info.setFont(new Font("Verdana", Font.PLAIN, 40));
        info.setBounds(200, 30, 600, 40);
        jp.add(info);


        //Patient first name implementation

        JLabel name = new JLabel("First name");
        name.setFont(new Font("Century", Font.PLAIN, 20));
        name.setBounds(100, 80, 200, 30);
        jp.setLayout(null);
        jp.add(name);

        JTextField jt = new JTextField();
        jt.setBounds(350, 80, 200, 30);
        jp.add(jt);


        //Patient last name implementation

        JLabel name1 = new JLabel("Surname");
        name1.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension s3 = name1.getPreferredSize();
        name1.setBounds(100, 140, s3.width, s3.height);
        jp.setLayout(null);
        jp.add(name1);

        JTextField jt1 = new JTextField();
        jt1.setBounds(350, 140, 200, 30);
        jp.add(jt1);

        //Patient date of birth
        JLabel name2 = new JLabel("Date of Birth ");
        name2.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension sd = name2.getPreferredSize();
        name2.setBounds(100, 200, sd.width, sd.height);
        jp.setLayout(null);
        jp.add(name2);

        JTextField jt2 = new JTextField(20);
        jt2.setBounds(350, 200, 200, 30);
        jp.add(jt2);


        JButton jt3 = new JButton("Date");
        Dimension sp = jt3.getPreferredSize();
        jt3.setBounds(600, 200, sp.width, sp.height);
        jp.add(jt3);
        jt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ac) {
                jt2.setText(new PickDate(jf).setDatePick());
            }
        });

        //patient contact name
        JLabel name3 = new JLabel("Patient Contact No.");
        name3.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension s5 = name3.getPreferredSize();
        name3.setBounds(100, 260, s5.width, s5.height);
        jp.setLayout(null);
        jp.add(name3);

        JTextField jt4 = new JTextField();
        jt4.setBounds(350, 260, 200, 30);
        jp.add(jt4);


        JLabel name4 = new JLabel("Consult Information");
        name4.setFont(new Font("Verdana", Font.PLAIN, 40));
        name4.setSize(700, 40);
        name4.setLocation(200, 300);
        jp.add(name4);

        //pick a consultation date
        JLabel name5 = new JLabel("Date ");
        name5.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension sdate = name5.getPreferredSize();
        name5.setBounds(100, 350, sdate.width, sdate.height);
        jp.setLayout(null);
        jp.add(name5);

        JTextField name6 = new JTextField(20);
        name6.setBounds(350, 350, 200, 30);
        jp.add(name6);

        JButton p = new JButton("Date");
        Dimension psize = jt3.getPreferredSize();
        p.setBounds(600, 350, psize.width, psize.height);
        jp.add(p);
        p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ac) {
                name6.setText(new PickDate(jf).setDatePick());
            }
        });

        //Start time of the consultation
        JLabel name7 = new JLabel("Start Time");
        name7.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension s7 = name7.getPreferredSize();
        name7.setBounds(100, 400, s7.width, s7.height);
        jp.setLayout(null);
        jp.add(name7);

        String[] JArray = new String[18];
        for (int x = 6; x < 18; x++) {
            JArray[x] = String.valueOf(x);
        }
        JComboBox name8 = new JComboBox(JArray);
        name8.setBounds(350, 400, 200, 30);
        jp.add(name8);


        String[] array = new String[100];
        for (int x = 0; x < 12; x++) {
            array[x] = String.valueOf(x + 1);

        }

        //Input the number of hours to the consultation
        JLabel name9 = new JLabel("No. of Hours");
        name9.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension shour = name9.getPreferredSize();
        name9.setBounds(100, 450, shour.width, shour.height);
        jp.setLayout(null);
        jp.add(name9);

        JComboBox name10 = new JComboBox(array);
        name10.setBounds(350, 450, 200, 30);
        jp.add(name10);

        //End time of the consultation
        JLabel name11 = new JLabel("End Time");
        name11.setFont(new Font("Century", Font.PLAIN, 20));
        name11.setBounds(100, 500, 100, 30);
        jp.setLayout(null);
        jp.add(name11);

        JTextField etime = new JTextField();
        etime.setBounds(350, 500, 200, 30);
        jp.add(etime);

        name10.addActionListener(e -> {
            int Starttime = Integer.parseInt(name8.getSelectedItem().toString());

            int Nohours = Integer.parseInt(name10.getSelectedItem().toString());
            int EndTime = Starttime + Nohours;
            etime.setText(String.valueOf(EndTime));
        });


        String[] docname = new String[10];
        for (int x = 0; x < doc.size(); x++) {
            docname[x] = doc.get(x).getFname();
        }

        //Enter the doctor name
        JLabel name12 = new JLabel("Doctor Name ");
        name12.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension s10 = name12.getPreferredSize();
        name12.setBounds(100, 550, s10.width, s10.height);
        jp.setLayout(null);
        jp.add(name12);

        JComboBox name13 = new JComboBox(docname);
        name13.setBounds(350, 550, 200, 30);
        jp.add(name13);

        //Add additional notes
        JLabel name14 = new JLabel("Description ");
        name14.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension s11 = name14.getPreferredSize();
        name14.setBounds(100, 600, s11.width, s11.height);
        jp.setLayout(null);
        jp.add(name14);

        JTextField name15 = new JTextField();
        name15.setBounds(350, 600, 600, 30);
        jp.add(name15);


        JLabel name16 = new JLabel("First time booking? ");
        name16.setFont(new Font("Century", Font.PLAIN, 20));
        Dimension con1 = name16.getPreferredSize();
        name16.setBounds(100, 650, con1.width, con1.height);
        jp.setLayout(null);
        jp.add(name16);

        JButton y = new JButton("Yes");
        y.setBounds(350, 650, 100, 20);
        jp.add(y);


        JButton n = new JButton("No");
        n.setBounds(450, 650, 100, 20);
        jp.add(n);


        //Calculate the full cost
        JLabel name17 = new JLabel("Full cost");
        name17.setFont(new Font("Century", Font.PLAIN, 20));
        name17.setBounds(100, 700, 100, 30);
        jp.setLayout(null);
        jp.add(name17);

        JTextField fullcost = new JTextField();
        fullcost.setBounds(350, 700, 200, 30);
        jp.add(fullcost);

        y.addActionListener(e -> {
            int noofhrs = Integer.parseInt(name10.getSelectedItem().toString());
            int totalc = noofhrs * 15;
            fullcost.setText(String.valueOf(totalc));

        });
        n.addActionListener(e -> {
            int noofhrs = Integer.parseInt(name10.getSelectedItem().toString());
            int totalc = noofhrs * 25;
            fullcost.setText(String.valueOf(totalc));

        });

        //book doctor button implementation
        JButton b = new JButton("Book Consultation");
        b.setBounds(400, 750, 300, 30);
        jp.add(b);
        b.addActionListener(e ->
        {
            System.out.println("Book Consultation Start");

            Consultation consult1 = new Consultation();
            consult1.setDocfname(name13.getSelectedItem().toString());
            consult1.setConDate(name6.getText());
            consult1.setStime(name8.getSelectedItem().toString());
            consult1.setOther(name15.getText());
            consult1.setEtime(etime.getText());
            consult1.setPatientfname(jt.getText());
            consult1.setFullcost(Integer.parseInt(fullcost.getText()));
            consult1.setBirthdate(jt2.getText());
            consult1.setPhoneno(Integer.parseInt(jt4.getText()));
            consult1.setPatientlname(jt1.getText());

            if (consul.isEmpty()) {
                consul.add(consult1);
            } else {
                for (Consultation consult : consul) {
                    if (consult.getDocfname().equals(consult1.getDocfname())) {
                        if (consult.getConDate().equals(consult1.getConDate())) {
                            int p1 = Integer.parseInt(consult.getStime());
                            int p2 = Integer.parseInt(consult.getEtime());
                            int q1 = Integer.parseInt(consult1.getEtime());
                            int q2 = Integer.parseInt(consult1.getStime());

                            if (p1 > q1 || p2 < q2) {
                                consul.add(consult1);
                                JOptionPane.showMessageDialog(jf, "Your Dr "+ consult1.getDocfname()+ " is booked");

                            } else {
                                System.out.println("This doctor has already been booked");
                                JOptionPane.showMessageDialog(null, "booked");
                            }
                        } else {
                            consul.add(consult1);
                            JOptionPane.showMessageDialog(jf, "Your Dr "+ consult1.getDocfname()+ " is booked");
                        }

                    } else {
                        consul.add(consult1);
                        JOptionPane.showMessageDialog(jf, "Your Dr "+ consult1.getDocfname()+ " is booked");
                    }
                }
            }
        });
        System.out.println("END");
    }

    //View the doctor table
    @Override
    public void viewdoc(ArrayList<Doctor> doc) {
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        f.getContentPane();
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        f.add(p);
        f.setSize(500, 300);
        f.setVisible(true);
        doc.sort(new Comparator<Doctor>() {
            @Override
            public int compare(Doctor doc1, Doctor doc2) {
                return doc1.getLname().compareTo(doc2.getLname());
            }
        });

        String[] colm = {"Last Name", "First Name", "License Number", "Phone number", "Specialication"};
        DefaultTableModel table = new DefaultTableModel(colm, 0);
        JTable tabl = new JTable(table);
        JScrollPane scroll = new JScrollPane(tabl);
        f.add(scroll);
        for (int x = 0; x < doc.size(); x++) {
            String lname = doc.get(x).getLname();
            String fname = doc.get(x).getFname();
            String liceno = doc.get(x).getLiceno();
            int phoneno = doc.get(x).getPhoneno();
            String spec = doc.get(x).getSpec();
            Object[] info = {lname, fname, liceno, phoneno, spec};
            table.addRow(info);
        }
    }
}

