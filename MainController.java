/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXSpinner;
import covid19.DataCollector;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

/**
 *
 * @author HP
 */
public class MainController implements Initializable {

    @FXML
    private Tab dashboardTab;
    @FXML
    private Tab tableViewTab;
    @FXML
    private Tab pieChartTab;
    @FXML
    private JFXSpinner loader;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Node node=FXMLLoader.load(getClass().getResource("/fxmls/TableView.fxml"));
            tableViewTab.setContent(node);
            Node node1=FXMLLoader.load(getClass().getResource("/fxmls/DashBoard.fxml"));
            dashboardTab.setContent(node1);
            Node node2=FXMLLoader.load(getClass().getResource("/fxmls/PieChart.fxml"));
            pieChartTab.setContent(node2);
            
            loading();
        } catch (IOException ex) 
        {
            ex.printStackTrace();

        }
        
               
    }    
    private void loading()
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        
                         DataCollector dataCollector=new DataCollector();
                         dataCollector.collector();
                         Platform.runLater(new Runnable() {
                             @Override
                             public void run() {
                                 loader.setVisible(false);
                             }
                         });
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                       
                }
            }).start();
        }

    
}
