package com.crophealer.web;
import com.crophealer.domain.ProblemAIProduct;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/problemaiproducts")
@Controller
@RooWebScaffold(path = "problemaiproducts", formBackingObject = ProblemAIProduct.class)
public class ProblemAIProductController {
}
