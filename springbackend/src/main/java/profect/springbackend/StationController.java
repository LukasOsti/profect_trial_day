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
    public ResponseList sortedStations(){
        return new ResponseList(StationHandler.getStationsFromApi());
    }


    @GetMapping("/lines/station")
    @ResponseBody
    public ResponseList sortedBusLines(@RequestParam(required = false) String station){
        return new ResponseList(new ArrayList<>());
    }


    @PostMapping(path = "/times", consumes = "application/json")
    @ResponseBody
    public ResponseList timings(@RequestBody RequestStationAndBusBody requestStationAndBusBody){
        System.out.println(requestStationAndBusBody);

        return new ResponseList(new ArrayList<>());
    }
}
