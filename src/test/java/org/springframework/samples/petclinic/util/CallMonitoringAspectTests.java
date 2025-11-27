package org.springframework.samples.petclinic.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CallMonitoringAspectTests {

    private CallMonitoringAspect aspect;

    @BeforeEach
    void setup() {
        aspect = new CallMonitoringAspect();
        aspect.reset();
        aspect.setEnabled(true);
    }

    @Test
    void testInvokeIncrementsAndRecordsTime() throws Throwable {
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        when(joinPoint.toShortString()).thenReturn("short");
        when(joinPoint.proceed()).thenReturn("ok");

        Object result = aspect.invoke(joinPoint);
        assertEquals("ok", result);
        assertEquals(1, aspect.getCallCount());
        assertTrue(aspect.getCallTime() >= 0);
    }

    @Test
    void testInvokeDisabledDoesNotCount() throws Throwable {
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        when(joinPoint.proceed()).thenReturn("ok");

        aspect.setEnabled(false);
        Object result = aspect.invoke(joinPoint);
        assertEquals("ok", result);
        assertEquals(0, aspect.getCallCount());
    }

    @Test
    void testResetClearsCounters() throws Throwable {
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        when(joinPoint.toShortString()).thenReturn("short");
        when(joinPoint.proceed()).thenReturn("ok");

        aspect.invoke(joinPoint);
        assertEquals(1, aspect.getCallCount());

        aspect.reset();
        assertEquals(0, aspect.getCallCount());
        assertEquals(0, aspect.getCallTime());
    }
}
