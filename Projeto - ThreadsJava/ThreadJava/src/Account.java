import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private double balance;
	private static final int CAPACITY = 1;
	private final Queue<Integer> queue = new LinkedList<>();

	
	private final Lock lock = new ReentrantLock();
	private final Condition bufferNotFull = lock.newCondition();
	private final Condition bufferNotEmpty = lock.newCondition();

	
	public Account(double balance) {
        this.balance = balance;
    }


    public void deposit(Integer number) throws InterruptedException {
		lock.lock();
		try {
			
			while (queue.size() == CAPACITY) {
				System.out.println(Thread.currentThread().getName() +
						" : Conta está cheia, aguarde...");
				bufferNotEmpty.await();
			}
			
			boolean isAdded = queue.offer(number);
			if (isAdded) {
				balance += number;
                System.out.printf("%s depositou %d na conta%n",
						Thread.currentThread().getName(), number);
				System.out.printf("Saldo atual: %.2f%n", balance);
				bufferNotFull.signalAll();
			}
		} 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
			lock.unlock();
			Integer value = queue.poll();
		}
	}

	
    public void withdraw(Integer number) throws InterruptedException {
		lock.lock();
		try {
			
			while (queue.size() == CAPACITY) {
				System.out.println(Thread.currentThread().getName() +
						" : Conta está cheia, aguarde...");
				bufferNotFull.await();
			}
			
			boolean isAdded = queue.offer(number);
			if (isAdded) {
				balance -= number;
                System.out.printf("%s sacou %d na conta%n",
						Thread.currentThread().getName(), number);
				System.out.printf("Saldo atual: %.2f%n", balance);
				bufferNotEmpty.signalAll();
			}
		} 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
			lock.unlock();
			Integer value = queue.poll();
		}
	}
}
