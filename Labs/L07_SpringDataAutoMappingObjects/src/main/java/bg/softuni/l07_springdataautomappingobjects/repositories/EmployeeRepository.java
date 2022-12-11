package bg.softuni.l07_springdataautomappingobjects.repositories;

import bg.softuni.l07_springdataautomappingobjects.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
