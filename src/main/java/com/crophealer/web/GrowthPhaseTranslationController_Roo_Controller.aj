// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.web;

import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.GrowthPhaseTranslation;
import com.crophealer.domain.Languages;
import com.crophealer.web.GrowthPhaseTranslationController;
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

privileged aspect GrowthPhaseTranslationController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String GrowthPhaseTranslationController.create(@Valid GrowthPhaseTranslation growthPhaseTranslation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, growthPhaseTranslation);
            return "growthphasetranslations/create";
        }
        uiModel.asMap().clear();
        growthPhaseTranslation.persist();
        return "redirect:/growthphasetranslations/" + encodeUrlPathSegment(growthPhaseTranslation.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String GrowthPhaseTranslationController.createForm(Model uiModel) {
        populateEditForm(uiModel, new GrowthPhaseTranslation());
        return "growthphasetranslations/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String GrowthPhaseTranslationController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("growthphasetranslation", GrowthPhaseTranslation.findGrowthPhaseTranslation(id));
        uiModel.addAttribute("itemId", id);
        return "growthphasetranslations/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String GrowthPhaseTranslationController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("growthphasetranslations", GrowthPhaseTranslation.findGrowthPhaseTranslationEntries(firstResult, sizeNo));
            float nrOfPages = (float) GrowthPhaseTranslation.countGrowthPhaseTranslations() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("growthphasetranslations", GrowthPhaseTranslation.findAllGrowthPhaseTranslations());
        }
        return "growthphasetranslations/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String GrowthPhaseTranslationController.update(@Valid GrowthPhaseTranslation growthPhaseTranslation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, growthPhaseTranslation);
            return "growthphasetranslations/update";
        }
        uiModel.asMap().clear();
        growthPhaseTranslation.merge();
        return "redirect:/growthphasetranslations/" + encodeUrlPathSegment(growthPhaseTranslation.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String GrowthPhaseTranslationController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, GrowthPhaseTranslation.findGrowthPhaseTranslation(id));
        return "growthphasetranslations/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String GrowthPhaseTranslationController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        GrowthPhaseTranslation growthPhaseTranslation = GrowthPhaseTranslation.findGrowthPhaseTranslation(id);
        growthPhaseTranslation.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/growthphasetranslations";
    }
    
    void GrowthPhaseTranslationController.populateEditForm(Model uiModel, GrowthPhaseTranslation growthPhaseTranslation) {
        uiModel.addAttribute("growthPhaseTranslation", growthPhaseTranslation);
        uiModel.addAttribute("growthphases", GrowthPhase.findAllGrowthPhases());
        uiModel.addAttribute("languageses", Languages.findAllLanguageses());
    }
    
    String GrowthPhaseTranslationController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
