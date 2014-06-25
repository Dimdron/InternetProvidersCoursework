package com.dimdron.InternetProviders.controller;

import com.dimdron.InternetProviders.dbconnection.factory.Factory;
import com.dimdron.InternetProviders.dbconnection.model.EventLog;
import com.dimdron.InternetProviders.dbconnection.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 01.06.14
 * Time: 8:56
 * To change this template use File | Settings | File Templates.
 */
public class EventLogController {
    @FXML private TableView<EventLog> tableView;
    @FXML private TableColumn<EventLog, User> userColumn;
    @FXML private TableColumn<EventLog, String> eventColumn;
    @FXML private TableColumn<EventLog, Integer> numberColumn;

    @FXML private void initialize() {
        userColumn.setCellValueFactory(new PropertyValueFactory<EventLog, User>("user"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<EventLog, String>("event"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<EventLog, Integer>("id"));
        ObservableList<EventLog> eventLogs = FXCollections.observableArrayList();
        eventLogs.addAll(Factory.getInstance().getEventLogDAO().getAll());
        tableView.setItems(eventLogs);
    }
}
