package com.crophealer.web;

import com.crophealer.domain.ProductReseller;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productresellers")
@Controller
@RooWebScaffold(path = "productresellers", formBackingObject = ProductReseller.class)
public class ProductResellerController
{
}
