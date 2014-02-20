package com.crophealer.web;
import com.crophealer.domain.ProblemTranslation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/problemtranslations")
@Controller
@RooWebScaffold(path = "problemtranslations", formBackingObject = ProblemTranslation.class)
public class ProblemTranslationController {
}
