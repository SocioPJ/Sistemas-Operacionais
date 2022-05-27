import java.util.Random;

public class Account{
    Random random = new Random();
    private int balance;
    private double initialBalance;
    public Account(double initialBalance) {
        this.initialBalance = initialBalance;
    }
    public void deposit(){
        Random random = new Random();
		try {
			// Tenta produzir um número inteiro aleatório
			while (true) {
				balance.put(random.nextInt(1000));
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

    public void withdraw(){

    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}