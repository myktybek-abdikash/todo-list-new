package com.todo_list_new.controller;

import com.todo_list_new.exception.UserAlreadyExistsException;
import com.todo_list_new.exception.ValidationException;
import com.todo_list_new.model.dto.user.UserRegistrationDTO;
import com.todo_list_new.model.dto.user.UserRequestDTO;
import com.todo_list_new.model.dto.user.UserResponseDTO;
import com.todo_list_new.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

//    @Autowired
//    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void register_ShouldReturnCreatedStatusAndUserResponse() throws Exception {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(1);
        responseDTO.setName("testuser");
        responseDTO.setEmail("testuser@example.com");

        when(userService.register(any(UserRegistrationDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "name": "testuser",
                        "password": "password123",
                        "email": "testuser@example.com"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }

    @Test
    void register_ShouldReturnBadRequestWhenDataIsInvalid() throws Exception {
        when(userService.register(any(UserRegistrationDTO.class)))
                .thenThrow(new ValidationException("Registration data cannot be null"));

        mockMvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "name": null,
                        "password": null,
                        "email": null
                    }
                    """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_ShouldReturnConflictWhenUserAlreadyExists() throws Exception {
        when(userService.register(any(UserRegistrationDTO.class)))
                .thenThrow(new UserAlreadyExistsException("User already exists"));

        mockMvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "name": "existinguser",
                        "password": "password123",
                        "email": "existinguser@example.com"
                    }
                    """))
                .andExpect(status().isConflict());
    }

    @Test
    void register_ShouldReturnInternalServerErrorWhenServiceFails() throws Exception {
        when(userService.register(any(UserRegistrationDTO.class)))
                .thenThrow(new RuntimeException("Service exception"));

        mockMvc.perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "name": "testuser",
                        "password": "password123",
                        "email": "testuser@example.com"
                    }
                    """))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void login_ShouldReturnToken() throws Exception {
        String expectedToken = "jwt_token";

        when(userService.verify(any(UserRequestDTO.class))).thenReturn(expectedToken);

        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "name": "user",
                        "password": "password"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedToken));
    }

    @Test
    void login_ShouldReturnUnauthorized() throws Exception {
        when(userService.verify(any(UserRequestDTO.class))).thenReturn("fail");

        mockMvc.perform(post("/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
                "name": "user",
                "password": "wrong_password"
            }
            """))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("fail"));
    }

}