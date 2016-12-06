package C_S;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

import DataBase.LikeJDBC;
import DataBase.TestJDBC;
import DataBase.Values;
import UI.UI_Server;

public class DictionaryServer extends JFrame{
	TestJDBC DataBase=new TestJDBC();
	LikeJDBC LikeBase=new LikeJDBC();
	UI_Server ui_server;
	int clientNo;
	public static void main(String[] args){
		new DictionaryServer();
	}
	
	public DictionaryServer(){
		try{
			//创建连接
			ServerSocket serverSocket=new ServerSocket(8000);
			System.out.println("DictionaryServer started at "+new Date());
			//记录用户端数目,刷新界面
			clientNo=1;
			try{
				Values[] value=DataBase.showAll();
				ui_server=new UI_Server(value);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			ui_server.right_times_change(0,LikeBase.getLikeTimes("baidu"));
			ui_server.right_times_change(1,LikeBase.getLikeTimes("youdao"));
			ui_server.right_times_change(2,LikeBase.getLikeTimes("bing"));
			
			//清空点赞次数
			ui_server.clear_liketable.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					LikeBase.clear();
					LikeBase.creatTable();
					ui_server.right_times_change(0,LikeBase.getLikeTimes("baidu"));
					ui_server.right_times_change(1,LikeBase.getLikeTimes("youdao"));
					ui_server.right_times_change(2,LikeBase.getLikeTimes("bing"));
				}
			});
			//删除用户
			ui_server.button_delete_user.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int row=ui_server.table_left.getSelectedRow();
					int column=ui_server.table_left.getSelectedColumn();
					if(column==0){
						String username=(String)ui_server.table_left.getValueAt(row,column);
						if(DataBase.delete(username))
							ui_server.left_passwd_Remove(username);
					}
					else if(column==1){
						String username=(String)ui_server.table_left.getValueAt(row,0);
						if(DataBase.delete(username))
							ui_server.left_passwd_Remove(username);
					}	
					else
						JOptionPane.showMessageDialog(null,"请选中用户", "error", JOptionPane.ERROR_MESSAGE);
				}
			});
			
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
				
				while(true){
					//信息交换
					int commandType=inputFromClient.readInt();
					switch(commandType){
					case 0:{//关闭
						String username=inputFromClient.readUTF();
						String password=inputFromClient.readUTF();
						boolean bool=DataBase.offLine(username,password);
						if(bool){
							ui_server.left_passwd_Outline(username);
							clientNo--;
						}
						outputToClient.writeBoolean(bool);
						}break;
					case 1:{//登录
						String username=inputFromClient.readUTF();
						String password=inputFromClient.readUTF();
						boolean bool=DataBase.check(username,password);
						if(bool)
							ui_server.left_passwd_Online(username);
						outputToClient.writeBoolean(bool);
					}break;
					case 2:{//注册
						String username=inputFromClient.readUTF();
						String password=inputFromClient.readUTF();
						boolean bool=DataBase.creatNew(username, password);
						if(bool)
							ui_server.left_passwd_Add(username);
						outputToClient.writeBoolean(bool);
					}break;
					case 3:{//删除
						String username=inputFromClient.readUTF();
						boolean bool=DataBase.delete(username);
						if(bool)
							ui_server.left_passwd_Remove(username);
						outputToClient.writeBoolean(bool);
						
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
						//点赞之后刷新界面
						ui_server.right_times_change(0,LikeBase.getLikeTimes("baidu"));
						ui_server.right_times_change(1,LikeBase.getLikeTimes("youdao"));
						ui_server.right_times_change(2,LikeBase.getLikeTimes("bing"));
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

