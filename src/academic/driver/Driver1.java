package academic.driver;

import academic.model.*;
import java.util.*;

/**
 * @author 12S22040-Gracia Purba
 * @author 12S22020-Beatrice Siahaan
 */

public class Driver1 {

    public static void main(String[] args) {

        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<CourseOpening> CourseOpenings = new ArrayList<>();

        HashSet<String> studentIds = new HashSet<>();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("---")) {
            String[] tokens = input.split("#");
            String action = tokens[0];
            switch (action) {

                case "lecturer-add":
                    lecturers.add(new Lecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                    break;

                case "course-add":
                    courses.add(new Course(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]));
                    break;

                case "student-add":
                    Student newStudent = new Student(tokens[1], tokens[2], tokens[3], tokens[4]);
                    if (!isStudentDuplicate(studentIds, newStudent.getId())) {
                        students.add(newStudent);
                        studentIds.add(newStudent.getId());
                    }
                    break;

                case "enrollment-add":
                    enrollments.add(new Enrollment(tokens[1], tokens[2], tokens[3], tokens[4], "None"));
                    break;

                case "enrollment-grade":
                    String courseCodeGrade = tokens[1];
                    String studentIdGrade = tokens[2];
                    String academicYearGrade = tokens[3];
                    String semesterGrade = tokens[4];
                    String grade = tokens[5];

                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCourseCode().equals(courseCodeGrade) &&
                                enrollment.getStudentID().equals(studentIdGrade) &&
                                enrollment.getAcademicYear().equals(academicYearGrade) &&
                                enrollment.getSemester().equals(semesterGrade)) {
                            // Set the grade for the enrollment
                            enrollment.setGrade(grade);
                            break;
                        }

                    }

                    break;
                case "course-open":
                    String courseCodeOpen = tokens[1];
                    String academicYearOpen = tokens[2];
                    String semesterOpen = tokens[3];
                    String lecturerInitialOpen = tokens[4].split(",")[0];

                    // Menambahkan email dosen ke dalam list

                    for (Lecturer lecturer : lecturers) {
                        if (lecturer.getInitial().equals(lecturerInitialOpen)) {
                            CourseOpening courseOpening = new CourseOpening(courseCodeOpen, academicYearOpen,
                                    semesterOpen, lecturer.getInitial() + " (" + lecturer.getEmail() + ")");
                            CourseOpenings.add(courseOpening);
                        }
                    }

                    // Menampilkan array dari CourseOpening
                    CourseOpening[] courseOpeningsArray = CourseOpenings.toArray(new CourseOpening[0]);
                    for (CourseOpening course : courseOpeningsArray) {
                    }
                    break;

                case "course-history":
                    String courseCodeHistory = tokens[1];

                    // urutkan course opening berdasarkan semester
                    Collections.sort(CourseOpenings, new Comparator<CourseOpening>() {
                        @Override
                        public int compare(CourseOpening co2, CourseOpening co1) {
                            if (co1.getAcademicYear().equals(co2.getAcademicYear())) {
                                return co1.getSemester().compareTo(co2.getSemester());
                            }
                            return co1.getSemester().compareTo(co2.getSemester());
                        }
                    });

                    // Mencari course opening yang sesuai dengan kode mata kuliah
                    List<CourseOpening> courseOpeningsFound = new ArrayList<>();
                    for (CourseOpening co : CourseOpenings) {
                        if (co.getCourseCode().equals(courseCodeHistory)) {
                            courseOpeningsFound.add(co);
                        }
                    }

                    // Mencetak data course history
                    for (CourseOpening co : courseOpeningsFound) {
                        for (Course course : courses) {
                            if (course.getCode().equals(co.getCourseCode())) {
                                System.out.println(course + "|" + co.getAcademicYear() + "|" + co.getSemester() + "|"
                                        + co.getLecturerList());
                            }
                        }

                        // Mencetak data enrollment yang terkait dengan course opening
                        for (Enrollment enrollment : enrollments) {
                            if (enrollment.getCourseCode().equals(courseCodeHistory) &&
                                    enrollment.getAcademicYear().equals(co.getAcademicYear()) &&
                                    enrollment.getSemester().equals(co.getSemester())) {

                                System.out.printf("%s|%s|%s|%s|%s%n", enrollment.getCourseCode(),
                                        enrollment.getStudentID(), enrollment.getAcademicYear(),
                                        enrollment.getSemester(), enrollment.getGrade());
                            }
                        }
                    }
                    break;

