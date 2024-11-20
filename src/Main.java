import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Dish>menu= Arrays.asList(
                new Dish("pork",false,800, Dish.Type.MEAT),
                new Dish("Beef",false,700, Dish.Type.MEAT),
               new Dish("vp",true,80, Dish.Type.OTHER),
                new Dish("Chicken",false,120, Dish.Type.MEAT)
        );

        List<String>VegetarianMenu=menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName) //Map function helps us select a particular column
                .collect(Collectors.toUnmodifiableList());
        System.out.println(VegetarianMenu);

        List<String>ThreeHighCaloriesDishName=menu.stream()
                        .filter(d->(d.isVegetarian()==false && d.getCalories()>=100))
                .map(Dish::getName) //We use map, to parameterize getName method and extract name of dish
                .limit(2)
                .collect(Collectors.toUnmodifiableList());

        // filter, map and limit can be connected together to form a pipeline
        //Collect causes the pipeline to be executed and closes it

        System.out.println(ThreeHighCaloriesDishName);

        List<String>title=Arrays.asList("Hello","How","Are","You");
        List<Integer>wordLength=title.stream()
                .map(n->n.length()) // Map here creates a new function, which takes an element and returns it's length
                .collect(Collectors.toUnmodifiableList());
        System.out.println(wordLength);
        Stream<String>s=title.stream();
//        System.out.println(s.count());
        s.forEach(System.out::println);
try {
    s.forEach(System.out::println); //Gives java.lang.IllegalStateException: stream has already been operated upon or closed
}
catch (IllegalStateException e){
    System.out.print(e);
}

        //A stream can only be consumed once in java
        //Intermediate operations such as filter or sorted returns another stream as return type. Multiple intermediate operations form a query.
        //Intermediate operations are lazy as they do not provide any processing until a terminal operation is invoked. Intermediate operations are lazy
//Streams let us move forward from external iteration to internal iteration.
        //Benefit of internal iteration is that we can run our code in parallel
    List<Integer> numbers = Arrays.asList(88,56,2,4,67,75,88,94,99,78);
    List<Integer> numberStream = numbers.stream()
            .filter(n -> (n % 2 == 0))
            .limit(5)
            .map(n->n+100) //Streams support map, which takes function as an argument and that function is applied to each element and maps the current element to a new element
            .sorted()
            .distinct()
            .skip(1)// Removes the first element from the stream
            .collect(Collectors.toUnmodifiableList());
//Mapping creates a new version, rather than modifying
    numberStream.forEach(System.out::println);

        List<Integer> nums = Arrays.asList(2,4,6,8);
    List<Integer>SquaredNums= nums.stream()
            .map(n->n*n)
            .collect(Collectors.toUnmodifiableList());
        System.out.println(SquaredNums);


        Optional<Dish> d=menu.stream()
                .filter(Dish::isVegetarian)
                .findAny(); //Here, it is possible that no value would have been found and then d would have been null, which is error prone.
        //To avoid, the null error we use optional as optional can be null
        System.out.println(d.get());

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(x-> System.out.println(x.getName()));

        //Finding First element in a stream that satisfies a particular condition
       Optional<Integer>first= nums.stream()
                .filter(n->n%3==0) //Finding first number in an array divisible by 3
                .findFirst();
        System.out.println(first.get());

        //Summing up all the elements of an array using stream
        int x= nums.stream()
                .reduce(0,(a,b)->a+b); //The lambda combines each element repeatedly until the stream is reduced to a single value
        System.out.println(x);

        //Another overloaded variant of reduce that doesn't take an initial value, but return an optional object
        Optional<Integer>SumWithOutInitialiser=nums.stream()
                .reduce((a,b)->a+b);
        System.out.println(SumWithOutInitialiser.get());


    }
}