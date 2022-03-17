package ec.edu.ups.springboot.form.app.controllers;

import ec.edu.ups.springboot.form.app.editors.NombreMayusculaEditor;
import ec.edu.ups.springboot.form.app.editors.PaisPropertyEditor;
import ec.edu.ups.springboot.form.app.editors.RolePropertyEditor;
import ec.edu.ups.springboot.form.app.models.domain.Pais;
import ec.edu.ups.springboot.form.app.models.domain.Role;
import ec.edu.ups.springboot.form.app.models.domain.Usuario;
import ec.edu.ups.springboot.form.app.services.IPaisService;
import ec.edu.ups.springboot.form.app.services.IRoleService;
import ec.edu.ups.springboot.form.app.validations.UsuarioValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @Autowired
    private UsuarioValidations usuarioValidations;

    @Autowired
    private PaisPropertyEditor paisPropertyEditor;

    @Autowired
    private RolePropertyEditor rolePropertyEditor;

    @Autowired
    private IPaisService paisService;

    @Autowired
    private IRoleService roleService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(usuarioValidations);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class, "username", new NombreMayusculaEditor());
        binder.registerCustomEditor(Pais.class, "pais", paisPropertyEditor);
        binder.registerCustomEditor(Role.class, "roles", rolePropertyEditor);
    }

    @ModelAttribute("roles")
    public List<Role> roles() {
        return roleService.listar();
    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises() {
        return paisService.listar();
    }

    @ModelAttribute("paises")
    public List<String> paises() {
        return List.of("Ecuador", "Peru", "Bolivia", "Colombia", "Venezuela");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap() {
        Map<String, String> paises = new HashMap<>();
        paises.put("es", "España");
        paises.put("fr", "Francia");
        paises.put("al", "Alemania");
        paises.put("is", "Israel");
        return paises;
    }

    @ModelAttribute("rolesMap")
    public Map<String, String> rolesMap() {
        Map<String, String> paises = new HashMap<>();
        paises.put("ROLE_ADMIN", "Administrador");
        paises.put("ROLE_USER", "Usuario");
        paises.put("ROLE_MODERATOR", "Moderador");
        return paises;
    }

    @ModelAttribute("genero")
    public List<String> genero() {
        return List.of("Masculino", "Femenino");
    }

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("Fernando");
        usuario.setApellido("Cusco");
        usuario.setIdentificador("123.456.789-EC");
        usuario.setValorSecreto("mipassword-@#@!#");
        usuario.setHabilitar(true);
        usuario.setPais(new Pais(1, "EC", "Ecuador"));
        usuario.setRoles(List.of(
                new Role(1, "Administrador", "ROLE_ADMIN"),
                new Role(2, "Usuario", "ROLE_USER")));
        model.addAttribute("titulo", "Formulario");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
//        usuarioValidations.validate(usuario, result);
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario procesado");
            // forma manual de manejar errores
//            Map<String, String> errores = new HashMap<>();
//            result.getFieldErrors().forEach(error -> {
//                errores.put(error.getField(), "El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
//            });
//            model.addAttribute("error", errores);
            return "form";
        }

        return "redirect:/ver";
    }

    @GetMapping("/ver")
    public String ver(@SessionAttribute(name="usuario", required = false) Usuario usuario, Model model, SessionStatus status) {
        if (usuario == null) {
            return "redirect:/form";
        }
        model.addAttribute("titulo", "Formulario procesado");
        status.setComplete();
        return "resultado";
    }

}
