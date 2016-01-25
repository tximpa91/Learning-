package PECL2;

import java.util.ArrayList;

public class Item {
	
	private String nombre;
	private String value;
	private String tipo;
	private int numero=0;
	private ArrayList<Item> parametros;
	
	

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        
    }

    public void setParametros(ArrayList<Item> parametros) {
        this.parametros = parametros;
        
    }
    
    public void setNumero(int numero) {
        this.numero = this.numero +numero;
    }

    public Item(String nombre, String value, String tipo) {
        this.nombre = nombre;
        this.value = value;
        this.tipo = tipo;
        
    }
    
    public Item (String nombre, String value, String tipo, ArrayList<Item> lista)
    {
    	this.nombre = nombre;
        this.value = value;
        this.tipo = tipo;
        this.parametros=lista;
    	
    	
    }

    public Item(String nombre) {
        this.nombre = nombre;
    }
    
    
	
	
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getValue()
	{
		return this.value;	
	}
	
	public String getTipo()
	{
		return this.tipo;
	}
	
	public ArrayList getParametros ()
	{
		return this.parametros;
	}

	public int getNumero()
	{
		return this.numero;
	}
}
