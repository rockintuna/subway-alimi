package kr.co.e8ight.subwayalimi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RealtimeTrainPositionResponse {
    private SubwayInfoMessage errorMessage;
    private List<RealtimeTrainPosition> realtimeTrainPositionList;
}
