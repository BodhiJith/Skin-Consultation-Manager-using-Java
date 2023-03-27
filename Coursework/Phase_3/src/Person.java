public class Person {
    public String fname;
    public String lname;
    private String birthdate;
    private int phoneno;

    public Person(String fname, String lname, String birthdate, int phoneno) {
        this.fname = fname;
        this.lname = lname;
        this.birthdate = birthdate;
        this.phoneno = phoneno;
    }

    public Person() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(int phoneno) {
        this.phoneno = phoneno;
    }

}
