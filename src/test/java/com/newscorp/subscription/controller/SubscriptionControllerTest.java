package com.newscorp.subscription.controller;

import com.newscorp.subscription.service.ILogUtilService;
import com.newscorp.subscription.service.impl.LogUtilService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.apache.commons.lang.math.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ILogUtilService logUtilService;

    @Test
    public void shouldRespondWith200() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/subscribe")).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void shouldRespondWithAppropriateView() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/subscribe")).andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).contains("These are the order details");
    }

    @Test
    public void shouldCallTheTruncateLogService() throws Exception {
        Integer random = nextInt();
        mockMvc.perform(MockMvcRequestBuilders.get("/subscribe")
                .param("orderDetails", random.toString())).andReturn();
        verify(logUtilService).truncate(random.toString(), 250);
    }
}
