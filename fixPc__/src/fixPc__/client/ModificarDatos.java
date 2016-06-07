package fixPc__.client;




import com.google.appengine.api.search.query.ExpressionParser.name_return;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import fixPc__.client.FixPc__;
import sun.security.util.PendingException;

import com.google.gwt.user.client.ui.CheckBox;

public class ModificarDatos implements EntryPoint {
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private Reparacion reparacion=null;
	
	private String pendiente1, reparado1;
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		final RootPanel online = RootPanel.get();
		final TextBox incidencia = new TextBox();
		online.add(incidencia, 174, 66);
		incidencia.setSize("95px", "10px");
		
		
		Button buscar = new Button("Buscar");
		final TextBox nombre = new TextBox();
		online.add(nombre, 36, 164);
		nombre.setSize("99px", "10px");
		
		online.add(buscar, 316, 60);
		buscar.setSize("56px", "28px");
		
		MenuBar menuBar = new MenuBar(false);
		online.add(menuBar, 0, 0);
		
		MenuItem mntmModificarDatos = new MenuItem("Modificar datos", false, (Command) null);
		menuBar.addItem(mntmModificarDatos);
		
		
		Label lblNumeroDe = new Label("Numero de indicencia");
		online.add(lblNumeroDe, 17, 66);
		lblNumeroDe.setSize("128px", "16px");
		
		Label lblNombre = new Label("Nombre");
		online.add(lblNombre, 36, 142);
		lblNombre.setSize("128px", "16px");
		
		final CheckBox reparado = new CheckBox("Reparado");
		online.add(reparado, 624, 415);
		reparado.setSize("170px", "20px");
		
		
		
	
		
		final TextBox apellidos = new TextBox();
		online.add(apellidos, 174, 164);
		apellidos.setSize("118px", "10px");
		
		Label lblEmail = new Label("Tel\u00E9fono");
		online.add(lblEmail, 36, 201);
		lblEmail.setSize("109px", "16px");
		
		TextBox tlf = new TextBox();
		online.add(tlf, 36, 223);
		tlf.setSize("99px", "10px");
		
		Label textoper = new Label("Correo electr\u00F3nico");
		online.add(textoper, 174, 201);
		textoper.setSize("109px", "16px");
		
		final TextBox email = new TextBox();
		online.add(email, 174, 223);
		email.setSize("182px", "10px");
		
		Label lblLocalidad = new Label("Direcci\u00F3n");
		online.add(lblLocalidad, 36, 260);
		lblLocalidad.setSize("109px", "16px");
		
		final TextBox direccion = new TextBox();
		online.add(direccion, 36, 282);
		direccion.setSize("99px", "10px");
		
		Label lblDireccin = new Label("Codigo Postal");
		online.add(lblDireccin, 174, 260);
		lblDireccin.setSize("109px", "16px");
		
		final TextBox codigo_postal = new TextBox();
		online.add(codigo_postal, 174, 282);
		codigo_postal.setSize("182px", "10px");
		
		Label lblArtculos = new Label("Art\u00EDculos");
		online.add(lblArtculos, 10, 331);
		lblArtculos.setSize("109px", "16px");
		
		Label numero_Incidencia = new Label("Numero de indicencia");
		online.add(numero_Incidencia, 30, 363);
		numero_Incidencia.setSize("68px", "16px");
		
		Label texjjfj = new Label("Modelo");
		online.add(texjjfj, 341, 379);
		texjjfj.setSize("47px", "16px");
		
		final TextBox modelo = new TextBox();
		online.add(modelo, 401, 373);
		modelo.setSize("118px", "10px");
		
		Label lblMarca = new Label("Producto");
		online.add(lblMarca, 30, 415);
		lblMarca.setSize("47px", "16px");
		
		Label lblNumeroDeSerie = new Label("Numero de serie");
		online.add(lblNumeroDeSerie, 292, 449);
		lblNumeroDeSerie.setSize("111px", "16px");
		
		final TextBox producto = new TextBox();
		online.add(producto, 119, 409);
		producto.setSize("99px", "10px");
		
		
		
		final TextBox sistOp = new TextBox();
		online.add(sistOp, 401, 476);
		sistOp.setSize("118px", "10px");
		
		Label lblSistemaOperativo = new Label("Sistema Operativo");
		online.add(lblSistemaOperativo, 277, 482);
		lblSistemaOperativo.setSize("111px", "16px");
		
		final TextBox num_serie = new TextBox();
		online.add(num_serie, 401, 443);
		num_serie.setSize("133px", "10px");
		final TextBox inicidencia2 = new TextBox();
		online.add(inicidencia2, 119, 371);
		inicidencia2.setSize("128px", "10px");
		final DateBox fecha = new DateBox();
		online.add(fecha, 119, 476);
		fecha.setSize("128px", "10px");
		
		Label lblFecha = new Label("Fecha");
		online.add(lblFecha, 30, 482);
		lblFecha.setSize("47px", "16px");
		
		Label lblGaranta = new Label("Garant\u00EDa");
		online.add(lblGaranta, 30, 517);
		lblGaranta.setSize("47px", "16px");
		
