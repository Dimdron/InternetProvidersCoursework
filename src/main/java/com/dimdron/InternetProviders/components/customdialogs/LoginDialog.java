package com.dimdron.InternetProviders.components.customdialogs;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 03.06.14
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class LoginDialog extends Dialog{
    final TextField username = new TextField();
    final PasswordField password = new PasswordField();

    public LoginDialog(Stage stage, final Action actionLogin) {
        super(stage, "Sing In");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        username.setPromptText("Login");
        password.setPromptText("Password");

        grid.add(new Label("Login:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        ButtonBar.setType(actionLogin, ButtonBar.ButtonType.OK_DONE);
        actionLogin.disabledProperty().set(true);

        // validation
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            actionLogin.disabledProperty().set(newValue.trim().isEmpty());
        });

        this.setMasthead("Please, enter your Login and Password");
        this.setContent(grid);
        this.getActions().addAll(actionLogin, Dialog.Actions.CANCEL);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());
    }

    public PasswordField getPassword() {
        return password;
    }
    public TextField getLogin() {
        return username;
    }
}
