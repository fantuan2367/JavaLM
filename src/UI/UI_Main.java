package UI;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class UI_Main extends JFrame{
	public JTextField input=new JTextField();
	public JButton search_button=new JButton("查询");
	private JScrollPane scroll_1=new JScrollPane();
	private JScrollPane scroll_2=new JScrollPane();
	private JScrollPane scroll_3=new JScrollPane();
	public JCheckBox check_baidu=new JCheckBox("百度");
	public JCheckBox check_youdao=new JCheckBox("有道");
	public JCheckBox check_Iciba=new JCheckBox("Iciba");
	public JTextArea text_1=new JTextArea();
	public JTextArea text_2=new JTextArea();
	public JTextArea text_3=new JTextArea();
	public JTextArea text_name1=new JTextArea("");
	public JTextArea text_name2=new JTextArea("");
	public JTextArea text_name3=new JTextArea("");
	public JButton button_log=new JButton("登录/注册");
	public JButton button_send_card=new JButton("发送单词卡");
	public JButton button_receive_card=new JButton("接受单词卡");
	public JButton button_passwd_change=new JButton("修改密码");
	public JButton button_like_baidu=new JButton("百度√");
	public JButton button_like_youdao=new JButton("有道√");
	public JButton button_like_Iciba=new JButton("Iciba√");
	
	public UI_Main(){
		setTitle("Net Dictionary");
		setSize(600,820);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//设置周围的空白边界
		setContentPane(content_panel);
		content_panel.setLayout(null);

		//输入面板
		JPanel panel_input=new JPanel();
		panel_input.setBounds(10, 10, 570, 30);
		content_panel.add(panel_input);
		panel_input.setLayout(new BorderLayout(20,0));
		panel_input.add(input,BorderLayout.CENTER);
		panel_input.add(search_button, BorderLayout.EAST);
		
		//修改密码面板
		JPanel panel_account=new JPanel();
		panel_account.setBounds(10, 50, 570, 30);
		content_panel.add(panel_account);
		panel_account.setLayout(new GridLayout(1, 4,30,0));
		panel_account.add(button_log);
		panel_account.add(button_send_card);
		panel_account.add(button_receive_card);
		panel_account.add(button_passwd_change);
		
		//复选框面板
		JPanel panel_choice=new JPanel();
		panel_choice.setBounds(50,90,570,40);
		content_panel.add(panel_choice);
		panel_choice.setLayout(new GridLayout(1, 3, 10, 0));
		check_baidu.setFocusPainted(false);
		check_baidu.setSelected(true);
		panel_choice.add(check_baidu);
		check_youdao.setFocusPainted(false);
		check_youdao.setSelected(true);
		panel_choice.add(check_youdao);
		check_Iciba.setFocusPainted(false);
		check_Iciba.setSelected(true);
		panel_choice.add(check_Iciba);
		
		Border border=BorderFactory.createTitledBorder("");
		//翻译面板
		JPanel panel_1=new JPanel();
		panel_1.setBounds(10,150, 570,190);
		panel_1.setBorder(border);
		content_panel.add(panel_1);
		panel_1.setLayout(new BorderLayout());
		panel_1.add(scroll_1,BorderLayout.CENTER);
		text_name1.setBorder(BorderFactory.createEmptyBorder());
		text_name1.setOpaque(false);
		panel_1.add(text_name1,BorderLayout.NORTH);
		scroll_1.add(text_1);
		scroll_1.setViewportView(text_1);
		text_name1.setEditable(false);
		text_1.setEditable(false);
		//有道面板
		JPanel panel_2=new JPanel();
		panel_2.setBounds(10,340, 570,180);
		panel_2.setBorder(border);
		content_panel.add(panel_2);
		panel_2.setLayout(new BorderLayout());
		panel_2.add(scroll_2,BorderLayout.CENTER);
		text_name2.setBorder(BorderFactory.createEmptyBorder());
		text_name2.setOpaque(false);
		panel_2.add(text_name2,BorderLayout.NORTH);
		scroll_2.add(text_2);
		scroll_2.setViewportView(text_2);
		text_name2.setEditable(false);
		text_2.setEditable(false);
		//Iciba面板
		JPanel panel_3=new JPanel();
		panel_3.setBounds(10,530, 570,180);
		panel_3.setBorder(border);
		content_panel.add(panel_3);
		panel_3.setLayout(new BorderLayout());
		panel_3.add(scroll_3,BorderLayout.CENTER);
		text_name3.setBorder(BorderFactory.createEmptyBorder());
		text_name3.setOpaque(false);
		panel_3.add(text_name3,BorderLayout.NORTH);
		scroll_3.add(text_3);
		scroll_3.setViewportView(text_3);
		text_name3.setEditable(false);
		text_3.setEditable(false);
		//点赞面板
		JPanel panel_like=new JPanel();
		panel_like.setBounds(10, 730, 570, 50);
		content_panel.add(panel_like);
		panel_like.setLayout(new GridLayout(1, 3, 50, 0));
		button_like_baidu.setFocusPainted(false);
		panel_like.add(button_like_baidu);
		button_like_youdao.setFocusPainted(false);
		panel_like.add(button_like_youdao);
		button_like_Iciba.setFocusPainted(false);
		panel_like.add(button_like_Iciba);
		
		//窗体设置为可见
		setVisible(false);
	}
}
