package pl.codeme.jeeb.e2.bank.backend.model;

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "departments")
public class Department extends Model {

    @Column(name = "name", columnDefinition = "varchar(100) NOT NULL COMMENT 'Nazwa departamentu'")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
