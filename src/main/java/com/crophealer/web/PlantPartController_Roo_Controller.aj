// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.web;

import com.crophealer.domain.PlantPart;
import com.crophealer.web.PlantPartController;
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

privileged aspect PlantPartController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PlantPartController.create(@Valid PlantPart plantPart, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, plantPart);
            return "plantparts/create";
        }
        uiModel.asMap().clear();
        plantPart.persist();
        return "redirect:/plantparts/" + encodeUrlPathSegment(plantPart.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PlantPartController.createForm(Model uiModel) {
        populateEditForm(uiModel, new PlantPart());
        return "plantparts/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String PlantPartController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("plantpart", PlantPart.findPlantPart(id));
        uiModel.addAttribute("itemId", id);
        return "plantparts/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PlantPartController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("plantparts", PlantPart.findPlantPartEntries(firstResult, sizeNo));
            float nrOfPages = (float) PlantPart.countPlantParts() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("plantparts", PlantPart.findAllPlantParts());
        }
        return "plantparts/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PlantPartController.update(@Valid PlantPart plantPart, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, plantPart);
            return "plantparts/update";
        }
        uiModel.asMap().clear();
        plantPart.merge();
        return "redirect:/plantparts/" + encodeUrlPathSegment(plantPart.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String PlantPartController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, PlantPart.findPlantPart(id));
        return "plantparts/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String PlantPartController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PlantPart plantPart = PlantPart.findPlantPart(id);
        plantPart.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/plantparts";
    }
    
    void PlantPartController.populateEditForm(Model uiModel, PlantPart plantPart) {
        uiModel.addAttribute("plantPart", plantPart);
    }
    
    String PlantPartController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
