package com.crophealer.web;
import com.crophealer.domain.DiagnosedProblemPicture;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/diagnosedproblempictures")
@Controller
@RooWebScaffold(path = "diagnosedproblempictures", formBackingObject = DiagnosedProblemPicture.class)
public class DiagnosedProblemPictureController {
}
