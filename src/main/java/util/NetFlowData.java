package util;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import converter.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NetFlowData {
    @CsvCustomBindByName(converter = LocalDateTimeConverter.class)
    private LocalDateTime date;
    @CsvBindByName
    private Long bytesIn;
    @CsvBindByName
    private Long bytesOut;
    @CsvBindByName
    private Long packetsIn;
    @CsvBindByName
    private Long packetsOut;
    @CsvBindByName
    private String applicationName;
    @CsvBindByName
    private String destinationAddress;
    @CsvBindByName
    private String protocolNumber;
    @CsvBindByName
    private String sourceAddress;
}
