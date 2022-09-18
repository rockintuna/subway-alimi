package kr.co.e8ight.subwayalimi.service;

import kr.co.e8ight.subwayalimi.domain.Station;
import kr.co.e8ight.subwayalimi.domain.StationInfo;
import kr.co.e8ight.subwayalimi.domain.Train;
import kr.co.e8ight.subwayalimi.dto.RealtimeTrainPosition;
import kr.co.e8ight.subwayalimi.repository.StationRepository;
import kr.co.e8ight.subwayalimi.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubwayService {

    private final StationRepository stationRepository;
    private final SubwayDataLoader subwayDataLoader;
    private final TrainRepository trainrepository;

    @Autowired
    public SubwayService(StationRepository stationRepository, SubwayDataLoader subwayDataLoader, TrainRepository trainrepository) {
        this.stationRepository = stationRepository;
        this.subwayDataLoader = subwayDataLoader;
        this.trainrepository = trainrepository;
//        saveStationPositionInfo();
    }

    public List<Station> getStationPositionInfo(String lineName, Point point, Double radius) {
//    public List<Station> getStationPositionInfo(String lineName) {
        String pointString = "POINT("+point.getX()+" "+point.getY()+")";
        System.out.println(pointString);
        return stationRepository.findAllByLineName(lineName, pointString, radius);
    }

    public Train getRealtimeTrainPosition(Long trainId) {
        return trainrepository.findById(trainId).orElseThrow(() -> new RuntimeException("not found train ID"));
    }

    @Transactional
    public void refreshRealtimeTrainPositions(String lineName) {
        List<RealtimeTrainPosition> realtimeTrainPositions = subwayDataLoader.getRealtimePositions(lineName);
        List<Train> trainList = Train.getListFrom(realtimeTrainPositions);
        trainrepository.deleteBySubwayName(lineName);
        trainrepository.saveAll(trainList);
    }

    public List<Train> getRealtimeTrainPositionList(String lineName) {
        return trainrepository.findAllBySubwayName(lineName);
    }

    private void saveStationPositionInfo() {
        List<StationInfo> stationInfos = subwayDataLoader.getStationInfoList();
        stationRepository.saveAll(Station.getListFrom(stationInfos));

    }
}
