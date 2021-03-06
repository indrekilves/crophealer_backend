package com.crophealer.web;

import com.crophealer.domain.GrowthPhase;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/growthphases")
@Controller
@RooWebScaffold(path = "growthphases", formBackingObject = GrowthPhase.class)
public class GrowthPhaseController
{
}
