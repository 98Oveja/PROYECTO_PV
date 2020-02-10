package controllers.Ventas;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Ventas_Compras.Ventas;
import utils.ConsultasVentasCompras;
import utils.LoadModalesMovibles;
import utils.ValidacionesGenerales;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class ModalVentas implements Initializable{
    @FXML public Button btnCerrarModal;
    @FXML public JFXButton addNewCustomer;
    @FXML public JFXButton btn_agregarVenta;
    @FXML public JFXButton btn_venderTodo;
    @FXML public JFXTextField direccion_text;
    @FXML public JFXTextField telefono_text;
    @FXML public JFXTextField nit_text;
    @FXML public JFXTextField descripcion_text;
    @FXML public JFXTextField precio_text;
    @FXML public JFXTextField cantidad_text;
    @FXML public JFXTextField disponibilidad_text;
    @FXML public JFXTextField ClienteText;
    @FXML public JFXDatePicker fecha_text;
    @FXML public BorderPane borderPVentas;
    @FXML public JFXComboBox<String> listadoClientes;
    @FXML public JFXComboBox<String> listadoProductos;
    @FXML public TableView<Ventas> tablaDetalle;
    @FXML public TableColumn colNumero;
    @FXML public TableColumn colCantidad;
    @FXML public TableColumn colCodigo;
    @FXML public TableColumn colProducto;
    @FXML public TableColumn colPrecio;
    @FXML public TableColumn colSubtotal;
    @FXML public TableColumn colEditar;
    @FXML public TableColumn colEliminar;
    @FXML public ImageView buscar;
    @FXML public JFXCheckBox autoRellenar;
    @FXML public TextField Total_field;
    Ventas ventas1,ventasAuxiliar,ventas = new Ventas();
    private ObservableList<Ventas> ventasObservableList;
    public URL urlEditar, urlEliminar;
    public Image imgEditar, imgEliminar;
    public int cuantoCamposVacios;
    public String search_id;
    public ArrayList result_querys;
    public String Datos_de_las_Querys;
    public String CODIGOPRODUCTO ="";
    public int numeros = 0;
    public void cargarClientes(){
        ArrayList<String> arrayList = ConsultasVentasCompras.listadoClientes();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(arrayList);
        listadoClientes.setItems(items);
        listadoClientes.setVisibleRowCount(3);
    }
    public void cargarProductos(){
        ArrayList<String> arrayProductos = ConsultasVentasCompras.listaProductos();
        ObservableList<String> itemsProd = FXCollections.observableArrayList();
        itemsProd.addAll(arrayProductos);
        listadoProductos.setItems(itemsProd);
        listadoProductos.setVisibleRowCount(3);
    }
    public void mostrarFecha(){
        LocalDate localDate = LocalDate.now();
        fecha_text.setValue(localDate);
    }
    public int todolosCamposVacios(){
        cuantoCamposVacios = 0;
        if (ValidacionesGenerales.camposVacios(direccion_text)) {
            cuantoCamposVacios += 1;
        }
        if (ValidacionesGenerales.camposVacios(telefono_text)) {
            cuantoCamposVacios += 1;
        }
        if (ValidacionesGenerales.camposVacios(nit_text)) {
            cuantoCamposVacios += 1;
        }
        if (ValidacionesGenerales.camposVacios(cantidad_text)) {
            cuantoCamposVacios += 1;
        }
        return cuantoCamposVacios;
    }
    public void modeloAsignacionEnTabla(){
        ventasObservableList = FXCollections.observableArrayList();
        this.colNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.colCodigo.setCellValueFactory(new PropertyValueFactory<>("CodigoProducto"));
        this.colProducto.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));
        this.colSubtotal.setCellValueFactory(new PropertyValueFactory<>("SubTotal"));
        this.colEditar.setCellValueFactory(new PropertyValueFactory<>("Editar"));
        this.colEliminar.setCellValueFactory(new PropertyValueFactory<>("Eliminar"));
    }
    public void activarNodos(){
        Image imagemodal = new Image("images/info.png");
        LoadModalesMovibles.LoadAlert(
                getClass().getResource("/fxml/Alertas.fxml"),
                "Verificación",
                "Verifica que todo los campos\n" +
                        "esten llenos.\n"+
                        "Para poder continuar preciona"+"\nsobre cualquier Boton",
                imagemodal,
                null
        );
    }
    public void desactivarNodos(){
        addNewCustomer.setDisable(true);
        listadoClientes.setDisable(true);
        telefono_text.setDisable(true);
        direccion_text.setDisable(true);
        nit_text.setDisable(true);
        fecha_text.setDisable(true);
        ClienteText.setDisable(true);
        autoRellenar.setDisable(true);
    }
    public void autoRellenarCampos(){
        this.ClienteText.setText("Consumidor Final");
        this.direccion_text.setText("Ciudad");
        this.telefono_text.setText("00000000");
        this.nit_text.setText("C/F");
    }
    public void vaciarautoRellenarCampos(){
        this.ClienteText.setText("");
        this.direccion_text.setText("");
        this.telefono_text.setText("");
        this.nit_text.setText("");
    }
    public double calCulaElTotal(){
        double total = 0;
        for (Ventas vtot:ventasObservableList) {
            total += vtot.getSubTotal();
        }return total;
    }
