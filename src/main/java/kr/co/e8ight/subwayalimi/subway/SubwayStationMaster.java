package kr.co.e8ight.subwayalimi.subway;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubwayStationMaster {
    @JsonProperty("list_total_count")
    private Integer listTotalCount;
    @JsonProperty("RESULT")
    private StationMasterMessage result;
    @JsonProperty("row")
    private List<StationPosition> stationPositionList;
}
