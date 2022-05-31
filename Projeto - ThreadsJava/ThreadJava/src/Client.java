public class Client {
    private String name;
    private Account account;
    public Client(String name, Account account) {
        this.name = name;
        this.account = account;
    }
public void execute(){
    try {
        // Tenta consumir um número inteiro
        while (true) {
            account.withdraw();
            // Dorme 200 ms
            Thread.sleep(200);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
}

