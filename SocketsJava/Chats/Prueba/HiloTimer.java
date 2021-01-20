package Chats.Prueba;

public class HiloTimer extends Thread{

    @Override
    public void run() {
        try {
            while (true){
                sleep(5000);
                Server.eliminarUserDesconectado();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
