import java.util.concurrent.Semaphore;

class Garfo {
    private Semaphore semaforo = new Semaphore(1);  // Apenas um filósofo pode pegar o garfo por vez

    public boolean pegarGarfo() {
        try {
            semaforo.acquire();  // Adquire o garfo
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void liberarGarfo() {
        semaforo.release();  // Libera o garfo
    }
}

class Filosofo extends Thread {
    private int id;
    private Garfo garfoEsquerda;
    private Garfo garfoDireita;

    public Filosofo(int id, Garfo garfoEsquerda, Garfo garfoDireita) {
        this.id = id;
        this.garfoEsquerda = garfoEsquerda;
        this.garfoDireita = garfoDireita;
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            comer();
        }
    }

    private void pensar() {
        try {
            System.out.println("Filósofo " + id + " está pensando.");
            Thread.sleep((int)(Math.random() * 1000));  // Simula o tempo de pensamento
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void comer() {
        try {
            // Tenta pegar o garfo da esquerda e da direita
            if (garfoEsquerda.pegarGarfo()) {
                System.out.println("Filósofo " + id + " pegou o garfo esquerdo.");

                if (garfoDireita.pegarGarfo()) {
                    System.out.println("Filósofo " + id + " pegou o garfo direito e está comendo.");
                    Thread.sleep((int)(Math.random() * 1000));  // Simula o tempo de comer
                    System.out.println("Filósofo " + id + " terminou de comer.");

                    // Liberar os garfos após comer
                    garfoDireita.liberarGarfo();
                    garfoEsquerda.liberarGarfo();
                } else {
                    // Se não conseguir pegar o garfo direito, libera o esquerdo e tenta novamente
                    System.out.println("Filósofo " + id + " não conseguiu pegar o garfo direito, liberando o esquerdo.");
                    garfoEsquerda.liberarGarfo();
                    Thread.sleep(100);  // Espera um pouco antes de tentar novamente
                }
            } else {
                System.out.println("Filósofo " + id + " não conseguiu pegar o garfo esquerdo, tentando novamente.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class JantarFilosofos {
    public static void main(String[] args) {
        // Criação dos garfos (semaforos)
        Garfo[] garfos = new Garfo[5];
        for (int i = 0; i < 5; i++) {
            garfos[i] = new Garfo();
        }

        // Criação dos filósofos
        Filosofo[] filosofos = new Filosofo[5];
        for (int i = 0; i < 5; i++) {
            filosofos[i] = new Filosofo(i + 1, garfos[i], garfos[(i + 1) % 5]);
        }

        // Inicia os filósofos
        for (int i = 0; i < 5; i++) {
            filosofos[i].start();
        }
    }
}
