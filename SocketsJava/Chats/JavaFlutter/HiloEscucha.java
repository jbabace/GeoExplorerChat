package Chats.JavaFlutter;

import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 * action(msg,login,logout)
 * value
 * from
 * to all/o a un usuario
 */

public class HiloEscucha extends Thread {
    private Socket socket;
    private BufferedReader bf;
    private String usuario;

    //getters
    public Socket getSocket() { return socket; }
    public String getUsuario() { return usuario; }

    //setters
    public void setSocket(Socket socket) { this.socket = socket; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    //constructor
    public HiloEscucha(Socket socket, String usuario) {
        try {
            this.bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
