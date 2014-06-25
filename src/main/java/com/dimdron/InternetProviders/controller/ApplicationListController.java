package com.dimdron.InternetProviders.controller;

import com.dimdron.InternetProviders.dbconnection.factory.Factory;
import com.dimdron.InternetProviders.dbconnection.model.ApplicationModel;
import com.dimdron.InternetProviders.dbconnection.model.District;
import com.dimdron.InternetProviders.dbconnection.model.Provider;
import com.dimdron.InternetProviders.dbconnection.model.User;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.action.AbstractAction;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 05.06.14
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationListController {
    @FXML private ComboBox<Provider> providersBox;
    @FXML private ComboBox<District> districtBox;
    @FXML private TableView<ApplicationModel> table;
    @FXML private TableColumn<ApplicationModel, District> columnDistrict;
    @FXML private TableColumn<ApplicationModel, String> columnAddress;
    @FXML private TableColumn<ApplicationModel, Provider> columnProvider;
    @FXML private TableColumn<ApplicationModel, String> columnProblem;
    @FXML private CheckBox actual;
    @FXML private CheckBox made;
    Collection<ApplicationModel> collection;

    @FXML
    public void initialize() {
        columnDistrict.setCellValueFactory(new PropertyValueFactory<ApplicationModel, District>("district"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<ApplicationModel, String>("address"));
        columnProvider.setCellValueFactory(new PropertyValueFactory<ApplicationModel, Provider>("provider"));
        columnProblem.setCellValueFactory(new PropertyValueFactory<ApplicationModel, String>("text"));

        providersBox.getItems().addAll(Factory.getInstance().getProviderDAO().getAll());
        districtBox.getItems().addAll(Factory.getInstance().getDistrictDAO().getAll());
        providersBox.getSelectionModel().select(0);
        districtBox.getSelectionModel().select(0);


        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ApplicationModel>() {
            @Override
            public void changed(ObservableValue<? extends ApplicationModel> observableValue, ApplicationModel application, ApplicationModel application2) {
                 if (application2 == null) return;
                User user = UserSession.getInstance().getUser();
                final Dialog dialog = new Dialog(null, "");
                final Action actionLogin;
                if (UserSession.getInstance().getUser().getLevel() == (byte)3)
                    actionLogin = new AbstractAction("Take") {
                        @Override
                        public void execute(ActionEvent actionEvent) {
                            if (!application2.getState()) {
                            application2.setState(true);
                            application2.setRepairman(UserSession.getInstance().getUser());
                            Factory.getInstance().getApplicationModelDAO().update(application2);
                            }
                            dialog.hide();
                        }
                    };
                else if (UserSession.getInstance().getUser().getLevel() == (byte)2)
                    actionLogin = new AbstractAction("Delete") {
                        @Override
                        public void execute(ActionEvent actionEvent) {
                            Factory.getInstance().getApplicationModelDAO().delete(application2);
                            table.getItems().removeAll(application2);
                            dialog.hide();
                        }
                    };
                else
                    return;

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(0, 10, 0, 10));

                grid.add(new Label("Region:"), 0, 0);
                grid.add(new Label(application2.getDistrict().getName()), 1, 0);

                grid.add(new Label("Address:"), 0, 1);
                grid.add(new Label(application2.getAddress()), 1, 1);

                grid.add(new Label("Provider:"), 0, 2);
                grid.add(new Label(application2.getProvider().getName()), 1, 2);

                grid.add(new Label("Problem:"), 0, 3);
                grid.add(new Label(application2.getText()), 1, 3);

                grid.add(new Label("State:"), 0, 4);
                grid.add(new Label(application2.getState().toString()), 1, 4);

                Button report = new Button("Report");
                final List<ApplicationModel> list = new ArrayList<ApplicationModel>();
                list.add(application2);
                report.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                        ReportListController.openApplicationsReport(list);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                ButtonBar.setType(actionLogin, ButtonBar.ButtonType.OK_DONE);
                dialog.setContent(grid);
                dialog.getActions().addAll(actionLogin, Dialog.Actions.CANCEL);
                dialog.show();
            }
        });

        loadList(Factory.getInstance().getApplicationModelDAO().getAll());
    }

    public void onActionBtnFind(ActionEvent actionEvent) {
        if (!(actual.isSelected() ^ made.isSelected()))
            loadList(Factory.getInstance().getApplicationModelDAO()
                    .getByProviderAndDistrict(providersBox.getValue().getId(), districtBox.getValue().getId()));
        else
            loadList(Factory.getInstance().getApplicationModelDAO()
                    .getByProviderAndDistrict(providersBox.getValue().getId(),
                            districtBox.getValue().getId(), made.isSelected()));

    }

    private void loadList(Collection<ApplicationModel> collec) {
        collection = collec;
        ObservableList<ApplicationModel> items= FXCollections.observableArrayList();
        items.addAll(collection);
        table.setItems(items);
    }

    public void onActionBtnAll(ActionEvent actionEvent) {
        loadList(Factory.getInstance().getApplicationModelDAO().getAll());
    }

    public void onActionBtnReport(ActionEvent actionEvent) throws Exception {
        ReportListController.openApplicationsReport(collection);
    }
}
