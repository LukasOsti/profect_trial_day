import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StationServiceService {

  private stationUrl: string = 'http://localhost:8080';

  public async findStations(): Promise<string[]> {
    const data = await fetch(this.stationUrl + "/stations");
    return (await data.json()) ?? [];
  }

  public async findBusLines(station: string): Promise<string[]> {
    const data = await fetch(this.stationUrl + "/lines/station?station=" + station);
    return (await data.json()) ?? [];
  }

  public async findTimings(station: string, busLine: string): Promise<string[]> {
    const data = await fetch(this.stationUrl + "/times", {
      method: "POST",
      body: JSON.stringify({station: station, busLine: busLine}),
      headers: {
        "Content-Type": "application/json",
      },
    });
    return (await data.json()) ?? [];
  }
}
