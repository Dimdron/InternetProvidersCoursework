package com.dimdron.InternetProviders.reportsmanager.util;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 02.06.14
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import com.dimdron.InternetProviders.reportsmanager.annotations.Report;

import java.lang.reflect.Field;
import java.util.Date;

public class ReportBuilder {
    public DynamicReport buildReport(String title, Class inClass) throws Exception {

        FastReportBuilder drb = new FastReportBuilder();
        if(inClass != null){

            Field[] fileds = inClass.getDeclaredFields();
            for(Field f : fileds){
                if(f.isAnnotationPresent(Report.class)){
                    try{
                        drb.addColumn(f.getName(), f.getName(),  f.getType().getName(), 30);
                    } catch (Exception ex){

                    }

                }
            }
        }

        drb.setTitle(title)
                .setSubtitle("This report was generated at " + new Date())
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true);
        Style atStyle = new StyleBuilder(true).setFont(Font.COMIC_SANS_SMALL).build();
        drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER,
                AutoText.ALIGMENT_LEFT, AutoText.PATTERN_DATE_DATE_TIME, 30, 30); //, atStyle
        DynamicReport dr = drb.build();
        return dr;
    }
}
