// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.LanguageResource;
import com.crophealer.rest.v1.LanguageResourceList;
import java.util.List;

privileged aspect LanguageResourceList_Roo_JavaBean {
    
    public List<LanguageResource> LanguageResourceList.getLanguages() {
        return this.languages;
    }
    
    public void LanguageResourceList.setLanguages(List<LanguageResource> languages) {
        this.languages = languages;
    }
    
}