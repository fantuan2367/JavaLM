package UI;
import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DataBase.Values;

public class UI_Server extends JFrame{
	public JButton button_delete_user=new JButton("删除用户");
	public JButton clear_liketable=new JButton("清空点赞");
	private Vector info_left=new Vector();
	private Object[][] info_right= {{"百度",new Integer(0)},{"有道",new Integer(0)},{"Iciba",new Integer(0)}};
	private Vector<String> name_left;
	private String[] name_right= {"提供商","被赞次数"};
	private DefaultTableModel model;
	private JTable table_left=new JTable();
	private JTable table_right=new JTable(info_right, name_right);
	
	public void left_passwd_Online(String username){
		int i;
		for(i=0;i<info_left.size();i++)
			if(info_left.get(i).toString().split(", ")[0].equals("["+username))
				break;
		model.removeRow(i);
		Vector vRow=new Vector();
		vRow.add(username);
		vRow.add(1);
		model.addRow(vRow);
		table_left.updateUI();
	}
	
	public void left_passwd_Outline(String username){
		int i;
		for(i=0;i<info_left.size();i++)
			if(info_left.get(i).toString().split(", ")[0].equals("["+username))
				break;
		model.removeRow(i);
		Vector vRow=new Vector();
		vRow.add(username);
		vRow.add(0);
		model.addRow(vRow);
		table_left.updateUI();
	}
	
	public void left_passwd_Remove(String username){
		int i;
		for(i=0;i<info_left.size();i++)
			if(info_left.get(i).toString().split(", ")[0].equals("["+username))
				break;
		model.removeRow(i);
		table_left.updateUI();
	}
	
	public void left_passwd_Add(String username){
		Vector vRow=new Vector();
		vRow.add(username);
		vRow.add(0);
		model.addRow(vRow);
		table_left.updateUI();
	}
	
	public void right_times_change(int type,int times) {//type-0:百度 1:有道 2:Iciba
		switch(type) {
		case 0:
			info_right[0][1]=new Integer(times);
			break;
		case 1:
			info_right[1][1]=new Integer(times);
			break;
		case 2:
			info_right[2][1]=new Integer(times);
			break;
		}
		table_right.repaint();
	}
	
	public UI_Server(Values[] value) {
		//对左边框架初始化
		for(int i=0;i<value.length;i++){
			Vector vRow=new Vector();
			vRow.add(value[i].getUsername());
			vRow.add(value[i].OnlineOrNot());
			info_left.add(vRow.clone());
		}
		Vector<String> name_left=new Vector();
		name_left.add("用户名");
		name_left.add("在线状态");
		model=new DefaultTableModel(info_left,name_left);
		table_left.setModel(model);
		
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

		//操作面板
		JPanel panel_button=new JPanel();
		panel_button.setBounds(120,20,350, 50);
		content_panel.add(panel_button);
		panel_button.setLayout(new BorderLayout());
		button_delete_user.setFocusPainted(false);
		panel_button.add(button_delete_user,BorderLayout.WEST);
		clear_liketable.setFocusPainted(false);
		panel_button.add(clear_liketable,BorderLayout.EAST);

		//表格显示区
		//左表格
		JPanel panel_left=new JPanel();
		panel_left.setLayout(new BorderLayout());
		panel_left.setBounds(20, 90,270, 320);
		content_panel.add(panel_left);
		JScrollPane left=new JScrollPane(table_left);
		panel_left.add(left,BorderLayout.CENTER);
		//右表格
		JPanel panel_right=new JPanel();
		panel_right.setLayout(new BorderLayout());
		panel_right.setBounds(305, 90,270, 320);
		content_panel.add(panel_right);
		JScrollPane right=new JScrollPane(table_right);
		panel_right.add(right,BorderLayout.CENTER);
		//窗体设置为可见
		setVisible(true);
	}
}