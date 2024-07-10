package profect.springbackend;

public class RequestStationAndBusBody {
    private String station;
    private String busLine;

    public RequestStationAndBusBody(String station, String busLine) {
        this.station = station;
        this.busLine = busLine;
    }


    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }
}
