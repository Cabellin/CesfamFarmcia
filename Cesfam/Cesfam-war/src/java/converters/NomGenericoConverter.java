/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import pojos.NomGenerico;
import services.NomGenericoFacadeLocal;

/**
 *
 * @author Sebastian
 */
@FacesConverter("nomGenConverter")
public class NomGenericoConverter implements Converter {
    
    @EJB
    private NomGenericoFacadeLocal nomGenFacade;
    
    @Override
    public Object getAsObject(FacesContext context,
            UIComponent component, String newValue)
            throws ConverterException {

        BigDecimal id = new BigDecimal(newValue);
        NomGenerico convertedValue = nomGenFacade.find(id);
        return convertedValue;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String id = ((NomGenerico) value).getId().toString();
        return id;
    }
}
