package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseEmail{
    private String email = null;
    Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public ParseEmail(){
    }

    public boolean isValid(String email){
        Matcher mather = pattern.matcher(email);
        if (mather.find()== true) {
            return true;
        }else {
            return false;
        }
    }

}
