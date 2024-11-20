import java.lang.reflect.Type;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
    public String getName() {
        return name;
    }
    public int getCalories() {
        return calories;
    }
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                '}';
    }
    public enum Type {MEAT, FISH, OTHER }
}
