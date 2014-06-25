package com.dimdron.InternetProviders.components.adapter.cestomViewCells;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 26.05.14
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class ReviewCustomCell extends AnchorPane {
    private Integer id;
    private Label lblReview;
    private Label lblConnection;
    private Label lblSupport;
    private Label lblSpeed;

    public ReviewCustomCell() {
        setPrefWidth(300.0);
        setPrefHeight(70.);
        setPadding(new Insets(5, 5, 5, 5));

        lblReview = new Label();
        lblConnection = new Label();
        lblSpeed = new Label();
        lblSupport = new Label();

        this.getChildren().addAll(lblSpeed, lblSupport, lblConnection, lblReview);

        AnchorPane.setRightAnchor(lblReview, 70.0);
        AnchorPane.setLeftAnchor(lblReview, 10.0);
        AnchorPane.setTopAnchor(lblReview, 10.0);
        AnchorPane.setBottomAnchor(lblReview, 10.0);


        AnchorPane.setTopAnchor(lblSpeed, 10.0);
        AnchorPane.setTopAnchor(lblSupport, 20.0);
        AnchorPane.setTopAnchor(lblConnection, 30.0);

        AnchorPane.setRightAnchor(lblSpeed, 10.0);
        AnchorPane.setRightAnchor(lblSupport, 10.0);
        AnchorPane.setRightAnchor(lblConnection, 10.0);
    }

    public void setReviewText(String reviewText) {
        lblReview.setText(reviewText);
    }

    public String getReviewText() {
        return  lblReview.getText();
    }

    public Label getLabelReview() {
        return lblReview;
    }

    public Label getLabelConnection() {
        return lblConnection;
    }

    public Label getLabelSupport() {
        return lblSupport;
    }

    public Label getLabelSpeed() {
        return lblSpeed;
    }

    public void setSupport(Byte quality) {
       lblSupport.setText("Support: " + quality);
    }

    public void setConnection(Byte quality) {
        lblConnection.setText("Connection: " + quality);
    }

    public void setSpeed(Byte quality) {
        lblSpeed.setText("Speed: " + quality);
    }

    public void setReviewId(Integer id) {
        this.id = id;
    }

    public Integer getReviewId() {
        return id;
    }
}
