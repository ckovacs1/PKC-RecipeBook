import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        String[] ing = new String[]{"egg", "water", "flour"};
        String[] ins = new String[]{"crack egg", "eat it"};
        String[] ing2 = new String[]{"wheat", "flour"};
        String[] ins2 = new String[]{"bake it", "eat it"};


        ArrayList<Recipe> r = new ArrayList<Recipe>();


        Recipe pie = createRecipe("Pie", "American Dessert", ing, ins);
        Recipe bread = createRecipe("Bread", "bread", ing2, ins2);


        r.add(pie);
        r.add(bread);

        /*
        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i).getName());
        }
         */

        /*
        try {
            FileOutputStream fileOut = new FileOutputStream("temp.json");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(pie);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in temp.json");
        } catch (IOException i) {
            i.printStackTrace();
        }
         */

        Scanner input = new Scanner(System.in);
        int inp;
        do {
            System.out.println("Enter a number to make selection(0 for Exit): ");
            System.out.println("1. Add New Recipe");
            System.out.println("2. Read Existing Recipe");
            System.out.println("3. Update Existing");
            System.out.println("4. Delete Existing Recipe");
            inp = input.nextInt();

            switch (inp) {
                case 1:
                    System.out.println("Add");
                    Recipe newRecipe = createRecipe("Spaghetti", "Italian Dinner", ing, ins);
                    r.add(newRecipe);
                    break;
                case 2:
                    System.out.println("Recipes: ");
                    for (int i = 0; i < r.size(); i++) {
                        System.out.println(r.get(i).getName());
                    }
                    break;
                case 3:
                    System.out.println("Update");
                    for( int i=0; i < r.size(); i++){
                        if(r.get(i).getName()=="Bread"){
                            r.get(i).setName("Pita Bread");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Delete");
                    for( int i=0; i < r.size(); i++){
                        if(r.get(i).getName()=="Spaghetti"){
                            r.remove(i);
                        }
                    }
                    break;
            }
        } while (inp != 0);

        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i).getName());
        }
    }


    public static Recipe createRecipe(String name, String desc, String[] ingredients, String[] instructions){
        Recipe newRecipe = new Recipe(name, desc, ingredients, instructions);
        return newRecipe;
    }

}