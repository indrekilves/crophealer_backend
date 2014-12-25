package com.crophealer.web;

import com.crophealer.domain.Producer;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/producers")
@Controller
@RooWebScaffold(path = "producers", formBackingObject = Producer.class)
public class ProducerController
{
}
