package kr.co.e8ight.subwayalimi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import javax.persistence.*;
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

    @Column(columnDefinition = "geometry(Point,5181)")
    @JsonIgnore
    private Point point;

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
        Geometry geometry;
        try {
            WKTReader wktReader = new WKTReader();
            geometry = wktReader.read("POINT(" + stationInfo.getX() + " " + stationInfo.getY() + ")");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return Station.builder()
                .id(Long.getLong(stationInfo.getStationId()))
                .name(stationInfo.getStationName())
                .lineName(stationInfo.getLineName())
                .addressName(stationInfo.getAddressName())
                .x(stationInfo.getX())
                .y(stationInfo.getY())
                .point((Point) geometry)
                .build();
    }
}
