package com.crophealer.web;

import com.crophealer.domain.PlantPartPhaseSymptom;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/plantpartphasesymptoms")
@Controller
@RooWebScaffold(path = "plantpartphasesymptoms", formBackingObject = PlantPartPhaseSymptom.class)
public class PlantPartPhaseSymptomController
{
}
