package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.rest.v1.ActiveIngredientResource;
import com.crophealer.rest.v1.ActiveIngredientResourceList;
import com.crophealer.rest.v1.PaipResourceList;
import com.crophealer.rest.v1.ProductResourceList;
import com.crophealer.rest.v1.service.ActiveIngredientRestService;

@Controller
@RequestMapping("/rest/v1/est/activeIngredients")
public class ActiveIngredientRestController extends GenericController
{

    @Autowired
    private ActiveIngredientRestService activeIngredientRestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity < ActiveIngredientResourceList > getAllActiveIngredients()
    {
        return activeIngredientRestService
                .getAllActiveIngredientsByLanguage( activeIngredientRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity < ActiveIngredientResource > getActiveIngredient( @PathVariable("id")
    Long id )
    {
        return activeIngredientRestService
                .getActiveIngredientByLanguage( id, activeIngredientRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/products")
    public ResponseEntity < ProductResourceList > getProductsForActiveIngrediens( @PathVariable("id")
    Long id )
    {
        return activeIngredientRestService.getProductsForActiveIngrediensByLanguage( id,
                activeIngredientRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/paips")
    public ResponseEntity < PaipResourceList > getPaips( @PathVariable("id")
    Long id )
    {
        return activeIngredientRestService.getPaipsById( id );
    }

}
