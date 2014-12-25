package com.crophealer.web;

import com.crophealer.domain.DiagnosedProblem;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/diagnosedproblems")
@Controller
@RooWebScaffold(path = "diagnosedproblems", formBackingObject = DiagnosedProblem.class)
public class DiagnosedProblemController
{
}
