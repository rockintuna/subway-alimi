package kr.co.e8ight.subwayalimi.domain;

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
    private String lineName;
    private String x;
    private String y;
    private String addressName;

    public static List<Station> getListFrom(List<StationInfo> stationInfoList) {
        List<Station> stationList = new ArrayList<>();
        for (StationInfo stationInfo : stationInfoList) {
            stationList.add(Station.from(stationInfo));
        }
        return stationList;
    }

    public static Station from(StationInfo stationInfo) {
        return Station.builder()
                .id(Long.getLong(stationInfo.getStationId()))
                .name(stationInfo.getStationName())
                .lineName(stationInfo.getLineName())
                .addressName(stationInfo.getAddressName())
                .x(stationInfo.getX())
                .y(stationInfo.getY())
                .build();
    }
}
