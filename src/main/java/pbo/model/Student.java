package pbo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @Column(name = "NIM", nullable = false, unique = true, length = 255)
    private String nim;
    @Column(name = "Name", nullable = false, length = 255)
    private String name;
    @Column(name = "Prodi", nullable = false, length = 255)
    private String prodi;

    public Student(){
    }

    public Student(String nim, String name, String prodi) {
        this.nim = nim;
        this.name = name;
        this.prodi = prodi;
    }
    
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getProdi(){
        return prodi;
    }

    public void setProdi(String prodi){
        this.prodi = prodi;
    }

    @Override
    public String toString() {
        return nim + "|" + name + "|" + prodi;

    }
}
