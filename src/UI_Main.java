import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
public class UI_Main extends JFrame{
	private JTextField input=new JTextField();
	private JButton search_button=new JButton("查询");
	private JScrollPane scroll_baidu=new JScrollPane();
	private JScrollPane scroll_youdao=new JScrollPane();
	private JScrollPane scroll_bing=new JScrollPane();
	private JCheckBox check_baidu=new JCheckBox("百度");
	private JCheckBox check_youdao=new JCheckBox("有道");
	private JCheckBox check_bing=new JCheckBox("Bing");
	private JTextArea text_baidu=new JTextArea();
	private JTextArea text_youdao=new JTextArea();
	private JTextArea text_bing=new JTextArea();
	private JButton button_like_baidu=new JButton("百度√");
	private JButton button_like_youdao=new JButton("有道√");
	private JButton button_like_bing=new JButton("Bing√");
	public UI_Main(){
		setTitle("Net Dictionary");
		setSize(600,820);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		
		//复选框面板
		JPanel panel_choice=new JPanel();
		panel_choice.setBounds(70, 70, 570, 50);
		content_panel.add(panel_choice);
		panel_choice.setLayout(new GridLayout(1, 3, 10, 0));
		check_baidu.setFocusPainted(false);
		panel_choice.add(check_baidu);
		check_youdao.setFocusPainted(false);
		panel_choice.add(check_youdao);
		check_bing.setFocusPainted(false);
		panel_choice.add(check_bing);
		
		
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
		//bing面板
		JPanel panel_bing=new JPanel();
		panel_bing.setBounds(10,530, 570,190);
		Border border_bing=BorderFactory.createTitledBorder("Bing");
		panel_bing.setBorder(border_bing);
		content_panel.add(panel_bing);
		panel_bing.setLayout(new BorderLayout());
		panel_bing.add(scroll_bing);
		scroll_bing.add(text_bing);
		scroll_bing.setViewportView(text_bing);
		//点赞面板
		JPanel panel_like=new JPanel();
		panel_like.setBounds(10, 730, 570, 50);
		content_panel.add(panel_like);
		panel_like.setLayout(new GridLayout(1, 3, 50, 0));
		button_like_baidu.setFocusPainted(false);
		panel_like.add(button_like_baidu);
		button_like_youdao.setFocusPainted(false);
		panel_like.add(button_like_youdao);
		button_like_bing.setFocusPainted(false);
		panel_like.add(button_like_bing);
		
		Baidu baidu=new Baidu();
		
		//查询按钮监听
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check_baidu.isSelected()) {
					text_baidu.setText(baidu.doTranslate(input.getText()));
				}
				if(check_youdao.isSelected()) {
					
				}
				if(check_bing.isSelected()) {
					
				}
			}
		});
		
		//窗体设置为可见
		setVisible(true);
	}
}
