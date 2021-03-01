package com.accenture.assessment.models.report;

import java.util.List;

public class TopAirports {

  private final List<TopAirport> topAirports;

  public TopAirports(List<TopAirport> topAirports) {
    this.topAirports = topAirports;

  }

  public List<TopAirport> getTopAirports() {
    return topAirports;
  }


}
