package ec.edu.ups.springboot.form.app.services;

import ec.edu.ups.springboot.form.app.models.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> listar();
    public Role buscarPorId(int id);
}
