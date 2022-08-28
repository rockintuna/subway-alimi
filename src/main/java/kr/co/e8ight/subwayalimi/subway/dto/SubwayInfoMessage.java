package kr.co.e8ight.subwayalimi.subway.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubwayInfoMessage {
    private Integer status;
    private String code;
    private String message;
    private String link;
    private String developerMessage;
    private Integer total;
}
