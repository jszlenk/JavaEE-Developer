package pl.codeme.jeeb.e2.bank.backend.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class User extends Model {

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT 'Imię osoby'")
    protected String name;

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT 'Nazwisko osoby'")
    protected String surname;

    @Column(name = "tax_number", columnDefinition = "varchar(10) NOT NULL COMMENT 'NIP osoby'")
    protected String taxNumber;

    @Column(columnDefinition = "varchar(80) COMMENT 'Adres email osoby'")
    protected String email;

    @Column(columnDefinition = "varchar(12) COMMENT 'Numer telefonu osoby'")
    protected String phone;

    @Column(columnDefinition = "varchar(150) NOT NULL COMMENT 'Adres zamieszkania osoby'")
    protected String address;

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT 'Miejscowość zamiezkania osoby'")
    protected String city;

    @Column(columnDefinition = "varchar(5) NOT NULL COMMENT 'Kod pocztowy osoby'")
    protected String zipcode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
