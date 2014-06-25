package com.dimdron.InternetProviders.components.adapter;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CustomListView extends ScrollPane {
    private  VBox root;
    private Adapter adapter = null;

    public CustomListView() {
        root = new VBox();
        this.setContent(root);
    }

    public CustomListView(Adapter adapter){
        this();
        setAdapter(adapter);
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        try {
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() throws Exception {
        if (adapter == null)
            throw new Exception("don't set the adapter") ;
        root.getChildren().removeAll(root.getChildren());
        for(Object object : adapter.getCollection())
            root.getChildren().add(adapter.generateView(object));
    }
}
