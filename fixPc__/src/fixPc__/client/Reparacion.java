package fixPc__.client;

import java.io.Serializable;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Reparacion implements Serializable{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String codigo;
	
	
	@Persistent
	private String fecha;
	@Persistent
	private String tipo;
	@Persistent
	private String producto;
	@Persistent
	private String marca;
	@Persistent
	private String modelo;
	@Persistent
	private String num_serie;
	@Persistent
	private String so;
	@Persistent
	private String descripcion;
	@Persistent
	private String observaciones;
	@Persistent
	private String garantia;
	@Persistent
	private float presupuesto;
	@Persistent
	private String reparar;
	@Persistent
	private String recoger;
	@Persistent
	private String dni;
	@Persistent
	private String nombre;
	@Persistent
	private String apellidos;
	@Persistent
	private String localizacion;
	@Persistent
	private int cp;
	@Persistent
	private String email;
	
	
	

	
	
	public Reparacion(){}
	
	public Reparacion(String codigo, String fecha, String tipo, String producto, String marca, String modelo,
			String num_serie, String so, String descripcion, String observaciones, String garantia, float presupuesto,
			String reparar, String recoger, String dni, String nombre, String apellidos, String localizacion, int cp,
			String email) {
	
		this.codigo = codigo;
		this.fecha = fecha;
		this.tipo = tipo;
		this.producto = producto;
		this.marca = marca;
		this.modelo = modelo;
		this.num_serie = num_serie;
		this.so = so;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
		this.garantia = garantia;
		this.presupuesto = presupuesto;
		this.reparar = reparar;
		this.recoger = recoger;
		this.dni = dni;
		this.nombre = nombre; 
		this.apellidos = apellidos;
		this.localizacion = localizacion;
		this.cp = cp;
		this.email = email;
	}




	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNum_serie() {
		return num_serie;
	}
	public void setNum_serie(String num_serie) {
		this.num_serie = num_serie;
	}
	public String getSo() {
		return so;
	}
	public void setSo(String so) {
		this.so = so;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getGarantia() {
		return garantia;
	}
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}
	public float getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(float presupuesto) {
		this.presupuesto = presupuesto;
	}
	public String getReparar() {
		return reparar;
	}
	public void setReparar(String reparar) {
		this.reparar = reparar;
	}
	public String getRecoger() {
		return recoger;
	}
	public void setRecoger(String recoger) {
		this.recoger = recoger;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}

