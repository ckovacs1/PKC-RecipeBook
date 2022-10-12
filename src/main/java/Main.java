
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        // original arraylist of all recipe objects
        Scanner scan = new Scanner(System.in);

        // GSON object to create JSON file
        Gson gson = new Gson();

        // create file to store recipes
        File db = new File("database.json");

        // convert arraylist of recipes to jsonarray
//        writeToFile(jsonArr.toString(), db);

        // parse database file
        String json = FileUtils.readFileToString(new File("database.json"), StandardCharsets.UTF_8);
        ;
        // convert parsed JSON string into arraylist of recipes
        ArrayList<Recipe> recipeList = new Gson().fromJson(json, new TypeToken<ArrayList<Recipe>>() {
        }.getType());

        // flag for quitting program
        boolean flag = false;

        // boolean flag to see if user is in search mode
        boolean searching = false;

        // backup recipebook to store original recipebook
        ArrayList<Recipe> backupRecipeList = new ArrayList<Recipe>();
        System.out.println("\nHello!\n");
        while (!flag) {
            if (!searching) {
                System.out.println("Enter 1 to view all recipes \nEnter 2 to add a recipe \n" +
                        "Enter 3 to search through recipe names \n" +
                        "Enter 4 to remove a recipe \n" +
                        "Enter 5 to edit a recipe \n" +
                        "Enter 0 to quit the program");
            }
            // else, user is search mode
            else {
                System.out.println("Press 1 to show all matching search results");
            }

            // TODO: INPUT VALIDATION: make sure user inputs number from 0-5


            int input = scan.nextInt();
            scan.nextLine();
            // switch cases for user input
            switch (input) {
                case 0:
                    flag = true;
                    break;

                // user is in view all recipes mode
                case 1:
                    System.out.println("Viewing all recipes: ");

                    for (int i = 0; i < recipeList.size(); i++) {
                        System.out.println(i + 1 + ": " + recipeList.get(i).getName());
                    }
                    System.out.println("---------------------------------------------------- \n");

                    // now let user pick a recipe
                    System.out.println("Enter recipe number to view");
                    int pickRecipe = scan.nextInt();
                    scan.nextLine();

                    // display recipe that is picked
                    Recipe cur = recipeList.get(pickRecipe - 1);
                    System.out.println("Name: " + cur.getName());
                    System.out.println("Description: " + cur.getDescription());
                    System.out.printf("Ingredients: ");
                    for (int i = 0; i < cur.getIng().size() - 1; i++) {
                        System.out.printf(cur.getIng().get(i) + ", ");
                    }
                    System.out.println(cur.getIng().get(cur.getIng().size() - 1));

                    // let user pick choice of how to see instructions
                    System.out.println("Press 1 to see all the instructions");
                    System.out.println("Press 2 to see instructions one at a time \n \n");

                    int choice = scan.nextInt();
                    scan.nextLine();
                    int instructionLen = cur.getIns().size();

                    if (choice == 1) {
                        System.out.println("All instructions: ");
                        for (int i = 0; i < instructionLen - 1; i++) {
                            System.out.println(i + 1 + ": " + cur.getIns().get(i));
                        }
                        System.out.println(instructionLen + " " + cur.getIns().get(instructionLen - 1));
                    } else {
                        System.out.println("Keep pressing 2 to see the next instruction");
                        for (int i = 0; i < instructionLen; i++) {
                            choice = scan.nextInt();
                            scan.nextLine();
                            System.out.println(i + 1 + ": " + cur.getIns().get(i));
                        }
                    }
                    System.out.println("That's it! Well done.");
                    System.out.println("--------------------------------------------- \n \n \n \n");

                    // user is done searching, switch back to viewing all recipes
                    if (searching) {
                        searching = !searching;
                        recipeList = backupRecipeList;
                        backupRecipeList = new ArrayList<Recipe>();
                    }

                    break;

                // add a recipe functionality
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
                    // parse through user string input as an array
                    String[] ingArr = ing.split("[,]+");
                    String[] insArr = ins.split("[,]+");

                    //convert array to arraylist
                    ArrayList<String> ingAL = new ArrayList<>(Arrays.asList(ingArr));
                    ArrayList<String> insAL = new ArrayList<>(Arrays.asList(insArr));
                    Recipe newRecipe = new Recipe(name, des, ingAL, insAL);

                    // add new recipe to recipebook
                    recipeList.add(newRecipe);

                    // update database
                    JsonArray jsonArr = new Gson().toJsonTree(recipeList).getAsJsonArray();
                    writeToFile(jsonArr.toString(), db);
                    break;

                // user is in search mode
                case 3:
                    System.out.println("Enter search phrase: ");
                    String search = scan.nextLine();

                    // new arraylist containing all recipes name that match search
                    ArrayList<Recipe> arrMatching = new ArrayList<>();

                    // loop through all recipes to find matching string names
                    for (Recipe rec : recipeList) {
                        if (rec.getName().equals(search)) {
                            arrMatching.add(rec);
                        }
                    }
                    System.out.println("Searching...");

                    if (arrMatching.size() > 0) {
                        // show only the filtered list of recipes
                        backupRecipeList = recipeList;
                        recipeList = arrMatching;
                        searching = true;
                    }
                    else{
                        System.out.println("No results found. \n");
                    }
                    break;
                    // user is in delete mode
                case 4:
                    System.out.println("Deletion mode: \n");
                    for (int i = 0; i < recipeList.size(); i++) {
                        System.out.println(i + 1 + ": " + recipeList.get(i).getName());
                    }
                    System.out.println("Enter recipe number you wish to delete: ");
                    int toDelete = scan.nextInt();
                    scan.nextLine();

                    // delete recipe from arraylist
                    cur = recipeList.get(toDelete - 1);
                    recipeList.remove(cur);

                    // update database
                    JsonArray arrToWrite = new Gson().toJsonTree(recipeList).getAsJsonArray();
                    writeToFile(arrToWrite.toString(), db);

                    // notify user
                    System.out.println("Removed recipe number: " + toDelete);
                    break;

                case 5:
                    System.out.println("Edit mode \n");
                    System.out.println("Enter recipe number you wish to edit: ");
                    for (int i = 0; i < recipeList.size(); i++) {
                        System.out.println(i + 1 + ": " + recipeList.get(i).getName());
                    }
                    int toEdit = scan.nextInt();
                    scan.nextLine();
                    cur = recipeList.get(toEdit - 1);

                    System.out.println("Editing: " + cur.getName());
                    System.out.println("Enter 1 to edit name. \nEnter 2 to edit description. " +
                            "\nEnter 3 to edit ingredients list. \nEnter 4 to edit instructions list.");
                    int editChoice = scan.nextInt();
                    scan.nextLine();

                    switch (editChoice) {
                        case 1:
                            // change name
                            System.out.println("Enter new name: ");
                            String newName = scan.nextLine();
                            cur.setName(newName);
                            System.out.println("Name of recipe has been changed to: " + cur.getName());
                            break;

                        case 2:
                            // change des
                            System.out.println("Old description: ");
                            System.out.println(cur.getDescription());
                            System.out.println("Enter new description: ");
                            String newDes = scan.nextLine();
                            cur.setDescription(newDes);
                            System.out.println("Description has been changed to: " + cur.getDescription());
                            break;

                        case 3:
                            System.out.println("Enter new ingredients list, separated by commas");
                            String newIng = scan.nextLine();

                            // parse through user string input as an array
                            String[] newIngArr = newIng.split("[,]+");

                            //convert array to arraylist
                            ArrayList<String> newIngAL = new ArrayList<>(Arrays.asList(newIngArr));

                            // set new ingredients
                            cur.setIng(newIngAL);
                            System.out.println("Ingredients list has been changed.");
                            break;


                        case 4:
                            System.out.println("Enter new instructions list, separated by commas");
                            String newIns = scan.nextLine();
                            // parse through user string input as an array
                            String[] newInsArr = newIns.split("[,]+");

                            //convert array to arraylist
                            ArrayList<String> newInsAL = new ArrayList<>(Arrays.asList(newInsArr));

                            // set new ingredients
                            cur.setIng(newInsAL);
                            System.out.println("Instructions list has been changed");
                            break;

                    }
                    System.out.println("\n");
                    // update database
                    JsonArray updatedArr = new Gson().toJsonTree(recipeList).getAsJsonArray();
                    writeToFile(updatedArr.toString(), db);

            }
        }
    }

    // function to write to file
    public static void writeToFile(String s, File output)
            throws IOException {
        String out = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        writer.write(s);
        writer.close();
    }


}


