package com.dimdron.InternetProviders.components.reportview;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 03.06.14
 * Time: 2:15
 * To change this template use File | Settings | File Templates.
 */
public class ReportView {
    public void showReport(DynamicReport dr, Collection<?> data) {
        JasperPrint jp = null;
        try {
            jp = DynamicJasperHelper.generateJasperPrint(dr,
                    new ClassicLayoutManager(),
                    data);
        } catch (Exception e) { e.printStackTrace(); }

        JFrame frame = new JFrame("Report");
        frame.getContentPane().add(new JRViewer(jp));
        frame.pack();
        frame.setVisible(true);
    }
}
