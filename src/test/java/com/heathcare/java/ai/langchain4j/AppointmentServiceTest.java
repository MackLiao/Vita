package com.heathcare.java.ai.langchain4j;

import com.healthcare.java.ai.langchain4j.VitaApp;
import com.healthcare.java.ai.langchain4j.entity.Appointment;
import com.healthcare.java.ai.langchain4j.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = VitaApp.class)
class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    void testGetOne() {
        Appointment appointment = new Appointment();
        appointment.setUsername("John");
        appointment.setIdCard("123456789012345678");
        appointment.setDepartment("Neurology");
        appointment.setDate("2025-06-10");
        appointment.setTime("morning");

        Appointment appointmentDB = appointmentService.getOne(appointment);
        System.out.println(appointmentDB);
    }

    @Test
    void testSave() {
        Appointment appointment = new Appointment();
        appointment.setUsername("John");
        appointment.setIdCard("123456789012345678");
        appointment.setDepartment("Neurology");
        appointment.setDate("2025-06-10");
        appointment.setTime("morning");
        appointment.setDoctorName("Dr. Zhang");

        appointmentService.save(appointment);
    }

    @Test
    void testRemoveById() {
        appointmentService.removeById(1L);
    }
}