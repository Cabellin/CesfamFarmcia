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
import pojos.Presentacion;
import services.PresentacionFacadeLocal;
/**
 *
 * @author Sebastian
 */
@FacesConverter("presentacionConverter")
public class PresentacionConverter implements Converter{
    @EJB
    private PresentacionFacadeLocal presentacionFacade;
    
    @Override
    public Object getAsObject(FacesContext context,
            UIComponent component, String newValue)
            throws ConverterException {

        BigDecimal id = new BigDecimal(newValue);
        Presentacion convertedValue = presentacionFacade.find(id);
        return convertedValue;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String id = ((Presentacion) value).getId().toString();
        return id;
    }
}
