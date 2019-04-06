package util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ComputedData {
    @JsonProperty("top_10_receivers_by_traffic")
    private Map<String, Long> receivers;
    @JsonProperty("top_10_transmitter_by_traffic")
    private Map<String, Long> transmitters;
    @JsonProperty("top_3_used_protocol")
    private Map<String, Long> protocols;
    @JsonProperty("top_10_used_applications")
    private Map<String, Long> applications;
    @JsonIgnore
    private List<NetFlowData> allData;
    @JsonIgnore
    private String topReceiver;
    @JsonIgnore
    private String topTransmitter;
    @JsonIgnore
    private List<NetFlowData> filteredListReceiver;
    @JsonIgnore
    private List<NetFlowData> filteredListTransmitter;
}
