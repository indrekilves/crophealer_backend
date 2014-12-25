package com.crophealer.web;

import com.crophealer.domain.SymptomPicture;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/symptompictures")
@Controller
@RooWebScaffold(path = "symptompictures", formBackingObject = SymptomPicture.class)
public class SymptomPictureController
{
}
