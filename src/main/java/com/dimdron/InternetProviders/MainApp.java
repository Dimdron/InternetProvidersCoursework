package com.dimdron.InternetProviders;

import com.dimdron.InternetProviders.dbconnection.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 16.05.14
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
public class MainApp extends Application {

    public static void main(String [] arg){
        launch(arg);
        HibernateUtil.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/main.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("Internet Providers");
        stage.setScene(new Scene(root));
        stage.show();
    }


}
