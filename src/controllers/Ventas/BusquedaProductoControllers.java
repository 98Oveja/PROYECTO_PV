package controllers.Ventas;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import utils.Consultas;
import utils.ConsultasVentasCompras;
import utils.LoadModalesMovibles;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BusquedaProductoControllers implements Initializable {
    public StackPane ContenedorPrincipal;
    public ImageView btn_CloseModal;
    public ImageView btn_Search;
    public JFXTextField inputText;
    public JFXToggleButton searchByCode;
    public JFXToggleButton searchByMarc;
    public JFXToggleButton searchByCategory;
    public JFXComboBox comBoxSeleccion;
    public TextField Name_Product;
    public TextField Description_Product;
    public JFXButton btn_SelectProduct;
    public JFXListView Resultados;

    public ArrayList arrayListResultQuerys;
    public String stringQuetyData;

    public void cargarMarcas(){
        ArrayList<String> arrayList = ConsultasVentasCompras.ListadeMarcas();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(arrayList);
        comBoxSeleccion.setItems(items);
        comBoxSeleccion.setVisibleRowCount(3);
    }
    public void cargarCategorias(){
        ArrayList<String> arrayList = ConsultasVentasCompras.ListadeCategorias();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(arrayList);
        comBoxSeleccion.setItems(items);
        comBoxSeleccion.setVisibleRowCount(3);
    }
    public void cargarProductosporMarca(String marca){
        ArrayList <String> arrayList; arrayList = ConsultasVentasCompras.ListadeProductosPorNombreMarca(marca);
    }
    public void cargarProductoDescripcionPorMarca(String marcaName){
        ArrayList<String> arrayProductoDecricion = ConsultasVentasCompras.NombresProductoporNombreMarca(marcaName);
        Resultados.getItems().addAll(arrayProductoDecricion);
    }
    public void cargarProductosDescripcionPorCategoria(String categoria){
        ArrayList<String> stringArrayListCategoriaProducto = ConsultasVentasCompras.returnNameandDescriptionByCategory(categoria);
        Resultados.getItems().addAll(stringArrayListCategoriaProducto);
    }



    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_CloseModal.setOnMouseClicked(mouseEvent -> {LoadModalesMovibles.CerrarModal(ContenedorPrincipal);});
        btn_Search.setOnMouseClicked(mouseEvent -> {
        });
        searchByCode.selectedProperty().addListener(observable -> {
            if (searchByCode.isSelected()){
                inputText.clear();
                searchByMarc.setDisable(true);
                searchByCategory.setDisable(true);
                comBoxSeleccion.getItems().clear();
                comBoxSeleccion.setVisible(false);
                inputText.requestFocus();
            }else{
                inputText.clear();
                comBoxSeleccion.getItems().clear();
                comBoxSeleccion.setVisible(true);
                searchByMarc.setDisable(false);
                searchByCategory.setDisable(false);
            }
        });
        searchByMarc.selectedProperty().addListener(observable -> {
            if (searchByMarc.isSelected()){
                inputText.clear();
                searchByCategory.setDisable(true);
                searchByCode.setDisable(true);
                cargarMarcas();
                inputText.setVisible(false);
                comBoxSeleccion.requestFocus();
                comBoxSeleccion.setOnAction(actionEvent -> {
                    try {
                        Resultados.getItems().clear();
                        stringQuetyData = comBoxSeleccion.getValue().toString();
//                        cargarProductosporMarca(stringQuetyData);
                        cargarProductoDescripcionPorMarca(stringQuetyData);
                    }catch (Exception e){
                        System.out.println(e.getMessage()+" Error al cargar el Producto por Marca");
                    }

                });


            }else{
                inputText.clear();
                inputText.setVisible(true);
                searchByCategory.setDisable(false);
                searchByCode.setDisable(false);
            }
        });
        searchByCategory.selectedProperty().addListener(observable -> {
            if (searchByCategory.isSelected()){
                inputText.clear();
                searchByMarc.setDisable(true);
                searchByCode.setDisable(true);
                cargarCategorias();
                comBoxSeleccion.requestFocus();
                comBoxSeleccion.setOnAction(actionEvent -> {
                    try {
                        Resultados.getItems().clear();
                        stringQuetyData = comBoxSeleccion.getValue().toString();
                        cargarProductosDescripcionPorCategoria(stringQuetyData);
                    }catch (Exception e){
                        System.out.println(e.getMessage()+" Error al cargar los producto por categoria");
                    }

                });


                inputText.setVisible(false);
            }else{
                inputText.clear();
                inputText.setVisible(true);
                searchByMarc.setDisable(false);
                searchByCode.setDisable(false);
            }
        });
    }
}
