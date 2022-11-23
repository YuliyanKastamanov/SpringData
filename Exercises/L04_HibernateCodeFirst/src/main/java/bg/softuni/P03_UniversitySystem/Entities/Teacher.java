package bg.softuni.P03_UniversitySystem.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "03_teachers")
public class Teacher extends Person{

    @Column(nullable = false, unique = true)
    private String email;


    @Column(name = "salary_per_hour", nullable = false)
    private float salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;

    public Teacher (){
        super();
        this.courses = new HashSet<>();
    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, float salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.courses = new HashSet<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(float salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;

    }

}
