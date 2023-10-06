public class Student {

    public static final int NAME_LENGTH = 30;
    public static final int GRADE_LENGTH = 2;
    public static final int DATE_OF_BIRTH_LENGTH = 10;
    public static final int RECORD_LENGTH =
            Integer.BYTES + 2 * (NAME_LENGTH * Character.BYTES) + GRADE_LENGTH * Character.BYTES + DATE_OF_BIRTH_LENGTH * Character.BYTES + Byte.BYTES;

    private int id;
    private String firstName, lastName;
    private String grade;
    private String dateOfBirth;
    private boolean frequentante;

    public Student(int id, String firstName, String lastName, String grade, String dateOfBirth, boolean frequentante) {
        if (!(setFirstName(firstName) && setLastName(lastName) && setGrade(grade) && setDateOfBirth(dateOfBirth)))
            throw new RuntimeException("stringa di dimensione inadeguata passata al costruttore di Student");
        setId(id);
        setFrequentante(frequentante);
    }
    public Student() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean setFirstName(String firstName) {
        if (firstName.length() > NAME_LENGTH)
            return false;
        this.firstName = firstName;
        return true;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean setLastName(String lastName) {
        if (lastName.length() > NAME_LENGTH)
            return false;
        this.lastName = lastName;
        return true;
    }

    public String getGrade() {
        return grade;
    }

    public boolean setGrade(String grade) {
        if (grade.length() > GRADE_LENGTH)
            return false;
        this.grade = grade;
        return true;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean setDateOfBirth(String dateOfBirth) {
        if (dateOfBirth.length() > DATE_OF_BIRTH_LENGTH)
            return false;
        this.dateOfBirth = dateOfBirth;
        return true;
    }

    public boolean isFrequentante() {
        return frequentante;
    }

    public void setFrequentante(boolean frequentante) {
        this.frequentante = frequentante;
    }
}
