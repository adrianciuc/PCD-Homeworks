package com.fii.pcd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/unauthorized")
public class UnauthorizedController {

    @RequestMapping(method = GET)
    public String getUnauthorizedPage() {
        return "unauthorized";
    }
}
