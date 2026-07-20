package com.example.myspringboot.service;

import com.example.myspringboot.dto.AddressRequest;
import com.example.myspringboot.dto.AddressResponse;
import com.example.myspringboot.dto.EmployeeRequest;
import com.example.myspringboot.dto.EmployeeResponse;
import com.example.myspringboot.entity.Address;
import com.example.myspringboot.entity.Employee;
import com.example.myspringboot.exception.EmployeeNotFoundException;
import com.example.myspringboot.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = toEntity(request);
        return toResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getById(Long id) {
        return toResponse(findEmployeeOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee employee = findEmployeeOrThrow(id);
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setDepartment(request.department());
        employee.setSalary(request.salary());
        employee.setHireDate(request.hireDate());
        updateAddress(employee, request.address());
        return toResponse(employeeRepository.save(employee));
    }

    @Override
    public void delete(Long id) {
        Employee employee = findEmployeeOrThrow(id);
        employeeRepository.delete(employee);
    }

    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private void updateAddress(Employee employee, AddressRequest request) {
        Address address = employee.getAddress();
        if (address == null) {
            address = new Address();
            employee.setAddress(address);
        }
        address.setCity(request.city());
        address.setState(request.state());
        address.setPinCode(request.pinCode());
    }

    private Employee toEntity(EmployeeRequest request) {
        return Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .department(request.department())
                .salary(request.salary())
                .hireDate(request.hireDate())
                .address(toAddressEntity(request.address()))
                .build();
    }

    private Address toAddressEntity(AddressRequest request) {
        return Address.builder()
                .city(request.city())
                .state(request.state())
                .pinCode(request.pinCode())
                .build();
    }

    private EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getSalary(),
                employee.getHireDate(),
                toAddressResponse(employee.getAddress())
        );
    }

    private AddressResponse toAddressResponse(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressResponse(address.getCity(), address.getState(), address.getPinCode());
    }
}
