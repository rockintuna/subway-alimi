package kr.co.e8ight.subwayalimi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StationInfo {
    @JsonProperty("STATION_CD")
    private String stationId;
    @JsonProperty("STATION_NM")
    private String stationName;
    @JsonProperty("STATION_NM_ENG")
    private String stationEngName;
    @JsonProperty("LINE_NUM")
    private String lineName;
}
