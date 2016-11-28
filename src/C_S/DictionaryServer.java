package C_S;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

import DataBase.LikeJDBC;
import DataBase.TestJDBC;

public class DictionaryServer extends JFrame{
	
	public static void main(String[] args){
		new DictionaryServer();
	}
	
	public DictionaryServer(){
		try{
			//创建连接
			ServerSocket serverSocket=new ServerSocket(8000);
			System.out.println("DictionaryServer started at "+new Date());
			//记录用户端数目
			int clientNo=1;
			
			while(true){
				//连接用户端
				Socket socket=serverSocket.accept();
				System.out.println("Starting thread for client"+clientNo+" at "+new Date());
				InetAddress inetAddress=socket.getInetAddress();
				System.out.println("Client"+clientNo+"'s host name is "+inetAddress.getHostName());
				System.out.println("Client"+clientNo+"'s IP Address is "+inetAddress.getHostAddress());
				//建立线程运行用户端
				HandleAClient task=new HandleAClient(socket);
				new Thread(task).start();
				clientNo++;
			}
		}
		catch(IOException ex){
			System.err.println(ex);
		}
	}
	
	//定义一个线程来运行用户端
	class HandleAClient implements Runnable{
		private Socket socket;
		
		//创建线程
		public HandleAClient(Socket socket){
			this.socket=socket;
		}
		
		//运行
		public void run(){
			try{
				DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
				TestJDBC DataBase=new TestJDBC();
				LikeJDBC LikeBase=new LikeJDBC();
				while(true){
					//信息交换
					int commandType=inputFromClient.readInt();
					
					switch(commandType){
					case 1:{//登录
						String username=inputFromClient.readUTF();
						String password=inputFromClient.readUTF();
						outputToClient.writeBoolean(DataBase.check(username, password));
					}break;
					case 2:{//注册
						String username=inputFromClient.readUTF();
						String password=inputFromClient.readUTF();
						outputToClient.writeBoolean(DataBase.creatNew(username, password));
					}break;
					case 3:{//删除
						String username=inputFromClient.readUTF();
						outputToClient.writeBoolean(DataBase.delete(username));
					}break;
					case 4:{//改密码
						String username=inputFromClient.readUTF();
						String password=inputFromClient.readUTF();
						outputToClient.writeBoolean(DataBase.change(username, password));
					}break;
					case 5:{//点赞
						String selection=inputFromClient.readUTF();
						if(selection.equals("baidu"))
							LikeBase.add("baidu");
						else if(selection.equals("youdao"))
							LikeBase.add("youdao");
						else if(selection.equals("bing"))
							LikeBase.add("bing");
						else System.out.println("error in selection");
					}break;
					case 6:{//对三个内容排序
						int[] order=LikeBase.getOrder();
						outputToClient.writeInt(order[0]);
						outputToClient.writeInt(order[1]);
						outputToClient.writeInt(order[2]);
					}break;
					default:System.out.println("error in ServerLike");break;
					}
				}
			}
			catch(IOException e){
				System.err.println(e);
			}
		}
	}
}

