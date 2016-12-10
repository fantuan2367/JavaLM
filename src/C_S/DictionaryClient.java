package C_S;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.sql.Date;

import javax.swing.*;

import DataBase.MD5;
import Reptile.Baidu;
import Reptile.Iciba;
import Reptile.Youdao;
import UI.UI_Main;
import UI.UI_Passwd_Change;
import UI.UI_login;
import Wordcard.*;

public class DictionaryClient extends JFrame{
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	private UI_Main ui_main;
	
	public static void main(String[] args){
		new DictionaryClient();
	}
	
	private void UIOrder(String s1,String s2,String s3,String s4,String s5,String s6){
		ui_main.text_name1.setText(s1);
		ui_main.text_name2.setText(s2);
		ui_main.text_name3.setText(s3);
		ui_main.text_1.setText(s4);
		ui_main.text_2.setText(s5);
		ui_main.text_3.setText(s6);
	}
	
	public DictionaryClient(){
		//界面
		UI_login ui=new UI_login();
	    ui_main=new UI_Main();
		UI_Passwd_Change ui_passwd_change=new UI_Passwd_Change();
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
		
		//游客登录按钮监听
		ui.tourist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.setVisible(false);
				ui.name_input.setText("");
				ui.passwd_input.setText("");
				ui_main.button_like_baidu.setEnabled(false);
				ui_main.button_like_youdao.setEnabled(false);
				ui_main.button_like_Iciba.setEnabled(false);
				ui_main.button_passwd_change.setEnabled(false);
				ui_main.button_send_card.setEnabled(false);
				ui_main.button_receive_card.setEnabled(false);
				ui_main.button_log.setEnabled(true);
				ui_main.setVisible(true);
			}
		});
		
		//登录按钮监听
		ui.sign_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int commandType=1;
					MD5 md5=new MD5();
					String username=ui.name_input.getText();
					String password=String.valueOf(ui.passwd_input.getPassword());
					if(password.length()==0)
						JOptionPane.showMessageDialog(null,"密码不能为空", "error", JOptionPane.ERROR_MESSAGE);
					toServer.writeInt(commandType);
					toServer.writeUTF(username);
					toServer.writeUTF(md5.encryptMD5(password));
					
					boolean judge=fromServer.readBoolean();
					if(judge){
						ui.setVisible(false);
						ui_main.setVisible(true);
						ui_main.button_like_baidu.setEnabled(true);
						ui_main.button_like_youdao.setEnabled(true);
						ui_main.button_like_Iciba.setEnabled(true);
						ui_main.button_passwd_change.setEnabled(true);
						ui_main.button_send_card.setEnabled(true);
						ui_main.button_receive_card.setEnabled(true);
						ui_main.button_log.setEnabled(false);
					}
					else JOptionPane.showMessageDialog(null, "用户名或密码错误", "error", JOptionPane.ERROR_MESSAGE);
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
					MD5 md5=new MD5();
					String username=ui.name_input.getText();
					String password=String.valueOf(ui.passwd_input.getPassword());
					
					for(int i=0;i<username.length();i++)
						if(!(((username.charAt(i)>='0')&&(username.charAt(i)<='9'))||
								((username.charAt(i)>='a')&&(username.charAt(i)<='z'))||
								((username.charAt(i)>='A')&&(username.charAt(i)<='Z')))){
							JOptionPane.showMessageDialog(null,"用户名只能由数字和英语字母组成", "error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					
					if(password.length()==0)
						JOptionPane.showMessageDialog(null,"密码不能为空", "error", JOptionPane.ERROR_MESSAGE);
					
					toServer.writeInt(commandType);
					toServer.writeUTF(username);
					toServer.writeUTF(md5.encryptMD5(password));
					
					boolean judge=fromServer.readBoolean();
					if(judge)
						JOptionPane.showMessageDialog(null, "注册成功", "ok", JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "用户已存在", "error", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException ex){
					System.out.println("error in listener2");
				}
			}
		});
		
		//主窗口关闭监听
		ui_main.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				try {
					int commandType=0;
					MD5 md5=new MD5();
					String username=ui.name_input.getText();
					String password=String.valueOf(ui.passwd_input.getPassword());
					toServer.writeInt(commandType);
					toServer.writeUTF(username);
					toServer.writeUTF(md5.encryptMD5(password));
					boolean judge=fromServer.readBoolean();
					if(!judge)
						System.out.println("error in windows close");
				} catch (IOException e1) {
					System.out.println("error in windows close");
				}
			}
		});
		
		//修改密码按钮监听
		ui_main.button_passwd_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui_main.setVisible(false);
				ui_passwd_change.setVisible(true);
			}
		});
		//修改密码确认监听
		ui_passwd_change.change_yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//确认操作
				try{
					String passwordOne=String.valueOf(ui_passwd_change.passwd_change_once.getPassword());
					String passwordTwo=String.valueOf(ui_passwd_change.passwd_change_twice.getPassword());
					if(!(passwordTwo.equals(passwordOne)))
						JOptionPane.showMessageDialog(null, "两次密码不一致", "error", JOptionPane.ERROR_MESSAGE);
					else{
		                if(passwordOne.length()==0)
		                	JOptionPane.showMessageDialog(null, "密码不能为空", "error", JOptionPane.ERROR_MESSAGE);
		                int commandType=4;
		                MD5 md5=new MD5();
						String username=ui.name_input.getText();
						toServer.writeInt(commandType);
						toServer.writeUTF(username);
						toServer.writeUTF(md5.encryptMD5(passwordOne));
						
						boolean judge=fromServer.readBoolean();
						if(judge){
							JOptionPane.showMessageDialog(null, "修改密码成功", "ok", JOptionPane.ERROR_MESSAGE);
							ui_main.setVisible(true);
							ui_passwd_change.passwd_change_once.setText(null);
							ui_passwd_change.passwd_change_twice.setText(null);
							ui_passwd_change.setVisible(false);
						}
						else
							JOptionPane.showMessageDialog(null, "修改密码失败", "error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch(IOException ex){
					System.out.println("error in listener2");
				}
				
			}
		});
		//修改密码取消监听
		ui_passwd_change.change_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui_main.setVisible(true);
				ui_passwd_change.passwd_change_once.setText(null);
				ui_passwd_change.passwd_change_twice.setText(null);
				ui_passwd_change.setVisible(false);
			}
		});
		//修改密码窗口异常关闭监听
		ui_passwd_change.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				ui_main.setVisible(true);
				ui_passwd_change.passwd_change_once.setText(null);
				ui_passwd_change.passwd_change_twice.setText(null);
				ui_passwd_change.setVisible(false);
			}
		});
		
		//登录注册按钮监听
		ui_main.button_log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.setVisible(true);
				ui_main.setVisible(false);
				ui_main.input.setText("");
				ui_main.text_1.setText("");
				ui_main.text_2.setText("");
				ui_main.text_3.setText("");
			}
		});

		//发送单词卡按钮监听
		ui_main.button_send_card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuffer temp=new StringBuffer("");
				if(ui_main.check_baidu.isSelected()){
					if(ui_main.text_name1.getText()=="baidu")
						temp.append(ui_main.text_name1.getText()+"\n"+ui_main.text_1.getText()+"\n");
					else if(ui_main.text_name2.getText()=="baidu")
						temp.append(ui_main.text_name2.getText()+"\n"+ui_main.text_2.getText()+"\n");
					else if(ui_main.text_name3.getText()=="baidu")
						temp.append(ui_main.text_name3.getText()+"\n"+ui_main.text_3.getText()+"\n");
				}
				if(ui_main.check_youdao.isSelected()){
					if(ui_main.text_name1.getText()=="youdao")
						temp.append(ui_main.text_name1.getText()+"\n"+ui_main.text_1.getText()+"\n");
					else if(ui_main.text_name2.getText()=="youdao")
						temp.append(ui_main.text_name2.getText()+"\n"+ui_main.text_2.getText()+"\n");
					else if(ui_main.text_name3.getText()=="youdao")
						temp.append(ui_main.text_name3.getText()+"\n"+ui_main.text_3.getText()+"\n");
				}
				if(ui_main.check_Iciba.isSelected()) {
					if(ui_main.text_name1.getText()=="iciba")
						temp.append(ui_main.text_name1.getText()+"\n"+ui_main.text_1.getText()+"\n");
					else if(ui_main.text_name2.getText()=="iciba")
						temp.append(ui_main.text_name2.getText()+"\n"+ui_main.text_2.getText()+"\n");
					else if(ui_main.text_name3.getText()=="iciba")
						temp.append(ui_main.text_name3.getText()+"\n"+ui_main.text_3.getText()+"\n");
				}
				String tempp=temp.toString();
				String clientName="";
				try {
					toServer.writeInt(7);
					toServer.writeUTF(clientName);
					if(fromServer.readBoolean())
						toServer.writeUTF(tempp);
					else
						JOptionPane.showMessageDialog(null, "用户不存在", "error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//card.image_gengeration(temp.toString(), "D://card.jpg");
			}
		});
		
		//接受单词卡按钮监听
		ui_main.button_receive_card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					toServer.writeInt(8);
					String username=ui.name_input.getText();
					toServer.writeUTF(username);
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		//查询按钮监听		
		ui_main.search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//对输入单词进行判断
				String content=ui_main.input.getText();
				for(int i=0;i<content.length();i++)
					if(!(((content.charAt(i)>='a')&&(content.charAt(i)<='z'))||((content.charAt(i)>='A')&&(content.charAt(i)<='Z')))){
							JOptionPane.showMessageDialog(null, "单词格式错误", "error", JOptionPane.ERROR_MESSAGE);
							return;
					}
				
				ui_main.searchOnceBaidu=0;
				ui_main.searchOnceIciba=0;
				ui_main.searchOnceYoudao=0;
				ui_main.text_1.setText("");
				ui_main.text_2.setText("");
				ui_main.text_3.setText("");
				String baidu=null;
				String youdao=null;
				String iciba=null;
				
				int[] order={0,0,0};
				try{
					int commandType=6;
					toServer.writeInt(commandType);
					order[0]=fromServer.readInt();
					order[1]=fromServer.readInt();
					order[2]=fromServer.readInt();
				}
				catch(IOException ex){
					System.out.println("error in searchListener");
				}
				for(int i=0;i<3;i++)
					if((order[i]>3)||(order[i]<1))
						System.out.println("error in order");
				
				if(ui_main.check_baidu.isSelected()) {
					baidu=Baidu.doTranslate(content);
				}
				else order[0]=order[0]+5;//没有选择就要将文本框放在后面
				if(ui_main.check_youdao.isSelected()){
					youdao=Youdao.doTranslate(content);
				}
				else order[1]=order[1]+5;
				if(ui_main.check_Iciba.isSelected()) {
					iciba=Iciba.doTranslate(content);
				}
				else order[2]=order[2]+5;
				
				//判断单词不存在
				if((ui_main.check_baidu.isSelected())||(ui_main.check_youdao.isSelected())||(ui_main.check_Iciba.isSelected()))
					if((baidu==null)&&(youdao==null)&&(iciba==null)){
						JOptionPane.showMessageDialog(null, "单词不存在", "error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				
				if(order[0]>=order[1])
					if(order[0]>=order[2])
						if(order[1]>=order[2]){
							UIOrder("iciba","youdao","baidu",iciba,youdao,baidu);
						}
						else{
							UIOrder("youdao","iciba","baidu",youdao,iciba,baidu);
						}
					else{
						UIOrder("youdao","baidu","iciba",youdao,baidu,iciba);
					}
				else if(order[1]>=order[2])
					if(order[2]>=order[0]){
						UIOrder("baidu","iciba","youdao",baidu,iciba,youdao);
					}
					else{
						UIOrder("iciba","baidu","youdao",iciba,baidu,youdao);
					}
				else{
					UIOrder("baidu","youdao","iciba",baidu,youdao,iciba);
				}
			}
		});

		//点赞功能
		ui_main.button_like_baidu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(ui_main.searchOnceBaidu==0)
					try{
						int commandType=5;
						String s="baidu";
						ui_main.searchOnceBaidu=1;
						toServer.writeInt(commandType);
						toServer.writeUTF(s);
					}
					catch(IOException ex){
						System.out.println("error in listenerBaidu");
					}
				else
					JOptionPane.showMessageDialog(null, "已点赞", "error", JOptionPane.ERROR_MESSAGE);
			}
		});
		ui_main.button_like_youdao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(ui_main.searchOnceYoudao==0)
					try{
						int commandType=5;
						String s="youdao";
						ui_main.searchOnceYoudao=1;
						toServer.writeInt(commandType);
						toServer.writeUTF(s);
					}
					catch(IOException ex){
						System.out.println("error in listenerYoudao");
					}
				else
					JOptionPane.showMessageDialog(null, "已点赞", "error", JOptionPane.ERROR_MESSAGE);
			}
		});
		ui_main.button_like_Iciba.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(ui_main.searchOnceIciba==0)
					try{
						int commandType=5;
						String s="bing";
						ui_main.searchOnceIciba=1;
						toServer.writeInt(commandType);
						toServer.writeUTF(s);
					}
					catch(IOException ex){
						System.out.println("error in listenerbBing");
					}
				else
					JOptionPane.showMessageDialog(null, "已点赞", "error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
