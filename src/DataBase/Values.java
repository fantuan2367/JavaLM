package DataBase;
public class Values
{
	private String username;
	private String password;
	private int online;
	
	public Values(){
		this.username=null;
		this.password=null;
		this.online=0;
	}
	
	public Values(String username,String password){
		this.username=username;
		this.password=password;
		this.online=0;
	}
	
	public Values(String username,String password,int online){
		this.username=username;
		this.password=password;
		this.online=online;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public int OnlineOrNot(){
		return online;
	}
	
	public void setOnline(){
		this.online=1;
	}
	
	public void setNotOnline(){
		this.online=0;
	}
	
}