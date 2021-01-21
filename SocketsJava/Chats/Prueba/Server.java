package Chats.Prueba;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{

	static ArrayList<HiloLogeo> clientes = new ArrayList<HiloLogeo>();



	/*public static void eliminarUser(Socket s,JSONObject json) {
		for(HiloLogeo c : clientes) {
			if(c.getS() == s) {
				clientes.remove(c);
			}
		}
		System.out.println(json);
	}*/

	/*public static void broadcast(Socket cliente,JSONObject msg) throws IOException{
		for(HiloLogeo ss: clientes){
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ss.getS().getOutputStream()));
			//if(ss.getS()!=cliente) {
				bw.write(msg + "\n");
				bw.flush();
			//}
		}
		System.out.println(msg);
	}*/

	/*public static void directMsg(Socket cliente , JSONObject msg) throws IOException{
		for(HiloLogeo ss: clientes){
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ss.getS().getOutputStream()));
			//System.out.println(ss.getNick() + "Parametro: " + nick);
			if((ss.getNick().equalsIgnoreCase((String) msg.get("to")))){
				bw.write(msg + "\n");
				bw.flush();
			}
		}
		System.out.print(msg);
	}*/

	public static void main(String[]args){
		try {
			ServerSocket serverSocket = new ServerSocket(3001);
			System.out.print("Servidor escuchando\n");
			HiloTimer hiloTimer = new HiloTimer();
			hiloTimer.start();
			while(true) {
				Socket s = serverSocket.accept();
				HiloLogeo hilo= new HiloLogeo(s,"anonimo", "anonimo");
				hilo.start();
				clientes.add(hilo);
			}
		}catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Error en server main");
		}

	}

	public static JSONObject meterUser(Socket s){
		try {
			//AQUI VIENE EL LOGIN
			while(true) {
				BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String linea;
				while((linea=bf.readLine())!=null) {
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonobject= (JSONObject) jsonParser.parse(linea);
					System.out.println(jsonobject);
					jsonobject.remove("action");
					return jsonobject;
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return null;
	}

	public static void eliminarUserDesconectado() {
		for(HiloLogeo c: clientes) {
			System.out.println(c.getS());
			if(!c.getS().isConnected()){
				clientes.remove(c);
				System.out.println("Eliminado socket");
			}
		}
	}
}
