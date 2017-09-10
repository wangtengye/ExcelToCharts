package com.wty.entity.bar.impl;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Series;
import com.wty.entity.bar.BarHandler;
import com.wty.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BarHandler3 implements BarHandler {
    @Override
    public String getJson() {
        Workbook workbook = ExcelUtil.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        GsonOption option = new GsonOption();
        option.toolbox().show(true).feature(Feature.saveAsImage, Feature.magicType);
        option.tooltip().trigger(Trigger.axis).axisPointer().type(PointerType.shadow);
        Row firstRow = sheet.getRow(0);
        List<String> legend = new ArrayList<>();
        firstRow.forEach(cell -> legend.add(cell.getStringCellValue()));
        option.legend().data(legend.subList(1, legend.size()));
        List<List<String>> data = new ArrayList<>();
        List<String>stacks=new ArrayList<>();
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            data.add(new ArrayList<>());
        }
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 1; i <= sheet.getLastRowNum()-2; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                data.get(j).add(dataFormatter.formatCellValue(row.getCell(j)));
            }
        }
        Row lastRow=sheet.getRow(sheet.getLastRowNum());
        for(int i=1;i<lastRow.getLastCellNum();i++)
            stacks.add(dataFormatter.formatCellValue(lastRow.getCell(i)));
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setData(data.get(0));
        option.xAxis(categoryAxis);
        option.yAxis(new ValueAxis());
        List<Series> bars = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            List<String> temp = data.get(i);
            Bar bar = new Bar(legend.get(i));
            bar.setData(temp);
            bar.setStack(stacks.get(i-1));
            bars.add(bar);
        }
        option.series(bars);
        return option.toPrettyString();

    }

}
