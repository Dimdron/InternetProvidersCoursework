package com.dimdron.InternetProviders.controller;

import com.dimdron.InternetProviders.components.adapter.CustomListView;
import com.dimdron.InternetProviders.components.adapter.implementation.ReviewListAdapter;
import com.dimdron.InternetProviders.dbconnection.factory.Factory;
import com.dimdron.InternetProviders.dbconnection.model.*;
import com.dimdron.InternetProviders.dbconnection.usersession.UserSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 25.05.14
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class ProviderController {
    private Provider provider = null;
    private boolean createNew = false;
    private boolean changedList = false;
    @FXML private ImageView image;
    @FXML private Label name;
    @FXML private Hyperlink site;
    @FXML private Label rating;
    @FXML private ListView<District> districtsList;
    @FXML private TableView<Tariff> tvTariffs;
    @FXML private TableColumn<Tariff, String> tariff;
    @FXML private TableColumn<Tariff, Integer> speed;
    @FXML private TableColumn<Tariff, Integer> price;
    @FXML private TableColumn<Tariff, String> description;
    @FXML private ComboBox<District> allDistricts;
    @FXML private Button btnAddDistrict;
    @FXML private Button btnSave;
    @FXML private Button btnDelete;
    @FXML private TextField nameEdit;
    @FXML private Label emptyDistrictsList;
    @FXML private MenuItem siteEditMenuItem;
    @FXML private TextField siteEdit;
    @FXML private Button btnAddConnection;
    @FXML private ComboBox<ConnectionType> allConnection;
    @FXML private Label emptyConnectionList;
    @FXML private ListView<ConnectionType> connectionList;
    @FXML private Button btnAddTariff;
    @FXML private TextField textFieldTariff;
    @FXML private TextField textFieldPrice;
    @FXML private TextField textFieldSpeed;
    @FXML private TextField textFieldDescription;
    @FXML private Tab tabComment;

    private boolean boolTariff = false;
    private boolean boolPrice = false;
    private boolean boolSpeed = false;
    private boolean boolDescription = false;

    @FXML
    private void initialize() {
        tariff.setCellValueFactory(new PropertyValueFactory<Tariff, String>("name"));
        speed.setCellValueFactory(new PropertyValueFactory<Tariff, Integer>("speed"));
        price.setCellValueFactory(new PropertyValueFactory<Tariff, Integer>("price"));
        description.setCellValueFactory(new PropertyValueFactory<Tariff, String>("description"));
        nameEdit.setPromptText("Provider's name");
        siteEdit.setPromptText("For example: google.com");
        textFieldDescription.setPromptText("Information");
        textFieldPrice.setPromptText("Price");
        textFieldSpeed.setPromptText("Speed");
        textFieldTariff.setPromptText("Tariff");

        btnAddTariff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Tariff t = new Tariff(textFieldTariff.getText(),
                        Double.parseDouble(textFieldPrice.getText()),
                        Double.parseDouble(textFieldSpeed.getText()),
                        textFieldDescription.getText(),
                        provider);
                tvTariffs.getItems().add(t);
                provider.getTariffs().add(t);
                textFieldPrice.clear();
                textFieldSpeed.clear();
                textFieldTariff.clear();
                textFieldDescription.clear();
                changeState();
            }
        });

        textFieldPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                boolPrice = isDoubleInTextFieldAndChange(textFieldPrice, s2);
                btnAddTariff.setVisible(isTariffFilled());
            }
        });

        textFieldSpeed.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                boolSpeed = isDoubleInTextFieldAndChange(textFieldSpeed, s2);
                btnAddTariff.setVisible(isTariffFilled());
            }
        });

        textFieldTariff.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (!s2.isEmpty())
                    boolTariff = true;
                else
                    boolTariff = false;
                btnAddTariff.setVisible(isTariffFilled());
            }
        });

        textFieldDescription.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (!s2.isEmpty())
                    boolDescription = true;
                else
                    boolDescription = false;
                btnAddTariff.setVisible(isTariffFilled());
            }
        });



        tvTariffs.editingCellProperty().addListener(new ChangeListener<TablePosition<Tariff, ?>>() {
            @Override
            public void changed(ObservableValue<? extends TablePosition<Tariff, ?>> observableValue, TablePosition<Tariff, ?> tariffTablePosition, TablePosition<Tariff, ?> tariffTablePosition2) {
                System.out.println("Click");
                System.out.println(tariffTablePosition2.getRow());
            }
        });

        //add district (button "+")
        btnAddDistrict.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                   District newDistrict = allDistricts.getValue();
                if (newDistrict.getId() == -1)
                    return;

                if (!provider.getDistricts().contains(newDistrict)) {
                    provider.getDistricts().add(newDistrict);
                    initDistrictList();
                    changeState();
                }
            }
        });

        btnAddConnection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConnectionType newConnection = allConnection.getValue();
                if (newConnection.getId() == -1)
                    return;

                if (!provider.getConnectionTypes().contains(newConnection)) {
                    provider.getConnectionTypes().add(newConnection);
                    initConnectionList();
                    changeState();
                }
            }
        });

        nameEdit.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (provider.getName() != nameEdit.getText()) {
                        provider.setName(nameEdit.getText());
                        name.setText(nameEdit.getText());
                        changeState();
                    }
                    nameEdit.setVisible(false);
                    name.setVisible(true);
                }
            }
        });

        siteEditMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                site.setVisible(false);
                siteEdit.setVisible(true);
                siteEdit.setText(site.getText());
            }
        });

        siteEdit.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (provider.getSite() != siteEdit.getText()) {
                        provider.setSite(siteEdit.getText());
                        site.setText(siteEdit.getText());
                        changeState();
                    }
                    siteEdit.setVisible(false);
                    site.setVisible(true);
                }
            }
        });

        initDistrictComboBox();
        initConnectionComboBox();
        userInit();
    }

    private void initContent(Provider provider) {
        fillingTariffTable();
        if (!createNew)
            initForUpdate();
        else
            initForCreating();
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
        if (provider != null)
            initContent(provider);
    }

    private void fillingTariffTable() {
        ObservableList<Tariff> tariffItems = FXCollections.observableArrayList();
        tariffItems.addAll(provider.getTariffs());
        tvTariffs.setItems(tariffItems);
    }
    private void initDistrictList() {
        ObservableList<District> districtItems = FXCollections.observableArrayList();
        districtItems.addAll(provider.getDistricts());
        if (districtItems.size() == 0)
            return;

        districtsList.setDisable(false);
        emptyDistrictsList.setVisible(false);
        districtsList.setItems(districtItems);
    }

    private void initConnectionList() {
        ObservableList<ConnectionType> connectionItems = FXCollections.observableArrayList();
        connectionItems.addAll(provider.getConnectionTypes());
        if (connectionItems.size() == 0)
            return;

        connectionList.setDisable(false);
        emptyConnectionList.setVisible(false);
        connectionList.setItems(connectionItems);
    }

    private void initDistrictComboBox() {
        ObservableList<District> districtItems = FXCollections.observableArrayList();
        District d =  new District(-1, "Nothing");
        districtItems.add(d);
        districtItems.addAll(Factory.getInstance().getDistrictDAO().getAll());
        allDistricts.setItems(districtItems);
        allDistricts.setValue(d);

    }

    private void initConnectionComboBox() {
        ObservableList<ConnectionType> connectionItems = FXCollections.observableArrayList();
        ConnectionType type =  new ConnectionType(-1, "Nothing");
        connectionItems.add(type);
        connectionItems.addAll(Factory.getInstance().getTypeDAO().getAll());
        allConnection.setItems(connectionItems);
        allConnection.setValue(type);

    }

    private void changeState() {
        btnSave.setDisable(false);
    }

    //******************************
    @FXML private Slider sliderSpeed;
    @FXML private Slider sliderSupport;
    @FXML private Slider sliderConnection;
    @FXML private Button btnSend;
    @FXML private TextArea reviewText;
    @FXML private CustomListView listReview;
    @FXML private Label labelReviewsIsEmpty;

    private void initComment() {

        reviewText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if(s2.isEmpty())
                    btnSend.setDisable(true);
                else
                    btnSend.setDisable(false);
            }
        });

        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Review review = new Review(reviewText.getText(),
                        ((Double) sliderSupport.getValue()).byteValue(),
                        ((Double) sliderSpeed.getValue()).byteValue(),
                        ((Double) sliderConnection.getValue()).byteValue());
                review.setProvider(provider);
                Factory.getInstance().getReviewDAO().insert(review);
                provider.getReviews().add(review);
                fillingReviews();
                reviewText.clear();
            }

        });
    }

    private void fillingReviews() {
        if (provider.getReviews().isEmpty())
            return;
        labelReviewsIsEmpty.setVisible(false);
        listReview.setVisible(true);
        listReview.setAdapter(new ReviewListAdapter(provider.getReviews()));
    }

    public void setCreateNew(boolean createNew) {
        this.createNew = createNew;
        if (!createNew)
            return;
        if (provider != null)
            return;
        setProvider(new Provider());
    }

    public boolean isChangedList() {
        return changedList;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    private void initForUpdate() {
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
                Factory.getInstance().getProviderDAO().delete(provider);
                ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
                changedList = true;
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Factory.getInstance().getProviderDAO().update(provider);
                btnSave.setDisable(true);
            }
        });

        initComment();

            name.setText(provider.getName());
            site.setText(provider.getSite());
            StringBuilder str = new StringBuilder();
            str.append("Quality: \n").append("support: ").append(provider.getSupportQuality()).append("\nspeed: \n")
                    .append(provider.getRateQuality()).append("\nCommunication: \n").append(provider.getCommunicationQuality());
            rating.setText(str.toString());
            initDistrictList();
            initConnectionList();
            fillingReviews();
    }

    private void initForCreating() {
       tabComment.setDisable(true);
       rating.setVisible(false);
        btnDelete.setText("Cancel");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Factory.getInstance().getProviderDAO().insert(provider);
                changedList = true;
                ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
            }
        });

            name.setVisible(false);
            nameEdit.setVisible(true);
            siteEdit.setVisible(true);
            site.setVisible(false);
    }

    private boolean isDoubleInTextFieldAndChange(TextField textField, String s1) {
        String s = textField.getText();
        if(!s.isEmpty()) {
            try {
                Double.parseDouble(s);
            }
            catch (Exception e) {
                textFieldSpeed.setText(s1);
                return false;
            }
            return true;
        }
        else return false;
    }

    private boolean isTariffFilled() {
        return boolTariff && boolPrice && boolSpeed && boolDescription;
    }

    public void onActionBtnReport(ActionEvent actionEvent) throws Exception {
        List<Provider> p = new ArrayList<Provider> ();
        ReportListController.openFromProvidersReport(p);
    }

    @FXML private AnchorPane adminPane;
    private void userInit() {
        if(!UserSession.getInstance().isOpen()) {
            adminPane.setVisible(false);
            siteEditMenuItem.setVisible(false);
            return;
        }
        else if (UserSession.getInstance().getUser().getLevel() >= 1){
            adminPane.setVisible(true);
            siteEditMenuItem.setVisible(true);

            name.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    name.setVisible(false);
                    nameEdit.setVisible(true);
                    nameEdit.setText(name.getText());
                }
            });
        }
    }
}
