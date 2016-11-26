package UI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UI_Server extends JFrame{
	public JButton button_passwd_root_change=new JButton("修改密码");
	public JButton button_delete_user=new JButton("删除用户");
	public UI_Server() {
		setTitle("Server");
		setSize(600,450);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//设置周围的空白边界
		setContentPane(content_panel);
		content_panel.setLayout(null);

		//点赞面板
		JPanel panel_button=new JPanel();
		panel_button.setBounds(120,20,350, 50);
		content_panel.add(panel_button);
		panel_button.setLayout(new BorderLayout());
		button_passwd_root_change.setFocusPainted(false);
		panel_button.add(button_passwd_root_change,BorderLayout.WEST);
		button_delete_user.setFocusPainted(false);
		panel_button.add(button_delete_user,BorderLayout.EAST);

		JPanel panel_list=new JPanel();
		panel_list.setBounds(10, 90, 570, 350);
		//窗体设置为可见
		setVisible(true);
	}
}
