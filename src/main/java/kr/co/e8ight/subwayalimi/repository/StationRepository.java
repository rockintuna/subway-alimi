package kr.co.e8ight.subwayalimi.repository;

import kr.co.e8ight.subwayalimi.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Query(nativeQuery = true,
            value = "select * from station " +
                    "where line_name = ?1 " +
                    "and st_contains( " +
                        "ST_BUFFER( " +
                            "st_geometryfromtext(?2, 5181) " +
                            ", ?3 " +
                        ") " +
                        ",point) "
                    )
    List<Station> findAllByLineName(String lineName, String pointString, Double radius);
}
