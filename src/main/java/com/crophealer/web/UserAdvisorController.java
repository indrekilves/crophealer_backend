package com.crophealer.web;

import com.crophealer.domain.UserAdvisor;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/useradvisors")
@Controller
@RooWebScaffold(path = "useradvisors", formBackingObject = UserAdvisor.class)
public class UserAdvisorController
{
}
