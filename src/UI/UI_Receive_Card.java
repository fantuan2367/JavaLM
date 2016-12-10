package UI;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UI_Receive_Card extends JFrame{
	private JTextField passwd_change_first=new JTextField("����·����");
	public JTextField receiver=new JTextField();
	public JButton button_yes=new JButton("ȷ��");
	public JButton button_no=new JButton("ȡ��");
	public JButton button_view=new JButton("���");
	public UI_Receive_Card() {
		setTitle("Receive Card");
		setSize(300,150);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//������Χ�Ŀհױ߽�
		setContentPane(content_panel);
		content_panel.setLayout(null);
		
		//������ʾ���
		JPanel panel_hint=new JPanel();
		panel_hint.setBounds(10,10,200,20);
		content_panel.add(panel_hint);
		panel_hint.setLayout(new BorderLayout());
		//���뱣��λ��������ʾ��
		passwd_change_first.setBorder(null);
		passwd_change_first.setOpaque(false);
		passwd_change_first.setEditable(false);
		panel_hint.add(passwd_change_first,BorderLayout.CENTER);
		
		//�����������
		JPanel panel_input=new JPanel();
		panel_input.setBounds(10,35,200,20);
		content_panel.add(panel_input);
		panel_input.setLayout(new BorderLayout());
		//�����û���������
		panel_input.add(receiver,BorderLayout.CENTER);
		
		//���������ť���
		JPanel panel_view=new JPanel();
		panel_view.setBounds(220, 35, 60, 20);
		content_panel.add(panel_view);
		panel_view.setLayout(new BorderLayout());
		panel_view.add(button_view,BorderLayout.CENTER);
		
		//���ð�ť���
		JPanel panel_button=new JPanel();
		panel_button.setBounds(30,80,250,50);
		content_panel.add(panel_button);
		//���밴ť
		button_yes.setFocusPainted(false);
		panel_button.add(button_yes,BorderLayout.WEST);
		button_no.setFocusPainted(false);
		panel_button.add(button_no,BorderLayout.EAST);
		
		button_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
			}
		});
		button_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.showOpenDialog(null);
				receiver.setText(jfc.getSelectedFile().toString());
			}
		});

		//��������Ϊ�ɼ�
		setVisible(true);
	}
}
