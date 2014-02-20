package com.crophealer.web;
import com.crophealer.domain.Symptom;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/symptoms")
@Controller
@RooWebScaffold(path = "symptoms", formBackingObject = Symptom.class)
public class SymptomController {
}
