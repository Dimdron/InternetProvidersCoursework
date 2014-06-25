package com.dimdron.InternetProviders.components.adapter;

import javafx.scene.layout.Pane;
import java.util.Collection;

public interface Adapter<T> {
    public Pane generateView(Object object);
    public Collection<T> getCollection();
}
