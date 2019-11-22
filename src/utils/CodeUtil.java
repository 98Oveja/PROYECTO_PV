package utils;

import java.util.Random;

public class CodeUtil {
    public static String generateCode(){
        String numeros =  "123456789";
        String letras = "abcdefghijklmnopqrstuvwxyz";
        String caracteres = "#$%&/=¡?¿";
        char [] chars = "0123456789abcdefghijklmnopqrstuvwxyz#$%&/=¡?¿ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        int charsLength = chars.length;

        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<10;i++){
            buffer.append(chars[random.nextInt(charsLength)]);
        }
        return( buffer.toString());
    }
}
