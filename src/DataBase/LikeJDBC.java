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
	
	public static void main(String args[]) throws SQLException, ClassNotFoundException{
		LikeJDBC A=new LikeJDBC();
		A.add("baidu");
		A.showAll();
	}

}
