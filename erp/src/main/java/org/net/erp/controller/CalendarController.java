package org.net.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarController {

    @RequestMapping("/calendar")
    public String showAppointments() {
        return "calendar";
    }
}
