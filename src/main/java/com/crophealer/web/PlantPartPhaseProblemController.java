package com.crophealer.web;

import com.crophealer.domain.PlantPartPhaseProblem;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/plantpartphaseproblems")
@Controller
@RooWebScaffold(path = "plantpartphaseproblems", formBackingObject = PlantPartPhaseProblem.class)
public class PlantPartPhaseProblemController
{
}
