package com.dimdron.InternetProviders.components.customdialogs;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
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
 * Date: 04.06.14
 * Time: 9:20
 * To change this template use File | Settings | File Templates.
 */
public class AddUserDialog extends Dialog {
    private final TextField username = new TextField();
    private final TextField userlogin = new TextField();
    private final PasswordField password = new PasswordField();
    private final PasswordField password2 = new PasswordField();
    private final ComboBox<Byte> levelBox = new ComboBox<Byte>();

    public AddUserDialog(Stage stage, final Action actionLogin) {
        super(stage, "Addition new user");
            levelBox.getItems().addAll(new Byte[] {(byte)1, (byte)2, (byte)3});
        levelBox.setValue((byte)3);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        username.setPromptText("Name");
        userlogin.setPromptText("Login");
        password.setPromptText("Password");
        password2.setPromptText("Password");
        grid.add(new Label("Name:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Login:"), 0, 1);
        grid.add(userlogin, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(password, 1, 2);
        grid.add(new Label("Password \nverification:"), 0, 3);
        grid.add(password2, 1, 3);
        grid.add(levelBox, 1, 4);

        ButtonBar.setType(actionLogin, ButtonBar.ButtonType.OK_DONE);
        actionLogin.disabledProperty().set(true);

        // validation
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            actionLogin.disabledProperty().set(newValue.trim().isEmpty());
        });

        this.setMasthead("Please, fill the fields");
        this.setContent(grid);
        this.getActions().addAll(actionLogin, Dialog.Actions.CANCEL);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

    }

        public TextField getUsername() {
            return username;
        }

        public PasswordField getPassword2() {
            return password2;
        }

        public PasswordField getPassword() {
            return password;
        }

        public TextField getUserlogin() {
            return userlogin;
        }

        public Byte getLevel() {
            return levelBox.getValue();
        }

    public ComboBox<Byte> getLevelBox() {
        return levelBox;
    }
}
