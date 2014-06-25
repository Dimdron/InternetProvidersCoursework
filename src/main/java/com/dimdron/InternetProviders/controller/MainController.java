package com.dimdron.InternetProviders.controller;

import com.dimdron.InternetProviders.components.adapter.CustomListView;
import com.dimdron.InternetProviders.components.adapter.implementation.ProvidersListAdapter;
import com.dimdron.InternetProviders.components.customdialogs.AddUserDialog;
import com.dimdron.InternetProviders.components.customdialogs.DialogApplications;
import com.dimdron.InternetProviders.components.customdialogs.LoginDialog;
import com.dimdron.InternetProviders.components.providerpicker.ProviderPicker;
import com.dimdron.InternetProviders.dbconnection.factory.Factory;
import com.dimdron.InternetProviders.dbconnection.model.*;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.action.AbstractAction;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 16.05.14
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public class MainController {
    @FXML private MenuItem menuItemSingIn;
    @FXML private MenuItem menuItemCloseSession;
    @FXML private CustomListView listView;
    @FXML private ProviderPicker picker;
    @FXML private AnchorPane root;
    @FXML private Label lblCount;
    private List<Provider> providerList;

    @FXML
    public void initialize() {
        picker.getBtnFind().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!(picker.isSelectedDistrict() || picker.isSelectedConnectionType()))
                    updateListProvider(Factory.getInstance().getProviderDAO()
                            .getByPriceAndSpeed(picker.getPrice(), picker.getSpeed()));

                else if (picker.isSelectedDistrict() && !picker.isSelectedConnectionType())
                    updateListProvider(Factory.getInstance().getProviderDAO()
                            .getByPriceSpeedDistrict(picker.getPrice(), picker.getSpeed(),
                                    picker.getDistrict().getSelectedItem().getId()));

                else if (!picker.isSelectedDistrict() && picker.isSelectedConnectionType())
                    updateListProvider( Factory.getInstance().getProviderDAO()
                            .getByPriceSpeedConnectionType(
                                    picker.getPrice(),
                                    picker.getSpeed(),
                                    picker.getConnectionType().getSelectedItem().getId()));

                else
                    updateListProvider(Factory.getInstance().getProviderDAO()
                            .getByPriceSpeedDistrictConnectionType(picker.getPrice(), picker.getSpeed(),
                                    picker.getDistrict().getSelectedItem().getId(),
                                    picker.getConnectionType().getSelectedItem().getId()));

                picker.getBtnAllProviders().setDisable(false);
            }
        });

        picker.getBtnAllProviders().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateListProvider(Factory.getInstance().getProviderDAO().getAll());
                picker.getBtnAllProviders().setDisable(true);
            }
        });
        updatePickerDistrict();
        updatePickerConnection();
        updateListProvider(Factory.getInstance().getProviderDAO().getAll());
        updateSliders();
    }

    private void updatePickerDistrict() {
        District d = new District(-1, "any");
        picker.getComboBoxDistrict().getItems().setAll(d);
        picker.getComboBoxDistrict().getItems().addAll(Factory.getInstance().getDistrictDAO().getAll());
        picker.getComboBoxDistrict().setValue(d);
    }

    private void updatePickerConnection() {
        ConnectionType c = new ConnectionType(-1, "any");
        picker.getComboBoxConnection().getItems().setAll(c);
        picker.getComboBoxConnection().getItems().addAll(Factory.getInstance().getTypeDAO().getAll());
        picker.getComboBoxConnection().setValue(c);
    }

    private void updateSliders() {
        picker.getSldPrice().setMax(Factory.getInstance().getTariffDAO().getMaxPrice());
        picker.getSldSpeed().setMax(Factory.getInstance().getTariffDAO().getMinSpeed());
        picker.getSldSpeed().setValue(Factory.getInstance().getTariffDAO().getMinSpeed());
        picker.getSldPrice().setValue(Factory.getInstance().getTariffDAO().getMaxPrice());
    }

    private void updateListProvider(List<Provider> providers) {
        providerList = providers;
        ProvidersListAdapter adapter = new ProvidersListAdapter(providers);
        adapter.setOnClickName(new ProvidersListAdapter.OnClickName() {
            @Override
            public void handle(MouseEvent mouseEvent, Provider provider) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("/fxml/components/subform/provider.fxml"));
                    Parent root = (Parent) loader.load();
                    ProviderController controller = (ProviderController)loader.getController();
                    controller.setProvider(provider);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(provider.getName());
                    stage.showAndWait();
                    if (controller.isChangedList()) {
                        updateListProvider(Factory.getInstance().getProviderDAO().getAll());
                        updateSliders();
                    }
                }
                catch (Exception e) {
                    System.out.print(e.toString());
                    e.printStackTrace();
                }
            }
        });
        listView.setAdapter(adapter);
        lblCount.setText("Fined "+ providers.size() + " providers");
    }
    /**********************/

    public void onActionMenuItemAddUser(ActionEvent actionEvent) {
        new AddUserDialog((Stage)root.getScene().getWindow(), new AbstractAction("Add") {
            @Override
            public void execute(ActionEvent actionEvent) {
                AddUserDialog dialog = (AddUserDialog)actionEvent.getSource();
                if (dialog.getPassword().getText().isEmpty() ||
                        dialog.getUserlogin().getText().isEmpty() ||
                        dialog.getUsername().getText().isEmpty()) {
                     return;
                }

                String p = dialog.getPassword().getText();
                String p1 = dialog.getPassword2().getText();
                if (!p.equals(p1)) {
                     return;
                }
                Factory.getInstance().getUser().insert(
                        new User(dialog.getUsername().getText(),
                                dialog.getUserlogin().getText(), dialog.getPassword().getText(), dialog.getLevel()));
                dialog.hide();
            }
        }).show();
    }



    public void onActionEventLogsMenuItem(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/components/subform/eventlog.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Event Log");
            stage.showAndWait();
        }
        catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    public void onActionSingInMenuItem(ActionEvent actionEventMenuItem) {
        new LoginDialog(null, new AbstractAction("Login") {
            @Override
            public void execute(ActionEvent actionEvent) {
                LoginDialog  d = (LoginDialog) actionEvent.getSource();
                if(!UserSession.getInstance().sessionStart(d.getLogin().getText(), d.getPassword().getText())) {
                    Dialogs.create()
                            .owner((Stage)root.getScene().getWindow())
                            .title("Warning")
                            .masthead("Please, enter the correct password")
                            .message("You entered incorrect password!")
                            .showWarning();
                }
                else {
                    enterUser();
                    menuItemSingIn.setDisable(true);
                    menuItemCloseSession.setDisable(false);
                    Dialogs.create()
                            .owner((Stage)root.getScene().getWindow())
                            .title("Hello")
                            .masthead(null)
                            .message("You entered the system, " + UserSession.getInstance().getUser().getName() + " !")
                            .showInformation();
                }
                d.hide();
            }
        }).show();
    }

    public void onActionRemoveConnectionTypeMenuItem(ActionEvent actionEvent) {
        List<ConnectionType> choices = Factory.getInstance().getTypeDAO().getAll();
        ConnectionType response = Dialogs.create()
                .owner((Stage)root.getScene().getWindow())
                .title("Remove connection type")
                .masthead("Please, choose connection, which will deleted")
                .message("Choose the connection:")
                .showChoices(choices);
        if(null == response)
            return;
        Factory.getInstance().getTypeDAO().delete(response);
        picker.getComboBoxConnection().getItems().removeAll(response);
    }

    public void onActionRemoveDistrictMenuItem(ActionEvent actionEvent) {
        List<District> choices = Factory.getInstance().getDistrictDAO().getAll();
        District response = Dialogs.create()
                .owner((Stage)root.getScene().getWindow())
                .title("Remove region")
                .masthead("Please, choose region, which will deleted")
                .message("Choose the region:")
                .showChoices(choices);
        if(null == response)
            return;
        Factory.getInstance().getDistrictDAO().delete(response);
        picker.getComboBoxDistrict().getItems().removeAll(response);
    }

    public void onActionAddConnectionTypeMenuItem(ActionEvent actionEvent) {
        String response = Dialogs.create()
                .owner((Stage)root.getScene().getWindow())
                .title("Add connection type")
                .masthead("Adding the new type of connection")
                .message("Please enter the \nconnection name:")
                .showTextInput("connection1");
        ConnectionType connection;
        connection = (response != null) ? new ConnectionType(response) : null;
        if((connection != null) && Factory.getInstance().getTypeDAO().insertUnique(connection))
               picker.getComboBoxConnection().getItems().add(connection);
    }

    public void onActionAddDistrictMenuItem(ActionEvent actionEvent) {
        String response = Dialogs.create()
                .owner((Stage)root.getScene().getWindow())
                .title("Add region")
                .masthead("Adding the new region")
                .message("Please enter \nregion name:")
                .showTextInput("region1");
        District district;
        district = (response != null) ? new District(response) : null;
        if((district != null) && Factory.getInstance().getDistrictDAO().insertUnique(district))
            picker.getComboBoxDistrict().getItems().add(district);
    }

    public void onActionNewProviderMenuItem(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/components/subform/provider.fxml"));
            Parent root = (Parent) loader.load();
            ProviderController controller = ((ProviderController)loader.getController());
            controller.setProvider(null);
            controller.setCreateNew(true);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("New provider");
            stage.showAndWait();
            if (controller.isChangedList())
                updateListProvider(Factory.getInstance().getProviderDAO().getAll());
        }
        catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    public void onActionCloseSessionMenuItem(ActionEvent actionEvent) {
        UserSession.getInstance().close();
        menuItemCloseSession.setDisable(true);
        menuItemSingIn.setDisable(false);
        exitUser();
    }

    public void onActionCloseMenuItem(ActionEvent actionEvent) {
        ((Stage)root.getScene().getWindow()).close();
    }

    public void onActionReportsMenuItem(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            pane = (AnchorPane) loader.load(getClass().getResourceAsStream("/fxml/components/subform/reportlist.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pane == null) return;

        Dialog dialog = new Dialog((Stage)root.getScene().getWindow(), "Report");
        dialog.setMasthead("Choose the report");
        dialog.setContent(pane);
        dialog.show();
    }

    public void onActionAboutMenuItem(ActionEvent actionEvent) {
    }


    @FXML private MenuItem newProviderMenuItem;
    @FXML private MenuItem menuItemAddUser;
    @FXML private MenuItem miReports;
    @FXML private Menu menuEventLog;
    @FXML private MenuItem  newDistrictMenuItem;
    @FXML private MenuItem removeDistrictMenuItem;
    @FXML private MenuItem newConnectionTypeMenuItem ;
    @FXML private MenuItem removeConnectionTypeMenuItem ;
    @FXML private MenuItem miComplaints;

    private void enterUser() {
        if (!UserSession.getInstance().isOpen()) return;
        if (UserSession.getInstance().getUser().getLevel() <= 3)
            miComplaints.setVisible(true);
        if (UserSession.getInstance().getUser().getLevel() <= 2) {
            miReports.setDisable(false);

            if (UserSession.getInstance().getUser().getLevel() == 1)  {
                newProviderMenuItem.setVisible(true);
                menuItemAddUser.setVisible(true);
                menuEventLog.setVisible(true);
                newDistrictMenuItem.setVisible(true);
                newConnectionTypeMenuItem.setVisible(true);
                removeDistrictMenuItem.setVisible(true);
                removeConnectionTypeMenuItem.setVisible(true);
            }
        }
    }
    private void exitUser() {
        miReports.setDisable(true);
        newProviderMenuItem.setVisible(false);
        menuItemAddUser.setVisible(false);
        newDistrictMenuItem.setVisible(false);
        newConnectionTypeMenuItem.setVisible(false);
        removeDistrictMenuItem.setVisible(false);
        removeConnectionTypeMenuItem.setVisible(false);
        menuEventLog.setVisible(false);
        miComplaints.setVisible(false);
    }

    public void onActionComplaintsMenuItem(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/components/subform/applicationslist.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Complaints");
            stage.showAndWait();
        }
        catch (Exception e) {
            System.out.print(e.toString());
            e.printStackTrace();
        }
    }

    public void onActionComplainMenuItem(ActionEvent actionEvent) {
        new DialogApplications((Stage)root.getScene().getWindow(), new AbstractAction("Send") {
            @Override
            public void execute(ActionEvent actionEvent) {
                DialogApplications dlg = (DialogApplications)actionEvent.getSource();

                Factory.getInstance().getApplicationModelDAO().insert(new ApplicationModel(
                        dlg.getAddress().getText(), dlg.getDistrict(), dlg.getProvider(),
                        dlg.getText().getText()));

                dlg.hide();
            }
        }).show();
    }

    public void onActiveBtnPrint(ActionEvent actionEvent) throws Exception {
        ReportListController.openFromProvidersReport(providerList);
    }
}
