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
	private JTextField name_hint=new JTextField("�û�����");
	private JTextField passwd_hint=new JTextField("���룺");
	public JPasswordField passwd_input=new JPasswordField();
	public JTextField name_input=new JTextField();
	public JButton sign_in=new JButton("��¼");
	public JButton sign_up=new JButton("ע��");
	public UI_login() {
		setTitle("Login");
		setSize(300,150);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//������Χ�Ŀհױ߽�
		setContentPane(content_panel);
		content_panel.setLayout(null);
		
		//������ʾ���
		JPanel panel_hint=new JPanel();
		panel_hint.setBounds(10,10,50,60);
		content_panel.add(panel_hint);
		panel_hint.setLayout(new BorderLayout());
		//�����û�����ʾ��
		name_hint.setBorder(null);
		name_hint.setOpaque(false);
		name_hint.setEditable(false);
		panel_hint.add(name_hint,BorderLayout.NORTH);
		//����������ʾ��
		passwd_hint.setBorder(null);
		passwd_hint.setOpaque(false);
		passwd_hint.setEditable(false);
		panel_hint.add(passwd_hint,BorderLayout.SOUTH);
		
		//�����������
		JPanel panel_input=new JPanel();
		panel_input.setBounds(80, 10,200,60);
		content_panel.add(panel_input);
		panel_input.setLayout(new BorderLayout());
		//�����û���������
		panel_input.add(name_input,BorderLayout.NORTH);
		//��������������
		panel_input.add(passwd_input,BorderLayout.SOUTH);
		
		//���ð�ť���
		JPanel panel_button=new JPanel();
		panel_button.setBounds(30,80,250,50);
		content_panel.add(panel_button);
		//���밴ť
		panel_button.add(sign_in,BorderLayout.WEST);
		panel_button.add(sign_up,BorderLayout.EAST);
		
		//��������Ϊ�ɼ�
		setVisible(true);
	}
}
