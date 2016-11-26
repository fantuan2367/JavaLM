package UI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Reptile.Baidu;
import Reptile.Iciba;
import Reptile.Youdao;
public class UI_Main extends JFrame{
	private JTextField input=new JTextField();
	private JButton search_button=new JButton("查询");
	private JScrollPane scroll_baidu=new JScrollPane();
	private JScrollPane scroll_youdao=new JScrollPane();
	private JScrollPane scroll_Iciba=new JScrollPane();
	private JCheckBox check_baidu=new JCheckBox("百度");
	private JCheckBox check_youdao=new JCheckBox("有道");
	private JCheckBox check_Iciba=new JCheckBox("Iciba");
	private JTextArea text_baidu=new JTextArea();
	private JTextArea text_youdao=new JTextArea();
	private JTextArea text_Iciba=new JTextArea();
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
		JPanel panel_passwd_change=new JPanel();
		panel_passwd_change.setBounds(10, 50, 570, 30);
		content_panel.add(panel_passwd_change);
		panel_passwd_change.setLayout(new BorderLayout(20,0));
		panel_passwd_change.add(button_passwd_change,BorderLayout.EAST);
		
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
		
		
		//翻译面板
		JPanel panel_baidu=new JPanel();
		panel_baidu.setBounds(10,150, 570,190);
		Border border_baidu=BorderFactory.createTitledBorder("百度");
		panel_baidu.setBorder(border_baidu);
		content_panel.add(panel_baidu);
		panel_baidu.setLayout(new BorderLayout());
		panel_baidu.add(scroll_baidu);
		scroll_baidu.add(text_baidu);
		scroll_baidu.setViewportView(text_baidu);
		//有道面板
		JPanel panel_youdao=new JPanel();
		panel_youdao.setBounds(10,340, 570,190);
		Border border_youdao=BorderFactory.createTitledBorder("有道");
		panel_youdao.setBorder(border_youdao);
		content_panel.add(panel_youdao);
		panel_youdao.setLayout(new BorderLayout());
		panel_youdao.add(scroll_youdao);
		scroll_youdao.add(text_youdao);
		scroll_youdao.setViewportView(text_youdao);
		//Iciba面板
		JPanel panel_Iciba=new JPanel();
		panel_Iciba.setBounds(10,530, 570,190);
		Border border_Iciba=BorderFactory.createTitledBorder("Iciba");
		panel_Iciba.setBorder(border_Iciba);
		content_panel.add(panel_Iciba);
		panel_Iciba.setLayout(new BorderLayout());
		panel_Iciba.add(scroll_Iciba);
		scroll_Iciba.add(text_Iciba);
		scroll_Iciba.setViewportView(text_Iciba);
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
		
		//查询按钮监听
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content=input.getText();
				text_baidu.setText("");
				text_youdao.setText("");
				text_Iciba.setText("");
				if(check_baidu.isSelected()) {
					text_baidu.setText(Baidu.doTranslate(content));
				}
				if(check_youdao.isSelected()) {
					text_youdao.setText(Youdao.doTranslate(content));
					
				}
				if(check_Iciba.isSelected()) {
					text_Iciba.setText(Iciba.doTranslate(content));
					
				}
			}
		});
		
		//窗体设置为可见
		setVisible(false);
	}
}
