/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import covid19.Case;
import covid19.DataManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TableViewController implements Initializable {

    @FXML
    private TableView<Case> table;
    @FXML
    private TableColumn<Case, Integer> sno;
    @FXML
    private TableColumn<Case, String> state;
    @FXML
    private TableColumn<Case, Integer> activeCases;
    @FXML
    private TableColumn<Case, Integer> cured;
    @FXML
    private TableColumn<Case, Integer> death;
    @FXML
    private TableColumn<Case, Integer> confirmedCase;

    private DataManager dataManager;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataManager =new DataManager();
        sno.setCellValueFactory(new PropertyValueFactory("sno"));
        state.setCellValueFactory(new PropertyValueFactory("state"));
        activeCases.setCellValueFactory(new PropertyValueFactory("activeCases"));
        cured.setCellValueFactory(new PropertyValueFactory("cured"));
        death.setCellValueFactory(new PropertyValueFactory("deaths"));
        confirmedCase.setCellValueFactory(new PropertyValueFactory("confirmedCases"));
        ObservableList<Case> data =table.getItems();
        dataManager.getData().addListener(new ListChangeListener<Case>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Case> c) {
              table.getItems().clear();
              table.getItems().addAll(dataManager.getData());
            }
        });
    }    
    
}