                case "student-details":
                    String studentIdDetails = tokens[1];
                    // Temukan mahasiswa berdasarkan ID
                    Student student = null;
                    for (Student s : students) {
                        if (s.getId().equals(studentIdDetails)) {
                            student = s;
                            break;
                        }
                    }
                    // Hitung GPA dan total kredit jika mahasiswa ditemukan
                    if (student != null) {
                        double gpa = calculateGPA(student.getId(), enrollments, courses);
                        int totalCredits = 0; // Inisialisasi total kredit
                        Map<String, Integer> listSKS = new HashMap<>();

                        // Iterasi melalui setiap pendaftaran untuk menghitung total kredit
                        for (Enrollment enrollment : enrollments) {
                            if (enrollment.getStudentID().equals(student.getId())
                                    && !enrollment.getGrade().equals("None")) {
                                listSKS.put(enrollment.getCourseCode(),
                                        getCourseCredits(enrollment.getCourseCode(), courses));
                            }
                        }

                        for (double value : listSKS.values()) {
                            totalCredits += value;
                        }

                        /// Ubah bagian "student-details" di dalam main()
                        System.out.printf("%s|%s|%s|%s|%.2f|%d%n", student.getId(), student.getName(),
                                student.getYear(), student.getStudyProgram(), gpa, totalCredits);
                    }
                    break;

                    case "student-transcript":
                    String studentIdTranscript = tokens[1];

                    // Menggunakan konsep local class
                    // Temukan mahasiswa berdasarkan ID
                    class StudentTranscriptFinder {
                        Student findStudent(String studentId) {
                            for (Student s : students) {
                                if (s.getId().equals(studentId)) {
                                    return s;
                                }
                            }
                            return null;
                        }
                    }
                    StudentTranscriptFinder studentTranscriptFinder = new StudentTranscriptFinder();
                    Student studentTranscript = studentTranscriptFinder.findStudent(studentIdTranscript);
                
                    // Hitung GPA dan total kredit jika mahasiswa ditemukan
                    if (studentTranscript != null) {
                        ArrayList<Enrollment> sortedEnrollments = new ArrayList<>(enrollments);
                        sortArrayList(sortedEnrollments);
                        double gpa = calculateGPA(studentIdTranscript, sortedEnrollments, courses);
                
                        int totalCredits = 0; // Inisialisasi total kredit
                        Map<String, Integer> listSKS = new HashMap<>();
                
                        // Iterasi melalui setiap pendaftaran untuk menghitung total kredit
                        class EnrollmentCreditCalculator {
                            void calculateCredits(ArrayList<Enrollment> enrollments, String studentId, Map<String, Integer> sks) {
                                for (Enrollment enrollment : enrollments) {
                                    if (enrollment.getStudentID().equals(studentId)
                                            && !enrollment.getGrade().equals("None")) {
                                        sks.put(enrollment.getCourseCode(),
                                                getCourseCredits(enrollment.getCourseCode(), courses));
                                    }
                                }
                            }
                        }
                        EnrollmentCreditCalculator creditCalculator = new EnrollmentCreditCalculator();
                        creditCalculator.calculateCredits(sortedEnrollments, studentIdTranscript, listSKS);
                
                        for (int value : listSKS.values()) {
                            totalCredits += value;
                        }
                
                        // Cetak transkrip mahasiswa
                        System.out.printf("%s|%s|%s|%s|%.2f|%d%n", studentTranscript.getId(),
                                studentTranscript.getName(), studentTranscript.getYear(),
                                studentTranscript.getStudyProgram(), gpa, totalCredits);
                
                        Map<String, Enrollment> listEnrollments = new HashMap<>();
                        for (Enrollment enrollment : sortedEnrollments) {
                            if (enrollment.getStudentID().equals(studentIdTranscript)) {
                                listEnrollments.put(enrollment.getCourseCode(), enrollment);
                            }
                        }
                
                        // Mencetak setiap pendaftaran yang telah diurutkan
                        class EnrollmentPrinter {
                            void printEnrollments(Map<String, Enrollment> enrollments) {
                                for (Enrollment enrollment : enrollments.values()) {
                                    System.out.printf("%s|%s|%s|%s|%s%n", enrollment.getCourseCode(), enrollment.getStudentID(),
                                            enrollment.getAcademicYear(), enrollment.getSemester(), enrollment.getGrade());
                                }
                            }
                        }
                        EnrollmentPrinter enrollmentPrinter = new EnrollmentPrinter();
                        enrollmentPrinter.printEnrollments(listEnrollments);
                    }
                    break;
                    

