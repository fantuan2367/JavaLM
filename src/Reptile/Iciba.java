package Reptile;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Iciba {
	public static String doTranslate(String keyword) {
		String resource = null;
		try {
			// 得到网页的内容
			Document document = Jsoup
					.connect("http://dict-co.iciba.com/api/dictionary.php?w="+keyword+"&key=4AAC6F23945055569DC8AFB098632EF7")
					.ignoreContentType(true).get();	
			// 得到body的内容
			resource = document.getElementsByTag("body").text().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//正则表达式确定范围
		Pattern p=Pattern.compile("(.*)(\\.mp3 )(.*)(；)(.*)");
		Matcher m=p.matcher(resource);
		if(m.matches()){
			return (m.group(3)).replaceAll("\n","").replaceAll("；","；\n")
					.replaceAll("n. ","n.\n").replaceAll("v.","v.\n")
					.replaceAll("adj.","adj.\n").replaceAll("prep.","prep.\n")
					.replaceAll("abbr. ","abbr.\n").replaceAll("conj.","conj.\n")
					.replaceAll("adv.", "adv.\n");
		}
		return null;
	}
}