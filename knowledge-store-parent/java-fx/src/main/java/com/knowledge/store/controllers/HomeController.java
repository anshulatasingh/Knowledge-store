
package com.knowledge.store.controllers;

import com.knowledge.store.model.NodeDataVo;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

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
    private TreeView<NodeDataVo> treeView;

    @FXML
    private TabPane tabPane;

    @FXML
    private HBox statusBar;
    
    Map<Integer, InfoDataController>  tabMap= new HashMap<>();
    
    Map<Integer, TreeItem<NodeDataVo>> itemById = new HashMap<>();
    
    Map<Integer, Integer> parents = new HashMap<>();

    @FXML
    void onCloseAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
      List<NodeDataVo> list= Arrays.asList(new NodeDataVo(1,1, "Language", false),new NodeDataVo(3,1, "JAVA", false),new NodeDataVo(4,2, "SQL", false),new NodeDataVo(5,3, ".NET", true),new NodeDataVo(6,3, ".NET", true));

      TreeItem<NodeDataVo> root=  constructTree(list);
        if(root!=null)
        treeView.setRoot(root);


//        TreeItem titem =new TreeItem();
//        TreeItem titem1 =new TreeItem(new NodeDataVo(3,1, "JAVA", false));
//        TreeItem titem2 =new TreeItem(new NodeDataVo(4,2, "SQL", false));
//        TreeItem titem3 =new TreeItem(new NodeDataVo(5,3, ".NET", true));
//        TreeItem titem4 =new TreeItem(new NodeDataVo(6,3, ".NET", true));
//               titem.getChildren().addAll(titem1,titem2);
//               titem1.getChildren().add(titem3);
//               titem2.getChildren().add(titem4);
//               treeView.setRoot(titem);
        
        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleMouseClicked(event);
            }
        });
        
        
        
    
       splitPane.setDividerPosition(0, 0.10);
       TextField filterTreeTextBoxField = UIUtil.createClearableTextField();
        searchboxContainr.getChildren().add(filterTreeTextBoxField);
        filterTreeTextBoxField.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               System.out.println(newValue);
           }
       });
        
    }

    
    private void handleMouseClicked(MouseEvent event) {
    Node node = event.getPickResult().getIntersectedNode();
    // Accept clicks only on node cells, and not on empty spaces of the TreeView
    if ((node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null))&& event.getClickCount()==2) {
        NodeDataVo selectedNode = treeView.getSelectionModel().getSelectedItem().getValue();
        System.out.println("Node click: " + selectedNode.getLabel());
        if(selectedNode.isIsLeaf())
        addTab(selectedNode);
     
    }
}
    
 public void addTab(NodeDataVo nodeDataVo){
  
        if(tabMap.containsKey(nodeDataVo.getNodeId())){
        tabPane.getSelectionModel().select(nodeDataVo.getNodeId());
            System.out.println("exist");
        }else{
             System.out.println("need to create");
        InfoDataController controller=  new InfoDataController();
        Tab tab=new Tab(nodeDataVo.getLabel());
        tab.setClosable(true);
        tab.setId(nodeDataVo.getNodeId()+"");
        try {
            FXMLLoader an=new FXMLLoader(getClass().getResource("/com/knowledge/store/views/InfoDataView.fxml"));
            an.setController(controller);
            an.load();          
                               
            tab.setContent((SplitPane) an.getRoot());
            
            tab.setOnCloseRequest(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {                    
                   tabMap.remove(Integer.valueOf(tabPane.getSelectionModel().getSelectedItem().getId()));
                   
                    
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       tabPane.getTabs().add(tab);
       tabPane.getSelectionModel().select(nodeDataVo.getNodeId());
       tabMap.put(nodeDataVo.getNodeId(),controller);
       
       
       
        } 
} 
  public void initTreeMap(List<NodeDataVo> listOfNodes){

      for(NodeDataVo ndVo:listOfNodes){
          Integer nodeId= ndVo.getNodeId();
          Integer parentId =ndVo.getParentId();
          itemById.put(nodeId, new TreeItem<>(ndVo));
          parents.put(nodeId, parentId);
      }           
        
        }
 
  
  public TreeItem<NodeDataVo> constructTree(List<NodeDataVo> list){ 
      initTreeMap(list);
TreeItem<NodeDataVo> root = null;
for (Map.Entry<Integer, TreeItem<NodeDataVo>> entry : itemById.entrySet()) {
    Integer key = entry.getKey();
    Integer parent = parents.get(key);
    if (parent.equals(key)) {
        // in case the root item points to itself, this is it
        root = entry.getValue();
    } else {
        TreeItem<NodeDataVo> parentItem = itemById.get(parent);
        if (parentItem == null) {
            // in case the root item has no parent in the resultset, this is it
            root = entry.getValue();
        } else {
            // add to parent treeitem
            parentItem.getChildren().add(entry.getValue());
        }
    }
}
  return root;
  
  }
    
}
