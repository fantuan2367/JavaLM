package Wordcard;

public class CardValue{
	private String toClient;
	private String content;
	private String fromClient;
	private int readOrNot;
	
	public CardValue(){
		
	}
	
	public CardValue(String toclient,String fromclient,String content){
		this.toClient=toclient;
		this.fromClient=fromclient;
		this.content=content;
		this.readOrNot=0;
	}
	
	public void Read(){
		this.readOrNot=1;
	}
	
	public boolean ReadOrNot(){
		if(this.readOrNot==0)
			return true;//没有被读
		else return false;//有被读
	}
	public String getToClient(){
		return this.toClient;
	}
	public String getFromClient(){
		return this.fromClient;
	}
	public String getContent(){
		return this.content;
	}

}
