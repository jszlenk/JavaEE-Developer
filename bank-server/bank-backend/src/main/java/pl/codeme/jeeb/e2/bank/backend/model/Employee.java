package pl.codeme.jeeb.e2.bank.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(
        name = "employees",
        indexes = {
                @Index(name = "IX_EMPLOYEE_BUSNESS_EMAIL", columnList = "business_email")
        }
)
public class Employee extends User {

    @Column(name = "business_email", columnDefinition = "varchar(80) NOT NULL COMMENT 'Służbowy adres email'")
    private String businessEmail;

    @Column(columnDefinition = "varchar(32) NOT NULL COMMENT 'Hasło MD5'")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_department",
            joinColumns = {@JoinColumn(name = "employeeId", columnDefinition = "int(11) unsigned COMMENT " +
                    "'Identyfikator pracownika'")},
            inverseJoinColumns = {@JoinColumn(name = "departmentId", columnDefinition = "int(11) unsigned COMMENT " +
                    "'Identyfikator departamentu'")}
    )
    private List<Department> departments;

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}