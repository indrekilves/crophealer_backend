package com.crophealer.web;

import com.crophealer.domain.PlantPartTranslation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/plantparttranslations")
@Controller
@RooWebScaffold(path = "plantparttranslations", formBackingObject = PlantPartTranslation.class)
public class PlantPartTranslationController
{
}
