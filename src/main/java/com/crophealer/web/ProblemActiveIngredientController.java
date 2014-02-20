package com.crophealer.web;
import com.crophealer.domain.ProblemActiveIngredient;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/problemactiveingredients")
@Controller
@RooWebScaffold(path = "problemactiveingredients", formBackingObject = ProblemActiveIngredient.class)
public class ProblemActiveIngredientController {
}