                    case "find-the-best-student":
                String academicYearFind = tokens[1];
                String semesterFind = tokens [2];
                
                HashMap<String, Double> studentGPA = new HashMap<>();
                HashMap<String, String> studentSemester = new HashMap<>();

                for (Student student1:students) {
                    double gpa = calculateGPA (student1.getId(),enrollments, courses);
                    studentGPA.put(student1.getId(),gpa);
                    studentSemester.put(student1.getId(),student1.getYear());
                }
                    String bestStudent = null;
                    double maxGpa = 0;

                    for (Map.Entry<String, Double> entry:studentGPA.entrySet()) {
                        String studentId = entry.getKey();
                        double Gpa = entry.getValue();
                        String studentYear = studentSemester.get(studentId);
                    if (studentYear.equals(academicYearFind) && semesterFind.equals("odd") 
                        && studentYear.endsWith("1")){
                    if (Gpa > maxGpa){
                        maxGpa = Gpa;
                        bestStudent = studentId;
                    } else if (Gpa == maxGpa) {
                        if (studentSemester.get(bestStudent).equals("Even"))
                        bestStudent = studentId;
                    }
                }
            }
                System.out.println(bestStudent);
                break;

                case "enrollment-remedial":
                    handleEnrollmentRemedial(enrollments, tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                    break;
            }
        }

        scanner.close();

        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer);
        }

        for (Course course : courses) {
            System.out.println(course);
        }

        for (Student student : students) {
            System.out.println(student);
        }

        for (Enrollment enrollment : enrollments) {
            System.out.printf("%s|%s|%s|%s|%s%n", enrollment.getCourseCode(), enrollment.getStudentID(),
                    enrollment.getAcademicYear(), enrollment.getSemester(), enrollment.getGrade());
        }
    }

    private static boolean isStudentDuplicate(HashSet<String> studentIds, String studentId) {
        return studentIds.contains(studentId);
    }

    private static void sortArrayList(ArrayList<Enrollment> list){
        list.sort((o1, o2)
                  -> o1.getAcademicYear().compareTo(
                      o2.getAcademicYear()));
    }

    private static double calculateGPA(String studentId, ArrayList<Enrollment> enrollments, ArrayList<Course> courses) {
        double totalScore = 0;
        double totalCredit = 0;
        Map<String, Double> listGrades = new HashMap<>();
        Map<String, Integer> listSKS = new HashMap<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentID().equals(studentId) && !enrollment.getGrade().equals("None")) {
                String grade = enrollment.getGrade();
                if (grade.contains("(")) {
                    grade = grade.substring(0, grade.indexOf("("));
                }

                double gradePoint = convertGradeToPoint(grade);
                int courseCredits = getCourseCredits(enrollment.getCourseCode(), courses);
                listGrades.put(enrollment.getCourseCode(), gradePoint * courseCredits);
                listSKS.put(enrollment.getCourseCode(), courseCredits);
            }
        }

        for (double value : listGrades.values()) {
            totalScore += value;
        }

        for (int value : listSKS.values()) {
            totalCredit += value;
        }

        return totalScore / totalCredit;
    }

    private static int getCourseCredits(String courseCode, ArrayList<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course.getCredits();
            }
        }
        return 0;
    }

    private static double convertGradeToPoint(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "E":
                return 0.0;
            default:
                return 0.0;
        }
    }


    private static void handleEnrollmentRemedial(ArrayList<Enrollment> enrollments, String courseCode, String studentId,
            String academicYear, String semester, String grade) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseCode().equals(courseCode) &&
                    enrollment.getStudentID().equals(studentId) &&
                    enrollment.getAcademicYear().equals(academicYear) &&
                    enrollment.getSemester().equals(semester)) {
                String currentGrade = enrollment.getGrade();
                if (!currentGrade.equals("None") && !currentGrade.contains("(")) {
                    enrollment.setGrade(grade + "(" + currentGrade + ")");
                } else if (currentGrade.equals("None")) {
                    enrollment.setGrade(grade);
                }
                break;
            }
        }
    }
}