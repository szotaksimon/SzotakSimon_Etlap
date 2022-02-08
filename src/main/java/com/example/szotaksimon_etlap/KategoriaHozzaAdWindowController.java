package com.example.szotaksimon_etlap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class KategoriaHozzaAdWindowController extends Controller{
    @FXML
    private TextField kategoriaNevInput;

    private EtlapDb db;

    public void initialize() throws SQLException {
        db = new EtlapDb();
    }

    @FXML
    public void kategoriaHozzaadasButtonClick(ActionEvent actionEvent) {
        String ujKategoria = kategoriaNevInput.getText().toString().trim();
        try {
            db.kategoriaAdd(ujKategoria);
            return;
        } catch (SQLException e) {
            alert("„Ezzel a névvel már létezik kategória");
            return;
        }
    }
}