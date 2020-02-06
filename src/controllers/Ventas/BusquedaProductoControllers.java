package controllers.Ventas;

import com.jfoenix.controls.*;
import controllers.Compras.PanelCompras;
import controllers.Productos.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import utils.ConsultasVentasCompras;
import utils.LoadModalesMovibles;
import java.net.URL;
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

    public ModalVentas modalVentas = null;
    public PanelCompras panelCompras = null;
    Controller controller1 = new Controller();
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
    public void cargarProductoDescripcionPorMarca(String marcaName){
        ArrayList<String> arrayProductoDecricion = ConsultasVentasCompras.NombresProductoporNombreMarca(marcaName);
        Resultados.getItems().addAll(arrayProductoDecricion);
    }

    public void setControllerModalCompras(PanelCompras panel){ this.panelCompras = panel;}
    public PanelCompras getControllerModalCompras(){return this.panelCompras;}
    public void setControllerModalVenta( ModalVentas controller){this.modalVentas= controller;}
    public ModalVentas getContollerModalVenta(){return this.modalVentas;}
    public void cargarProductosDescripcionPorCategoria(String categoria){
        ArrayList<String> stringArrayListCategoriaProducto = ConsultasVentasCompras.returnNameandDescriptionByCategory(categoria);
        Resultados.getItems().addAll(stringArrayListCategoriaProducto);
    }
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_CloseModal.setOnMouseClicked(mouseEvent -> {LoadModalesMovibles.CerrarModal(ContenedorPrincipal);});
        inputText.setOnKeyTyped(keyEvent -> {
            Resultados.getItems().clear();
            arrayListResultQuerys = ConsultasVentasCompras.selectProductoByNameinLikeQuery(inputText.getText());
            Resultados.getItems().addAll(arrayListResultQuerys);
        });
        searchByCode.selectedProperty().addListener(observable -> {
            if (searchByCode.isSelected()){
                inputText.clear();
                searchByMarc.setDisable(true);
                searchByCategory.setDisable(true);
                comBoxSeleccion.getItems().clear();
                comBoxSeleccion.setVisible(false);
                inputText.requestFocus();
                inputText.setPromptText("Buscar por código");
                inputText.setOnKeyTyped(keyEvent -> {
                    Resultados.getItems().clear();
                    arrayListResultQuerys = ConsultasVentasCompras.selectProductoByCodeinLikeQuery(inputText.getText());
                    Resultados.getItems().addAll(arrayListResultQuerys);
                });

            }else{
                inputText.clear();
                comBoxSeleccion.getItems().clear();
                comBoxSeleccion.setVisible(true);
                searchByMarc.setDisable(false);
                searchByCategory.setDisable(false);
                inputText.setPromptText("Buscar por Nombre");
                inputText.setOnKeyTyped(keyEvent -> {
                    Resultados.getItems().clear();
                    arrayListResultQuerys = ConsultasVentasCompras.selectProductoByNameinLikeQuery(inputText.getText());
                    Resultados.getItems().addAll(arrayListResultQuerys);
                });
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
        Resultados.setOnMouseClicked(mouseEvent -> {
            Name_Product.setEditable(false);
            Description_Product.setEditable(false);
            try {
                String cadena = Resultados.getSelectionModel().getSelectedItem().toString();
                if (cadena.length() !=0){
                    String[] getProductinList = cadena.split(": ");
                    String producto = getProductinList[0];
                    String descript = getProductinList[1];
                    Name_Product.setText(producto);
                    Description_Product.setText(descript);
                }else {
                    System.out.println("Agrga productos a la lista para poder seleccionar por favor");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }



        });
        btn_SelectProduct.setOnAction(actionEvent -> {
            if (Name_Product.getLength()!=0 && Description_Product.getLength() !=0){
                if (getContollerModalVenta()!=null) {
                    try {
                        arrayListResultQuerys = ConsultasVentasCompras.getProductByName(Name_Product.getText());
                        String consultaCompleta = arrayListResultQuerys.get(0).toString();
                        String[] contenedorConsultaProducto = consultaCompleta.split("#");
                        ModalVentas v = getContollerModalVenta();
                        v.setProductoinComboBox(Name_Product.getText());
                        v.setdisponibilidad_text(contenedorConsultaProducto[0]);
                        v.setprecio_text(contenedorConsultaProducto[1]);
                        v.setCodigoProducto(contenedorConsultaProducto[2]);
                        v.setdescripcion_text(contenedorConsultaProducto[3]);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (getControllerModalCompras() !=null){
                    try {

                        arrayListResultQuerys = ConsultasVentasCompras.seleccionarProductoParaComprar(Name_Product.getText());
                        String consultaCompleta = arrayListResultQuerys.get(0).toString();
                        String[] contenedorConsultaProducto = consultaCompleta.split("#");
                        String maraca = contenedorConsultaProducto[0];
                        String la_marca = ConsultasVentasCompras.getNameMarcabyID(maraca);
                        PanelCompras p = getControllerModalCompras();
                        p.setComboboxProductos(Name_Product.getText());
                        p.setMarcaField(la_marca);
                        p.setDescripcionField(contenedorConsultaProducto[1]);
                        p.setCodigoField(contenedorConsultaProducto[2]);
                        p.setDisponibilidadField(contenedorConsultaProducto[3]);
                        String unodad = contenedorConsultaProducto[4];
                        p.setPrecioVentaField(contenedorConsultaProducto[5]);
                        p.setPrecioCompraField(contenedorConsultaProducto[6]);
                        System.out.println(unodad);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }


                }
            }else{
                Image imagemodal = new Image("images/info.png");
                LoadModalesMovibles.LoadAlert(getClass().getResource("/fxml/Alertas.fxml"),
                        "Atención","Selecciona un producto\n" +
                                "para poder continuar.",imagemodal,null);
            }

        });

    }
}
