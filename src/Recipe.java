import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Recipe {

    String name;
    String description;
    ArrayList<String> ins = new ArrayList<>();
    ArrayList<String> ing = new ArrayList<>();

    public Recipe (String name, String description,
                   ArrayList<String> ing, ArrayList<String> ins){

        this.name = name;
        this.description = description;
        this.ins = ins;
        this.ing = ing;
    }

    public String getDescription(){
        return this.description;
    }
    public String getName(){
        return this.name;
    }

    public ArrayList<String> getIns() {
        return this.ins;
    }

    public ArrayList<String> getIng() {
        return this.ing;
    }

}
