package com.crophealer.web;
import com.crophealer.domain.PlantTranslation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/planttranslations")
@Controller
@RooWebScaffold(path = "planttranslations", formBackingObject = PlantTranslation.class)
public class PlantTranslationController {
}
