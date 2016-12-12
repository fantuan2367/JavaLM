package C_S;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

import DataBase.LikeJDBC;
import DataBase.TestJDBC;
import DataBase.Values;
import UI.UI_Server;
import Wordcard.CardValue;

public class DictionaryServer extends JFrame{
	TestJDBC DataBase=new TestJDBC();
	LikeJDBC LikeBase=new LikeJDBC();
	CardValue[] card=new CardValue[500];
	UI_Server ui_server;
	int clientNo;
	int cardNo=0;
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
						int temp=(int)ui_server.table_left.getValueAt(row,1);
						if(temp==1){
							JOptionPane.showMessageDialog(null,"不能删除在线用户", "error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						String username=(String)ui_server.table_left.getValueAt(row,0);
						if(DataBase.delete(username))
							ui_server.left_passwd_Remove(username);
					}
					else if(column==1){
						int temp=(int)ui_server.table_left.getValueAt(row,1);
						if(temp==1){
							JOptionPane.showMessageDialog(null,"不能删除在线用户", "error", JOptionPane.ERROR_MESSAGE);
							return;
						}
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
						boolean bool=DataBase.checkOnline(username);
						outputToClient.writeBoolean(bool);
						if(!bool)
							continue;
						String password=inputFromClient.readUTF();
						bool=DataBase.check(username,password);
						outputToClient.writeBoolean(bool);
						if(bool)//更新客户端列表
							ui_server.left_passwd_Online(username);
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
					case 7:{//接受发送的单词卡
						//判断用户名是否符合规范
						String clientName=inputFromClient.readUTF();
						String[] clientNames=clientName.split(",");
						for(int i=0;i<clientNames.length;i++)
							clientNames[i]=clientNames[i].trim();
						boolean judgement=true;
						for(int i=0;i<clientNames.length;i++)
							if(!DataBase.searchIn(clientNames[i])){
								judgement=false;
								break;
							}
						outputToClient.writeBoolean(judgement);
						boolean bool=inputFromClient.readBoolean();
						if((judgement)&&(bool)){
							String username=inputFromClient.readUTF();
							String temp=inputFromClient.readUTF();
							for(int i=0;i<clientNames.length;i++){
								int j=0;
								for(j=0;j<cardNo;j++)
									if((card[j].getToClient().compareTo(clientNames[i])==0)
											&&(card[j].getFromClient().compareTo(username)==0)
											&&(card[j].getContent().compareTo(temp)==0)
											&&(card[j].ReadOrNot())){
										outputToClient.writeBoolean(false);
										outputToClient.writeUTF(clientNames[i]);
										break;
									}
								if(j<cardNo)
									break;
								card[cardNo]=new CardValue(clientNames[i],username,temp);
								cardNo++;
								if(cardNo>=500)
									cardNo=0;
							}
							outputToClient.writeBoolean(true);
						}
					}break;
					case 8:{//发送接受的单词卡
						String username=inputFromClient.readUTF();
						int count=0;
						for(int i=0;i<cardNo;i++){
							if((card[i].ReadOrNot())&&(card[i].getToClient().equals(username)))
								count++;
						}
						outputToClient.writeInt(count);
						if(count>0){
							for(int i=0;i<cardNo;i++){
								if((card[i].ReadOrNot())&&(card[i].getToClient().equals(username))){
									outputToClient.writeUTF(card[i].getFromClient());
									outputToClient.writeUTF(card[i].getContent());
									card[i].Read();
								}
							}
						}
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

