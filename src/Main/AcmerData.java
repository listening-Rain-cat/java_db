package Main;

import DB.AcmDB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AcmerData {
  static   public JPanel tu(String s) {

         //默认是当前日期
         Calendar c = Calendar.getInstance();

        //成员统计
        @SuppressWarnings("deprecation")
        TimeSeries timeSeries1 = new TimeSeries("A", Month.class);
        int user_num [] = AcmDB.userpass(s);

        // 添加成员数据
        timeSeries1.add(new Month(1, c.get(1)), user_num[0]);
        timeSeries1.add(new Month(2, c.get(1)), user_num[1]);
        timeSeries1.add(new Month(3, c.get(1)), user_num[2]);
        timeSeries1.add(new Month(4, c.get(1)), user_num[3]);
        timeSeries1.add(new Month(5, c.get(1)), user_num[4]);
        timeSeries1.add(new Month(6, c.get(1)), user_num[5]);
        timeSeries1.add(new Month(7, c.get(1)), user_num[6]);
        timeSeries1.add(new Month(8, c.get(1)), user_num[7]);
        timeSeries1.add(new Month(9, c.get(1)), user_num[8]);
        timeSeries1.add(new Month(10, c.get(1)), user_num[9]);
        timeSeries1.add(new Month(11, c.get(1)), user_num[10]);
        timeSeries1.add(new Month(12, c.get(1)), user_num[11]);

        // 平均量统计
        TimeSeries timeSeries2 = new TimeSeries("A", Month.class);
        int num []=new int [12];
        num=AcmDB.arv();
        // 添加平均量数据
        timeSeries2.add(new Month(1, c.get(1)),  num[0]);
        timeSeries2.add(new Month(2, c.get(1)),  num[1]);
        timeSeries2.add(new Month(3, c.get(1)),  num[2]);
        timeSeries2.add(new Month(4, c.get(1)), num[3]);
        timeSeries2.add(new Month(5, c.get(1)), num[4]);
        timeSeries2.add(new Month(6, c.get(1)), num[5]);
        timeSeries2.add(new Month(7, c.get(1)), num[6]);
        timeSeries2.add(new Month(8, c.get(1)), num[7]);
        timeSeries2.add(new Month(9, c.get(1)), num[8]);
        timeSeries2.add(new Month(10, c.get(1)), num[9]);
        timeSeries2.add(new Month(11, c.get(1)), num[10]);
        timeSeries2.add(new Month(12, c.get(1)), num[11]);

        // 定义时间序列的集合
        TimeSeriesCollection lineDataset = new TimeSeriesCollection();
        lineDataset.addSeries(timeSeries1);
        lineDataset.addSeries(timeSeries2);

//         JFreeChart chart = ChartFactory.createXYStepChart("Time line graph", "M", "F", xySeriesCollection, PlotOrientation.HORIZONTAL, false, false, false);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Time line graph", "Month", "Number", lineDataset, false, false, false);
        //设置主标题
        chart.setTitle(new TextTitle(Integer.toString(c.get(1))+"年做题量与平均量对比"));
        //设置子标题
       // TextTitle subtitle = new TextTitle(Integer.valueOf(c.get(1)).toString(), new Font("宋体", Font.BOLD, 12));
      //  chart.addSubtitle(subtitle);

        chart.setAntiAlias(true);

        //设置时间轴的范围。
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("M"));
        dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH, 1));

        //设置曲线是否显示数据点
        XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) plot.getRenderer();
        xylinerenderer.setBaseShapesVisible(true);

        //设置曲线显示各数据点的值
        XYItemRenderer xyitem = plot.getRenderer();
        xyitem.setBaseItemLabelsVisible(true);
        xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitem.setBaseItemLabelFont(new Font("Dialog", Font.BOLD, 12));
        plot.setRenderer(xyitem);

        JPanel jPanel = new ChartPanel(chart);
        return  jPanel;

    }

}