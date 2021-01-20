package Chats.JavaFlutter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;

public class Servidor {

    static ArrayList<HiloLogeo> usuarios = new ArrayList<HiloLogeo>();
    static BufferedReader bf;

    public static void main(String[] args) {
        try {

            //encendemos servidor
            ServerSocket servidorSocket = new ServerSocket(1234);
            System.out.print("Servidor encendido\n");

            //hacemos que se conecten todos los usuarios que quieras
            while(true) {
                Socket socketUsuario = servidorSocket.accept();

                //logeamos al usuario
                HiloLogeo hilo= new HiloLogeo(socketUsuario,"anonimo", "anonimo");
                bf = new BufferedReader(new InputStreamReader(socketUsuario.getInputStream()));
                hilo.start();
                usuarios.add(hilo);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error en Servidor");
        }
    }

    public static String meterUser(Socket socket){
        while(true) {
            try {
                String linea;
                while((linea=bf.readLine())!=null) {
                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonobject= (JSONObject) jsonParser.parse(linea);
                    System.out.println(jsonobject);
                    return (String) jsonobject.get("user");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error en Servidor, meterUser");
            } catch (ParseException e) {
                System.out.println("Error en Servidor, meterUser, parsear el json");
            }


        }
    }
    public static String meterRuta(Socket socket){
        while(true) {
            try {
                String linea;
                while((linea=bf.readLine())!=null) {
                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonobject= (JSONObject) jsonParser.parse(linea);
                    System.out.println(jsonobject);
                    return (String) jsonobject.get("route");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error en Servidor, meterUser");
            } catch (ParseException e) {
                System.out.println("Error en Servidor, meterUser, parsear el json");
            }


        }
    }


}
