package com.example.szotaksimon_etlap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class KategoriaWindowController extends Controller{

    private EtlapDb db;
    private List<Kategoria> kategoriak;

    @FXML
    private ListView<Kategoria> kategoriakListView;

    public void initialize() throws SQLException {
        db = new EtlapDb();
        kategoriakFeltolt();
    }

    @FXML
    public void kategoriaHozzaadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller kategoriaHozzaadas = ujWindow("kategoria_hozzad_window.fxml", "Kategória hozzáadás", 250,100);
            kategoriaHozzaadas.getStage().setOnCloseRequest(windowEvent -> {
                try {
                    kategoriakFeltolt();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            kategoriaHozzaadas.getStage().show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void kategoriakFeltolt() throws SQLException {
        kategoriak = db.getKategoriak();
        kategoriakListView.getItems().clear();
        ObservableList<Kategoria> kategoriakObservedList = FXCollections.observableArrayList(kategoriak);
        kategoriakListView.setItems(kategoriakObservedList);
    }

    @FXML
    public void kategoriaTorlesButtonClick(ActionEvent actionEvent){
        Kategoria obj = kategoriakListView.getSelectionModel().getSelectedItem();
        if (obj != null){
            String uzenet = String.format("Biztos törölni akarja a %s nevű kategóriát?", obj.getNev());
            if (confirm(uzenet)){
                try {
                    db.kategoriaTorles(obj.getId());
                    kategoriakFeltolt();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }else{
            alert("Nincs kiválasztva kategória!");
        }
    }
}