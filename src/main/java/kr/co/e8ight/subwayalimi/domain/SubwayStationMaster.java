package kr.co.e8ight.subwayalimi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.e8ight.subwayalimi.service.StationMasterMessage;
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
    private List<StationInfo> stationInfoList;
}
