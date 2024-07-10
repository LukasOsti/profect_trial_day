package profect.springbackend;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StationController {

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "Hello World!";
    }

    @GetMapping("/stations")
    @ResponseBody
    public List<String> sortedStations(){
        return StationHandler.getStationsFromApi();
    }


    @GetMapping("/lines/station")
    @ResponseBody
    public List<String> sortedBusLines(@RequestParam(required = false) String station){
        return StationHandler.getBusLinesFromApi(station);
    }


    @PostMapping(path = "/times", consumes = "application/json")
    @ResponseBody
    public List<String> timings(@RequestBody RequestStationAndBusBody requestStationAndBusBody){
        return StationHandler.getTimingsFromApi(requestStationAndBusBody.getStation(), requestStationAndBusBody.getBusLine());
    }
}
