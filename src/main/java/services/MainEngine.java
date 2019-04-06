package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import gui.LineChartAWT;
import org.jfree.ui.RefineryUtilities;
import util.ComputedData;
import util.NetFlowData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class MainEngine {

    public void startEngine(){
        startEngine(Paths.get(Property.getProperty("read.file.path")) ,null, null);
    }

    public void startEngine(LocalDateTime start, LocalDateTime end){
        startEngine(Paths.get(Property.getProperty("read.file.path")), start, end);
    }

    public void startEngine(Path pathFile){
        startEngine(pathFile, null, null);
    }

    public void startEngine(Path pathFile, LocalDateTime start, LocalDateTime end) {
        NetFlowReader netFlowReader = new NetFlowReader();
        List<NetFlowData> netFlowDataList = netFlowReader.readFromCSVFile(pathFile);

        NetFlowDataProcessor processor = new NetFlowDataProcessor();
        ComputedData computedData = processor.process(netFlowDataList, start, end);

        writeResultInJSON(computedData);

        runChartWindow(computedData);
    }

    private void runChartWindow(ComputedData computedData) {

        LineChartAWT chart = new LineChartAWT(
                Property.getProperty("application.title") ,
                Property.getProperty("chart.title"),
                computedData);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    private void writeResultInJSON(ComputedData computedData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(Property.getProperty("write.file.path")), computedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
