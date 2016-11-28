package UI;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UI_login extends JFrame {
	private JTextField name_hint=new JTextField("用户名：");
	private JTextField passwd_hint=new JTextField("密码：");
	public JPasswordField passwd_input=new JPasswordField();
	public JTextField name_input=new JTextField();
	public JButton sign_in=new JButton("登录");
	public JButton sign_up=new JButton("注册");
	public UI_login() {
		setTitle("Login");
		setSize(300,150);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//设置周围的空白边界
		setContentPane(content_panel);
		content_panel.setLayout(null);
		
		//设置提示面板
		JPanel panel_hint=new JPanel();
		panel_hint.setBounds(10,10,50,60);
		content_panel.add(panel_hint);
		panel_hint.setLayout(new BorderLayout());
		//加入用户名提示栏
		name_hint.setBorder(null);
		name_hint.setOpaque(false);
		name_hint.setEditable(false);
		panel_hint.add(name_hint,BorderLayout.NORTH);
		//加入密码提示栏
		passwd_hint.setBorder(null);
		passwd_hint.setOpaque(false);
		passwd_hint.setEditable(false);
		panel_hint.add(passwd_hint,BorderLayout.SOUTH);
		
		//设置输入面板
		JPanel panel_input=new JPanel();
		panel_input.setBounds(80, 10,200,60);
		content_panel.add(panel_input);
		panel_input.setLayout(new BorderLayout());
		//加入用户名输入栏
		panel_input.add(name_input,BorderLayout.NORTH);
		//加入密码输入栏
		panel_input.add(passwd_input,BorderLayout.SOUTH);
		
		//设置按钮面板
		JPanel panel_button=new JPanel();
		panel_button.setBounds(30,80,250,50);
		content_panel.add(panel_button);
		//加入按钮
		panel_button.add(sign_in,BorderLayout.WEST);
		panel_button.add(sign_up,BorderLayout.EAST);
		
		//窗体设置为可见
		setVisible(true);
	}
}
