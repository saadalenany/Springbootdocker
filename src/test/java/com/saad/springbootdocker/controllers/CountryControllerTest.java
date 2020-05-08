package com.saad.springbootdocker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saad.springbootdocker.utils.ErrorModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetByCode_codeUnknown() throws Exception {
        final ErrorModel errorModel = getForError("/invalid", status().is5xxServerError());
        assertEquals("INVALID_COUNTRY_CODE", errorModel.getError());
    }

    @Test
    public void testGetByCode_databaseDown() throws Exception {
        final ErrorModel errorModel = getForError("/BHR", status().is5xxServerError());
        assertEquals("INTERNAL_ERROR", errorModel.getError());
    }

    private ErrorModel getForError(String url, ResultMatcher expectedStatus) throws Exception {
        return objectMapper.readValue(this.mockMvc.perform(get(url)).andExpect(expectedStatus).andReturn().getResponse()
                .getContentAsString(), ErrorModel.class);
    }
}
