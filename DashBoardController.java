/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import covid19.Case;
import covid19.DataManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashBoardController implements Initializable {

    @FXML
    private BarChart barChart;
    @FXML
    private JFXComboBox comboBox;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choice();
        new DataManager().getData().addListener(new ListChangeListener<Case>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Case> c) {
                
                Chart("Active Cases");
            }
        });
        
        
        
       
    }
    private void Chart(String valueType)
    {
        this.barChart.getData().clear();
        ObservableList<Case> cases=new DataManager().getData();
        XYChart.Series series=new XYChart.Series();
        for(int i=0;i<cases.size();i++)
        {
            Case cs=cases.get(i);
            int value=1;
            if(valueType=="Active Cases")
            {
                value=cs.getActiveCases();
            }
            else if(valueType=="Cured")
            {
                value=cs.getCured();
            }
            else if(valueType=="Deaths")
            {
                value=cs.getDeaths();
            }
            else if(valueType=="Total Confirmed Cases")
            {
                value=cs.getConfirmedCases();
            }
            series.setName(valueType);
            series.getData().add(new XYChart.Data<>(cs.getState(),value));
        }
        barChart.getData().addAll(series);
    }
        
        private void choice()
        {
            comboBox.getItems().addAll("Active Cases","Cured","Deaths","Total Confirmed Cases");
            comboBox.getSelectionModel()
                    .selectedIndexProperty()
                    .addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                   String selValue=comboBox.getValue().toString();
                   Chart(selValue);
                }
            });
                            
        }
       
    
}
