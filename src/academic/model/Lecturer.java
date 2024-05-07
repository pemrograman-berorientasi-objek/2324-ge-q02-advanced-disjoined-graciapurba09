package academic.model;

/**
 * @author 12S22040-Gracia Purba
 * @author 12S22020-Beatrice Siahaan
 */
 public class Lecturer {
   private String id;
   private String name;
   private String initial;
   private String email;
   private String studyProgram;

   public Lecturer() {
      this.id = "";
      this.name = "";
      this.initial = "";
      this.email = "";
      this.studyProgram = "";
   }

   public Lecturer(String var1, String var2, String var3, String var4, String var5) {
      this.id = var1;
      this.name = var2;
      this.initial = var3;
      this.email = var4;
      this.studyProgram = var5;
   }

   public String getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }
   
   public String getInitial() {
      return this.initial;
   }

   public String getEmail() {
      return this.email;
   }

   public String getStudyProgram() {
      return this.studyProgram;  
   }

   public String toString() {
      return this.id + "|" + this.name + "|" + this.initial + "|" + this.email + "|" + this.studyProgram;
   }
   
}

   
   
