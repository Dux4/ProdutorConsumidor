//Integrantes: Eduardo Sousa Passos, Iago Suzart Silva, Iuri Santana Góes da Silva, Maria Eduarda Kassianney da Silva, Otávio dos Santos Souza,  Raian Oliveira da Cruz.

import java.util.concurrent.Semaphore;

public class Buffer {
    private int[] varBuffer;
    private int sizeBuffer, itemConsumer;
    private int in, out;
    private int itemCounter;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;

    public Buffer(int size) {
        this.sizeBuffer = size;
        this.varBuffer = new int[size];
        this.in = 0;
        this.out = 0;
        this.itemCounter = 0;
        this.mutex = new Semaphore(1);
        this.empty = new Semaphore(size);
        this.full = new Semaphore(0);
    }

    public void produce(int item) throws InterruptedException {
        try {
            empty.acquire();
            mutex.acquire();

            boolean verified = false;

            while(!verified) {
                in = (int) (Math.random() * sizeBuffer);
                if(varBuffer[in] == 0){
                    verified = true;
                    varBuffer[in] = item;
                }
            }

            itemCounter = 0;

            System.out.print("Produziu: " + item + " - Posição " + (in + 1) + "  |  Buffer status: [ ");

            for (int i = 0; i < varBuffer.length; i++) {
                System.out.print(varBuffer[i] + " ");
                if(varBuffer[i] > 0){
                    itemCounter += 1;
                }
            }

            System.out.print("]  |  Ocupação: " + itemCounter + "/" + sizeBuffer +"\n");

        } finally {
            mutex.release();
            full.release();
        }
    }

    public void consume() throws InterruptedException {
        try {
            full.acquire();
            mutex.acquire();

            boolean verified = false;

            while(!verified){
                out = (int) (Math.random() * sizeBuffer);
                if(varBuffer[out] > 0){
                    verified = true;
                    itemConsumer = varBuffer[out];
                    varBuffer[out] = 0;
                }
            }

            itemCounter = 0;

            System.out.print("  Consumiu: " + itemConsumer + " - Posição " + (out + 1) + "  |  Buffer status: [ ");

            for (int i = 0; i < varBuffer.length; i++) {
                System.out.print(varBuffer[i] + " ");
                if(varBuffer[i] > 0){
                    itemCounter += 1;
                }
            }

            System.out.print("]  |  Ocupação: " + itemCounter + "/" + sizeBuffer + "\n");

        } finally {
            mutex.release();
            empty.release();
        }
    }
}