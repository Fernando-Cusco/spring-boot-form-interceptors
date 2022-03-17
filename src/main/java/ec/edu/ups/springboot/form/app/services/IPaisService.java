package ec.edu.ups.springboot.form.app.services;

import ec.edu.ups.springboot.form.app.models.domain.Pais;

import java.util.List;

public interface IPaisService {
    public List<Pais> listar();
    public Pais buscarPorId(Integer id);
}
