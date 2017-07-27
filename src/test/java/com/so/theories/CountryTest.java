package com.so.theories;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.assertj.core.api.Condition;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sergiu.oltean on 7/27/2017.
 */
@RunWith(Theories.class)
public class CountryTest {

	private static final String GERMAN_USE_CASE = "german";
	private static final String FRANCE_USE_CASE = "france";

	@DataPoint(GERMAN_USE_CASE)
	public static List<Country> countryBatch1 = Arrays.asList(new Country("DE"), new Country("FR"), new Country("AT"), new Country("BE"));

	@DataPoint(GERMAN_USE_CASE)
	public static List<Country> countryBatch2 = Arrays.asList(new Country("DE"), new Country("EN"), new Country("IT"), new Country("SK"));

	@DataPoint(GERMAN_USE_CASE)
	public static List<Country> countryBatch3 = Arrays.asList(new Country("HU"), new Country("ES"), new Country("LUX"), new Country("AT"));

	private List<String> germanSpeakingCountries = Arrays.asList("DE", "AT", "CH");
	private List<String> frenchSpeakingCountries = Arrays.asList("FR", "BE");

	@Theory
	public void testGermanIsSpokenInAtLeastOneCountry(@FromDataPoints(GERMAN_USE_CASE) List<Country> countries) {
		assertThat(countries).areAtLeastOne(buildGermanCondition(countries));
	}

	private Condition buildGermanCondition(List<Country> countries) {
		Predicate<Country> predicate = country -> germanSpeakingCountries.contains(country.getLanguage());
		return new Condition(predicate, "German speaking countries", countries);
	}

	@DataPoint(FRANCE_USE_CASE)
	public static List<Country> countryBatch5 = Arrays.asList(new Country("DE"), new Country("FR"), new Country("AT"), new Country("BE"));

	@DataPoint(FRANCE_USE_CASE)
	public static List<Country> countryBatch6 = Arrays.asList(new Country("BE"), new Country("EN"), new Country("IT"), new Country("SK"));

	@Theory
	public void testFrenchIsSpokenInAtLeastOneCountry(@FromDataPoints(FRANCE_USE_CASE) List<Country> countries) {
		assertThat(countries).areAtLeastOne(buildFrenchCondition(countries));
	}

	private Condition buildFrenchCondition(List<Country> countries) {
		Predicate<Country> predicate = country -> frenchSpeakingCountries.contains(country.getLanguage());
		return new Condition(predicate, "French speaking countries", countries);
	}

}
