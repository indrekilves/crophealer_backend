package com.crophealer.web;
import com.crophealer.domain.ActiveIngredient;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/activeingredients")
@Controller
@RooWebScaffold(path = "activeingredients", formBackingObject = ActiveIngredient.class)
public class ActiveIngredientController {
}
