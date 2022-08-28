package kr.co.e8ight.subwayalimi.subway;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String route;
    private String x;
    private String y;
    public static List<Station> getListFrom(List<StationPosition> stationPositionList) {
        List<Station> stationList = new ArrayList<>();
        for (StationPosition stationPosition : stationPositionList) {
            stationList.add(Station.from(stationPosition));
        }
        return stationList;
    }

    private static Station from(StationPosition stationPosition) {
        return Station.builder()
                .id(Long.getLong(stationPosition.getStationId()))
                .name(stationPosition.getStationName())
                .route(stationPosition.getRoute())
                .x(stationPosition.getX())
                .y(stationPosition.getY())
                .build();
    }
}
