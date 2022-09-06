package kr.co.e8ight.subwayalimi.domain;

import kr.co.e8ight.subwayalimi.dto.RealtimeTrainPosition;
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

    public static List<Train> getListFrom(List<RealtimeTrainPosition> realtimeTrainPositionList) {
        List<Train> trainList = new ArrayList<>();
        for (RealtimeTrainPosition realtimeTrainPosition : realtimeTrainPositionList) {
            trainList.add(Train.from(realtimeTrainPosition));
        }
        return trainList;
    }

    private static Train from(RealtimeTrainPosition realtimeTrainPosition) {
        return Train.builder()
                .id(Long.getLong(realtimeTrainPosition.getTrainNo()))
                .subwayId(realtimeTrainPosition.getSubwayId())
                .subwayName(realtimeTrainPosition.getSubwayNm())
                .currentStationId(realtimeTrainPosition.getStatnId())
                .currentStationName(realtimeTrainPosition.getStatnNm())
                .lastReceivedDate(LocalDate.parse(realtimeTrainPosition.getLastRecptnDt(), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .receivedDateTime(LocalDateTime.parse(realtimeTrainPosition.getRecptnDt(), DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .direction(realtimeTrainPosition.getUpdnLine())
                .lastStationId(realtimeTrainPosition.getStatnTid())
                .lastStationName(realtimeTrainPosition.getStatnTnm())
                .trainStatus(Integer.getInteger(realtimeTrainPosition.getTrainSttus()))
                .isExpress(realtimeTrainPosition.getDirectAt().equals("1"))
                .isLast(realtimeTrainPosition.getLstcarAt().equals("1"))
                .build();
    }
}
