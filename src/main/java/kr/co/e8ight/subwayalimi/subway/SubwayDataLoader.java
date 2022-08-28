package kr.co.e8ight.subwayalimi.subway;

import kr.co.e8ight.subwayalimi.subway.dto.RealtimePosition;
import kr.co.e8ight.subwayalimi.subway.dto.RealtimeSubwayPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubwayDataLoader {
    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;
    private final RestTemplate restTemplate;
    private static final String JSON_REALTIME_POSITION_URL = "http://swopenapi.seoul.go.kr/api/subway/sample/json/realtimePosition/0/5/1호선";
    private static final String JSON_STATION_GEO_URL = "http://openapi.seoul.go.kr:8088/766368444a726f6339367861484170/json/subwayStationMaster/1/1000/";

    public SubwayStationReceived loadStationPositionInfo() {
        Map<String, String> uriVariables = new HashMap<>();
        return restTemplate.getForObject(JSON_STATION_GEO_URL, SubwayStationReceived.class, uriVariables);
    }

    public void saveStationPositionInfo() {
        Map<String, String> uriVariables = new HashMap<>();
        SubwayStationReceived subwayStationReceived = restTemplate.getForObject(JSON_STATION_GEO_URL, SubwayStationReceived.class, uriVariables);
        List<StationPosition> stationPositionList = subwayStationReceived.getSubwayStationMaster().getStationPositionList();

        stationRepository.saveAll(Station.getListFrom(stationPositionList));
    }

    public RealtimeSubwayPosition loadRealtimeTrainPositionInfo() {
        Map<String, String> uriVariables = new HashMap<>();
        return restTemplate.getForObject(JSON_REALTIME_POSITION_URL, RealtimeSubwayPosition.class, uriVariables);
    }

    public void saveSubwayInfo() {
        Map<String, String> uriVariables = new HashMap<>();
        RealtimeSubwayPosition realtimeSubwayPosition = restTemplate.getForObject(JSON_REALTIME_POSITION_URL, RealtimeSubwayPosition.class, uriVariables);

        List<RealtimePosition> realtimePositionList = realtimeSubwayPosition.getRealtimePositionList();
        List<Train> trainList = Train.getListFrom(realtimePositionList);
        trainRepository.saveAll(trainList);
    }
}
