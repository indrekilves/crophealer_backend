// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.web;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.DiagnosedProblemPicture;
import com.crophealer.domain.Message;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.security.Users;
import com.crophealer.web.DiagnosedProblemController;
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

privileged aspect DiagnosedProblemController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String DiagnosedProblemController.create(@Valid DiagnosedProblem diagnosedProblem, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, diagnosedProblem);
            return "diagnosedproblems/create";
        }
        uiModel.asMap().clear();
        diagnosedProblem.persist();
        return "redirect:/diagnosedproblems/" + encodeUrlPathSegment(diagnosedProblem.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String DiagnosedProblemController.createForm(Model uiModel) {
        populateEditForm(uiModel, new DiagnosedProblem());
        return "diagnosedproblems/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String DiagnosedProblemController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("diagnosedproblem", DiagnosedProblem.findDiagnosedProblem(id));
        uiModel.addAttribute("itemId", id);
        return "diagnosedproblems/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String DiagnosedProblemController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("diagnosedproblems", DiagnosedProblem.findDiagnosedProblemEntries(firstResult, sizeNo));
            float nrOfPages = (float) DiagnosedProblem.countDiagnosedProblems() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("diagnosedproblems", DiagnosedProblem.findAllDiagnosedProblems());
        }
        addDateTimeFormatPatterns(uiModel);
        return "diagnosedproblems/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String DiagnosedProblemController.update(@Valid DiagnosedProblem diagnosedProblem, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, diagnosedProblem);
            return "diagnosedproblems/update";
        }
        uiModel.asMap().clear();
        diagnosedProblem.merge();
        return "redirect:/diagnosedproblems/" + encodeUrlPathSegment(diagnosedProblem.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String DiagnosedProblemController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, DiagnosedProblem.findDiagnosedProblem(id));
        return "diagnosedproblems/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String DiagnosedProblemController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        DiagnosedProblem diagnosedProblem = DiagnosedProblem.findDiagnosedProblem(id);
        diagnosedProblem.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/diagnosedproblems";
    }
    
    void DiagnosedProblemController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("diagnosedProblem_createdtimestamp_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("diagnosedProblem_modifiedtimestamp_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("diagnosedProblem_closedtimestamp_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void DiagnosedProblemController.populateEditForm(Model uiModel, DiagnosedProblem diagnosedProblem) {
        uiModel.addAttribute("diagnosedProblem", diagnosedProblem);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("diagnosedproblempictures", DiagnosedProblemPicture.findAllDiagnosedProblemPictures());
        uiModel.addAttribute("messages", Message.findAllMessages());
        uiModel.addAttribute("plantpartphaseproblems", PlantPartPhaseProblem.findAllPlantPartPhaseProblems());
        uiModel.addAttribute("userses", Users.findAllUserses());
    }
    
    String DiagnosedProblemController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
