package kr.co.e8ight.subwayalimi.service;

import kr.co.e8ight.subwayalimi.domain.Address;
import kr.co.e8ight.subwayalimi.domain.StationInfo;
import kr.co.e8ight.subwayalimi.domain.SubwayStationInfo;
import kr.co.e8ight.subwayalimi.dto.AddressSearchResponse;
import kr.co.e8ight.subwayalimi.dto.RealtimeTrainPosition;
import kr.co.e8ight.subwayalimi.dto.RealtimeTrainPositionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubwayDataLoader {
    private final RestTemplate restTemplate;
    private static final String JSON_REALTIME_POSITION_URL = "http://swopenapi.seoul.go.kr/api/subway/5176624142726f63383972744a5672/json/realtimePosition/0/200/";
    private static final String JSON_STATION_GEO_URL = "http://openapi.seoul.go.kr:8088/766368444a726f6339367861484170/json/subwayStationMaster/1/1000/";
    private static final String STATION_INFO_JSON = "http://openapi.seoul.go.kr:8088/766368444a726f6339367861484170/json/SearchSTNBySubwayLineInfo/1/1000";
    private static final String LOCATION_SEARCH_JSON = "https://dapi.kakao.com/v2/local/search/keyword.json";

    public List<StationInfo> getStationInfoList() {
        SubwayStationInfo subwayStationInfo = restTemplate.getForObject(STATION_INFO_JSON, SubwayStationInfo.class);
        List<StationInfo> stationInfoList = subwayStationInfo.getSearchSTNBySubwayLineInfo().getStationInfoList();
        return attachAddressIntoStationInfo(stationInfoList);
    }

    private List<StationInfo> attachAddressIntoStationInfo(List<StationInfo> stationInfos) {
        for (StationInfo stationInfo : stationInfos) {

            String lineName = stationInfo.getLineName();
            if ( stationInfo.getLineName().startsWith("0") ) {
                lineName = stationInfo.getLineName().substring(1);
            }
            if ( stationInfo.getLineName().equals("인천선") ) {
                lineName = "인천1호선";
            }
            if ( stationInfo.getLineName().equals("우이신설경전철") ) {
                lineName = "우이신설선";
            }
            if ( stationInfo.getLineName().equals("인천2호선") &&
             stationInfo.getStationName().equals("인천시청") ) {
                lineName = "인천1호선";
            }

            String fullUrl = LOCATION_SEARCH_JSON +
                    "?page=1" +
                    "&size=1" +
                    "&sort=accuracy" +
                    "&category_group_code=SW8" +
                    "&query=" + stationInfo.getStationName() + "역 " + lineName;

            HttpHeaders headers = new HttpHeaders();
            headers.put("Authorization", List.of("KakaoAK ebdb9ca6d94d323172c62952239549b9"));
            HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
            ResponseEntity<AddressSearchResponse> responseEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, httpEntity, AddressSearchResponse.class);
            AddressSearchResponse searchResponse = responseEntity.getBody();
            if (searchResponse != null && !searchResponse.getDocuments().isEmpty()) {
                Address address = searchResponse.getDocuments().get(0);
                stationInfo.setAddress(address);
            }
        }
        return stationInfos;
    }

    public List<RealtimeTrainPosition> getRealtimePositions(String lineName) {
        Map<String, String> uriVariables = new HashMap<>();
        RealtimeTrainPositionResponse realtimeTrainPositionResponse = restTemplate.getForObject(JSON_REALTIME_POSITION_URL+lineName, RealtimeTrainPositionResponse.class, uriVariables);
        return realtimeTrainPositionResponse.getRealtimeTrainPositionList();
    }
}
