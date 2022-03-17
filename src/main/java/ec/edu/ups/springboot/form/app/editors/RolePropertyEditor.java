package ec.edu.ups.springboot.form.app.editors;

import ec.edu.ups.springboot.form.app.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class RolePropertyEditor extends PropertyEditorSupport {

    @Autowired
    private IRoleService roleService;
    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        try {
            setValue(roleService.buscarPorId(Integer.parseInt(id)));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
