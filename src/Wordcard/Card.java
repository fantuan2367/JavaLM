package Wordcard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Card {
	private BufferedImage image;
	private int height=1000,width=800;
	private void create(String location) {
		try {
			FileOutputStream s=new FileOutputStream(location);
			BufferedOutputStream bos = new BufferedOutputStream(s);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void image_gengeration(String from,String content,String location) {
		image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics graph=image.getGraphics();
		graph.setColor(Color.white);//设置背景色
		graph.fillRect(0, 0, width, height);//填充背景
		graph.setColor(Color.black);//设置前景色
		int row=70,jump=15;
		graph.setFont(new Font("幼圆",Font.BOLD,20));
		graph.drawString("                     From:  "+from,100,50);
		StringBuffer temp=new StringBuffer();
		for(int i=0;i<content.length();i++) {
			if(content.charAt(i)!='\n') {
				temp.append(content.charAt(i));
			}
			else {
				String word_to_print=temp.toString();
				if(word_to_print.compareTo("baidu")==0) {
					row+=2*jump;
					temp=new StringBuffer("百度");
					graph.setFont(new Font("黑体",Font.BOLD,30));
					graph.drawString(temp.toString(),50,row);
					row+=2*jump;
					temp.setLength(0);
					continue;
				}
				else if(word_to_print.compareTo("youdao")==0) {
					row+=2*jump;
					temp=new StringBuffer("有道");
					graph.setFont(new Font("黑体",Font.BOLD,30));
					graph.drawString(temp.toString(),50,row);
					row+=2*jump;
					temp.setLength(0);
					continue;
				}
				else if(word_to_print.compareTo("iciba")==0) {
					row+=2*jump;
					temp=new StringBuffer("Iciba");
					graph.setFont(new Font("黑体",Font.BOLD,30));
					graph.drawString(temp.toString(),50,row);
					row+=2*jump;
					temp.setLength(0);
					continue;
				}
				else{
					graph.setFont(new Font("宋体",Font.PLAIN,15));
					graph.drawString(temp.toString(),50,row);
					row+=jump;
					temp.setLength(0);
					continue;
				}
			}
		}
		create(location);
	}
}

