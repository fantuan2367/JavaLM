package C_S;
import java.io.*;
import java.net.*;
import javax.swing.*;

import UI.UI_login;

public class DictionaryClient extends JFrame{
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	
	public static void main(String[] args){
		new DictionaryClient();
	}
	
	public DictionaryClient(){
		//鐣岄潰
		UI_login ui=new UI_login();
		
		try{
			//杩炴帴鏈嶅姟绔�
			Socket socket=new Socket("localhost",8000);
			
			fromServer=new DataInputStream(socket.getInputStream());
			toServer=new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException ex){
			System.out.println("sss");
		}
		
	}

}
