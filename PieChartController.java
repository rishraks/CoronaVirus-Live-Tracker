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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PieChartController implements Initializable {

    @FXML
    private PieChart overAll;
    @FXML
    private PieChart stateWise;

    private ObservableList<Case> cases;
    String values[]=new String[]{"Active Cases","Cured","Deaths","Total Confirmed Cases"};
    @FXML
    private JFXComboBox selectValue;
    @FXML
    private JFXComboBox selectState;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new DataManager().getData().addListener(new ListChangeListener<Case>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Case> c) {
               
                selectState.getItems().clear();
                selectValue.getItems().clear();
                cases= DataManager.getData();
        loadAll(values[0]);
        selValueCombo();
        if(cases.size()>0)
        {
            loadState(cases.get(0));
        }
            }
        });
        
    }    
    
    private void loadAll(String valueType)
    {
        this.overAll.setTitle("Over All Cases");
        this.overAll.getData().clear();
        for(Case cs:cases)
        {
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

           
            this.overAll.getData().add(new PieChart.Data(cs.getState()+"("+value+")",value));
        }
    }
    
    private void loadState(Case c)
    {
        this.stateWise.getData().clear();
        this.stateWise.setTitle("Cases in "+c.getState());
        this.stateWise.getData().add(new PieChart.Data("Deaths("+c.getDeaths()+")",c.getDeaths()));
        this.stateWise.getData().add(new PieChart.Data("Cured("+c.getCured()+")",c.getCured()));
        this.stateWise.getData().add(new PieChart.Data("Confirmed Cases("+c.getConfirmedCases()+")",c.getConfirmedCases()));
        this.stateWise.getData().add(new PieChart.Data("Active Cases("+c.getActiveCases()+")",c.getActiveCases()));
    }
    
    private void selValueCombo()
    {
    selectValue.getItems().addAll(values);
    for(Case c:cases)
        {
            this.selectState.getItems().add(c);
            
        }
    selectState.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
         Case c=(Case)selectState.getValue();
            loadState(c);
        }
    });
    this.selectValue.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            String value=(String)selectValue.getValue();
            loadAll(value);
        }
    });
    }
    
}
