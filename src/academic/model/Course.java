package academic.model;
import java.util.*;

/**
 * @author 12S22040-Gracia Purba
 * @author 12S22020-Beatrice Siahaan
 */

public class Course {
    private String code;
    private String name;
    private int credits;
    private String level;

    public Course(String code, String name, int credits, String level) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits; 
    }

    public String getLevel() {
        return level;
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append(code).append("|").append(name).append("|").append(credits).append("|").append(level);

        return result.toString();
    }
}
