
public interface MealPlan {
    String getPlanName();
    String[] getSampleMeals();
}


class VegetarianMeal implements MealPlan {
    @Override
    public String getPlanName() {
        return "Vegetarian";
    }

    @Override
    public String[] getSampleMeals() {
        return new String[]{"Vegetable Stir Fry", "Lentil Soup", "Falafel Wrap"};
    }
}

class VeganMeal implements MealPlan {
    @Override
    public String getPlanName() {
        return "Vegan";
    }

    @Override
    public String[] getSampleMeals() {
        return new String[]{"Tofu Scramble", "Chickpea Curry", "Avocado Toast"};
    }
}

class Meal<T extends MealPlan> {
    private T mealPlan;
    private String[] customMeals;

    public Meal(T mealPlan) {
        this.mealPlan = mealPlan;
        this.customMeals = mealPlan.getSampleMeals();
    }

    public void addCustomMeal(String meal) {
        String[] newMeals = new String[customMeals.length + 1];
        System.arraycopy(customMeals, 0, newMeals, 0, customMeals.length);
        newMeals[customMeals.length] = meal;
        customMeals = newMeals;
    }

    public void displayMealPlan() {
        System.out.println("Meal Plan: " + mealPlan.getPlanName());
        System.out.println("Suggested Meals:");
        for (String meal : customMeals) {
            System.out.println("- " + meal);
        }
    }

    public static <T extends MealPlan> Meal<T> generateMealPlan(T mealPlan) {
        return new Meal<>(mealPlan);
    }
}


class Main3 {
    public static void main(String[] args) {
        Meal<VegetarianMeal> vegetarianMeal = Meal.generateMealPlan(new VegetarianMeal());
        vegetarianMeal.addCustomMeal("Mushroom Risotto");
        vegetarianMeal.displayMealPlan();

        Meal<VeganMeal> veganMeal = Meal.generateMealPlan(new VeganMeal());
        veganMeal.addCustomMeal("Quinoa Salad");
        veganMeal.displayMealPlan();
    }
}