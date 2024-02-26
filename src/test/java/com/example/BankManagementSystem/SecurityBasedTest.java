package com.example.BankManagementSystem;

import com.example.BankManagementSystem.Controllers.ApiController;
import com.example.BankManagementSystem.Interfaces.Bank;
import com.example.BankManagementSystem.Services.BankImplService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ApiController.class)
public class SecurityBasedTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    BankImplService bankImplService;
    @Test
    void unauthUserShouldNotAccessHomePage() throws Exception {
        mvc //
                .perform(get("/")) //
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void authUserShouldAccessHomePage() throws Exception {
        mvc.perform(get("/login")
                .with(csrf())
)

                .andExpect(status().isOk());
    }

    @Test
    void newBranchFromUnauthUserShouldFail() throws Exception {
        mvc.perform( //
                        post("/api/admin/branch") //
                                .param("address", "new address") //
                                .param("phone", "new phone")
                                .with(csrf())
) //

                        .andExpect(status().isUnauthorized());
    }
}