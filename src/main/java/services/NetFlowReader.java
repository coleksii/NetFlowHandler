package services;

import util.NetFlowData;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NetFlowReader {

    public List<NetFlowData> readFromCSVFile(Path path) {
        HeaderColumnNameMappingStrategy<NetFlowData> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(NetFlowData.class);
        List<NetFlowData> list = null;
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean cb = new CsvToBeanBuilder(reader).withType(NetFlowData.class)
                    .withMappingStrategy(ms)
                    .build();
            list = cb.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
