import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Comparator<Person> comparator = ((o1, o2) -> o1.getFamily().toUpperCase().compareTo(o2.getFamily().toUpperCase()));

        long underage = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        String conscripts = persons.stream()
                .filter(a -> a.getAge() >= 18 && a.getAge() <= 27)
                .filter(s -> s.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList()).toString();

        List<Person> educated = persons.stream()
                .filter(e -> e.getEducation() == Education.HIGHER)
                .filter(s -> (s.getSex() == Sex.MAN && (s.getAge() >= 18 && s.getAge() <= 65))
                        || (s.getSex() == Sex.WOMAN && (s.getAge() >= 18 && s.getAge() <= 60)))
                .sorted(comparator)
                .collect(Collectors.toList());

        System.out.println("Число совершеннолетних: " + underage);
        System.out.println(conscripts);
        System.out.println(educated);

    }
}