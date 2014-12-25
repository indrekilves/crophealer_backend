package com.crophealer.web;

import com.crophealer.domain.Problem;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/problems")
@Controller
@RooWebScaffold(path = "problems", formBackingObject = Problem.class)
public class ProblemController
{
}
