package com.wty.entity.bar.impl;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
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

/**
 * Created by user on 2017/8/9.
 */
@Component
public class BarHandler2 implements BarHandler {
    @Override
    public String getJson() {
        Workbook workbook = ExcelUtil.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        GsonOption option = new GsonOption();
        option.title(sheet.getSheetName()).toolbox().show(true).feature(Feature.saveAsImage, Feature.magicType);
        option.tooltip();
        Row firstRow = sheet.getRow(0);
        List<String> legend = new ArrayList<>();
        firstRow.forEach(cell -> legend.add(cell.getStringCellValue()));
        option.legend().data(legend.subList(1, legend.size()));
        List<List<String>> data = new ArrayList<>();
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            data.add(new ArrayList<>());
        }
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                data.get(j).add(dataFormatter.formatCellValue(row.getCell(j)));

            }
        }
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setData(data.get(0));
        option.yAxis(categoryAxis);
        option.xAxis(new ValueAxis());
        List<Series> bars = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            List<String> temp = data.get(i);
            Bar bar = new Bar(legend.get(i));
            bar.setData(temp);
            bars.add(bar);
        }
        option.series(bars);
        return option.toPrettyString();
    }
}
