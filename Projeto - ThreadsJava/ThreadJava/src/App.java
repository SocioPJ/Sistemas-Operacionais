public class App {
    public static void main(String[] args) throws Exception {
        Account account = new Account(1000);
        Client clients[] = { new Client("Alice", account), new Client("Bob", account) };
    }
}
