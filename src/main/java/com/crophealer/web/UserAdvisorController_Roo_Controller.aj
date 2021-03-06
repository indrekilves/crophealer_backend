// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.web;

import com.crophealer.domain.UserAdvisor;
import com.crophealer.security.Users;
import com.crophealer.web.UserAdvisorController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect UserAdvisorController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String UserAdvisorController.create(@Valid UserAdvisor userAdvisor, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userAdvisor);
            return "useradvisors/create";
        }
        uiModel.asMap().clear();
        userAdvisor.persist();
        return "redirect:/useradvisors/" + encodeUrlPathSegment(userAdvisor.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UserAdvisorController.createForm(Model uiModel) {
        populateEditForm(uiModel, new UserAdvisor());
        return "useradvisors/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String UserAdvisorController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("useradvisor", UserAdvisor.findUserAdvisor(id));
        uiModel.addAttribute("itemId", id);
        return "useradvisors/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String UserAdvisorController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("useradvisors", UserAdvisor.findUserAdvisorEntries(firstResult, sizeNo));
            float nrOfPages = (float) UserAdvisor.countUserAdvisors() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("useradvisors", UserAdvisor.findAllUserAdvisors());
        }
        addDateTimeFormatPatterns(uiModel);
        return "useradvisors/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String UserAdvisorController.update(@Valid UserAdvisor userAdvisor, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, userAdvisor);
            return "useradvisors/update";
        }
        uiModel.asMap().clear();
        userAdvisor.merge();
        return "redirect:/useradvisors/" + encodeUrlPathSegment(userAdvisor.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String UserAdvisorController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, UserAdvisor.findUserAdvisor(id));
        return "useradvisors/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String UserAdvisorController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        UserAdvisor userAdvisor = UserAdvisor.findUserAdvisor(id);
        userAdvisor.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/useradvisors";
    }
    
    void UserAdvisorController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("userAdvisor_effectivefrom_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("userAdvisor_effectiveto_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("userAdvisor_createddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("userAdvisor_modifieddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void UserAdvisorController.populateEditForm(Model uiModel, UserAdvisor userAdvisor) {
        uiModel.addAttribute("userAdvisor", userAdvisor);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("userses", Users.findAllUserses());
    }
    
    String UserAdvisorController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
