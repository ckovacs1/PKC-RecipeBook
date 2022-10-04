class Main {
    public static void main(String[] args) {

        String[] ing = new String[]{"egg", "water", "flour"};
        String[] ins = new String[]{"crack egg", "eat it"};

        Recipe pie = createRecipe("Pie", "American Dessert", ing, ins);
        System.out.println(pie.getName());
    }

    public static Recipe createRecipe(String name, String desc, String[] ingredients, String[] instructions){
        Recipe newRecipe = new Recipe(name, desc, ingredients, instructions);
        return newRecipe;
    }
}