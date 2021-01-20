package Chats.JavaFlutter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;

/*
 * action(msg,login,logout)
 * value
 * from
 * to all in a route
 */

public class HiloEscucha extends Thread {
    private Socket socket;
    private BufferedReader bf;
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
    public HiloEscucha(Socket socket, String usuario, String ruta) {
        try {
            this.socket = socket;
            this.usuario = usuario;
            this.ruta = ruta;
            this.bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error en HiloEscucha");
        }
    }

    @Override
    public void run() {

    }
}
