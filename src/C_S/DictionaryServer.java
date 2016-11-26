package C_S;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class DictionaryServer extends JFrame{
	
	public static void main(String[] args){
		new DictionaryServer();
	}
	
	public DictionaryServer(){
		try{
			//鍒涘缓鏈嶅姟绔繛鎺�
			ServerSocket serverSocket=new ServerSocket(8000);
			System.out.println("DictionaryServer started at "+new Date());
			//鏍囪鐢ㄦ埛绔暟鐩�
			int clientNo=1;
			
			while(true){
				//瀵规瘡涓繛鎺ョ殑鐢ㄦ埛绔仛鎿嶄綔
				Socket socket=serverSocket.accept();
				System.out.println("Starting thread for client"+clientNo+" at "+new Date());
				InetAddress inetAddress=socket.getInetAddress();
				System.out.println("Client"+clientNo+"'s host name is "+inetAddress.getHostName());
				System.out.println("Client"+clientNo+"'s IP Address is "+inetAddress.getHostAddress());
				//涓鸿繛鎺ュ垱閫犱竴涓柊杩涚▼
				HandleAClient task=new HandleAClient(socket);
				new Thread(task).start();
				clientNo++;
			}
		}
		catch(IOException ex){
			System.err.println(ex);
		}
	}
	
	//鐢ㄤ簬澶勭悊鏂拌繛鎺ョ殑杩涚▼绫�
	class HandleAClient implements Runnable{
		private Socket socket;
		
		//鍒涘缓杩涚▼
		public HandleAClient(Socket socket){
			this.socket=socket;
		}
		
		//
		public void run(){
			try{
				DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
				
				while(true){
					//鏈嶅姟绔鐞嗙敤鎴风杩斿洖鏁版嵁骞惰繑鍥炲��
					double r=inputFromClient.readDouble();
					double area=r*r*Math.PI;
					outputToClient.writeDouble(area);
					System.out.println("radius received from client: "+r);
					System.out.println("area found: "+area);
					//
				}
			}
			catch(IOException e){
				System.err.println(e);
			}
		}
	}
}

