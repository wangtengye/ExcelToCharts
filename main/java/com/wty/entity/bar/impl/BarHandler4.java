package com.wty.entity.bar.impl;

import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.itemstyle.Emphasis;
import com.github.abel533.echarts.style.itemstyle.Normal;
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
public class BarHandler4 implements BarHandler{

    @Override
    public String getJson() {
        Workbook workbook = ExcelUtil.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        GsonOption option = new GsonOption();
        option.title(sheet.getSheetName()).toolbox().show(true).feature(Feature.saveAsImage, Feature.magicType);
        Tooltip tooltip=new Tooltip();
        tooltip.trigger(Trigger.axis).axisPointer().type(PointerType.shadow);
        String format="function(params){var tar=params[1]; return tar.name + ' : ' + tar.value;}";
        tooltip.formatter(format);
        option.tooltip(tooltip);
        List<List<String>> data = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 0; i <= 1; i++) {
            data.add(new ArrayList<>());
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                data.get(i).add(dataFormatter.formatCellValue(row.getCell(j)));
            }
        }
        List<String>helpData=new ArrayList<>();
        helpData.add("0");
        int total=Integer.parseInt(data.get(1).get(0));
        int sum=0;
        for (int i = 1; i < data.get(1).size(); i++) {
            sum+=Integer.parseInt(data.get(1).get(i));
            helpData.add(total-sum+"");
        }

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setData(data.get(0));
        option.xAxis(categoryAxis);
        option.yAxis(new ValueAxis());
        List<Series> bars = new ArrayList<>();

        Bar help=new Bar();
        ItemStyle itemStyle=new ItemStyle();
        itemStyle.normal(new Normal().barBorderColor("rgba(0,0,0,0)").color("rgba(0,0,0,0)"))
                .emphasis(new Emphasis().barBorderColor("rgba(0,0,0,0)").color("rgba(0,0,0,0)"));
        help.stack("stack").itemStyle(itemStyle).setData(helpData);
        bars.add(help);

        Bar bar = new Bar();
        ItemStyle itemStyle2=new ItemStyle();
        itemStyle2.normal(new Normal().show(true).position(Position.inside));
        bar.stack("stack").label(itemStyle2);
        bar.setData(data.get(1));
        bars.add(bar);
        option.series(bars);

        return option.toPrettyString();
    }



}
