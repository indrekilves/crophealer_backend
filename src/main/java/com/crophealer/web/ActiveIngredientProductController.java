package com.crophealer.web;
import com.crophealer.domain.ActiveIngredientProduct;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/activeingredientproducts")
@Controller
@RooWebScaffold(path = "activeingredientproducts", formBackingObject = ActiveIngredientProduct.class)
public class ActiveIngredientProductController {
}
