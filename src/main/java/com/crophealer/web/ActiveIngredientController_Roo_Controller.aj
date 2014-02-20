// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.web;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientProduct;
import com.crophealer.domain.ActiveIngredientTranslation;
import com.crophealer.domain.Country;
import com.crophealer.domain.ProblemActiveIngredient;
import com.crophealer.web.ActiveIngredientController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ActiveIngredientController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ActiveIngredientController.create(@Valid ActiveIngredient activeIngredient, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, activeIngredient);
            return "activeingredients/create";
        }
        uiModel.asMap().clear();
        activeIngredient.persist();
        return "redirect:/activeingredients/" + encodeUrlPathSegment(activeIngredient.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ActiveIngredientController.createForm(Model uiModel) {
        populateEditForm(uiModel, new ActiveIngredient());
        return "activeingredients/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ActiveIngredientController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("activeingredient", ActiveIngredient.findActiveIngredient(id));
        uiModel.addAttribute("itemId", id);
        return "activeingredients/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ActiveIngredientController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("activeingredients", ActiveIngredient.findActiveIngredientEntries(firstResult, sizeNo));
            float nrOfPages = (float) ActiveIngredient.countActiveIngredients() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("activeingredients", ActiveIngredient.findAllActiveIngredients());
        }
        return "activeingredients/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ActiveIngredientController.update(@Valid ActiveIngredient activeIngredient, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, activeIngredient);
            return "activeingredients/update";
        }
        uiModel.asMap().clear();
        activeIngredient.merge();
        return "redirect:/activeingredients/" + encodeUrlPathSegment(activeIngredient.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ActiveIngredientController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, ActiveIngredient.findActiveIngredient(id));
        return "activeingredients/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ActiveIngredientController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        ActiveIngredient activeIngredient = ActiveIngredient.findActiveIngredient(id);
        activeIngredient.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/activeingredients";
    }
    
    void ActiveIngredientController.populateEditForm(Model uiModel, ActiveIngredient activeIngredient) {
        uiModel.addAttribute("activeIngredient", activeIngredient);
        uiModel.addAttribute("activeingredientproducts", ActiveIngredientProduct.findAllActiveIngredientProducts());
        uiModel.addAttribute("activeingredienttranslations", ActiveIngredientTranslation.findAllActiveIngredientTranslations());
        uiModel.addAttribute("countrys", Country.findAllCountrys());
        uiModel.addAttribute("problemactiveingredients", ProblemActiveIngredient.findAllProblemActiveIngredients());
    }
    
    String ActiveIngredientController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
