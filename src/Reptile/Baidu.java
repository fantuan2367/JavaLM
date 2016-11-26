package Reptile;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Baidu {
    public static String doTranslate(String keyword) {
    	String url=new String("http://dict.baidu.com/s?wd="+keyword);
    	String resource = null;
        try {
            // 得到网页的内容
        	Document document = Jsoup.connect(url)
                    .get();	
            // 得到body的内容
            resource = document.getElementsByTag("body").text().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern p=Pattern.compile("(.*)(简明释义 )(.*)(查看更多解释 柯)(.*)");
        Matcher m=p.matcher(resource);
		if(m.matches()){
			StringBuffer temp=new StringBuffer(m.group(3).replaceAll(" ","\n").replaceAll("：","\n"));
			for(int i=0;i<temp.length();i++) {
				if(((temp.charAt(i)<='z')&&(temp.charAt(i)>='a'))
						&&(temp.charAt(i+1)>=300)) {
					temp.insert(i+1, '\n');
				}
			}
			return temp.toString();
		}
        return null;
    }
    
}