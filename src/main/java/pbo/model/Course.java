package pbo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @Column(name = "ID", nullable = false, unique = true, length = 255)
    private String id;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Semester", nullable = false, length = 255)
    private String semester;

    @Column(name = "SKS", nullable = false, length = 255)
    private String sks;

    public Course(){
    }

    public Course(String id, String name, String semester, String sks) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.sks = sks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSemester(){
        return semester;
    }

    public void setSemester(String semester){
        this.semester = semester;
    }

    public String getSks(){
        return sks;
    }

    public void setSks(String sks){
        this.sks = sks;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + semester + "|" + sks;
    }
}
