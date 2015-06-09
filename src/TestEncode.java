import java.net.URI;
import java.net.URLEncoder;

/**
 * Created by Lin on 2015/6/8.
 * http://tool.chinaz.com/Tools/URLEncode.aspx
 */
public class TestEncode {
    public static void main(String[] args) throws Exception {
        //%e7%8e%b0%e5%9c%a8%e6%97%b6%e9%97%b4
        String s1="????";
        String s2;
        s1=new String(s1.getBytes("gb2312"),"utf-8");

//        param=java.net.URLDecoder.decode(s1, "UTF-8");

        System.out.println(s1);
//     System.out.println(URLEncoder.encode(s1, "GBK"));
        System.out.println("22222");
//        System.out.println(URLEncoder.encode("????", "GBK"));
        System.out.println(URLEncoder.encode("????", "utf-8"));
        System.out.println(URLEncoder.encode("????", "utf-8"));
//        A1();
        String chinese = "?¦b??";//java?³¡??
        String gbkChinese = new String(chinese.getBytes("GBK"),"ISO-8859-1");//???gbk??
        String unicodeChinese = new String(gbkChinese.getBytes("ISO-8859-1"),"GBK");//java????
        System.out.println(URLEncoder.encode(unicodeChinese,"utf-8"));//??
        String utf8Chinese = new String(unicodeChinese.getBytes("UTF-8"),"ISO-8859-1");//utf--8??
        System.out.println(URLEncoder.encode(utf8Chinese,"utf-8"));//??
        unicodeChinese = new String(utf8Chinese.getBytes("ISO-8859-1"),"UTF-8");//java????
        System.out.println(unicodeChinese);//??

    }
    public static  void  A1()
    {
        final String ENCODING = "UTF-8";

        try {
            String queryString = "a=" + URLEncoder.encode("????", ENCODING) ;


            URI uri = new URI("http", null, "example.com", -1, "/accounts", queryString, null);
            System.out.println(uri);

        }
        catch ( Exception E)
        {

        }

    }
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
