import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private File file;
    public FileManager(String filePath) {
        file = new File(filePath);
    }
    public int getNumberOfRecords() {
        if (!file.exists())
            return -1;
        return (int) file.length() / Computer.RECORD_LENGTH;
    }
    private void writeString(String string, int stringCapacity, RandomAccessFile raf) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(stringCapacity);
        for (int i = string.length(); i < stringCapacity; i++) {
            stringBuilder.append('\0');
        }
        raf.writeChars(stringBuilder.toString());
    }
    private void writeRecord(Computer computer, RandomAccessFile raf) throws IOException {
        writeString(computer.getMarca(), Computer.STRING_LENGTH, raf);
        writeString(computer.getModello(), Computer.STRING_LENGTH, raf);
        raf.writeInt(computer.getAnnoDiProduzione());
        writeString(computer.getProcessore(), Computer.STRING_LENGTH, raf);
        raf.writeInt(computer.getRamCapacity());
        raf.writeInt(computer.getStorageCapacity());
        writeString(computer.getSerialNumber(), Computer.SERIAL_NUMBER_LENGTH, raf);
    }
    public boolean writeRecord(Computer computer) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            writeRecord(computer, raf);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void writeRecords(List<Computer> computers, RandomAccessFile raf) throws IOException {
        for (Computer computer : computers) {
            writeRecord(computer, raf);
        }
    }
    public boolean writeRecords(List<Computer> computers) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            writeRecords(computers, raf);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean overwriteRecords(List<Computer> computers) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(0L);
            writeRecords(computers, raf);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private String readString(int stringCapacity, RandomAccessFile raf) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(stringCapacity);
        char ch;
        for (int i = 0; i < stringCapacity; i++) {
            ch = raf.readChar();
            if (ch != '\0')
                stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
    private Computer readRecord(RandomAccessFile raf) throws IOException {
        Computer computer = new Computer();
        computer.setMarca(readString(Computer.STRING_LENGTH, raf));
        computer.setModello(readString(Computer.STRING_LENGTH, raf));
        computer.setAnnoDiProduzione(raf.readInt());
        computer.setProcessore(readString(Computer.STRING_LENGTH, raf));
        computer.setRamCapacity(raf.readInt());
        computer.setStorageCapacity(raf.readInt());
        computer.setSerialNumber(readString(Computer.SERIAL_NUMBER_LENGTH, raf));
        return computer;
    }
    public Computer readRecord(int index) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek((long) Computer.RECORD_LENGTH * index);
            return readRecord(raf);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Computer> readAllRecords() {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            int numberOfRecords = getNumberOfRecords();
            if (numberOfRecords == -1)
                return null;
            ArrayList<Computer> computers = new ArrayList<>(numberOfRecords);
            for (int i = 0; i < numberOfRecords; i++) {
                computers.add(readRecord(raf));
            }
            return computers;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
