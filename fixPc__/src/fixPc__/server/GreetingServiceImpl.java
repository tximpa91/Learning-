package fixPc__.server;


import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


import fixPc__.client.GreetingService;
import fixPc__.client.LoginInfo;
import fixPc__.client.Reparacion;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	public String insertar (Reparacion reparacion)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
	        pm.makePersistent(reparacion);
	    } catch (Exception e ) {return e.getMessage();}finally {
	        pm.close();
	}

	   return "El cliente: "+reparacion.getCodigo()+" ha sido ingresado en el sistema";
		
	}
	public LoginInfo login(String requestUri) 
	{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();
		
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
			loginInfo.setNickname(user.getNickname());
			loginInfo.setEmailAddress(user.getEmail());
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}

	public Reparacion buscar(String incidencia) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
			    
		Reparacion Nuevo = pm.getObjectById(Reparacion.class,incidencia);
		pm.close();
		return Nuevo;
	}

	public String modificar(Reparacion reparacion) {
		
		
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	    		Reparacion cliente = pm.getObjectById(Reparacion.class, reparacion.getCodigo());
	    		cliente.setTipo(reparacion.getTipo());
	    		cliente.setProducto(reparacion.getProducto());
	    		cliente.setMarca(reparacion.getMarca());
	    		cliente.setModelo(reparacion.getModelo());
	    		cliente.setPresupuesto(reparacion.getPresupuesto());
	    		cliente.setNum_serie(reparacion.getNum_serie());
	    		cliente.setSo(reparacion.getSo());
	    		cliente.setDescripcion(reparacion.getDescripcion());
	    		cliente.setObservaciones(reparacion.getObservaciones());
	    		cliente.setDni(reparacion.getDni());
	    		cliente.setNombre(reparacion.getNombre());
	    		cliente.setApellidos(reparacion.getApellidos());
	    		cliente.setEmail(reparacion.getEmail());
	    		cliente.setLocalizacion(reparacion.getLocalizacion());
	    		cliente.setCodigo(reparacion.getCodigo());
	    		cliente.setReparar(reparacion.getReparar());
	    		cliente.setRecoger(reparacion.getRecoger());
	    } finally {
	        pm.close();
	    }
		return "Sus datos fueron modificados satisfactoriamente";
	}
	@Override
	public String[] consulta() {
		String prueba[];
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Reparacion.class);
		
		
		 List<Reparacion> clientes = (List<Reparacion>) query.execute();
		 pm.close();
		 if(clientes!= null){
		 
		 prueba = new String[clientes.size()];
		 for (int i=0; i<clientes.size();i++)
		 {
			 prueba[i]=clientes.get(i).getCodigo();
		 }
		 return prueba;
		 }
		 else return null;
		
	
		
		
		
	}
	@Override
	public String[] borrar() {
PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Reparacion.class);
		query.deletePersistentAll();
		
		pm.close();
		return null;
	}

	
/*	public String insertarCliente(Cliente cliente) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
	        pm.makePersistent(cliente);
	    } catch (Exception e ) {return "Error al insertar Cliente";}finally {
	        pm.close();
	}

	   return "El cliente: "+cliente.getDNI()+" ha sido ingresado en el sistema";
		
	}*/
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
