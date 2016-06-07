package fixPc__.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;




/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	public LoginInfo login(String requestUri);
	public String insertar (Reparacion reparacion);
	public Reparacion buscar(String incidencia);
	public String modificar(Reparacion reparacion);
	public String[] consulta();
	public String[] borrar();
	
}
