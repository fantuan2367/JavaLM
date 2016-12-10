package Reptile;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Youdao {
    public static String doTranslate(String keyword) {
    	String url=new String("http://dict.youdao.com/w/eng/"+keyword);
    	String resource = null;
        try {
            // �õ���ҳ������
        	Document document = Jsoup.connect(url)
                    .get();	
            // �õ�body������
            resource = document.getElementsByTag("body").text().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern p=Pattern.compile("(.*)(���� )(.*)(��������)(.*)");
        Matcher m=p.matcher(resource);
		if(m.matches()){
			return (m.group(3)).replaceAll(" adj","\nadj")
					.replaceAll(" n","\nn").replaceAll(" adv","\nadv")
					.replaceAll(" prop","\nprop").replaceAll(" conj","\nconj")
					.replaceAll(" vi","\nvi").replaceAll(" vt","\nvt");
		}
        return null;
    }
    
}