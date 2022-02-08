package com.example.szotaksimon_etlap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class HozzaAdWindowController extends Controller{

    @FXML
    private TextArea tfLeiras;
    @FXML
    private ChoiceBox<Kategoria> cbKategoria;
    @FXML
    private Spinner<Integer> spAr;
    @FXML
    private TextField tfNev;

    private EtlapDb db;
    private List<Kategoria> kategoriak;

    public void initialize() throws SQLException {
        db = new EtlapDb();
        kategoriak = db.getKategoriak();
        ObservableList<Kategoria> kategoriaObservableList = FXCollections.observableArrayList(kategoriak);
        cbKategoria.setItems(kategoriaObservableList);
        cbKategoria.getSelectionModel().selectFirst();
    }

    @FXML
    public void etelHozzaadasButtonClick(ActionEvent actionEvent) {
        boolean isTrue = true;
        String nev = tfNev.getText().toString();
        if (nev.isEmpty()){
            alert("A név nem lehet üres!");
            return;
        }
        String leiras = tfLeiras.getText().toString();
        if (leiras.isEmpty()){
            alert("A leírás nem lehet üres!");
            return;
        }
        Kategoria kat = cbKategoria.getSelectionModel().getSelectedItem();
        if (kat == null){
            alert("Kötelező kiválasztani kategóriát!");
            return;
        }
        int ar = spAr.getValue();
        if (ar == 0 || ar < 1){
            alert("Az ár nem lehet nulla vagy annál kisebb!");
            return;
        }
        try {
            int siker = db.etelAdd(nev, leiras, ar, kat.getId());
            if (siker == 1){
                alert("Étel hozzáadása sikeres!");
                tfLeiras.setText("");
                spAr.getEditor().setText("1000");
                tfNev.setText("");
            }else{
                alert("Étel hozzáadása sikertelen!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
