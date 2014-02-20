package com.crophealer.web;
import com.crophealer.domain.PlantPartPhase;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/plantpartphases")
@Controller
@RooWebScaffold(path = "plantpartphases", formBackingObject = PlantPartPhase.class)
public class PlantPartPhaseController {
}
