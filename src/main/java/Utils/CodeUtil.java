package Utils;

import java.util.Random;

public class CodeUtil {
    String code;
    public static String generateCode(){

        char [] chars = "0123456789abcdefghijklmnopqrstuvwxyz#$%&/=¡?¿ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        int charsLength = chars.length;

        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<10;i++){
            buffer.append(chars[random.nextInt(charsLength)]);
        }
        return  buffer.toString();
    }
    public  String getCode() {
        return code;
    }
}
