package UI;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

class UI_Server extends JFrame{
	public JButton button_passwd_root_change=new JButton("�޸�����");
	public JButton button_delete_user=new JButton("ɾ���û�");
	private Object[][] info_left= {{"1","2"},{"3","4"}};
	private Object[][] info_right= {{"�ٶ�",new Integer(0)},{"�е�",new Integer(0)},{"Iciba",new Integer(0)}};
	private String[] name_left= {"�û���","����״̬"};
	private JTable table_left=new JTable(info_left, name_left);
	private String[] name_right= {"�ṩ��","���޴���"};
	private JTable table_right=new JTable(info_right, name_right);
	public void left_list_append(String name,String passwd) {
	}
	public void left_list_remove(String name) {
		
	}
	public void left_passwd_change(String name,String passwd) {
		for(int i=0;i<info_left.length;i++) {
			if(info_left[i][0]==name) {
				info_left[i][1]=passwd;
				break;
			}
		}
	}
	public void right_times_change(int type,int times) {//type-0:�ٶ� 1:�е� 2:Iciba
		switch(type) {
		case 0:
			info_right[0][1]=new Integer(times);
			break;
		case 1:
			info_right[1][1]=new Integer(times);
			break;
		case 2:
			info_right[1][1]=new Integer(times);
			break;
		}
	}
	public UI_Server() {
		setTitle("Server");
		setSize(600,450);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		JPanel content_panel=new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));//������Χ�Ŀհױ߽�
		setContentPane(content_panel);
		content_panel.setLayout(null);

		//�������
		JPanel panel_button=new JPanel();
		panel_button.setBounds(120,20,350, 50);
		content_panel.add(panel_button);
		panel_button.setLayout(new BorderLayout());
		button_passwd_root_change.setFocusPainted(false);
		panel_button.add(button_passwd_root_change,BorderLayout.WEST);
		button_delete_user.setFocusPainted(false);
		panel_button.add(button_delete_user,BorderLayout.EAST);

		//�����ʾ��
		//����
		JPanel panel_left=new JPanel();
		panel_left.setLayout(new BorderLayout());
		panel_left.setBounds(20, 90,270, 320);
		content_panel.add(panel_left);
		JScrollPane left=new JScrollPane(table_left);
		panel_left.add(left,BorderLayout.CENTER);
		//�ұ��
		JPanel panel_right=new JPanel();
		panel_right.setLayout(new BorderLayout());
		panel_right.setBounds(305, 90,270, 320);
		content_panel.add(panel_right);
		JScrollPane right=new JScrollPane(table_right);
		panel_right.add(right,BorderLayout.CENTER);
		//��������Ϊ�ɼ�
		setVisible(true);
	}
}