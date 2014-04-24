package com.crophealer.web.security;
import com.crophealer.security.Authorities;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/authoritieses")
@Controller
@RooWebScaffold(path = "authoritieses", formBackingObject = Authorities.class)
public class AuthoritiesController {
}
