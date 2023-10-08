import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Computer> computers = List.of(
                new Computer("Dell", "Inspiron 5510", 2023, "Intel Core i7-12700H", 8, 512, "1234567890"),
                new Computer("Apple", "MacBook Pro M2", 2023, "Apple M2", 16, 1000, "9876543210"),
                new Computer("HP", "EliteBook 845 G9", 2023, "Intel Core i5-12500H", 20, 512, "3210987654")
        );
        Application application = new Application("dati.dat");
        System.out.println(application.readAllComputers());
        application.getComputersSorted();
        System.out.println(application.readAllComputers());
    }
}
