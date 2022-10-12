

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Recipe implements Serializable {

    String name;
    String description;
    ArrayList<String> ins = new ArrayList<>();
    ArrayList<String> ing = new ArrayList<>();

    boolean toRemove;

    public Recipe(String name, String description,
                  ArrayList<String> ing, ArrayList<String> ins) {

        this.name = name;
        this.description = description;
        this.ins = ins;
        this.ing = ing;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String des){this.description = des;}

    public String getName() {
        return this.name;
    }
    public void setName(String name){this.name = name;}


    public ArrayList<String> getIns() {
        return this.ins;
    }
    public void setIns(ArrayList<String> ins){this.ins = ins;}

    public ArrayList<String> getIng() {
        return this.ing;
    }
    public void setIng(ArrayList<String> ing){this.ing = ing;}





}
