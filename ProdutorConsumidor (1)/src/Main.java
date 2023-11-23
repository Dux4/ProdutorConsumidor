//Integrantes: Eduardo Sousa Passos, Iago Suzart Silva, Iuri Santana Góes da Silva, Maria Eduarda Kassianney da Silva, Otávio dos Santos Souza,  Raian Oliveira da Cruz.

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        consumer.start();
        producer.start();
    }
}