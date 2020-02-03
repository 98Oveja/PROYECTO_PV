package utils;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ValidacionesGenerales {
    public static void validarSoloNumeros(TextField campo){
        campo.addEventFilter(KeyEvent.ANY, event ->{
            char c = event.getCharacter().charAt(0);
            if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c!='.'){
                event.consume();
            }
            if (c == '.' && campo.getText().contains(".")){
                event.consume();
            }
        });
    }
    public static void validarSoloNumerosJfoenix(JFXTextField campo){
        campo.addEventFilter(KeyEvent.ANY, event ->{
            char c = event.getCharacter().charAt(0);
            if (!(Character.isDigit(c) ||
                    Character.isWhitespace(c) ||
                    Character.isISOControl(c)) && c!='.' && c!='/'){
                event.consume();
            }
            if (c == '.' && campo.getText().contains(".")){
                event.consume();
            }
            if (c == '/' && campo.getText().contains("/")){
                event.consume();
            }
        });
    }
    public static boolean isNifNumValid(String nif){
        //Si el largo del NIF es diferente a 9, acaba el método.
        if (nif.length()!=9){
            return false;
        }

        String secuenciaLetrasNIF = "TRWAGMYFPDXBNJZSQVHLCKE";
        nif = nif.toUpperCase();

        //Posición inicial: 0 (primero en la cadena de texto).
        //Longitud: cadena de texto menos última posición. Así obtenemos solo el número.
        String numeroNIF = nif.substring(0, nif.length()-1);

        //Si es un NIE reemplazamos letra inicial por su valor numérico.
        numeroNIF = numeroNIF.replace("X", "0").replace("Y", "1").replace("Z", "2");

        //Obtenemos la letra con un char que nos servirá también para el índice de las secuenciaLetrasNIF
        char letraNIF = nif.charAt(8);
        int i = Integer.parseInt(numeroNIF) % 23;
        return letraNIF == secuenciaLetrasNIF.charAt(i);
    }
    public static void validarNit(JFXTextField campo){
        campo.addEventFilter(KeyEvent.ANY, event ->{
            char cc = event.getCharacter().charAt(0);
                if (!(Character.isDigit(cc) || Character.isWhitespace(cc)
                        || Character.isISOControl(cc)) && cc != '-' && cc != 'A' && cc != 'B'
                        && cc != 'C' && cc != 'D' && cc != 'E' && cc != 'F' && cc != 'G'
                        && cc != 'H' && cc != 'J' && cc != 'N' && cc != 'P' && cc != 'Q' && cc != 'R'
                        && cc != 'S' && cc != 'U' && cc != 'V' && cc != 'W') {
                    event.consume();
                }
                if (cc == '-' && campo.getText().contains("-") ||
                        cc == 'A' && campo.getText().contains("A") ||
                        cc == 'B' && campo.getText().contains("B") ||
                        cc == 'C' && campo.getText().contains("C") ||
                        cc == 'D' && campo.getText().contains("D") ||
                        cc == 'E' && campo.getText().contains("E") ||
                        cc == 'F' && campo.getText().contains("F") ||
                        cc == 'G' && campo.getText().contains("G") ||
                        cc == 'H' && campo.getText().contains("H") ||
                        cc == 'J' && campo.getText().contains("J") ||
                        cc == 'N' && campo.getText().contains("N") ||
                        cc == 'P' && campo.getText().contains("P") ||
                        cc == 'Q' && campo.getText().contains("Q") ||
                        cc == 'R' && campo.getText().contains("R") ||
                        cc == 'S' && campo.getText().contains("S") ||
                        cc == 'U' && campo.getText().contains("U") ||
                        cc == 'V' && campo.getText().contains("V") ||
                        cc == 'W' && campo.getText().contains("W")) {
                    event.consume();
                }
        });
    }
    public static void validarNumTelefono(TextField campo, int tamanio){
        campo.addEventFilter(KeyEvent.ANY, event ->{
            char c = event.getCharacter().charAt(0);
            int tamCampo = campo.getText().length();
            if (Character.isDigit(c) || Character.isISOControl(c)) {
                if (tamCampo >= tamanio && !(Character.isISOControl(c))) {event.consume();}
            }else{event.consume();}
        });
    }
    public static boolean camposVacios(TextField jfxTextField){
        return jfxTextField.getLength()!=0?true:false;
    }



}
