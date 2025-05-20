package pbo;

import javax.persistence.*;
import java.util.*;
import pbo.model.Student;
import pbo.model.Course;
import pbo.model.Enrollment;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("study_plan_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        while (true) {
            String command = scanner.nextLine();
            String[] data = command.split("#");

            switch (data[0]) {
                case "student-add":
                    String nim = data[1];
                    String Name = data[2];
                    String prodi = data[3];

                    List<Student> students = entityManager.createQuery(
                            "SELECT s FROM Student s WHERE s.nim = :nim", Student.class)
                            .setParameter("nim", nim)
                            .getResultList();
                    if (!students.isEmpty()) {
                        continue;
                    }

                    Student student = new Student(nim, Name, prodi);
                    entityManager.getTransaction().begin();
                    entityManager.persist(student);
                    entityManager.getTransaction().commit();
                    break;

                case "student-show-all":
                    List<Student> studentsList = entityManager.createQuery(
                            "SELECT s FROM Student s ORDER BY s.nim ASC", Student.class)
                            .getResultList();

                    for (Student studentItem : studentsList) {
                        System.out.println(studentItem.toString());
                    }
                    break;

                case "course-add":
                    String id = data[1];
                    String NameCourse = data[2];
                    String semester = data[3];
                    String Sks = data[4];

                    entityManager.getTransaction().begin();
                    Course existingCourse = entityManager.find(Course.class, id);
                    if (existingCourse != null) {
                        existingCourse.setName(NameCourse);
                        existingCourse.setSemester(semester);
                        existingCourse.setSks(Sks);
                    } else {
                        Course course = new Course(id, NameCourse, semester, Sks);
                        entityManager.persist(course);
                    }
                    entityManager.getTransaction().commit();
                    break;

                case "course-show-all":
                    List<Course> coursesList = entityManager.createQuery(
                            "SELECT c FROM Course c ORDER BY c.id ASC", Course.class)
                            .getResultList();

                    for (Course courseItem : coursesList) {
                        System.out.println(courseItem.toString());
                    }
                    break;

                case "Enroll":
                    String nimEnrollment = data[1];
                    String idEnrollment = data[2];

                    List<Enrollment> EnrollmentList = entityManager.createQuery(
                            "SELECT e FROM Enrollment e WHERE e.nim = :nim AND e.id = :id", Enrollment.class)
                            .setParameter("nim", nimEnrollment)
                            .setParameter("id", idEnrollment)
                            .getResultList();

                    if (!EnrollmentList.isEmpty()) {
                        continue;
                    }

                    Student studentEnrollment = entityManager.find(Student.class, nimEnrollment);
                    Course courseEnrollment = entityManager.find(Course.class, idEnrollment);

                    if (studentEnrollment == null) {
                        System.out.println("Student not found.");
                        continue;
                    }

                    if (courseEnrollment == null) {
                        System.out.println("Course not found.");
                        continue;
                    }

                    Enrollment Enrollment = new Enrollment(studentEnrollment.getNim(), idEnrollment);
                    entityManager.getTransaction().begin();
                    entityManager.persist(Enrollment);
                    entityManager.getTransaction().commit();
                    break;

                case "student-show":
                    String nimDetail = data[1];

                    Student studentDetail = entityManager.find(Student.class, nimDetail);
                    if (studentDetail != null) {
                        System.out.println(studentDetail.toString());

                        List<Enrollment> EnrollmentedCourses = entityManager.createQuery(
                                "SELECT e FROM Enrollment e WHERE e.nim = :nim ORDER BY e.id ASC",
                                Enrollment.class)
                                .setParameter("nim", nimDetail)
                                .getResultList();

                        for (Enrollment EnrollmentItem : EnrollmentedCourses) {
                            Course EnrollmentedCourse = entityManager.find(Course.class, EnrollmentItem.getId());
                            System.out.println(EnrollmentItem.toString() + " - " + EnrollmentedCourse.getName());
                        }
                    }
                    break;

                default:
                    scanner.close();
                    entityManager.getTransaction().begin();
                    entityManager.createQuery("DELETE FROM Student").executeUpdate();
                    entityManager.createQuery("DELETE FROM Course").executeUpdate();
                    entityManager.createQuery("DELETE FROM Enrollment").executeUpdate();
                    entityManager.getTransaction().commit();
                    entityManager.close();
                    entityManagerFactory.close();
                    return;
            }
        }
    }
}
