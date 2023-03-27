public class Doctor extends Person{
    public String getFname;
    public String getLname;
    private String liceno;
    private String spec;


    public String getLiceno() {
        return liceno;
    }

    public void setLiceno(String liceno) {
        this.liceno = liceno;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}