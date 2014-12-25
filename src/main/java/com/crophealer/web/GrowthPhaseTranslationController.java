package com.crophealer.web;

import com.crophealer.domain.GrowthPhaseTranslation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/growthphasetranslations")
@Controller
@RooWebScaffold(path = "growthphasetranslations", formBackingObject = GrowthPhaseTranslation.class)
public class GrowthPhaseTranslationController
{
}
