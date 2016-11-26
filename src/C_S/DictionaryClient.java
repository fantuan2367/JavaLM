package C_S;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;

import UI.UI_Main;
import UI.UI_login;

public class DictionaryClient extends JFrame{
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	
	public static void main(String[] args){
		new DictionaryClient();
	}
	
	public DictionaryClient(){
		//界面
		UI_login ui=new UI_login();
		UI_Main ui_main=new UI_Main();
		ui_main.setVisible(false);
		
		try{
			//创建连接
			Socket socket=new Socket("localhost",8000);
			
			fromServer=new DataInputStream(socket.getInputStream());
			toServer=new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException ex){
			System.out.println("error in dictionaryClient");
		}
		
		//登录按钮监听
		ui.sign_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int commandType=1;
					String username=ui.name_input.getText();
					String password=ui.passwd_input.getText();
					
					toServer.writeInt(commandType);
					toServer.writeUTF(username);
					toServer.writeUTF(password);
					
					boolean judge=fromServer.readBoolean();
					if(judge){
						ui.setVisible(false);
						ui_main.setVisible(true);
					}
					else JOptionPane.showMessageDialog(null, "密码错误", "error", JOptionPane.ERROR_MESSAGE);
					
				}
				catch(IOException ex){
					System.out.println("error in listener1");
				}
			}
		});
		//注册按钮监听
		ui.sign_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int commandType=2;
					String username=ui.name_input.getText();
					String password=ui.passwd_input.getText();
					
					toServer.writeInt(commandType);
					toServer.writeUTF(username);
					toServer.writeUTF(password);
					
					boolean judge=fromServer.readBoolean();
					if(judge)
						JOptionPane.showMessageDialog(null, "注册成功", "error", JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "用户已存在", "error", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException ex){
					System.out.println("error in listener2");
				}
			}
		});
		
		//点赞功能
		ui_main.button_like_baidu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					int commandType=5;
					String s="baidu";
					
					toServer.writeInt(commandType);
					toServer.writeUTF(s);
				}
				catch(IOException ex){
					System.out.println("error in listenerBaidu");
				}
			}
		});
		ui_main.button_like_youdao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					int commandType=5;
					String s="youdao";
					
					toServer.writeInt(commandType);
					toServer.writeUTF(s);
				}
				catch(IOException ex){
					System.out.println("error in listenerYoudao");
				}
			}
		});
		ui_main.button_like_bing.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					int commandType=5;
					String s="bing";
					
					toServer.writeInt(commandType);
					toServer.writeUTF(s);
				}
				catch(IOException ex){
					System.out.println("error in listenerbBing");
				}
			}
		});
	}
	
	
	
}
