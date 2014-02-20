package com.crophealer.web;
import com.crophealer.domain.PlantPart;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/plantparts")
@Controller
@RooWebScaffold(path = "plantparts", formBackingObject = PlantPart.class)
public class PlantPartController {
}