		final ListBox garantia = new ListBox();
		garantia.addItem("No");
		garantia.addItem("Si");
		garantia.addItem("     ");
		garantia.setSelectedIndex(2);
		online.add(garantia, 119, 513);
		garantia.setSize("88px", "20px");
		
		Label lblPresupuesto = new Label("Presupuesto");
		online.add(lblPresupuesto, 316, 517);
		lblPresupuesto.setSize("47px", "16px");
		
		final TextBox presupuesto = new TextBox();
		online.add(presupuesto, 403, 511);
		presupuesto.setSize("78px", "10px");
		
		final TextBox marca = new TextBox();
		online.add(marca, 119, 443);
		marca.setSize("99px", "10px");
		
		
		Label label = new Label("\u20AC");
		online.add(label, 499, 517);
		label.setSize("111px", "16px");
		
		Label lblTipo = new Label("Tipo");
		online.add(lblTipo, 351, 415);
		lblTipo.setSize("31px", "16px");
		final ListBox tipoListBox = new ListBox();
		tipoListBox.addItem(" ");
		tipoListBox.addItem("Movil");
		tipoListBox.addItem("Tablet");
		tipoListBox.addItem("Port\u00E1til");
		tipoListBox.addItem("PC");
		tipoListBox.addItem("                 ");
		online.add(tipoListBox, 401, 415);
		tipoListBox.setSize("88px", "20px");
		Button btnOk = new Button("OK");
		final CheckBox pendiente = new CheckBox("Pendiente");
		online.add(pendiente, 624, 379);
		btnOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
	
				if(pendiente.isChecked())
					pendiente1="Si";
				else pendiente1="No";
				
				if(reparado.isChecked())
					reparado1="Si";
				else reparado1="No";
						
			     
				
				Reparacion rep = new Reparacion(incidencia.getText(),"", tipoListBox.getItemText(tipoListBox.getSelectedIndex()), producto.getText(), marca.getText(), modelo.getText(), num_serie.getText(), sistOp.getText(),"", "", garantia.getItemText(garantia.getSelectedIndex()), Float.valueOf(presupuesto.getText()), pendiente1,reparado1, reparacion.getDni(), nombre.getText(), apellidos.getText(), direccion.getText(), Integer.valueOf(codigo_postal.getText()), email.getText());
				greetingService.modificar(rep, new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						Window.alert("La transaccion ha sido exitosa");
						
						nombre.setText("");
						 apellidos.setText("");
						 email.setText("");
						 direccion.setText("");
						 codigo_postal.setText("");
						 inicidencia2.setText("");
						 inicidencia2.setEnabled(false);
						 producto.setText("");
						 tipoListBox.setItemText(0, "");
						 marca.setText("");
						 num_serie.setText("");
				
						 garantia.setItemText(0, "");
						 sistOp.setText("");
						 presupuesto.setText(String.valueOf("0"));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Error");
					}
				});
				
				
				
			}
		});
		online.add(btnOk, 190, 566);
		btnOk.setSize("68px", "22px");
		
		Button button = new Button("Atrás");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				online.clear();
				FixPc__ inicio = new FixPc__();
				inicio.onModuleLoad();
				
			}
		});
		button.setText("Atr\u00E1s");
		online.add(button, 316, 566);
		button.setSize("68px", "22px");
		
		
		Label lblMarca_1 = new Label("Marca");
		online.add(lblMarca_1, 30, 449);
		lblMarca_1.setSize("47px", "16px");
		
		
		
		
		
		
	
		buscar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(incidencia.getText().isEmpty())
				{
					Window.alert("Rellene el campo de incidencia");
				}
				else
				{
					greetingService.buscar(incidencia.getText(),new AsyncCallback<Reparacion>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(Reparacion result) {
							// TODO Auto-generated method stub
							 Window.alert("Sus datos se estan cargando");
							 reparacion=result;
							 nombre.setText(reparacion.getNombre());
							 apellidos.setText(reparacion.getApellidos());
							 email.setText(reparacion.getEmail());
							 direccion.setText(reparacion.getLocalizacion());
							 codigo_postal.setText(reparacion.getCodigo());
							 inicidencia2.setText(reparacion.getCodigo());
							 inicidencia2.setEnabled(false);
							 producto.setText(reparacion.getProducto());
							 tipoListBox.setItemText(0, reparacion.getTipo());
							 marca.setText(reparacion.getMarca());
							 num_serie.setText(reparacion.getNum_serie());
							 
							 garantia.setItemText(0, reparacion.getGarantia());
							 sistOp.setText(reparacion.getSo());
							 presupuesto.setText(String.valueOf(reparacion.getPresupuesto()));
							 modelo.setText(reparacion.getModelo());
							 
							 if(reparacion.getReparar().equalsIgnoreCase("Si"))
								 pendiente.setChecked(true);
							 else pendiente.setChecked(false);
							 
							 if (reparacion.getRecoger().equalsIgnoreCase("Si"))
								 reparado.setChecked(true);
							 else reparado.setChecked(false);
							 
							 
							 
							 
							 
							
						}} );
					
					
				}
				
			}
			
			
		
		});

		
		
	}
	}
