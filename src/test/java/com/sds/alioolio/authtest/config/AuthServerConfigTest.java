package com.sds.alioolio.authtest.config;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Date: 2019-06-03
 * Developer: dongha.shin
 * Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthServerConfigTest {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void getAuthToken() throws Exception {
        // Given
        String clientId = "dong";
        String clientSecret = "dong";

        // When
        mockMvc.perform(post("/oauth/token")
            .with(httpBasic(clientId, clientSecret))
            .param("grant_type", "client_credentials"))
        // Then
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("access_token").exists());
    }
}
