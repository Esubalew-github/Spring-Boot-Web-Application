package com.accenture.assessment.repositories;

import com.accenture.assessment.models.report.TopAirport;
import com.accenture.assessment.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

  Optional<Country> findByName(String name);

  @Query(
        value =  "SELECT name FROM countries c WHERE LOWER(c.name) LIKE LOWER(concat(?1,'%')) LIMIT 10;",
      nativeQuery = true)
  List<String> findByFuzzyName(String name);

  @Query(
      value = "SELECT c.name as name, count(*) as count " +
              "FROM countries c INNER JOIN airports a ON c.code = a.iso_country " +
              "GROUP BY c.name ORDER BY count DESC LIMIT ?1",
      nativeQuery = true)
  List<TopAirport> queryTopAirports(int limit);


}
