package academic.model;

/**
 * @author 12S22040-Gracia Purba
 * @author 12S22020-Beatrice Siahaan
 */
public class Student {
    private String id;
    private String name;
    private String year;
    private String studyProgram;

    // constructor
    public Student(String id, String name, String year, String studyProgram) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
    }
    
    //getter
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getStudyProgram() {
        return studyProgram;
    }
    
    @Override
    public String toString() {
        return getId() + "|" + getName() + "|" + getYear() + "|" + getStudyProgram();
    }

}