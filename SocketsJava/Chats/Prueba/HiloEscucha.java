package Chats.Prueba;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/*
 * action(msg,login,logout)
 * value
 * from
 * to all/o a un usuario
 */
public class HiloEscucha extends Thread {
    private Socket s;
	private BufferedReader bf;
	private String nick;
	private String ruta;

	public String getNick() {
		return nick;
	}
	public String getRuta() {
		return ruta;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public HiloEscucha(Socket s,String nick, String ruta){
		this.s = s;
		this.nick = nick;
		this.ruta = ruta;
		try {
			this.bf=new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void run(){
    	String linea;
    	try {
			while((linea=bf.readLine())!=null) {
				JSONParser jsonParser = new JSONParser();

				JSONObject jsonobject= (JSONObject) jsonParser.parse(linea);
				System.out.println(jsonobject);
				if(jsonobject.get("action").equals("logout")) {
					System.out.println(this.s + " <-- Socket");
					//Server.eliminarUser(this.s, jsonobject);
				}else if(jsonobject.get("to").equals("todos")){
					//Server.broadcast(s,jsonobject);
				}else{
					//Server.directMsg(s,jsonobject);
				}
			}
		} catch (SocketException se) {
			System.out.println("Finalizo la conexion entre sockets");
			System.out.println("Error en HiloEscucha run");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error en HiloEscucha run");
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error en HiloEscucha run");
		}

    }

}
