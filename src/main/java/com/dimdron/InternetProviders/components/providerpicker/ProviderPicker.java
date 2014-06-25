package com.dimdron.InternetProviders.components.providerpicker;

import com.dimdron.InternetProviders.dbconnection.model.ConnectionType;
import com.dimdron.InternetProviders.dbconnection.model.District;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 24.05.14
 * Time: 0:37
 * To change this template use File | Settings | File Templates.
 */
public class ProviderPicker extends VBox {
    @FXML private ComboBox<ConnectionType> cbConnection;
    @FXML private ComboBox<District> cbDistrict;
    @FXML private TextField tfSpeed;
    @FXML private TextField tfPrice;
    @FXML private Slider sldSpeed;
    @FXML private Slider sldPrice;
    @FXML private Button btnFind;
    @FXML private Button btnAllProviders;
    @FXML private ComboBox<String> perBox;

    private boolean selectPrice;
    private boolean selectDistrict;
    private boolean selectConnectionType;
    private boolean selectSpeed;
    private boolean per;

    public ProviderPicker() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/components/providerpicker/providerpicker.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            final Node root = (Node)fxmlLoader.load();
            assert root == this;
        }
        catch (IOException exception) {
            throw new RuntimeException("ProviderPicker was broken down: ", exception);
        }
        initListener();
    }

    public ComboBox<District> getComboBoxDistrict() {
        return cbDistrict;
    }

    public ComboBox<ConnectionType> getComboBoxConnection() {
        return cbConnection;
    }

    public Slider getSldSpeed() {
        return  sldSpeed;
    }

    public Slider getSldPrice() {
        return sldPrice;
    }

    public double getSpeed() {
        return sldSpeed.getValue();
    }

    public void setSpeed(double speed) {
        this.sldSpeed.setValue(speed);
    }

    public double getPrice() {
        return sldPrice.getValue();
    }

    public void setPrice(double price) {
        this.sldPrice.setValue(price);
    }

    public Button getBtnFind() {
        return btnFind;
    }

    public SingleSelectionModel<District> getDistrict() {
        return cbDistrict.getSelectionModel();
    }

    public SingleSelectionModel<ConnectionType> getConnectionType() {
        return cbConnection.getSelectionModel();
    }

    private void initListener() {
        sldPrice.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                tfPrice.setText(number2.toString());
                if (number2.doubleValue() != sldPrice.getMax())
                    selectPrice = true;
                else
                    selectPrice = false;
            }
        });
        sldPrice.setValue(1);
        sldSpeed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                tfSpeed.setText(number2.toString());
                if (number2.doubleValue() != sldSpeed.getMax())
                    selectSpeed = true;
                else
                    selectSpeed = false;
            }
        });
        sldSpeed.setValue(1);

        cbDistrict.valueProperty().addListener(new ChangeListener<District>() {
            @Override
            public void changed(ObservableValue<? extends District> observableValue,
                                District district, District district2) {
                if (district2.getId() == -1)
                    selectDistrict = false;
                else
                    selectDistrict = true;
            }
        });

        cbConnection.valueProperty().addListener(new ChangeListener<ConnectionType>() {
            @Override
            public void changed(ObservableValue<? extends ConnectionType> observableValue,
                                ConnectionType connectionType, ConnectionType connectionType2) {
                if (connectionType2.getId() == -1)
                    selectConnectionType = false;
                else
                    selectConnectionType= true;
            }
        });

        perBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object o2) {
                if (perBox.getValue() == "All") per = false;
                else per = true;
            }
        });

        perBox.getItems().addAll("All", "10", "20", "50");
        perBox.setValue("All");
    }

    public boolean isSelectedPrice() {
        return selectPrice;
    }

    public Integer selectedPer() {
    return (String)perBox.getValue() == "All" ? Integer.parseInt((String)perBox.getValue()) : 0;
    }

    public boolean isSelectedSpeed() {
        return selectSpeed;
    }

    public boolean isSelectedDistrict() {
        return selectDistrict;
    }

    public boolean isSelectedConnectionType() {
        return selectConnectionType;
    }

    public Button getBtnAllProviders() {
        return btnAllProviders;
    }

    public boolean isPer() {
        return per;
    }

}
