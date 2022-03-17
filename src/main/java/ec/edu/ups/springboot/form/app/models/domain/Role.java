package ec.edu.ups.springboot.form.app.models.domain;

public class Role {
    private Integer id;
    private String nombre;
    private String codigo;

    public Role() {
    }

    public Role(Integer id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return  true;
        }
        if (!(obj instanceof Role)) {
            return false;
        }
        Role role = (Role) obj;
        return this.id != null && this.id.equals(role.getId());
    }
}
