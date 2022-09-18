package kr.co.e8ight.subwayalimi.controller;

import kr.co.e8ight.subwayalimi.domain.Station;
import kr.co.e8ight.subwayalimi.domain.Train;
import kr.co.e8ight.subwayalimi.service.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubwayController {
    private final SubwayService subwayService;

    @GetMapping("/stations")
    public List<Station> getSubwayStationReceived(@RequestParam("line") String lineName
                                                  ,@RequestParam("x") Double x,
                                                  @RequestParam("y") Double y,
                                                  @RequestParam("radius") Double radius
                                                  ) {
//        return subwayService.getStationPositionInfo(lineName);
        return subwayService.getStationPositionInfo(lineName, new Point(x,y), radius);
    }

    @GetMapping("/trains/{trainId}")
    public Train getRealtimeTrainPosition(@PathVariable Long trainId) {
        return subwayService.getRealtimeTrainPosition(trainId);
    }

    @GetMapping("/trains")
    public List<Train> getRealtimeTrainPositionList(@RequestParam("line") String lineName) {
        return subwayService.getRealtimeTrainPositionList(lineName);
    }

    @PatchMapping("/trains")
    public void refreshRealtimeTrainPosition(@RequestParam("line") String lineName) {
        subwayService.refreshRealtimeTrainPositions(lineName);
    }


}
