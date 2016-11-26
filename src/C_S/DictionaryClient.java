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
						setVisible(false);
						UI_Main ui_main=new UI_Main();
					}
					else System.out.println("wrong key");
					
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
						System.out.println("注册成功");
					else
						System.out.println("注册失败");
				}
				catch(IOException ex){
					System.out.println("error in listener2");
				}
			}
		});
		
		try{
			//创建连接
			Socket socket=new Socket("localhost",8000);
			
			fromServer=new DataInputStream(socket.getInputStream());
			toServer=new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException ex){
			System.out.println("sss");
		}
	}
	
	
	
}
