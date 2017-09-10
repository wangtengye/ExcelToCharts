package com.wty.entity.line.impl;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Series;
import com.wty.entity.line.LineHandler;
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
@Component
public class LineHandler1 implements LineHandler {
    @Override
    public String getJson() {
        Workbook workbook = ExcelUtil.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        GsonOption option = new GsonOption();
        option.title(sheet.getSheetName()).toolbox().show(true).feature(Feature.saveAsImage, Feature.magicType);
        option.tooltip(Trigger.axis);
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
        option.xAxis(categoryAxis);
        option.yAxis(new ValueAxis());
        List<Series> lines = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            List<String> temp = data.get(i);
            Line line = new Line(legend.get(i));
            line.setData(temp);
            lines.add(line);
        }
        option.series(lines);
        return option.toPrettyString();
    }
    
}
