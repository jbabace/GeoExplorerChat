package Chats.JavaFlutter;

import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.net.ConnectException;
import java.net.Socket;

import java.util.Scanner;

public class Usuario {

    public static void main(String[] args) {

        try {
            //el usuario se cogeria de la app
            String usuario = "Babace";
            String ruta = "ruta X";

            Socket socket = new Socket("localhost", 1234);
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner sc = new Scanner(System.in);

            //json de login
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("action","login");
            jsonObject.put("user", usuario);
            jsonObject.put("route", ruta);

            bw.write(jsonObject + "\n");
            bw.flush();
            System.out.println("Logueado");

            HiloEscucha hilo = new HiloEscucha(socket,usuario, ruta);
            hilo.start();

            /*System.out.println("Mensaje: ");
            String mensaje= sc.nextLine();
            boolean finalizarChat = false;
            while (!finalizarChat) {
                if(mensaje == "logout"){
                    System.out.println("Has sido desconectado.");
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("action","logout");
                    jsonObject3.put("user", usuario);
                    bw.write(jsonObject3 + "\n");
                    bw.flush();
                    finalizarChat = true;
                }
            }*/

        } catch (ConnectException ce) {
            System.out.println("Error al conectarse al servidor");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error en Cliente");
        }
    }
}
