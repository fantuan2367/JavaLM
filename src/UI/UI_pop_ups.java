package UI;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UI_pop_ups extends JFrame{
	private JTextField error=new JTextField();
	
	public JButton sign_in=new JButton("��¼");
	public JButton sign_up=new JButton("ע��");
	public UI_pop_ups(String s){
		
		setTitle("");
		setSize(100,50);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//������Χ�Ŀհױ߽�
		setContentPane(content_panel);
		content_panel.setLayout(null);
		content_panel.add(error);
		error.setText(s);
		
	}
	public static void main(String[] args){
		
	}

}
