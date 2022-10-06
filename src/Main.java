import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Scanner scan = new Scanner(System.in);

        // add 3 test cases
        ArrayList<String> bananabreadIng = new ArrayList<>(Arrays.asList("banana", "bread"));
        ArrayList<String> bananabreadIns = new ArrayList<>(Arrays.asList("chop", "bake"));
        Recipe bananaBread = new Recipe("banana bread", "magic between banana and bread",
                bananabreadIng, bananabreadIns);
        recipeList.add(bananaBread);

        ArrayList<String> pbjIng = new ArrayList<>(Arrays.asList("peanut butter", "jelly", "bread"));
        ArrayList<String> pbjIns = new ArrayList<>(Arrays.asList("toast", "spread"));
        Recipe pbj = new Recipe("peanut butter & jelly sandwich", "magic between pb and jelly",
                pbjIng, pbjIns);
        recipeList.add(pbj);

        ArrayList<String> cwIng = new ArrayList<>(Arrays.asList("chickens", "Trader Joe's marinate"));
        ArrayList<String> cwIns = new ArrayList<>(Arrays.asList("marinate", "bake"));
        Recipe cw = new Recipe("chicken wings", "Trader Joe's chicken wings",
                cwIng, cwIns);
        recipeList.add(cw);

        boolean flag = false;
        boolean searching = false;
        ArrayList<Recipe> backupRecipeList = new ArrayList<>();

        while (!flag) {

            if (!searching){
                System.out.println("Hello! \n Enter 1 to view all recipes \n Enter 2 to add a recipe \n" +
                        "Enter 3 to search through recipe names \n" + " Enter 0 to quit the program" );
                System.out.println(recipeList + " RL");
            }
            else{
                System.out.println("Showing all recipes with matching names:");
                System.out.println("Press 1 to continue:");
            }

            int input = scan.nextInt();
            scan.nextLine();
            // switch cases
            switch(input){
                case 0:
                    flag = true;
                    break;
                case 1:
                    System.out.println("Viewing all recipes: ");

                    for (int i = 0; i < recipeList.size(); i++){
                        System.out.println(i+1 + ": " + recipeList.get(i).getName());
                    }
                    System.out.println("---------------------------------------------------- \n");

                    // now let user pick a recipe
                    System.out.println("Enter recipe number to view");
                    int pickRecipe = scan.nextInt();
                    scan.nextLine();

                    // display recipe that is picked
                    Recipe cur = recipeList.get(pickRecipe-1);
                    System.out.println("Name: " + cur.getName());
                    System.out.println("Description: " + cur.getDescription());
                    System.out.printf("Ingredients: ");
                    for (int i = 0; i < cur.getIng().size() - 1; i++){
                        System.out.printf(cur.getIng().get(i) + ", ");
                    }
                    System.out.println(cur.getIng().get(cur.getIng().size()-1));

                    System.out.println("Press 1 to see all the instructions");
                    System.out.println("Press 2 to see instructions one at a time \n \n");

                    int choice = scan.nextInt();
                    scan.nextLine();
                    int instructionLen = cur.getIns().size();

                    if (choice == 1){
                        System.out.println("All instructions: ");
                        for (int i = 0; i < instructionLen - 1; i++){
                            System.out.println(i + 1 + ": " + cur.getIns().get(i));
                        }
                        System.out.println(instructionLen + " " + cur.getIns().get(instructionLen - 1));
                    }
                    else{
                        System.out.println("Press 2 to see the next instruction");
                        for (int i = 0; i < instructionLen; i++){
                            choice = scan.nextInt();
                            scan.nextLine();
                            System.out.println(i + 1 + ": " + cur.getIns().get(i));
                        }
                    }
                    System.out.println("That's it! Well done.");
                    System.out.println("--------------------------------------------- \n \n \n \n");

                    if (searching){
                        searching = !searching;
                        recipeList = backupRecipeList;
                        backupRecipeList = new ArrayList<>();
                    }

                    break;
                case 2:
                    System.out.println("Enter recipe name: ");
                    String name = scan.nextLine();
                    System.out.println("You entered: " + name);
                    System.out.println("Enter recipe description: ");
                    String des = scan.nextLine();
                    System.out.println("You entered: " + des);

                    System.out.println("Enter recipe ingredients, separated by commas");
                    String ing = scan.nextLine();
                    System.out.println("Enter recipe instructions, separated by commas");

                    String ins = scan.nextLine();
                    String[] ingArr = ing.split("[,]+");
                    String[] insArr = ins.split("[,]+");

                    //convert list to arraylist
                    ArrayList<String> ingAL = new ArrayList<>(Arrays.asList(ingArr));
                    ArrayList<String> insAL = new ArrayList<>(Arrays.asList(insArr));
                    Recipe newRecipe = new Recipe(name, des, ingAL, insAL);
                    recipeList.add(newRecipe);
                    break;

                case 3:
                    boolean found = false;
                    System.out.println("Enter search phrase: ");
                    String search = scan.nextLine();

                    ArrayList<Recipe> arrMatching = new ArrayList<>();

                    for (Recipe rec : recipeList){
                        if (rec.getName().equals(search)){
                            System.out.println("match");
                            arrMatching.add(rec);
                        }
                    }
                    System.out.println("Searching...");

                    // change which array is shown to user
                    backupRecipeList = recipeList;
                    recipeList = arrMatching;
                    searching = true;
            }

        }
    }
}


