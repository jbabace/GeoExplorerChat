package Chats.Prueba;

import org.json.simple.JSONObject;

import java.net.Socket;

public class HiloLogeo extends Thread {
	private Socket s;
	private String nick;
	private String ruta;

	HiloLogeo(Socket s,String nick, String ruta){
		this.s=s;
		this.nick=nick;
		this.ruta = ruta;
	}

	public Socket getS() {
		return this.s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	public String getNick(){
		return nick;
	}
	public void setNick(String nick){
		this.nick=nick;
	}
	public String getRuta(){
		return ruta;
	}
	public void setRuta(String ruta){
		this.nick=ruta;
	}

	@Override
	    public void run(){
		//try {
			JSONObject objeto = Server.meterUser(s);
			this.nick = (String) objeto.get("user");
			this.ruta = (String) objeto.get("ruta");
			System.out.println(this.nick+"   "+this.ruta);
			HiloEscucha hiloescucha = new HiloEscucha(s, nick, ruta);
			hiloescucha.start();
		/*} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error en HiloLogeo run");
		}*/

	 }
}
