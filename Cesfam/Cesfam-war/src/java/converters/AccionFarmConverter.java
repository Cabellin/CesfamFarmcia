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
import pojos.AccionFarm;
import services.AccionFarmFacadeLocal;
/**
 *
 * @author Sebastian
 */
@FacesConverter("accionFarmConverter")
public class AccionFarmConverter implements Converter{
    @EJB
    private AccionFarmFacadeLocal accionFarmFacade;
    
    @Override
    public Object getAsObject(FacesContext context,
            UIComponent component, String newValue)
            throws ConverterException {

        BigDecimal id = new BigDecimal(newValue);
        AccionFarm convertedValue = accionFarmFacade.find(id);
        return convertedValue;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String id = ((AccionFarm) value).getId().toString();
        return id;
    }
}
