import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account{
    private final Lock lock = new ReentrantLock();
	private final Condition bufferNotFull = lock.newCondition();
	private static final int CAPACITY = 10;
	private final Queue<Integer> queue = new LinkedList<>();
	private final Condition bufferNotEmpty = lock.newCondition();
    Random random = new Random();
    private int balance;
    private double initialBalance;
    public Account(double initialBalance) {
        this.initialBalance = initialBalance;
    }
    
    public void deposit(Integer number) throws InterruptedException{
        lock.lock();
		try {
			// Se buffer estiver cheio, aguarda o consumidor consumir algum número
			while (queue.size() == CAPACITY) {
				System.out.println(Thread.currentThread().getName() +
						" : Fila está cheia, aguardando...");
				bufferNotEmpty.await();
			}
			// Adiciona um número na fila
			boolean isAdded = queue.offer(number);
			if (isAdded) {
				System.out.printf("%s produziu %d na fila%n",
						Thread.currentThread().getName(), number);
				// Sinaliza a thread consumidor que ela pode consumir
				System.out.println(Thread.currentThread().getName() +
						" : Buffer já tem elemento!");
				bufferNotFull.signalAll();
			}
		} finally {
			lock.unlock();
		}
	}
    

    public void withdraw() throws InterruptedException{
		lock.lock();
		try {
			// Se buffer estiver vazio, aguarda o produtor produzir algum número
			while (queue.size() == 0) {
				System.out.println(Thread.currentThread().getName() +
						" : Conta sem saldo, aguardando depósito...");
				bufferNotFull.await();
			}
			// Remove um número da fila
			Integer value = queue.poll();
			if (value != null) {
				System.out.printf("%s Sacou %d da conta%n",
						Thread.currentThread().getName(), value);
				// Sinaliza a thread produtor que ela pode produzir
				System.out.println(Thread.currentThread().getName() +
						" : Buffer tem espaço!");
				bufferNotEmpty.signalAll();
			}
		} finally {
			lock.unlock();
		}
	}

    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }


}