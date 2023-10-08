import java.util.*;

public class Application {
    private final Hashtable<String, Integer> table;
    private final FileManager fileManager;
    public Application(String filePath) {
        fileManager = new FileManager(filePath);
        // Creazione di un indice a partire da un file
        table = new Hashtable<>(fileManager.getNumberOfRecords() + 20);
        List<Computer> computers = fileManager.readAllRecords();
        if (computers == null)
            throw new RuntimeException("File vuoto o non trovato");
        int i = 0;
        for (Computer computer : computers) {
            table.put(computer.getSerialNumber(), i);
            i++;
        }
    }
    public Computer readComputer(String serialNumber) {
        Integer pos = table.get(serialNumber);
        if (pos == null)
            return null;
        return fileManager.readRecord(pos);
    }
    public List<Computer> getComputersWithEnoughRam(int ramCapacity) {
        List<Computer> computers = fileManager.readAllRecords();
        ArrayList<Computer> computersWithEnoughRam = new ArrayList<>(computers.size());
        for (Computer computer : computers) {
            if (computer.getRamCapacity() >= ramCapacity)
                computersWithEnoughRam.add(computer);
        }
        return computersWithEnoughRam;
    }
    public List<Computer> getComputersSorted() {
        List<Computer> computers = fileManager.readAllRecords();
        Collections.sort(computers);
        return computers;
    }
}
