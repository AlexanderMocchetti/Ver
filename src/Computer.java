public class Computer implements Comparable<Computer>{
    public static final int
        STRING_LENGTH = 30,
        SERIAL_NUMBER_LENGTH = 10,
        RECORD_LENGTH = 3 * (STRING_LENGTH * Character.BYTES) + SERIAL_NUMBER_LENGTH * Character.BYTES + 3 * Integer.BYTES;
    private String marca, modello, serialNumber, processore;
    private int annoDiProduzione, ramCapacity, storageCapacity;

    public Computer(String marca, String modello, int annoDiProduzione, String processore, int ramCapacity, int storageCapacity, String serialNumber) {
        if (!(setMarca(marca) && setModello(modello) && setSerialNumber(serialNumber) && setProcessore(processore)))
            throw new RuntimeException("Stringa inadeguata passata al costruttore di Computer");
        setAnnoDiProduzione(annoDiProduzione);
        setRamCapacity(ramCapacity);
        setStorageCapacity(storageCapacity);
    }
    public Computer() {

    }
    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getProcessore() {
        return processore;
    }

    public int getAnnoDiProduzione() {
        return annoDiProduzione;
    }

    public int getRamCapacity() {
        return ramCapacity;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public boolean setMarca(String marca) {
        if (marca.length() > STRING_LENGTH)
            return false;
        this.marca = marca;
        return true;
    }

    public boolean setModello(String modello) {
        if (modello.length() > STRING_LENGTH)
            return false;
        this.modello = modello;
        return true;
    }

    public boolean setSerialNumber(String serialNumber) {
        if (serialNumber.length() > SERIAL_NUMBER_LENGTH)
            return false;
        this.serialNumber = serialNumber;
        return true;
    }

    public boolean setProcessore(String processore) {
        if (processore.length() > STRING_LENGTH)
            return false;
        this.processore = processore;
        return true;
    }

    public void setAnnoDiProduzione(int annoDiProduzione) {
        this.annoDiProduzione = annoDiProduzione;
    }

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public int compareTo(Computer computer) {
        return this.marca.compareToIgnoreCase(computer.marca);
    }
}
