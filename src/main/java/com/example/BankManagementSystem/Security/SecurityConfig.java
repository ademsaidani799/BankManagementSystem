package com.example.BankManagementSystem.Security;

import com.example.BankManagementSystem.Entities.Customer;
import com.example.BankManagementSystem.Entities.Employee;
import com.example.BankManagementSystem.Repositories.CustomerManagementRepositories;
import com.example.BankManagementSystem.Repositories.CustomerRepositories;
import com.example.BankManagementSystem.Repositories.EmployeeManagementRepositories;
import com.example.BankManagementSystem.Repositories.EmployeeRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    CommandLineRunner initUsers(CustomerManagementRepositories customerManagementRepositories, EmployeeManagementRepositories employeeManagementRepositories) {
        return args -> {
            customerManagementRepositories.save(new Customer("user", "password", "ROLE_USER"));
            employeeManagementRepositories.save(new Employee("admin", "password", "ROLE_ADMIN"));
        };
    }


    @Bean
    UserDetailsService employeeUserService(EmployeeRepositories employeeRepositories, CustomerRepositories customerRepositories) {
        return username -> {
            Employee employee = employeeRepositories.findByUsername(username);
            if (employee != null) {
                return employee.asUser();
            } else {
                // If the user is not found in EmployeeRepository, check CustomerRepository
                Customer customer = customerRepositories.findByUsername(username);
                if (customer != null) {
                    // Convert CustomerEntity to UserDetails
                    // You may need to implement a method like asUser() in CustomerEntity
                    return customer.asUser();
                }
                return null;
            }
        };
    }

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // Use authorizeRequests() instead of authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/**").authenticated()

                .anyRequest().denyAll()
                .and()
                .httpBasic().and()
                .csrf().disable()
                .headers().frameOptions().disable()
        ;
        return http.build();
    }


}
