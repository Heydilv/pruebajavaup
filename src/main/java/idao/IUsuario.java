package idao;

import models.Usuario;

public interface IUsuario {
    public Usuario ObtenerUsuario(int id);
    public Usuario Login(String username, String password);
}
