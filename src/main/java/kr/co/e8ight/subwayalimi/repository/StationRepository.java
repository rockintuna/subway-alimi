package kr.co.e8ight.subwayalimi.repository;

import kr.co.e8ight.subwayalimi.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findAllByLineName(String lineName);
}
