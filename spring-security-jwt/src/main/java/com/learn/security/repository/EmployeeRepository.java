package com.learn.security.repository;

import com.learn.security.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Derived query
    List<Employee> findByNameAndEmail(String name, String email);

    List<Employee> findByNameContaining(String keyword);

    List<Employee> findByAgeGreaterThan(int age);

    List<Employee> findByNameStartingWith(String wildCard);

    List<Employee> findByName(String name);

    //Jpql query
    @Query("select employee from Employee employee where employee.name= :name")
    List<Employee> findByNameUsingJpql(@Param("name") String name);

    @Query("select employee from Employee employee where employee.name= :name and employee.email= :email")
    List<Employee> findByNameAndEmailUsingJpql(@Param("name") String name, @Param("email") String email);

    @Query("select employee.name from Employee employee")
    List<String> findAllNamesUsingJpql();

    @Query("select employee.name, employee.email from Employee employee")
    List<String> findAllNamesWithEmailUsingJpql();

    //native query
    @Query(value = "SELECT * FROM employee WHERE NAME= :name", nativeQuery = true)
    List<Employee> findByNameUsingNativeQuery(@Param("name") String name);

    @Query(value = "SELECT name FROM employee", nativeQuery = true)
    List<String> findAllNamesUsingNativeQuery();

}
