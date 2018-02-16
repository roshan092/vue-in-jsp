package com.newscorp.subscription.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LogUtilServiceTest {

    @InjectMocks
    private LogUtilService logUtilService;

    @Test
    public void shouldTruncateLogIf24CharAndAllowedIs23() throws Exception {
        String truncate = logUtilService.truncate("123456789012345678901234", 23);
        assertThat(truncate).isEqualTo("1 ... (truncated) ... 4");
        assertThat(truncate.length()).isLessThanOrEqualTo(23);
    }

    @Test
    public void shouldNotTruncateLogIf23CharAndAllowedEqual23() throws Exception {
        String truncate = logUtilService.truncate("12345678901234567890123", 23);
        assertThat(truncate).isEqualTo("12345678901234567890123");
    }

    @Test
    public void shouldNotTruncateLogIfAllowedLessThan23() throws Exception {
        String truncate = logUtilService.truncate("12345678901234567890123", 22);
        assertThat(truncate).isEqualTo("12345678901234567890123");
    }

    @Test
    public void shouldNotTruncateLogIfLessThan23Char() throws Exception {
        String truncate = logUtilService.truncate("1234567890123456789012", 24);
        assertThat(truncate).isEqualTo("1234567890123456789012");
    }

    @Test
    public void shouldNotTruncateLogIfLengthEqualToAllowedLength() throws Exception {
        String truncate = logUtilService.truncate("123456789012345678901234", 24);
        assertThat(truncate).isEqualTo("123456789012345678901234");
    }

    @Test
    public void shouldNotTruncateLogIfLengthLessThanAllowedLength() throws Exception {
        String truncate = logUtilService.truncate("12345678912345678901234", 25);
        assertThat(truncate).isEqualTo("12345678912345678901234");
    }

    @Test
    public void shouldTruncateLogs() throws Exception {
        String result1 = logUtilService.truncate("123456789012345678901234567890", 25);
        assertThat(result1).isEqualTo("12 ... (truncated) ... 90");
        assertThat(result1.length()).isEqualTo(25);
        String result2 = logUtilService.truncate("123456789012345678901234567890", 26);
        assertThat(result2).isEqualTo("123 ... (truncated) ... 90");
        assertThat(result2.length()).isEqualTo(26);
        String result3 = logUtilService.truncate("12345678901234567890123456789", 25);
        assertThat(result3).isEqualTo("12 ... (truncated) ... 89");
        assertThat(result3.length()).isEqualTo(25);
        String result4 = logUtilService.truncate("12345678901234567890123456789", 26);
        assertThat(result4).isEqualTo("123 ... (truncated) ... 89");
        assertThat(result4.length()).isEqualTo(26);
    }

    @Test
    public void shouldNotTruncateLogs() throws Exception {
        String result1 = logUtilService.truncate("123456789012345678901234567890", 31);
        assertThat(result1).isEqualTo("123456789012345678901234567890");
        String result2 = logUtilService.truncate("1234567890", 5);
        assertThat(result2).isEqualTo("1234567890");
    }
}