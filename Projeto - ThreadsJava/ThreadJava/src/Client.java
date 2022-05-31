import java.util.concurrent.ThreadLocalRandom;
public class Client extends Thread{
    private String name;
    private Account account;
    public Client(String name, Account account) {
        setName(name);
        this.account = account;
    }
public void execute() throws InterruptedException{
   int i = ThreadLocalRandom.current().nextInt(0, 2);
   int saques[] = {10, 20 ,50 ,100};
    if (i == 0) {
       account.deposit(saques[ThreadLocalRandom.current().nextInt(0, 4)]);}
    else{
        account.withdraw(saques[ThreadLocalRandom.current().nextInt(0, 4)]);
    }
}
@Override
	public void run() {
		try {
			while (true) {
                execute();
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

