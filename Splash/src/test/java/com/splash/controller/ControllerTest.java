package com.splash.controller;



import static org.mockito.Mockito.doReturn; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splash.entity.mysql.UserEntity;
import com.splash.services.UserService;


@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;
    
    
    

    @Test
    @DisplayName("post /add-user/0 - added " )
    public void testGetuserbyIdnotfound() throws Exception{

    	UserEntity userEntity = new UserEntity();
    	userEntity.setUsername("moazzamm2");
    	userEntity.setUserrole("A");
    	userEntity.setEmail("moazzamm2@gmail.com");
    	userEntity.setPassword("abc123xyz");
    	userEntity.setPhone("+923248262087");
    	userEntity.setStatus("E");
    	userEntity.setCreatedon(new Date());
    	userEntity.setCreatedby("self");
    	userEntity.setName("Moazzam Maqsood");
//    	 given(userService.adduser(userEntity));
//    	doReturn(userEntity).when(userService).adduser(userEntity);

        mockMvc.perform(post(("/user/add-user"))
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(asJsonString(userEntity)))
                .andExpect(status().isOk());


    }
    
    
    static String  asJsonString(final Object obj) {
    	try {
    		return new ObjectMapper().writeValueAsString(obj);
    	}catch (Exception exp) {
    		throw new RuntimeException(exp);
    	}
    }
}