//    geters and seters
    public void setNuevoTotal(Double elTotal){this.Total_field.setText(String.valueOf(elTotal));}
    public void setdisponibilidad_text(String diponibilidad){this.disponibilidad_text.setText(diponibilidad);}
    public void setprecio_text(String precio){this.precio_text.setText(precio);}
    public void setCodigoProducto(String codigo){this.CODIGOPRODUCTO = codigo;}
    public void setdescripcion_text(String descipcion){this.descripcion_text.setText(descipcion);}
    public String getDescripcion(){return descripcion_text.getText();}
    public void setProductoinComboBox(String producto){this.listadoProductos.setValue(producto);}
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
//      ACCIONES DE LOS BOTONES Y OTROS COMPONENTES DENTRO DEL MODAL
        buscar.setOnMouseClicked(mouseEvent -> {
            try{
                BusquedaProductoControllers busquedas =
                        (BusquedaProductoControllers) LoadModalesMovibles.LoadModalMovible(getClass().getResource("/fxml/Ventas/BusquedaProductos.fxml"),
                                null);
                busquedas.setControllerModalVenta(this);
            }catch (Exception e){ System.out.println("Error al cargar el modal de Busqueda");}
        });
        btnCerrarModal.setOnAction(actionEvent -> {
            try {
                Image imageModal = new Image("/images/info.png");
                LoadModalesMovibles.LoadAlert(getClass().getResource("/fxml/Alertas.fxml"),
                        "Cerra el Panel",
                        "¿Estas seguro de cerra el Panel?",
                        imageModal,
                        borderPVentas);
            }catch (Exception e){System.out.println("Error al querer cerrar el modal");}
        });
        addNewCustomer.setOnAction(actionEvent -> {
            try {
                LoadModalesMovibles.LoadModalMovible(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"),null);
            }catch (Exception e){
                System.out.println("Erro al cargar el modal de Clientes");
            }
        });
        btn_agregarVenta.setOnAction(actionEvent -> {
            try {
                if (todolosCamposVacios()<4){activarNodos();}
                else {
                    desactivarNodos();
                    urlEditar = getClass().getResource("/images/edit.png");
                    urlEliminar = getClass().getResource("/images/icons8_delete_50px_1.png");
                    imgEditar = new Image(urlEditar.toString(),16,16,false,true);
                    imgEliminar = new Image(urlEliminar.toString(),16,16,false,true);
                    JFXButton editJfxButton = new JFXButton();
                    JFXButton deletJfxButton = new JFXButton();
                    editJfxButton.setGraphic(new ImageView(imgEditar));
                    deletJfxButton.setGraphic(new ImageView(imgEliminar));
                    numeros = ventasObservableList.size();
                    ventas1 = new Ventas(
                            (numeros+1),
                            Integer.parseInt(cantidad_text.getText()),
                            CODIGOPRODUCTO,
                            listadoProductos.getValue()+" "+descripcion_text.getText(),
                            Double.parseDouble(precio_text.getText()),
                            (Double.parseDouble(precio_text.getText())*Integer.parseInt(cantidad_text.getText())),
                            editJfxButton,
                            deletJfxButton);
                    ventas1.setNOMBREPRODUCTO(listadoProductos.getValue());
                    ventas1.setModalVentas(this);
                    if(!ventasObservableList.contains(ventas1)){
                        this.ventasObservableList.add(ventas1);
                        this.tablaDetalle.setItems(ventasObservableList);
                        ventas1.setVentasObservableListAux(ventasObservableList);
                        ventas1.setTableViewAux(this.tablaDetalle);
                        numeros++;
                        ventasAuxiliar = ventas1;
                        Total_field.setText(String.valueOf(calCulaElTotal()));
                    } else{
                        Image imagemodal = new Image("images/info.png");
                        LoadModalesMovibles.LoadAlert(getClass().getResource("/fxml/Alertas.fxml"),
                                "Verifica","El "+"'"+listadoProductos.getValue().toUpperCase()+"'"+"\nYa esta en la tabla",
                                imagemodal,null);
                    }
                }
            }catch (Exception e){
                System.out.println("Erro al cargar los datos a la tabla");
            }
        });
        listadoClientes.setOnAction(actionEvent -> {
            try{
                String Nombre_Apellido_Cliente = listadoClientes.getValue();
                String[] SeparadaCadena = Nombre_Apellido_Cliente.split(" ");
                String nombreCliente = SeparadaCadena[0];
                String apelliCliente = SeparadaCadena[1];
                ClienteText.setText(nombreCliente +" "+ apelliCliente);
                search_id = ConsultasVentasCompras.getIdCostumerInDB(nombreCliente,apelliCliente);
                result_querys = ConsultasVentasCompras.getCustomerDatabyId(search_id);
                Datos_de_las_Querys = result_querys.get(0).toString();
                String[] getAllDataCustomer = Datos_de_las_Querys.split("#");
                telefono_text.setText(getAllDataCustomer[0]);
                direccion_text.setText(getAllDataCustomer[1]);
                nit_text.setText(getAllDataCustomer[2]);
            }catch (Exception e){
                System.out.println("Erro al cargar los datos del cliente "+ e.getMessage()+" localizacion "+e.getLocalizedMessage());
            }
        });
        listadoProductos.setOnAction(actionEvent -> {
            try {
                Datos_de_las_Querys = listadoProductos.getValue();
                result_querys= ConsultasVentasCompras.getProductByName(Datos_de_las_Querys);
                String consultaCompleta = result_querys.get(0).toString();
                String[] contenedorConsultaProducto = consultaCompleta.split("#");
                disponibilidad_text.setText(contenedorConsultaProducto[0]);
                precio_text.setText(contenedorConsultaProducto[1]);
                CODIGOPRODUCTO = contenedorConsultaProducto[2];
                descripcion_text.setText(contenedorConsultaProducto[3]);
//                System.out.println("Marca id "+contenedorConsultaProducto[4]);
//                System.out.println("El nombre de la marca es el: "+ConsultasVentasCompras.getNameMarcabyID(contenedorConsultaProducto[4]));
                cantidad_text.requestFocus();
            }catch (Exception e){
                System.out.println("Erro al cargar los datos del producto "+ e.getMessage()+" localizacion "+e.getLocalizedMessage());
            }
        });
        autoRellenar.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            try {
                if (autoRellenar.isSelected()) {
                    autoRellenarCampos();
                } else {
                    vaciarautoRellenarCampos();
                }
            }catch (Exception e){ System.out.println("Ne es posible Auto rrellenar el campo");}

        });
//      ASIGNACION DE VALORES INICIALES
        descripcion_text.setEditable(false);
        precio_text.setEditable(true);
        fecha_text.setDisable(true);
//        fecha_text.setEditable(false);
        disponibilidad_text.setEditable(false);
//      VALIDACIONES EXTERNAS
        ValidacionesGenerales.validarNumTelefono(telefono_text,8);
        ValidacionesGenerales.validarNit(nit_text);
        ValidacionesGenerales.validarSoloNumerosJfoenix(cantidad_text);
//      CARGANDO LOS METODOS PROPIOS
        mostrarFecha();
        cargarClientes();
        cargarProductos();
        modeloAsignacionEnTabla();
    }
}
