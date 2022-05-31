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
			// Tenta consumir um número inteiro
			while (true) {
                execute();
				// Dorme 200 ms
				Thread.sleep(200);
				// Ou comente a linha anterior e
				// descomente a linha de baixo
				// e dê a chance para outra thread
				// Thread.yield();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

