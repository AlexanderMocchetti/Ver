import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 Si realizzi un programma per la gestione di un file ad accesso diretto nel linguaggio Java.
 Il file conterrà un elenco di studenti, ogni record studente sarà composto dai seguenti campi: (2 punti)
- ID
- Nome
- Cognome
- Data di nascita
- Classe
- Frequentante (vero o falso)
Il programma deve permettere di:
1. Scrivere un record nel file (2 punti)
2. Leggere un record specifico indicando la posizione(2 punti)
3. Visualizzare il numero di record/studenti presente nel file (1 punto)
4. Modificare un record specifico indicando la posizione (si decida a piacere cosa modificare) (2 punti)
*/
public class FileManager {
    private File file;
    public FileManager(String filePath) {
        file = new File(filePath);
    }
    public int getNumberOfRecords() {
        if (!file.exists())
            return -1;
        return (int) (file.length() / Student.RECORD_LENGTH);
    }
    public boolean writeRecord(Student student) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            raf.writeInt(student.getId());
            writeString(student.getFirstName(), Student.NAME_LENGTH, raf);
            writeString(student.getLastName(), Student.NAME_LENGTH, raf);
            writeString(student.getGrade(), Student.GRADE_LENGTH, raf);
            writeString(student.getDateOfBirth(), Student.DATE_OF_BIRTH_LENGTH, raf);
            raf.writeBoolean(student.isFrequentante());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void writeString(String string, int stringCapacity, RandomAccessFile raf) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(stringCapacity);
        stringBuilder.append(string);
        for (int i = string.length(); i < stringCapacity; i++) {
            stringBuilder.append('\0');
        }
        raf.writeChars(stringBuilder.toString());
    }
    public Student readRecord(int index) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek((long) Student.RECORD_LENGTH * index);
            Student student = new Student();
            student.setId(raf.readInt());
            student.setFirstName(readString(Student.NAME_LENGTH, raf));
            student.setLastName(readString(Student.NAME_LENGTH, raf));
            student.setGrade(readString(Student.GRADE_LENGTH, raf));
            student.setDateOfBirth(readString(Student.DATE_OF_BIRTH_LENGTH, raf));
            student.setFrequentante(raf.readBoolean());
            return student;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String readString(int stringCapacity, RandomAccessFile raf) throws IOException{
        StringBuilder stringBuilder = new StringBuilder(stringCapacity);
        char ch;
        for (int i = 0; i < stringCapacity; i++) {
            ch = raf.readChar();
            if (ch != '\0')
                stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
}
