package kr.co.e8ight.subwayalimi.subway;

import kr.co.e8ight.subwayalimi.subway.dto.RealtimeSubwayPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubwayController {

    private final SubwayDataLoader subwayDataLoader;

    @GetMapping("/subwayMaster")
    public SubwayStationReceived getSubwayStationReceived() {
        return subwayDataLoader.loadStationPositionInfo();
    }

    @PostMapping("/subwayMaster")
    public void saveSubwayStationReceived() {
        subwayDataLoader.saveStationPositionInfo();
    }

    @GetMapping("/subway")
    public RealtimeSubwayPosition getRealtimeSubwayPosition() {
        return subwayDataLoader.loadRealtimeTrainPositionInfo();
    }

    @PostMapping("/subway")
    public void saveRealtimeSubwayPosition() {
        subwayDataLoader.saveSubwayInfo();
    }


}
