package fixPc__.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInfo> callback);

	void insertar(Reparacion reparacion, AsyncCallback<String> callback);

	void buscar(String dni, AsyncCallback<Reparacion> callback);
	
	void modificar(Reparacion reparacion, AsyncCallback<String> callback );

	void consulta(AsyncCallback<String[]> callback);
	
	void borrar (AsyncCallback<String[]> callback);
}
