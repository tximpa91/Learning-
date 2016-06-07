package fixPc__.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ListaClientes implements Serializable {
	
	List<Reparacion> Clientes = null;

	public List<Reparacion> getClientes() {
		return Clientes;
	}

	public void setClientes(List<Reparacion> clientes) {
		Clientes = clientes;
	}

}
