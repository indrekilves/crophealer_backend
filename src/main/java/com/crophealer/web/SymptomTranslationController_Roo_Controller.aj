// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.web;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Symptom;
import com.crophealer.domain.SymptomTranslation;
import com.crophealer.web.SymptomTranslationController;
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

privileged aspect SymptomTranslationController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SymptomTranslationController.create(@Valid SymptomTranslation symptomTranslation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, symptomTranslation);
            return "symptomtranslations/create";
        }
        uiModel.asMap().clear();
        symptomTranslation.persist();
        return "redirect:/symptomtranslations/" + encodeUrlPathSegment(symptomTranslation.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SymptomTranslationController.createForm(Model uiModel) {
        populateEditForm(uiModel, new SymptomTranslation());
        return "symptomtranslations/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SymptomTranslationController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("symptomtranslation", SymptomTranslation.findSymptomTranslation(id));
        uiModel.addAttribute("itemId", id);
        return "symptomtranslations/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String SymptomTranslationController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("symptomtranslations", SymptomTranslation.findSymptomTranslationEntries(firstResult, sizeNo));
            float nrOfPages = (float) SymptomTranslation.countSymptomTranslations() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("symptomtranslations", SymptomTranslation.findAllSymptomTranslations());
        }
        return "symptomtranslations/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SymptomTranslationController.update(@Valid SymptomTranslation symptomTranslation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, symptomTranslation);
            return "symptomtranslations/update";
        }
        uiModel.asMap().clear();
        symptomTranslation.merge();
        return "redirect:/symptomtranslations/" + encodeUrlPathSegment(symptomTranslation.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SymptomTranslationController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, SymptomTranslation.findSymptomTranslation(id));
        return "symptomtranslations/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SymptomTranslationController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SymptomTranslation symptomTranslation = SymptomTranslation.findSymptomTranslation(id);
        symptomTranslation.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/symptomtranslations";
    }
    
    void SymptomTranslationController.populateEditForm(Model uiModel, SymptomTranslation symptomTranslation) {
        uiModel.addAttribute("symptomTranslation", symptomTranslation);
        uiModel.addAttribute("languageses", Languages.findAllLanguageses());
        uiModel.addAttribute("symptoms", Symptom.findAllSymptoms());
    }
    
    String SymptomTranslationController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
