package ec.edu.ups.springboot.form.app.editors;

import ec.edu.ups.springboot.form.app.services.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyEditorSupport;

@Service
public class PaisPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private IPaisService paisService;

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null || id.length() > 0) {
            try {
                this.setValue(paisService.buscarPorId(Integer.parseInt(id)));
            } catch (NumberFormatException e) {
                setValue(null);
            }
        } else {
            setValue(null);
        }
    }
}
