import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {CommonModule} from '@angular/common';
import { StationServiceService } from './station-service.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <section>
      <select #stations (change)="onSelectedStation(stations.value)">
      @for (item of stationList; track item) {
        <option >{{item}}</option>
}
		
	</select>
  <section>
      <select #busLines (change)="onSelectedBusLine(busLines.value)">
      @for (item of busLineList; track item) {
        <option >{{item}}</option>
}
		
	</select>
    </section>
    <table>
    <tr>
    <th>Haltestelle: {{station }}  Buslinie {{busLine}} </th>
  </tr>
  @for (item of timing; track item) {
        <tr ><td>{{item}}</td></tr>
}
    </table>
  `,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ang-frontend';
  stationList: string[] = [];
  stationService: StationServiceService = inject(StationServiceService);
  busLineList: string[] = [];
  timing: string[] = [];
  station: string = "";
  busLine: string = "";
  constructor() {
    this.stationService.findStations().then((stationList: string[]) => {
      this.stationList = stationList;
      this.station = this.stationList[0];
      this.stationService.findBusLines(this.station).then((busLineList: string[]) => {
        this.busLineList = busLineList;
        this.busLine = this.busLineList[0];
        this.stationService.findTimings(this.station, this.busLine).then((timing: string[]) => {
          this.timing = timing;
        });
      });

    });
  }

  getBusLines(station: string) {
    this.stationService.findBusLines(station).then((busLineList: string[]) => {
      this.busLineList = busLineList;
      this.busLine = this.busLineList[0];
        this.stationService.findTimings(this.station, this.busLine).then((timing: string[]) => {
          this.timing = timing;
        });
    });
  }

  getTimings(station: string, busLine: string) {
    this.stationService.findTimings(station, busLine).then((timing: string[]) => {
      this.timing = timing;
    });
  }

  onSelectedStation(value:string): void {
    this.station = value;
		this.getBusLines(value);
	}

  onSelectedBusLine(value:string): void{
    this.busLine = value;
    this.getTimings(this.station, this.busLine);
  }

}
