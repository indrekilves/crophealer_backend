// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.DiagnosedProblemPictureResource;
import com.crophealer.rest.v1.DiagnosedProblemPictureResourceList;
import java.util.List;

privileged aspect DiagnosedProblemPictureResourceList_Roo_JavaBean {
    
    public List<DiagnosedProblemPictureResource> DiagnosedProblemPictureResourceList.getDiagnosedProblemPictures() {
        return this.diagnosedProblemPictures;
    }
    
    public void DiagnosedProblemPictureResourceList.setDiagnosedProblemPictures(List<DiagnosedProblemPictureResource> diagnosedProblemPictures) {
        this.diagnosedProblemPictures = diagnosedProblemPictures;
    }
    
}
