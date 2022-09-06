package kr.co.e8ight.subwayalimi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubwayStationInfo {
    @JsonProperty("SearchSTNBySubwayLineInfo")
    SubwayStationMaster searchSTNBySubwayLineInfo;
}
