import java.util.*;
import java.util.stream.*;
import java.time.*;
import java.time.temporal.*;

public class FunctionalJava {

	public static void main(String[] args) {
		final FunctionalJava fj = new FunctionalJava();
		System.out.println(fj.convertNumbersToStrings(Arrays.asList(1, 2, 3)));
		System.out.println(fj.convertNumbersToStringsFunctionally(Arrays.asList(1, 2, 3)));

		final Person person1 = new Person("Bob Smith", LocalDate.of(1994, 4, 12), "KS");
		final Person person2 = new Person("Sam Smith", LocalDate.of(2009, 2, 17), "KS");
		final Person person3 = new Person("Sally Sue", LocalDate.of(1983, 12, 1), "MO");
		final Person person4 = new Person("John Doe", LocalDate.of(1991, 10, 21), "MO");

		System.out.println(fj.getSortedLegalAdultFirstNamesByState(Arrays.asList(person1, person2, person3, person4)));
		System.out.println(fj.getSortedLegalAdultFirstNamesByStateFunctionally(Arrays.asList(person1, person2, person3, person4)));
	}

	/**
	 * Converts a list of numbers to a list of strings.
	 */
	public List<String> convertNumbersToStrings(List<Integer> numbers) {
	    List<String> strings = new ArrayList<>(numbers.size());
	    for (Integer number : numbers) {
	        strings.add(number.toString());
	    }
	    return strings;
	}

	/**
	 * Converts a list of numbers to a list of strings.
	 */
	public List<String> convertNumbersToStringsFunctionally(List<Integer> numbers) {
	    return numbers.stream()
	            .map(Object::toString)
	            .collect(Collectors.toList());
	}

	public Map<String, List<String>> getSortedLegalAdultFirstNamesByState(List<Person> allPeople) {
		Map<String, List<String>> results = new HashMap<>();
		for (Person person : allPeople) {
			long age = person.getBirthDate().until(LocalDate.now(), ChronoUnit.YEARS);
			if (age >= 18) {
				String state = person.getState();
				List<String> namesByState = results.get(state);
				if (namesByState == null) {
					namesByState = new ArrayList<>();
					results.put(state, namesByState);
				}
				namesByState.add(person.getName());
			}
		}
		for (List<String> firstNames : results.values()) {
			Collections.sort(firstNames);
		}
		return results;
	}

	public Map<String, List<String>> getSortedLegalAdultFirstNamesByStateFunctionally(List<Person> allPeople) {
		return allPeople.stream()
				.filter(p -> p.getBirthDate().until(LocalDate.now(), ChronoUnit.YEARS) >= 18)
				.sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
				.collect(Collectors.groupingBy(
						Person::getState,
						Collectors.mapping(Person::getName, Collectors.toList())
				));
	}

	public static class Person {

		private String name;
		private LocalDate birthDate;
		private String streetAddress;
		private String city;
		private String state;
		private String zipCode;

		// getters, setters, et. al. omitted...

		public Person(String name, LocalDate birthDate, String state) {
			this.name = name;
			this.birthDate = birthDate;
			this.state = state;
		}

		public String getName() { return name; }
		public LocalDate getBirthDate() { return birthDate; }
		public String getState() { return state; }
	}
}