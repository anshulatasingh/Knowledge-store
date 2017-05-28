
package com.knowledge.store.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class HomeController implements Initializable{

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private SplitPane splitPane;

    @FXML
    private HBox searchboxContainr;

    @FXML
    private TreeView<?> treeView;

    @FXML
    private TabPane tabPane;

    @FXML
    private HBox statusBar;

    @FXML
    void onCloseAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       splitPane.setDividerPosition(0, 0.10);
       TextField filterTreeTextBoxField = UIUtil.createClearableTextField();
        searchboxContainr.getChildren().add(filterTreeTextBoxField);
        filterTreeTextBoxField.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               System.out.println(newValue);
           }
       });
        Tab tab=new Tab("Data");
        tab.setClosable(true);
        try {
            FXMLLoader an=new FXMLLoader(getClass().getResource("/com/knowledge/store/views/InfoDataView.fxml"));
            an.load();
                       System.out.println("Con "+an.getController());
                               
            tab.setContent((SplitPane) an.getRoot());
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       tabPane.getTabs().add(tab);
    }

}
