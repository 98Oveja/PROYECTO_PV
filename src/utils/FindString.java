package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FindString {


    Collection<Object> name;

    public FindString(String... values){
        name = Arrays.asList(values);
    }

    public Collection<Object> getNombre() {
        return name;
    }
}
