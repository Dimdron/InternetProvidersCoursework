package com.dimdron.InternetProviders.components.providerview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import java.io.IOException;

public class ProviderView extends HBox {
    private Integer idProvider;

    @FXML
    private Label name;
    @FXML
    private Hyperlink link;
    @FXML Label labelRating;

    public ProviderView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/components/adapters/providerview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Label getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public Hyperlink getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link.setText(link);
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }

    public void setRating(String rating) {
        labelRating.setText("Rating :- " + rating);
    }
}
