package kr.co.e8ight.subwayalimi.controller;

import kr.co.e8ight.subwayalimi.service.SubwayService;
import kr.co.e8ight.subwayalimi.domain.Station;
import kr.co.e8ight.subwayalimi.domain.Train;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubwayController {
    private final SubwayService subwayService;

    @GetMapping("/stations")
    public List<Station> getSubwayStationReceived(@RequestAttribute("line") String lineName) {
        return subwayService.getStationPositionInfo(lineName);
    }

    @GetMapping("/trains/{trainId}")
    public Train getRealtimeTrainPosition(@PathVariable Long trainId) {
        return subwayService.getRealtimeTrainPosition(trainId);
    }

    @GetMapping("/trains")
    public List<Train> getRealtimeTrainPositionList(@RequestAttribute("line") String lineName) {
        return subwayService.getRealtimeTrainPositionList(lineName);
    }

    @PatchMapping("/trains")
    public void refreshRealtimeTrainPosition(@RequestAttribute("line") String lineName) {
        subwayService.refreshRealtimeTrainPositions(lineName);
    }


}
