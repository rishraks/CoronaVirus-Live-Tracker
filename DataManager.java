/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class DataManager {
    
  private static ObservableList<Case> data=FXCollections.observableList(new ArrayList<>());

    public static ObservableList<Case> getData() {
        return data;
    }

    public static void setData(ObservableList<Case> data) {
        DataManager.data = data;
    }

    
    
    public void append(Case cs)
    {
        data.add(cs);
    }
   public void clear()
   {
       data.clear();
   }
   
   public void addAll(ArrayList cases)
    {
        data.addAll(cases);
    }
}
