package DataBase;

import java.sql.Connection;      
import java.sql.DriverManager;     
import java.sql.PreparedStatement;         
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class LikeJDBC {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	String table="LikeTable";
	
	//数据库连接
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DataBaseName="TestJDBC";
			String DataBaseUser="root";
			String DataBaseKey="123456";
			con=(Connection) DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/"+DataBaseName+"?useUnicode=true&characterEncoding=utf-8",
							DataBaseUser,DataBaseKey);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库LikeJDBC连接失败");
		}
		return con;
	}
	
	//创建表
	public boolean creatTable(){
		conn=getConnection();
		try{
			String sql="insert into "+table+" (name,likenumber) values (?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"baidu");
			pstmt.setInt(2,0);
			pstmt.executeUpdate();
			sql="insert into "+table+" (name,likenumber) values (?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"youdao");
			pstmt.setInt(2,0);
			pstmt.executeUpdate();
			sql="insert into "+table+" (name,likenumber) values (?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"bing");
			pstmt.setInt(2,0);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("LikeTable创建失败");
			return false;
		}
		return true;
	}
	
	//清空表
	public boolean clear(){
		conn=getConnection();
		try {
			String sql="delete from "+table;
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("清空失败");
			return false;
		}
	}
	
	public void showAll() throws SQLException,ClassNotFoundException{
		conn=getConnection();
		Statement statement=conn.createStatement();
		ResultSet resultSet=statement.executeQuery("select * from "+table);
		ResultSetMetaData rsMetaData=resultSet.getMetaData();
		for(int i=1;i<=rsMetaData.getColumnCount();i++)
			System.out.printf("%-12s\t",rsMetaData.getColumnName(i));
		System.out.println();;
		while(resultSet.next()){
			for(int i=1;i<=rsMetaData.getColumnCount();i++)
				System.out.printf("%-12s\t",resultSet.getObject(i));
			System.out.println();
		}
	}
	
	public boolean add(String name){
		conn=getConnection();
		try {
			String sql="select * from "+table+" where name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,name);
			rs=pstmt.executeQuery();
			int temp=0;
			while(rs.next()){
				String s1=rs.getString("name");
				temp=rs.getInt(2);
			}
			temp++;
			sql="update "+table+" set likenumber=? where name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,temp);
			pstmt.setString(2,name);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("添加失败");
			return false;
		}
		return true;
	}
	
	public int getLikeTimes(String s){
		conn=getConnection();
		int temp1=0;
		try {
		    String sql="select * from "+table+" where name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,s);
			rs=pstmt.executeQuery();
			while(rs.next()){
				String stemp=rs.getString("name");
				temp1=rs.getInt(2);
			}
		}
		catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取点赞失败");
		}
		return temp1;
	}
	
	
	public int[] getOrder(){
		conn=getConnection();
		int[] order=new int[3];
		try {
		    String sql="select * from "+table+" where name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"baidu");
			rs=pstmt.executeQuery();
			String s="";
			int temp1=0;
			while(rs.next()){
				s=rs.getString("name");
				temp1=rs.getInt(2);
			}
	
			sql="select * from "+table+" where name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"youdao");
			rs=pstmt.executeQuery();
			int temp2=0;
			while(rs.next()){
				s=rs.getString("name");
				temp2=rs.getInt(2);
			}
			
			sql="select * from "+table+" where name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"bing");
			rs=pstmt.executeQuery();
			int temp3=0;
			while(rs.next()){
				s=rs.getString("name");
				temp3=rs.getInt(2);
			}
			
			if(temp3>=temp2)
			    if(temp3>=temp1){
			        order[2]=1;//1指第一个显示，2指bing
			        if(temp1>=temp2){
			            order[0]=2;
			            order[1]=3;
			        }
			        else{
			            order[0]=3;
			            order[1]=2;
			        }
			    }
			    else{
			        order[0]=1;
			        order[2]=2;
			        order[1]=3;
			    }
		    else if(temp2>=temp1){
		        order[1]=1;
		        if(temp1>=temp3){
		            order[0]=2;
		            order[2]=3;
		        }
		        else{
		            order[0]=3;
		            order[2]=2;
		        }
		    }
		    else{
		        order[0]=1;
		        order[1]=2;
		        order[2]=3;
		    }
		            
		}
		catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("搜索失败");
		}
		return order;
	}
	
	public static void main(String args[]) throws SQLException, ClassNotFoundException{
		LikeJDBC A=new LikeJDBC();
		A.add("baidu");
		//A.showAll();
		//System.out.println(A.getOrder()[2]);
	}

}
