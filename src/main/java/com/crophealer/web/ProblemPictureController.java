package com.crophealer.web;

import com.crophealer.domain.ProblemPicture;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/problempictures")
@Controller
@RooWebScaffold(path = "problempictures", formBackingObject = ProblemPicture.class)
public class ProblemPictureController
{
}
