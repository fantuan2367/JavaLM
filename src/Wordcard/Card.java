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
	public void image_gengeration(String content,String location) {
		image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics graph=image.getGraphics();
		graph.setColor(Color.white);//设置背景色
		graph.fillRect(0, 0, width, height);//填充背景
		graph.setColor(Color.black);//设置前景色
		graph.setFont(new Font("宋体",Font.PLAIN,15));//设置字体
		int row=50,jump=15;
		StringBuffer temp=new StringBuffer();
		for(int i=0;i<content.length();i++) {
			if(content.charAt(i)!='\n') {
				temp.append(content.charAt(i));
			}
			else {
				graph.drawString(temp.toString(),50,row);
				row+=jump;
				temp.setLength(0);;
			}
		}
		create(location);
	}
}

