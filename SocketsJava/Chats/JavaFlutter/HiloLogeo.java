package Chats.JavaFlutter;

import java.net.Socket;

public class HiloLogeo extends Thread {

    //atributos
    private Socket socket;
    private String usuario;
    private String ruta;

    //getters
    public Socket getSocket() { return this.socket; }
    public String getUsuario() { return this.usuario; }
    public String getRuta() { return this.ruta; }

    //setters
    public void setSocket(Socket socket) { this.socket = socket; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setRuta(String ruta) { this.ruta = ruta; }

    //constructor
    HiloLogeo(Socket socketUsuario, String usuario, String ruta){
        this.socket = socketUsuario;
        this.usuario = usuario;
        this.ruta = ruta;
    }

    //funcion del hilo
    @Override
    public void run() {
        System.out.println("Servidor detecto cliente anonimo");
        try {
            this.usuario = Servidor.meterUser(this.socket);
            this.ruta = Servidor.meterRuta(this.socket);
            HiloEscucha hiloEscucha = new HiloEscucha(this.socket, this.usuario, this.ruta);
            hiloEscucha.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en HiloLogeo");
        }


    }
}
