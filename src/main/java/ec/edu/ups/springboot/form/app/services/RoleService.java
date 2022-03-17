package ec.edu.ups.springboot.form.app.services;

import ec.edu.ups.springboot.form.app.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService{
    private List<Role> roles;
    public RoleService() {
        this.roles = List.of(
                new Role(1, "Administrador", "ROLE_ADMIN"),
                new Role(2, "Usuario", "ROLE_USER"),
                new Role(3, "Moderador", "ROLE_MODERATOR")
        );
    }

    @Override
    public List<Role> listar() {
        return roles;
    }

    @Override
    public Role buscarPorId(int id) {
        Role role = null;
        for(Role r: roles) {
            if (r.getId() == id){
                role = r;
                break;
            }
        }
        return role;
    }
}
