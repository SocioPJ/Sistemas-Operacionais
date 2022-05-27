package prodcon;

class Consumer extends Thread {
	// Referência para buffer compartilhado
	Buffer buffer;

	// Construtor
	public Consumer(Buffer buffer, String c) {
		super(c);
		this.buffer = buffer;
	}

	// Método redefinido que executa a função da thread
	@Override
	public void run() {
		try {
			// Tenta consumir um número inteiro
			while (true) {
				buffer.get();
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
