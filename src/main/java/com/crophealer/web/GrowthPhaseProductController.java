package com.crophealer.web;
import com.crophealer.domain.GrowthPhaseProduct;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/growthphaseproducts")
@Controller
@RooWebScaffold(path = "growthphaseproducts", formBackingObject = GrowthPhaseProduct.class)
public class GrowthPhaseProductController {
}
