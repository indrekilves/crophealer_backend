package com.crophealer.web.security;

import com.crophealer.security.Assignments;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/assignmentses")
@Controller
@RooWebScaffold(path = "assignmentses", formBackingObject = Assignments.class)
public class AssignmentsController
{
}
