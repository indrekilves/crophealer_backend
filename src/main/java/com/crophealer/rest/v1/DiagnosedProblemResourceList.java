package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "diagnosedProblems")
public class DiagnosedProblemResourceList
{

    private List < DiagnosedProblemResource > diagnosedProblems;

    public DiagnosedProblemResourceList()
    {
    }

    public DiagnosedProblemResourceList( List < DiagnosedProblemResource > diagnosedProblems )
    {
        this.setDiagnosedProblems( diagnosedProblems );
    }

}
