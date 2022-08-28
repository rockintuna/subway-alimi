package kr.co.e8ight.subwayalimi.subway;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StationPosition {
    @JsonProperty("STATN_ID")
    private String stationId;
    @JsonProperty("STATN_NM")
    private String stationName;
    @JsonProperty("ROUTE")
    private String route;
    @JsonProperty("CRDNT_X")
    private String x;
    @JsonProperty("CRDNT_Y")
    private String y;
}
