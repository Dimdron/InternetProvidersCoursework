package com.dimdron.InternetProviders.components.customdialogs;

import com.dimdron.InternetProviders.dbconnection.factory.Factory;
import com.dimdron.InternetProviders.dbconnection.model.District;
import com.dimdron.InternetProviders.dbconnection.model.Provider;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 05.06.14
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public class DialogApplications extends Dialog {
    private final TextField address = new TextField();
    private final TextArea text = new TextArea();
    private final ComboBox<Provider> provider = new ComboBox<Provider>();
    private final ComboBox<District> district = new ComboBox<District>();


    public DialogApplications(Stage stage, final Action actionLogin) {
        super(stage, "ApplicationModel");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        address.setPromptText("Address");
        text.setPromptText("Text");
        provider.getItems().addAll(Factory.getInstance().getProviderDAO().getAll());
        district.getItems().addAll(Factory.getInstance().getDistrictDAO().getAll());

        grid.add(new Label("Provider:"), 0, 0);
        grid.add(provider, 1, 0);
        grid.add(new Label("Region:"), 0, 1);
        grid.add(district, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(address, 1, 2);
        grid.add(new Label("Text:"), 0, 3);
        grid.add(text, 1, 3);

        ButtonBar.setType(actionLogin, ButtonBar.ButtonType.OK_DONE);
        actionLogin.disabledProperty().set(true);

        // validation
        address.textProperty().addListener((observable, oldValue, newValue) -> {
            actionLogin.disabledProperty().set(badValidation());
        });
        provider.valueProperty().addListener((observable, oldValue, newValue) -> {
            actionLogin.disabledProperty().set(badValidation());
        });
        district.valueProperty().addListener((observable, oldValue, newValue) -> {
            actionLogin.disabledProperty().set(badValidation());
        });
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            actionLogin.disabledProperty().set(badValidation());
        });

        this.setMasthead("Please, fill in all fields");
        this.setContent(grid);
        this.getActions().addAll(actionLogin, Dialog.Actions.CANCEL);

        // Request focus on the username field by default.
        Platform.runLater(() -> provider.requestFocus());
    }

    public TextArea getText() {
        return text;
    }
    public TextField getAddress() {
        return address;
    }

    public Provider getProvider() {
        return  provider.getValue();
    }

    public District getDistrict() {
        return district.getValue();
    }

    private boolean badValidation() {
        return district.getValue() == null || provider.getValue() == null ||
                address.getText().isEmpty() || text.getText().isEmpty();
    }
}
