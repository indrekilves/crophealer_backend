package com.crophealer.web;
import com.crophealer.domain.ProductTranslation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/producttranslations")
@Controller
@RooWebScaffold(path = "producttranslations", formBackingObject = ProductTranslation.class)
public class ProductTranslationController {
}
