package com.crophealer.web;

import com.crophealer.domain.Field;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/fields")
@Controller
@RooWebScaffold(path = "fields", formBackingObject = Field.class)
public class FieldController
{
}
