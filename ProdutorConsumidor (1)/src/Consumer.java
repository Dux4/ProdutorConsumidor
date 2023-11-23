//Integrantes: Eduardo Sousa Passos, Iago Suzart Silva, Iuri Santana Góes da Silva, Maria Eduarda Kassianney da Silva, Otávio dos Santos Souza,  Raian Oliveira da Cruz.

public class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep((long) (100 + (Math.random() * 1000)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
