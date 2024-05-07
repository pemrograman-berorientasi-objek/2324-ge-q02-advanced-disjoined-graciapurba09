package academic.model;

/**
 * @author 12S22040-Gracia Purba
 * @author 12S22020-Beatrice Siahaan
 */
public class Enrollment {
    private String courseCode;
    private String studentID;
    private String academicYear;
    private String semester;
    private String grade;
    private String previousGrade;
    
    // Constructor
    public Enrollment(String courseCode, String studentID, String academicYear, String semester, String grade) {
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = grade;
        this.previousGrade = "";
    }
    
    //getter
    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getAcademicYear() {
        return academicYear;
    }
    
    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }

    public String getPreviousGrade() {
        return previousGrade;
    }

    //setter
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPreviousGrade(String previousGrade) {
        this.previousGrade = previousGrade;
    }

    @Override
    public String toString() {
        if (previousGrade != null && !previousGrade.isEmpty()) {
            return courseCode + "|" + studentID + "|" + academicYear + "|" + semester + "|" + grade + "(" + previousGrade + ")";
        } else {
            return courseCode + "|" + studentID + "|" + academicYear + "|" + semester + "|" + grade;
        }
    }
}
