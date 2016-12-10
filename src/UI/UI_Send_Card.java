package UI;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UI_Send_Card extends JFrame {
	private JTextField passwd_change_first=new JTextField("接受者：");
	public JTextField receiver=new JTextField();
	public JButton button_yes=new JButton("确认");
	public JButton button_no=new JButton("取消");
	public UI_Send_Card() {
		setTitle("Send Card");
		setSize(300,150);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//设置周围的空白边界
		setContentPane(content_panel);
		content_panel.setLayout(null);
		
		//设置提示面板
		JPanel panel_hint=new JPanel();
		panel_hint.setBounds(10,10,50,60);
		content_panel.add(panel_hint);
		panel_hint.setLayout(new BorderLayout());
		//加入接受者输入提示栏
		passwd_change_first.setBorder(null);
		passwd_change_first.setOpaque(false);
		passwd_change_first.setEditable(false);
		panel_hint.add(passwd_change_first,BorderLayout.CENTER);
		
		//设置输入面板
		JPanel panel_input=new JPanel();
		panel_input.setBounds(80,30,200,20);
		content_panel.add(panel_input);
		panel_input.setLayout(new BorderLayout());
		//加入用户名输入栏
		panel_input.add(receiver,BorderLayout.CENTER);
		
		//设置按钮面板
		JPanel panel_button=new JPanel();
		panel_button.setBounds(30,80,250,50);
		content_panel.add(panel_button);
		//加入按钮
		button_yes.setFocusPainted(false);
		panel_button.add(button_yes,BorderLayout.WEST);
		button_no.setFocusPainted(false);
		panel_button.add(button_no,BorderLayout.EAST);
		
		//窗体设置为可见
		setVisible(true);
	}
}
