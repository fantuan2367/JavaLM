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

public class UI_Passwd_Change extends JFrame {
	private JTextField passwd_change_first=new JTextField("新密码：");
	private JTextField passwd_change_second=new JTextField("重新输入新密码：");
	public JPasswordField passwd_change_once=new JPasswordField();
	public JPasswordField passwd_change_twice=new JPasswordField();
	public JButton change_yes=new JButton("确认");
	public JButton change_no=new JButton("取消");
	public UI_Passwd_Change() {
		setTitle("Change passed");
		setSize(300,150);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//设置周围的空白边界
		setContentPane(content_panel);
		content_panel.setLayout(null);
		
		//设置提示面板
		JPanel panel_hint=new JPanel();
		panel_hint.setBounds(10,10,50,60);
		content_panel.add(panel_hint);
		panel_hint.setLayout(new BorderLayout());
		//加入第一次输入提示栏
		passwd_change_first.setBorder(null);
		passwd_change_first.setOpaque(false);
		passwd_change_first.setEditable(false);
		panel_hint.add(passwd_change_first,BorderLayout.NORTH);
		//加入第二次输入提示栏
		passwd_change_second.setBorder(null);
		passwd_change_second.setOpaque(false);
		passwd_change_second.setEditable(false);
		panel_hint.add(passwd_change_second,BorderLayout.SOUTH);
		
		//设置输入面板
		JPanel panel_input=new JPanel();
		panel_input.setBounds(80,10,200,60);
		content_panel.add(panel_input);
		panel_input.setLayout(new BorderLayout());
		//加入用户名输入栏
		panel_input.add(passwd_change_once,BorderLayout.NORTH);
		//加入密码输入栏
		panel_input.add(passwd_change_twice,BorderLayout.SOUTH);
		
		//设置按钮面板
		JPanel panel_button=new JPanel();
		panel_button.setBounds(30,80,250,50);
		content_panel.add(panel_button);
		//加入按钮
		panel_button.add(change_yes,BorderLayout.WEST);
		panel_button.add(change_no,BorderLayout.EAST);
		
		//窗体设置为可见
		setVisible(false);
	}
}
