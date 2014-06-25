package com.dimdron.InternetProviders.components.adapter.implementation;

import com.dimdron.InternetProviders.components.adapter.Adapter;
import com.dimdron.InternetProviders.components.providerview.ProviderView;
import com.dimdron.InternetProviders.dbconnection.model.Provider;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Collection;

public class ProvidersListAdapter implements Adapter<Provider> {
    public interface OnClickName {
        public void handle(MouseEvent mouseEvent, Provider provider) ;
    }

    private Collection<Provider> collection;
    private OnClickName event = null;

    public ProvidersListAdapter(Collection<Provider> collection) {
        this.collection = collection;
    }

    @Override
    public Pane generateView(Object object) {
        ProviderView view = new ProviderView();
        if (object instanceof Provider) {
            final Provider provider = (Provider)object;
            view.setLink(provider.getSite());
            view.setName(provider.getName());
            view.setIdProvider(provider.getId());
            view.setRating(new Double(provider.getCommonQuality()).toString());

            if (event != null)
                view.getName().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        event.handle(mouseEvent, provider);
                    }
                });
         }
        return view;
    }

    @Override
    public Collection<Provider> getCollection() {
        return collection;
    }

    public void setOnClickName(OnClickName eventHandler) {
        event = eventHandler;
    }

    public OnClickName getOnClickName() {
        return event;
    }
}
