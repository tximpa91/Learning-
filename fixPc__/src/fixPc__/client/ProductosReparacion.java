package fixPc__.client;





import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;


import fixPc__.client.FixPc__;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class ProductosReparacion implements EntryPoint {
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private Reparacion actual= null;
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
		final RootPanel modificar = RootPanel.get();
		
		final CheckBox pendiente = new CheckBox("Pendiente");
		modificar.add(pendiente, 83, 114);
		final CheckBox reparado = new CheckBox("Reparado");
		modificar.add(reparado, 83, 173);
		final Button enviar = new Button("Recoger");
		modificar.add(enviar, 286, 173);
		enviar.setSize("150px", "22px");
		final ListBox productos = new ListBox();
		productos.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				
				greetingService.buscar(productos.getItemText(productos.getSelectedIndex()), new  AsyncCallback<Reparacion>() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void onSuccess(Reparacion result) {
						// TODO Auto-generated method stub
						actual=result;
						if(actual.getReparar().equalsIgnoreCase("Si"))
						{	pendiente.setChecked(true);
							reparado.setChecked(false);
							enviar.setEnabled(false);
						}
						else{
							pendiente.setChecked(false);
							reparado.setChecked(true);
							enviar.setEnabled(true);
							}
						if(actual.getRecoger().equalsIgnoreCase("si"))
						{	pendiente.setChecked(false);
							reparado.setChecked(true);
						}
						else{ reparado.setChecked(false);
							  pendiente.setChecked(true);
							  
							}
						
							
							
							
							
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			
			}
		});
		productos.addItem(" ");
		productos.setDirectionEstimator(true);
		modificar.add(productos, 83, 62);
		productos.setSize("150px", "10");
		
		Label lblProducto = new Label("Producto:");
		modificar.add(lblProducto, 22, 62);
		lblProducto.setSize("55px", "22px");
		
		Label lblEstado = new Label("Estado:");
		modificar.add(lblEstado, 22, 114);
		lblEstado.setSize("55px", "29px");
	
		
		
		
		MenuBar menuBar = new MenuBar(false);
		modificar.add(menuBar, 0, 0);
		
		MenuItem mntmNewItem = new MenuItem("Lista de Productos", false, new Command() {
			public void execute() {
			}
		});
		mntmNewItem.setSize(String.valueOf(Window.getClientWidth())+"px","10px");
		
		menuBar.addItem(mntmNewItem);
		
		
		
		Button btnNewButton_1 = new Button("Reparar");
		btnNewButton_1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				actual.setReparar("no");
				actual.setRecoger("Si");
				
				greetingService.modificar(actual, new AsyncCallback<String>() {
					
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
		});
		modificar.add(btnNewButton_1, 286, 114);
		btnNewButton_1.setSize("150px", "22px");
		
		Button Cancelar = new Button("Cancelar");
		Cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				greetingService.borrar(new AsyncCallback<String[]>() {
					
					@Override
					public void onSuccess(String[] result) {
						// TODO Auto-generated method stub
						Window.alert("positivo");
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		modificar.add(Cancelar, 576, 306);
		
		Button btnNewButton = new Button("Atras");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				modificar.clear();
				FixPc__ fix = new FixPc__();
				fix.onModuleLoad();
			}
		});
		
		modificar.add(btnNewButton, 342, 249);
		
		greetingService.consulta(new AsyncCallback<String[]>() {
			
			@Override
			public void onSuccess(String[] result) {
				// TODO Auto-generated method stub
				for (int i =0 ; i< result.length; i++)
				{
					productos.addItem(result[i]);
					
					
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert(caught.getMessage());
			}
		});
	}
}
