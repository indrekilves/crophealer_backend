package com.crophealer.web;
import com.crophealer.domain.Languages;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/languageses")
@Controller
@RooWebScaffold(path = "languageses", formBackingObject = Languages.class)
public class LanguagesController {
}
