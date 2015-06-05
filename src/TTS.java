import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lin on 2015/6/4.
 */
public class TTS {
    private static final String serverURL = "http://vop.baidu.com/server_api";
    private static String token = "";
    private static final String testFileName = "C:\\Users\\Lin\\IdeaProjects\\Yuyin\\out\\production\\Yuyin\\1.MP3";
    //put your own params here
    private static final String apiKey = "bd6kSzmqtlUaG1SEjbqR4R28";
    private static final String secretKey = "5eaad29500bcbd35c84bf6bfac5e9190";
    private static final String cuid = "6131442";

    public static void main(String[] args) throws Exception {
        getToken();
        method1();
    }

    private static void method1() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL).openConnection();
        File mp3File = new File(testFileName);
        // construct params
        JSONObject params = new JSONObject();
        params.put("tex", "Hello");
        params.put("lan", "zh");
        params.put("tok", token);
        params.put("ctp", 1);
        params.put("cuid", cuid);

        // add request header
                conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "audio/mp3; charset=utf-8");

        conn.setDoInput(true);
        conn.setDoOutput(true);
//        File f = new File("C://temp.txt");·j¯ÁURL MyURL = new URL("http://www.yahoo.cn");InputStream inputstream = MyURL.openStream();DataInputStream din = new DataInputStream(inputstream);BufferedWriter target = new BufferedWriter(new FileWriter(f));output = din.readUTF();target.write(output);target.close();din.close();
        // send request
        DataInputStream WR = new DataInputStream(conn.getInputStream());
        WR.close();
        printResponse(conn);
    }
    private static void method2() throws Exception {
        File pcmFile = new File(testFileName);
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL
                + "?cuid=" + cuid + "&token=" + token).openConnection();

        // add request header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "audio/amr; rate=8000");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        // send request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//        wr.write(WriteFile(pcmFile));
        wr.flush();
        wr.close();

        printResponse(conn);
    }
    private static void getToken() throws Exception {
        String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" +
                "&client_id=" + apiKey + "&client_secret=" + secretKey;
        HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
        token = new JSONObject(printResponse(conn)).getString("access_token");
        System.out.println(token);
    }
    private static String printResponse(HttpURLConnection conn) throws Exception {
        if (conn.getResponseCode() != 200) {
            // request error
            return "";
        }
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        System.out.println(new JSONObject(response.toString()).toString(4));
        return response.toString();
    }
    private static void WriteFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
    }
}
