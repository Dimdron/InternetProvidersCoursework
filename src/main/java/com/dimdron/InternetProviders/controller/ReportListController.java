package com.dimdron.InternetProviders.controller;

import com.dimdron.InternetProviders.components.reportview.ReportView;
import com.dimdron.InternetProviders.dbconnection.factory.Factory;
import com.dimdron.InternetProviders.dbconnection.model.ApplicationModel;
import com.dimdron.InternetProviders.dbconnection.model.Provider;
import com.dimdron.InternetProviders.dbconnection.model.Tariff;
import com.dimdron.InternetProviders.dbconnection.model.User;
import com.dimdron.InternetProviders.reportsmanager.manager.ApplicationReport;
import com.dimdron.InternetProviders.reportsmanager.manager.ProviderReport;
import com.dimdron.InternetProviders.reportsmanager.manager.RepairmanReport;
import com.dimdron.InternetProviders.reportsmanager.manager.TariffReport;
import com.dimdron.InternetProviders.reportsmanager.util.ReportBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 03.06.14
 * Time: 7:30
 * To change this template use File | Settings | File Templates.
 */
public class ReportListController {
    @FXML private ImageView imgProviders;
    @FXML private ImageView imgTariffs;

    @FXML private void initialize() {
        imgProviders.setImage(new Image("/image/reports/providers.jpg"));
        imgTariffs.setImage(new Image("/image/reports/tariffs.jpg"));
    }

    public static void openFromProvidersReport(Collection<Provider> providers) throws Exception {
        List<ProviderReport> reports= new ArrayList<ProviderReport>();
        for (Provider p: providers) {
            ProviderReport r = new ProviderReport();
            r.setNameProvider(p.getName());
            r.setTelephoneProviders(p.getTelephone());
            r.setSiteProvider(p.getName());
            r.setQuality(new Double(p.getCommonQuality()).toString());
            r.setListRegion(p.districtToString());
            r.setListConnection(p.connectionsToString());
            r.setCountTariffs(new Integer(p.getTariffs().size()).toString());

            reports.add(r);
        }
        ReportBuilder reportBuilder = new ReportBuilder();
        ReportView reportView = new ReportView();

        reportView.showReport(reportBuilder.buildReport("List of all provider",
                ProviderReport.class), reports);
    }

    public static void openApplicationsReport(Collection<ApplicationModel> collection) throws Exception {
        List<ApplicationReport> reports= new ArrayList<ApplicationReport>();
        for (ApplicationModel a: collection) {
            ApplicationReport r = new ApplicationReport();
            r.setAddress(a.getAddress());
            r.setProvider(a.getProvider().getName());
            r.setProblem(a.getText());
            r.setRegion(a.getDistrict().getName());
            r.setState(a.getState().toString());

            reports.add(r);
        }
        ReportBuilder reportBuilder = new ReportBuilder();
        ReportView reportView = new ReportView();

        reportView.showReport(reportBuilder.buildReport("List of complaints",
                ApplicationReport.class), reports);
    }

    public static void openFromTariffsReport(Collection<Tariff> tariffs) throws Exception {
        List<TariffReport> reports= new ArrayList<TariffReport>();
        for (Tariff t: tariffs) {
            TariffReport r = new TariffReport();
            r.setConnection(t.getProvider().connectionsToString());
            r.setDescription(t.getProvider().districtToString());
            r.setNameTariff(t.getName());
            r.setPrice(t.getPrice());
            r.setSpeed(t.getSpeed());
            r.setProviderName(t.getProvider().getName());
            reports.add(r);
        }
        ReportBuilder reportBuilder = new ReportBuilder();
        ReportView reportView = new ReportView();

        reportView.showReport(reportBuilder.buildReport("List of all tariffs",
                TariffReport.class), reports);
    }

    public static void openFromUsersReport(Collection<User> users) throws Exception {
        List<RepairmanReport> reports= new ArrayList<RepairmanReport>();
        for (User u: users) {
            RepairmanReport r = new RepairmanReport();
            r.setCode(u.getId().toString());
            r.setName(u.getName());
            r.setPerformed(Factory.getInstance().getUser().getPerformedById(u.getId()).toString());
            reports.add(r);
        }
        ReportBuilder reportBuilder = new ReportBuilder();
        ReportView reportView = new ReportView();

        reportView.showReport(reportBuilder.buildReport("List of all repairman",
                RepairmanReport.class), reports);
    }

    public void onActionBtnPerformed(ActionEvent actionEvent) throws Exception {
        openFromUsersReport(Factory.getInstance().getUser().getByType((byte)3));
    }

    @FXML private void onClickedProviders() throws Exception {
        openFromProvidersReport(Factory.getInstance().getProviderDAO().getAll());
    }

    @FXML private void onClickedTariffs() throws Exception {
        openFromTariffsReport(Factory.getInstance().getTariffDAO().getAll());
    }


}
