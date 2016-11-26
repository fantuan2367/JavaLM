import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import net.sf.json.JSONObject;

public class Baidu {
	//dsasasadsda
    // ������
    public String doTranslate(String keyword) {
    	String from="en";
    	String to="zh";
    	String resource = null;
        try {
            // �õ���ҳ������
        	Document document = Jsoup
                    .connect("http://fanyi.baidu.com/transapi?from=" + from + "&to=" + to + "&query=" + keyword)
                    .ignoreContentType(true).get();	
            // �õ�body������
            resource = document.getElementsByTag("body").text().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ��Դ��ת��jsonobject
        JSONObject object = JSONObject.fromObject(resource);
        String temp = object.getString("data");
        temp = temp.substring(1, temp.indexOf(",\"result"));
        temp += "}";
        JSONObject data = JSONObject.fromObject(temp);
        // �õ�����������
        return data.getString("dst");
    }
    
}