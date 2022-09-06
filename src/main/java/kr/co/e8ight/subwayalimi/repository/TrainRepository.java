package kr.co.e8ight.subwayalimi.repository;

import kr.co.e8ight.subwayalimi.domain.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {
    void deleteBySubwayName(String subwayId);

    List<Train> findAllBySubwayName(String lineName);
}
