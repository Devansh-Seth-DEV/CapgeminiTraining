package threading;

class Library {
	int totalBooks = 0;
	
	// Consumer thread calls this
	public synchronized void buyBook() {
		while (totalBooks == 0) {
			try {
				System.out.println("No Books! Consumer is waiting ...");
				wait();		// consumer thread stops here and releases the lock
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		totalBooks--;
		System.out.println("Book Bought!, Total Books Left: " + totalBooks);
	}
	
	
	// Producer thread calls this
	public synchronized void restock() {
		totalBooks++;
		System.out.println("Book Added, Total Books Now: " + totalBooks);
		notify();	// producer thread wakes up the consumer thread
	}
}

public class InterThreadCommunicationBasics {

	public static void main(String[] args) {
		Library myLibrary = new Library();
		
		Thread consumer = new Thread(() -> {
			myLibrary.buyBook();
		}, "Consumer-Thread");
		
		Thread producer = new Thread(() -> {
			myLibrary.restock();
		}, "Producer-Thread");
		
		consumer.start();
		producer.start();
	}

}
