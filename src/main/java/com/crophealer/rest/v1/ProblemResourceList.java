package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "problems")
public class ProblemResourceList
{

    private List < ProblemResource > problems;

    public ProblemResourceList()
    {
    }

    public ProblemResourceList( List < ProblemResource > problems )
    {
        this.setProblems( problems );
    }

}
