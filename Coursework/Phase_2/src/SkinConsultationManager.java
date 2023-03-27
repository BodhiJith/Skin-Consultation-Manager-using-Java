import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface SkinConsultationManager {
    public ArrayList<Doctor> adddoc(ArrayList<Doctor> doc);
    public ArrayList<Doctor> dltdoc(ArrayList<Doctor> doc);
    public ArrayList<Doctor> printdoc(ArrayList<Doctor> doc);
    public ArrayList<Doctor> savedoc(ArrayList<Doctor> doc) throws FileNotFoundException;
    public ArrayList<Doctor> exit(ArrayList<Doctor> doc);

}
