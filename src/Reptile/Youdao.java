package Reptile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Youdao extends Thread{
	public static String doTranslate(String keyword) { 
        try {
            URL url = new URL("http://fanyi.youdao.com/openapi.do");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("encoding", "UTF-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write("keyfrom=lsc777&key=1093810149&type=data&doctype=json&version=1.1&q="+keyword);
            bw.flush();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line;
            StringBuilder builder = new StringBuilder();
            while((line = br.readLine()) != null){
                builder.append(line);
            }

            bw.close();
            osw.close();
            os.close();
            br.close();
            isr.close();
            is.close();
            
            String temp=builder.toString();
            Pattern p=Pattern.compile("(.*)(\"translation\":\\[\")(.*)(\"\\],\"basic\")(.*)");
            Matcher m=p.matcher(temp);
            if(m.matches()){
            	return m.group(3);
            }
            return null;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}