package com.accenture.assessment.services;

import com.accenture.assessment.models.Country;
import com.accenture.assessment.models.report.TopAirport;
import com.accenture.assessment.models.report.TopAirports;
import com.accenture.assessment.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

  public static final int REPORT_LIMIT = 10;

  @Autowired
  private CountryRepository countryRepository;

  public SearchCountry searchCountry(String name) {
    SearchCountry result;

    Optional<Country> countryOpt = countryRepository.findByName(name);

    if (countryOpt.isPresent()) {
      result = new SearchCountry(countryOpt);
    } else {
      List<String> suggestions = countryRepository.findByFuzzyName(name);
      result = new SearchCountry(suggestions);
    }

    return result;
  }

  public TopAirports reportTopAirports() {
    List<TopAirport> topAirports = countryRepository.queryTopAirports(REPORT_LIMIT);

    return new TopAirports(topAirports);
  }

  public class SearchCountry {
    private final Optional<Country> country;
    private final List<String> suggestions;

    public SearchCountry(Optional<Country> country) {
      this.country = country;
      this.suggestions = Collections.emptyList();
    }

    public SearchCountry(List<String> suggestions) {
      this.suggestions = suggestions;
      this.country = Optional.empty();
    }

    public Optional<Country> getCountry() {
      return country;
    }

    public List<String> getSuggestions() {
      return suggestions;
    }
  }
}
