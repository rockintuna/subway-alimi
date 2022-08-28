package kr.co.e8ight.subwayalimi.subway.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RealtimeSubwayPosition {
    private SubwayInfoMessage errorMessage;
    private List<RealtimePosition> realtimePositionList;
}
