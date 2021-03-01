package com.accenture.assessment.controllers;

import com.accenture.assessment.models.report.TopAirports;
import com.accenture.assessment.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CountryController {

  @Autowired
  private CountryService countryService;

  @GetMapping("/country")
  public String country(@RequestParam(name="name", required=false, defaultValue=" ") String name, Model model) {
    CountryService.SearchCountry country = countryService.searchCountry(name);
    if (country.getCountry().isPresent()) {
        model.addAttribute("country", country.getCountry().get());
    } else {
        model.addAttribute("suggestions", country.getSuggestions());
    }

    return "query_page";
  }

  @GetMapping("/report")
  public String report(Model model) {
    TopAirports topAirports = countryService.reportTopAirports();
    model.addAttribute("reportTopAirports", topAirports);
    return "report_page";
  }
}
