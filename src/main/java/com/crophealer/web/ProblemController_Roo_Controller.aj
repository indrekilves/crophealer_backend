// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.web;

import com.crophealer.domain.Country;
import com.crophealer.domain.Problem;
import com.crophealer.web.ProblemController;
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

privileged aspect ProblemController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ProblemController.create(@Valid Problem problem, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, problem);
            return "problems/create";
        }
        uiModel.asMap().clear();
        problem.persist();
        return "redirect:/problems/" + encodeUrlPathSegment(problem.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ProblemController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Problem());
        return "problems/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ProblemController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("problem", Problem.findProblem(id));
        uiModel.addAttribute("itemId", id);
        return "problems/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ProblemController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("problems", Problem.findProblemEntries(firstResult, sizeNo));
            float nrOfPages = (float) Problem.countProblems() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("problems", Problem.findAllProblems());
        }
        return "problems/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ProblemController.update(@Valid Problem problem, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, problem);
            return "problems/update";
        }
        uiModel.asMap().clear();
        problem.merge();
        return "redirect:/problems/" + encodeUrlPathSegment(problem.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ProblemController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Problem.findProblem(id));
        return "problems/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ProblemController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Problem problem = Problem.findProblem(id);
        problem.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/problems";
    }
    
    void ProblemController.populateEditForm(Model uiModel, Problem problem) {
        uiModel.addAttribute("problem", problem);
        uiModel.addAttribute("countrys", Country.findAllCountrys());
    }
    
    String ProblemController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
