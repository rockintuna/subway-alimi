package kr.co.e8ight.subwayalimi.subway;

import kr.co.e8ight.subwayalimi.subway.dto.RealtimePosition;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subwayId;
    private String subwayName;
    private String currentStationId;
    private String currentStationName;
    private LocalDate lastReceivedDate;
    private LocalDateTime receivedDateTime;
    private String direction;
    private String lastStationId;
    private String lastStationName;
    private Integer trainStatus;
    private Boolean isExpress;
    private Boolean isLast;

    public static List<Train> getListFrom(List<RealtimePosition> realtimePositionList) {
        List<Train> trainList = new ArrayList<>();
        for (RealtimePosition realtimePosition : realtimePositionList) {
            trainList.add(Train.from(realtimePosition));
        }
        return trainList;
    }

    private static Train from(RealtimePosition realtimePosition) {
        return Train.builder()
                .id(Long.getLong(realtimePosition.getTrainNo()))
                .subwayId(realtimePosition.getSubwayId())
                .subwayName(realtimePosition.getSubwayNm())
                .currentStationId(realtimePosition.getStatnId())
                .currentStationName(realtimePosition.getStatnNm())
                .lastReceivedDate(LocalDate.parse(realtimePosition.getLastRecptnDt(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .receivedDateTime(LocalDateTime.parse(realtimePosition.getRecptnDt(), DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .direction(realtimePosition.getUpdnLine())
                .lastStationId(realtimePosition.getStatnTid())
                .lastStationName(realtimePosition.getStatnTnm())
                .trainStatus(Integer.getInteger(realtimePosition.getTrainSttus()))
                .isExpress(realtimePosition.getDirectAt().equals("1"))
                .isLast(realtimePosition.getLstcarAt().equals("1"))
                .build();
    }
}
