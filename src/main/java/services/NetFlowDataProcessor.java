package services;

import util.ComputedData;
import util.NetFlowData;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class NetFlowDataProcessor {
    private Map<String, Long> receivers = new LinkedHashMap<>();
    private Map<String, Long> transmitters = new LinkedHashMap<>();
    private Map<String, Long> protocols = new LinkedHashMap<>();
    private Map<String, Long> applications = new LinkedHashMap<>();
    private List<NetFlowData> allData = new ArrayList<>();

    public ComputedData process(List<NetFlowData> list, LocalDateTime start, LocalDateTime end){
        countAllData(list, start, end);
        sortAndLimit();
        ComputedData data = ComputedData.builder()
                .applications(applications)
                .protocols(protocols)
                .receivers(receivers)
                .transmitters(transmitters)
                .allData(allData)
                .topReceiver(receivers.entrySet().iterator().next().getKey())
                .topTransmitter(transmitters.entrySet().iterator().next().getKey())
                .build();
        selectRecordsWithTop(data);
        return data;
    }

    private void selectRecordsWithTop(ComputedData computedData) {
        List<NetFlowData> filteredListReceiver = computedData.getAllData()
                .stream().filter(e -> e.getDestinationAddress().equals(computedData.getTopReceiver()))
                .collect(Collectors.toList());
        List<NetFlowData> filteredListTransmitter = computedData.getAllData()
                .stream().filter(e -> e.getDestinationAddress().equals(computedData.getTopTransmitter()))
                .collect(Collectors.toList());
        computedData.setFilteredListReceiver(filteredListReceiver);
        computedData.setFilteredListTransmitter(filteredListTransmitter);
    }

    private void sortAndLimit() {
        receivers = sortAndLimitMap(receivers, Property.getPropertyInteger("top.receivers"));
        transmitters = sortAndLimitMap(transmitters, Property.getPropertyInteger("top.transmitters"));
        protocols = sortAndLimitMap(protocols, Property.getPropertyInteger("top.protocols"));
        applications = sortAndLimitMap(applications, Property.getPropertyInteger("top.applications"));
    }

    private Map<String, Long> sortAndLimitMap(Map<String, Long> map, int limit) {
       return map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .limit(limit)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }

    private void countAllData(List<NetFlowData> list, LocalDateTime start, LocalDateTime end) {
        for (NetFlowData data : list){
            if ((start == null || data.getDate().isAfter(start) || data.getDate().isEqual(start)) &&
                    (end == null || data.getDate().isBefore(end) || data.getDate().isEqual(end))) {
                allData.add(data);
                String keyReceiver = data.getDestinationAddress();
                String keyTransmitter = data.getSourceAddress();
                String keyProtocol = data.getProtocolNumber();
                String keyApplicationName = data.getApplicationName();

                countByTraffic(keyReceiver, receivers,data.getBytesIn());
                countByTraffic(keyTransmitter, transmitters, data.getBytesOut());

                countByKey(keyProtocol, protocols);
                countByKey(keyApplicationName, applications);
            }
        }
    }

    private void countByTraffic(String key, Map<String, Long> map, Long data) {
        if (map.containsKey(key)){
            map.put(key ,map.get(key) + data);
        } else {
            map.put(key, data);
        }
    }

    private void countByKey(String key, Map<String, Long> map) {
        if (map.containsKey(key)){
            map.put(key ,map.get(key) + 1);
        } else {
            map.put(key, 1L);
        }
    }
}
