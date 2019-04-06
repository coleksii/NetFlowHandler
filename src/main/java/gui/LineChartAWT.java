package gui;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import services.Property;
import util.ComputedData;
import util.NetFlowData;

import java.util.List;

public class LineChartAWT extends ApplicationFrame {

    private List<NetFlowData> receievers;
    private List<NetFlowData> tranmitters;

    public LineChartAWT(String applicationTitle , String chartTitle, ComputedData computedData) {
        super(applicationTitle);
        this.tranmitters = computedData.getFilteredListTransmitter();
        this.receievers = computedData.getFilteredListReceiver();

        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                Property.getProperty("chart.category"),
                Property.getProperty("chart.value"),
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setVisible(false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560 ,367));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for (NetFlowData data : receievers){
            dataset.addValue(data.getBytesIn(), "Top receiver: " + data.getDestinationAddress(), data.getDate());
        }
        for (NetFlowData data : tranmitters){
            dataset.addValue(data.getBytesOut(), "Top transmitter " + data.getSourceAddress(), data.getDate());
        }
        return dataset;
    }
}