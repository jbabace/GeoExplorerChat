package Chats.JavaFlutter;

import java.net.Socket;

public class HiloLogeo extends Thread {

    //atributos
    private Socket socket;
    private String usuario;

    //getters
    public Socket getSocket() { return socket; }
    public String getUsuario() { return usuario; }

    //setters
    public void setSocket(Socket socket) { this.socket = socket; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    //constructor
    HiloLogeo(Socket socketUsuario, String usuario){
        this.socket = socketUsuario;
        this.usuario = usuario;
    }

    //funcion del hilo
    @Override
    public void run() {
        System.out.println("Servidor detecto cliente anonimo");
        /*try {
            hiloEscucha hiloEscucha = new HiloEscucha();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
