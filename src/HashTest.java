import java.util.HashMap;

/**
 * Created by Lin on 2015/7/17.
 */
public class HashTest {
    private static HashMap<String,Byte[]> transcript;
    private static HashMap<String,String> T1;
    public static void main(String[] args) throws Exception {
        String s1;
        String ByteTest="1060028C00FE02F202FE00F800FF000004047E018047300F10270047807F0000";
        byte[] hexStringToBytes;
        transcript=new HashMap<String,Byte[]>();
        T1=new HashMap<String,String>();
        T1.put("测","0x10,0x60,0x02,0x8C,0x00,0xFE,0x02,0xF2,0x02,0xFE,0x00,0xF8,0x00,0xFF,0x00,0x00");
        T1.put("试","0x00,0x00,0x00,0x3F,0x10,0x28,0x60,0x3F,0x10,0x10,0x01,0x0E,0x30,0x40,0xF0,0x00");
//                s1=T1.get("a"); null
//        System.out.println(s1);
        s1=T1.get("试");
        hexStringToBytes=hexStringToBytes(ByteTest);
                System.out.println("试:" + T1.get("试"));
        for (String name:T1.keySet()) {
            System.out.println(name);
        }
    }
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
        }

    public static byte[] hexToBytes(String hexString) {

        char[] hex = hexString.toCharArray();
        //轉rawData長度減半
        int length = hex.length / 2;
        byte[] rawData = new byte[length];
        for (int i = 0; i < length; i++) {
            //先將hex資料轉10進位數值
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            //將第一個值的二進位值左平移4位,ex: 00001000 => 10000000 (8=>128)
            //然後與第二個值的二進位值作聯集ex: 10000000 | 00001100 => 10001100 (137)
            int value = (high << 4) | low;
            //與FFFFFFFF作補集
            if (value > 127)
                value -= 256;
            //最後轉回byte就OK
            rawData[i] = (byte) value;
        }
        return rawData;
    }
    void ReadStrFile()
    {

    }
}
 