package pbo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "Enrollments")
public class Enrollment {
    @Id
    @Column(name = "ID", nullable = false, length = 255)
    private String id;
    @Column(name = "NIM", nullable = false, length = 255)
    private String nim;

    public Enrollment() {
    }

    public Enrollment(String id, String nim) {
        this.id = id;
        this.nim = nim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }


    @Override
    public String toString() {
        return id + "|" + nim;
    }
}
