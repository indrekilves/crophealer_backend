package com.crophealer.web;

import com.crophealer.domain.SymptomTranslation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/symptomtranslations")
@Controller
@RooWebScaffold(path = "symptomtranslations", formBackingObject = SymptomTranslation.class)
public class SymptomTranslationController
{
}
