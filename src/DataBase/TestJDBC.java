package DataBase;

import java.sql.Connection;      
import java.sql.DriverManager;     
import java.sql.PreparedStatement;
import java.sql.Statement;         
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class TestJDBC
{
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private PreparedStatement ps=null;
	private Statement st=null;
	private ResultSet rs=null;
	String table="TestTable";
	
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
			System.out.println("数据库TestJDBC连接失败");
		}
		return con;
	}
	
	//创建新用户
	public boolean creatNew(String username,String password){
		conn=getConnection();
		try {
			String sql="insert into "+table+" (username,password) values (?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建失败");
			return false;
		}
		return true;
	}
	
	//删除用户
	public boolean delete(String username){
		conn=getConnection();
		try {
			String sql="delete from "+table+" where username=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("删除失败");
			return false;
		}
	}
	
	//搜索用户
	public ResultSet search(String username){
		conn=getConnection();
		try {
		    String sql="select * from "+table+" where username=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,username);
			rs=pstmt.executeQuery();
		}
		catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("搜索失败");
		}
		return rs;
	}
	
	//更改密码
	public boolean change(String username,String password){
		conn=getConnection();
		try {
			String sql="update "+table+" set password=? where username=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,password);
			pstmt.setString(2,username);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更改密码失败");
			return false;
		}
		return true;
	}
	
	//检验密码
	public boolean check(String username,String userPassword) {
		conn = getConnection();
		boolean flag=false;
		try {
			String sql = "select * from "+table+" where username=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,userPassword);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
			flag=true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("密码错误");
			return false;
		}
		return flag;
	}
	
	public void showAll() throws SQLException{
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
	
	public static void main(String args[]) throws SQLException{
		Values value=new Values();
		TestJDBC kkk=new TestJDBC();
		kkk.creatNew("2343", "2ss");
		ResultSet s=kkk.search("2333");
		while(s.next()){
			String s1=s.getString("username");
			System.out.println(s1);
			String s2=s.getString(2);
			System.out.println(s2);
		}
		System.out.println(kkk.check("2333", "sss"));
		kkk.showAll();
		
    }
}
        