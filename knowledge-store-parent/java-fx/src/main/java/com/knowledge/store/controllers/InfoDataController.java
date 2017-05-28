package com.knowledge.store.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.web.HTMLEditor;

public class InfoDataController implements Initializable{

    @FXML
    private HBox infoButtonBar;
    
    
    @FXML
    private Region regionBar;

    @FXML
    private Button newInfoButton;

    @FXML
    private Button saveInfoButton;

    @FXML
    private Button updateInfoButton;

    @FXML
    private Button deleteInfoButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button reloadButon;

    @FXML
    private TableView<?> infoTable;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> label;
    
    @FXML
    private TableColumn<?, ?> description;

   
    @FXML
    private AnchorPane details;

    @FXML
    private Label displayedIssueLabel;
 @FXML
    private HTMLEditor descriptionText;

    @FXML
    private TextField dateText;

    @FXML
    private TextField labelText;

    @FXML
    void onClearAction(ActionEvent event) {

    }

    @FXML
    void onDeleteInfoButtonAction(ActionEvent event) {

    }

    @FXML
    void onNewInfoButtonAction(ActionEvent event) {

    }

    @FXML
    void onReloadAction(ActionEvent event) {

    }

    @FXML
    void onSaveInfoButtonAction(ActionEvent event) {

    }

    @FXML
    void onUpdateInfoAction(ActionEvent event) {
        System.out.println("Udated");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextField filteredTextBox = UIUtil.createClearableTextField();
        
         infoButtonBar.getChildren().add(0, filteredTextBox);
         filteredTextBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });
        
      
    }

}
