package com.crophealer.web;
import com.crophealer.domain.ActiveIngredientTranslation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/activeingredienttranslations")
@Controller
@RooWebScaffold(path = "activeingredienttranslations", formBackingObject = ActiveIngredientTranslation.class)
public class ActiveIngredientTranslationController {
}
