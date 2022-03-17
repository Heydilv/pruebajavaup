package idao;

import java.util.List;
import models.Dato;

public interface IDato {
    public int GuardarDato(Dato d);
    public Dato ObtenerDatoByID(int id);
    public List<Dato> ObtenerDatosByIDHallazgo(int idhallazgo);
}
