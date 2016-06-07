package fixPc__.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import fixPc__.client.FixPc__;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;




import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import java.util.*;
public class Ingresar_Reparacion implements EntryPoint{
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	String garantia1="";
	String tipo1="";
	
	String dateString;
	@Override
	public void onModuleLoad() {
			// TODO Auto-generated method stub
		final RootPanel ingresar= RootPanel.get();
		ingresar.setSize(String.valueOf(Window.getClientWidth())+"px", String.valueOf(Window.getClientHeight())+"px");
		
		Label lblNewLabel = new Label("Numero Incidencia");
		ingresar.add(lblNewLabel, 36, 42);
		lblNewLabel.setSize("128px", "16px");
		
		final TextBox incidenciatext = new TextBox();
		ingresar.add(incidenciatext, 36, 64);
		incidenciatext.setSize("99px", "10px");
		
		final TextBox dnitext = new TextBox();
		ingresar.add(dnitext, 36, 114);
		dnitext.setSize("99px", "10px");
		
		Label lblDni = new Label("DNI");
		ingresar.add(lblDni, 36, 92);
		lblDni.setSize("128px", "16px");
		
		Label lblNombre = new Label("Nombre");
		ingresar.add(lblNombre, 36, 142);
		lblNombre.setSize("128px", "16px");
		
		final TextBox nombretext = new TextBox();
		ingresar.add(nombretext, 36, 164);
		nombretext.setSize("99px", "10px");
		
		Label lblApellidos = new Label("Apellidos");
		ingresar.add(lblApellidos, 174, 142);
		lblApellidos.setSize("128px", "16px");
		
		final TextBox apellidos = new TextBox();
		ingresar.add(apellidos, 174, 164);
		apellidos.setSize("118px", "10px");
		
		Label lblEmail = new Label("Tel\u00E9fono");
		ingresar.add(lblEmail, 36, 201);
		lblEmail.setSize("109px", "16px");
		
		final TextBox telefonotext = new TextBox();
		ingresar.add(telefonotext, 36, 223);
		telefonotext.setSize("99px", "10px");
		
		Label lblCorreoElectrnico = new Label("Correo electr\u00F3nico");
		ingresar.add(lblCorreoElectrnico, 174, 201);
		lblCorreoElectrnico.setSize("109px", "16px");
		
		final TextBox emailtext = new TextBox();
		ingresar.add(emailtext, 174, 223);
		emailtext.setSize("182px", "10px");
		
		Label lblLocalidad = new Label("Localidad");
		ingresar.add(lblLocalidad, 36, 260);
		lblLocalidad.setSize("109px", "16px");
		
		final TextBox localidad = new TextBox();
		ingresar.add(localidad, 36, 282);
		localidad.setSize("99px", "10px");
		
		Label lblDireccin = new Label("Codigo Postal");
		ingresar.add(lblDireccin, 174, 260);
		lblDireccin.setSize("109px", "16px");
		
		final TextBox codigopostal = new TextBox();
		ingresar.add(codigopostal, 174, 282);
		codigopostal.setSize("182px", "10px");
		
		Label lblArtculos = new Label("Art\u00EDculo");
		ingresar.add(lblArtculos, 21, 341);
		lblArtculos.setSize("109px", "16px");
		
		Label lblArtculo = new Label("Tipo");
		ingresar.add(lblArtculo, 36, 379);
		lblArtculo.setSize("47px", "16px");
		
		final ListBox tipo = new ListBox();
		tipo.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
			 tipo1 = tipo.getItemText(tipo.getSelectedIndex());
			}
		});
		tipo.addItem("       ");
		tipo.addItem("M\u00F3vil");
		tipo.addItem("Tablet");
		tipo.addItem("Port\u00E1til");
		tipo.addItem("PC");
		ingresar.add(tipo, 119, 375);
		tipo.setSize("88px", "20px");
		
		Label lblModelo = new Label("Modelo");
		ingresar.add(lblModelo, 255, 379);
		lblModelo.setSize("47px", "16px");
		
		final TextBox modelo = new TextBox();
		ingresar.add(modelo, 378, 373);
		modelo.setSize("118px", "10px");
		
		Label lblMarca = new Label("Marca");
		 ingresar.add(lblMarca, 36, 415);
		lblMarca.setSize("47px", "16px");
		
		Label lblNumeroDeSerie = new Label("Numero de serie");
		ingresar.add(lblNumeroDeSerie, 255, 415);
		lblNumeroDeSerie.setSize("111px", "16px");
		
		final TextBox marca = new TextBox();
		ingresar.add(marca, 119, 409);
		marca.setSize("99px", "10px");
		
		final TextBox serie = new TextBox();
		ingresar.add(serie, 378, 409);
		serie.setSize("141px", "10px");
		
		Label lblSistemaOperativo = new Label("Sistema Operativo");
		ingresar.add(lblSistemaOperativo, 255, 449);
		lblSistemaOperativo.setSize("111px", "16px");
		
		final TextBox OS = new TextBox();
		ingresar.add(OS, 378, 443);
		OS.setSize("118px", "10px");
		
		final DateBox fecha = new DateBox();
		fecha.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				
				 //fecha_aux = event.getValue();
			 
				// dateString = DateTimeFormat.getFormat("MM/dd/yyyy").format(fecha_aux);
				
			}
		});
		ingresar.add(fecha, 119, 443);
		fecha.setSize("99px", "10px");
		
		Label lblFecha = new Label("Fecha");
		ingresar.add(lblFecha, 36, 449);
		lblFecha.setSize("47px", "16px");
		
		Label lblGaranta = new Label("Garant\u00EDa");
		ingresar.add(lblGaranta, 36, 482);
		lblGaranta.setSize("47px", "16px");
		
		final ListBox garantia = new ListBox();
		garantia.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
			 garantia1 = garantia.getItemText(garantia.getSelectedIndex());
			}
		});
		garantia.addItem("No");
		garantia.addItem("Si");
		garantia.addItem("     ");
		garantia.setSelectedIndex(2);
		ingresar.add(garantia, 119, 478);
		garantia.setSize("88px", "20px");
		
		Label lblPresupuesto = new Label("Presupuesto");
		ingresar.add(lblPresupuesto, 255, 482);
		lblPresupuesto.setSize("47px", "16px");
		
		final TextBox presupuesto = new TextBox();
		ingresar.add(presupuesto, 378, 482);
		presupuesto.setSize("78px", "10px");
		
		Label label = new Label("\u20AC");
		ingresar.add(label, 475, 488);
		label.setSize("111px", "16px");
		final TextBox descripcion = new TextBox();
		ingresar.add(descripcion, 36, 571);
		descripcion.setSize("482px", "91px");
		
		MenuBar menuBar = new MenuBar(false);
		ingresar.add(menuBar, 0, 0);
		menuBar.setSize(String.valueOf(Window.getClientWidth())+"px", "33px");
		
		MenuItem mntmIngresarReparacin = new MenuItem("Ingresar reparaci\u00F3n", false, (Command) null);
		menuBar.addItem(mntmIngresarReparacin);
		final TextBox producto = new TextBox();
		ingresar.add(producto, 119, 335);
		producto.setSize("148px", "10px");
		
		final TextBox observaciones = new TextBox();
		ingresar.add(observaciones, 36, 736);
		observaciones.setSize("482px", "91px");
		Button btnOk = new Button("OK");
		btnOk.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				if(apellidos.getText().isEmpty()||codigopostal.getText().isEmpty()||dnitext.getText().isEmpty()||emailtext.getText().isEmpty()||localidad.getText().isEmpty()
						|| marca.getText().isEmpty()||modelo.getText().isEmpty()||nombretext.getText().isEmpty()||incidenciatext.getText().isEmpty()||telefonotext.getText().isEmpty()
						||presupuesto.getText().isEmpty()||OS.getText().isEmpty()||telefonotext.getText().isEmpty() )
				{
					Window.alert("Rellene todos los campos");
					
				}
				else{
					
					
					
				    
				   
					Reparacion rep = new Reparacion(incidenciatext.getText(), ""  , tipo.getItemText(tipo.getSelectedIndex()),producto.getText(), marca.getText(), modelo.getText(), serie.getText(), OS.getText(), descripcion.getText(), observaciones.getText(), garantia1, Float.valueOf(presupuesto.getText()), "Si", "No ",
							 dnitext.getText(), nombretext.getText(), apellidos.getText(),  localidad.getText(), Integer.valueOf(codigopostal.getText()), emailtext.getText());
					
					greetingService.insertar(rep, new AsyncCallback<String>() {
					
						@Override
						public void onSuccess(String result) {
							// TODO Auto-generated method stub
							Window.alert(result);
						}
					
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert(caught.getMessage());
						}
					});
				}
				
				
				
			}
		});
		ingresar.add(btnOk, 108, 866);
		btnOk.setSize("68px", "22px");
		
		Button button = new Button("OK");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ingresar.clear();
				FixPc__ pc= new FixPc__ ();
				pc.onModuleLoad();
				
			}
		});
		button.setText("Atr\u00E1s");
		ingresar.add(button, 263, 866);
		button.setSize("68px", "22px");
		
		
		
		Label lblDescripcinDeLa = new Label("Descripci\u00F3n de la aver\u00EDa");
		ingresar.add(lblDescripcinDeLa, 36, 538);
		lblDescripcinDeLa.setSize("158px", "16px");
		
		Label lblObservaciones = new Label("Observaciones");
		ingresar.add(lblObservaciones, 36, 706);
		lblObservaciones.setSize("158px", "16px");
		

		
		
		
		
	}
}
