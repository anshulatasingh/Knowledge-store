/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.knowledge.store.controllers;

import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author KRISHNA
 */
public class UIUtil {
    
    public static TextField createClearableTextField(){
      final  TextField clearableTextField =TextFields.createClearableTextField();     
      clearableTextField.setMinWidth(0);
      clearableTextField.getStyleClass().add("tree-scearch-text-box");
      clearableTextField.setPromptText("Search...");
      return clearableTextField;
      
    }
    
}
