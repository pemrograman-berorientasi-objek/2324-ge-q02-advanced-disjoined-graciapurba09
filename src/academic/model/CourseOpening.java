package academic.model;

/**
 * @author 12S22040-Gracia Purba
 * @author 12S22020-Beatrice Siahaan
*/

public class CourseOpening {
   private String courseCode;
   private String academicYear;
   private String semester;
   private String lecturerList;
   private String initials;
   

   // Constructor
   public CourseOpening(String courseCode, String academicYear, String semester, String lecturerList) {
       this.courseCode = courseCode;
       this.academicYear = academicYear;
       this.semester = semester;
       this.lecturerList = lecturerList;
       this.initials = "";
   }
   //getter
   public String getCourseCode() {
       return courseCode;
   }
   public String getAcademicYear() {
       return academicYear;
   }
   public String getSemester() {
       return semester;
   }
   public String getLecturerList() {
       return lecturerList;
   }
   public String getInitials() {
       return initials;
   }
   //setter
   public void setCourseCode(String courseCode) {
       this.courseCode = courseCode;
   }
   public void setAcademicYear(String academicYear) {
       this.academicYear = academicYear;
   }
   public void setSemester(String semester) {
       this.semester = semester;
   }
   public void setLecturerList(String lecturerList) {
       this.lecturerList = lecturerList;
   }
   public void setInitials(String initials) {
       this.initials = initials;
   }
   
   @Override
   public String toString() {
       return courseCode + "|" + academicYear + "|" + semester + "|" + lecturerList + "|" + initials;
   }
}
   