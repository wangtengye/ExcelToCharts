package com.wty.entity.pie.impl;

import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Pie;
import com.wty.entity.pie.PieHandler;
import com.wty.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/8/15.
 */
class PieData{
    String name;
    String value;

    public PieData(String category, String value) {
        this.name = category;
        this.value = value;
    }
}
@Component
public class PieHandler1 implements PieHandler {
    @Override
    public String getJson() {
        Workbook workbook = ExcelUtil.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        GsonOption option = new GsonOption();
        option.title(sheet.getSheetName()).toolbox().show(true).feature(Feature.saveAsImage);
        String format="{b} : {c} ({d}%)";
        option.tooltip(new Tooltip().formatter(format));
        DataFormatter dataFormatter = new DataFormatter();
        List<PieData>pieDatas=new ArrayList<>();
        List<String >legend=new ArrayList<>();
        for(int i=0;i<=sheet.getLastRowNum();i++){
            Row row=sheet.getRow(i);
            String category=dataFormatter.formatCellValue(row.getCell(0));
            legend.add(category);
            String value=dataFormatter.formatCellValue(row.getCell(1));
            pieDatas.add(new PieData(category,value));
        }
        option.legend().data(legend);
        Pie pie=new Pie();
        pie.setData(pieDatas);
        option.series(pie);
        return option.toPrettyString();
    }
    
}
