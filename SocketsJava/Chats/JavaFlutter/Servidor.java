package Chats.JavaFlutter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    static ArrayList<HiloLogeo> clientes = new ArrayList<HiloLogeo>();

    public static void main(String[] args) {
        try {
            //encendemos servidor
            ServerSocket servidorSocket = new ServerSocket(1234);
            System.out.print("Servidor encendido\n");
            //hacemos que se conecten todos los usuarios que quieras
            while(true) {
                Socket s = servidorSocket.accept();
                HiloLogeo hilo= new HiloLogeo(s,"guest");
                hilo.start();
                clientes.add(hilo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
