package Controllers.Productos;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class eventos {
    public void validarSoloLetras(TextField campoDeTexto) {
        campoDeTexto.addEventFilter(KeyEvent.ANY, event -> {
            char c = event.getCharacter().charAt(0);
            if (!(Character.isLetter(c)|| Character.isWhitespace(c) || Character.isISOControl(c))){
                event.consume();
                //System.out.println("Dato no Valido: "+c);
            }
        });
    }
    public void validarSoloNumeros(TextField campo) {
        campo.addEventFilter(KeyEvent.ANY, event -> {
            char c = event.getCharacter().charAt(0);
            //System.out.println(c);
            if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c != '.') {
                event.consume();
//                   System.out.println("Dato no valido: "+event.getCode());
            }
            if (c == '.' && campo.getText().contains(".")) {
                //    System.out.println("No se puede almacenar ms de 2 puntos");
                event.consume();
            }
        });
    }
}