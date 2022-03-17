package idao;

import models.Hallazgo;

public interface IHallazgo {
    public int GuardarHallazgo(Hallazgo h);
    public Hallazgo ObtenerHallazgoByID(int id);
}
