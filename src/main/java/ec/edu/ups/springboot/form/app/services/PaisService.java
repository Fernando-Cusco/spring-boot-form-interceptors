package ec.edu.ups.springboot.form.app.services;

import ec.edu.ups.springboot.form.app.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService implements IPaisService {
    private List<Pais> paises;
    public PaisService() {
        paises = List.of(new Pais(1, "EC", "Ecuador"),
                new Pais(2, "PE", "Peru"),
                new Pais(3, "BO", "Bolivia"),
                new Pais(4, "COL", "Colombia"),
                new Pais(5,"AR", "Argentina"));
    }

    @Override
    public List<Pais> listar() {
        return paises;
    }

    @Override
    public Pais buscarPorId(Integer id) {
        Pais pais = null;
        for(Pais p: paises) {
            if(p.getId() == id){
                pais = p;
                break;
            }
        }
        return pais;
    }
}
