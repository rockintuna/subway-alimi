package kr.co.e8ight.subwayalimi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RealtimeTrainPosition {
    private String subwayId;
    private String subwayNm;
    private String statnId;
    private String statnNm;
    private String trainNo;
    private String lastRecptnDt;
    private String recptnDt;
    private String updnLine;
    private String statnTid;
    private String statnTnm;
    private String trainSttus;
    private String directAt;
    private String lstcarAt;
}

