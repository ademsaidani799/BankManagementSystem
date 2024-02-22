package com.example.BankManagementSystem;

import com.example.BankManagementSystem.Entities.Customer;
import com.example.BankManagementSystem.Entities.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CoreDomainTest {
    @Test
    void newCustomerShouldHaveNullId() {
        Customer entity = new Customer("Adem",
                "password", "ROLE_USER");
        assertThat(entity.getId()).isNull();//because id is genereated when the entity persisted in db(after save)
        assertThat(entity.getUsername()).isEqualTo("Adem");
        assertThat(entity.getPassword()).isEqualTo("password");

    }
    @Test
    void settersShouldMutateState() {
        Customer entity = new Customer("Adem",
                "password", "ROLE_USER");
        entity.setId(99L);
        entity.setName("new name");
        entity.setPassword("new pass");
        entity.setUsername("bob");
        assertThat(entity.getId()).isEqualTo(99L);
        assertThat(entity.getUsername()).isEqualTo("bob");
        assertThat(entity.getName()).isEqualTo("new name");
        assertThat(entity.getPassword()) //
                .isEqualTo("new pass");
    }
    @Test
    void newEmployeeShouldHaveNullId() {
        Employee employee = new Employee("John", "password", "ROLE_TELLER");
        assertThat(employee.getId()).isNull();
        assertThat(employee.getUsername()).isEqualTo("John");
        assertThat(employee.getPassword()).isEqualTo("password");
        assertThat(employee.getPosition()).isNull(); // Assuming position is initially null
    }

    @Test
    void settersShouldMutateStateForEmployee() {
        Employee employee = new Employee("John", "password", "ROLE_TELLER");
        employee.setId(99L);
        employee.setUsername("Bob");
        employee.setPassword("newPass");
        employee.setPosition("Teller");

        assertThat(employee.getId()).isEqualTo(99L);
        assertThat(employee.getUsername()).isEqualTo("Bob");
        assertThat(employee.getPassword()).isEqualTo("newPass");
        assertThat(employee.getPosition()).isEqualTo("Teller");
    }

    @Test
    void employeeWithManagerAndBranchShouldHaveNonNullManagerAndBranch() {
        Employee manager = new Employee("Manager", "pass", "ROLE_MANAGER");
        Employee employee = new Employee("John", "password", "ROLE_TELLER");
        employee.setManager(manager);

        assertThat(employee.getManager()).isNotNull();
        assertThat(employee.getManager().getUsername()).isEqualTo("Manager");
    }

}
