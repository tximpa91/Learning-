package fixPc__.client;




import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.RootPanel;

import fixPc__.client.GreetingService;
import fixPc__.client.GreetingServiceAsync;
import fixPc__.client.Ingresar_Reparacion;
import fixPc__.client.LoginInfo;
import fixPc__.client.ModificarDatos;
import fixPc__.client.ProductosReparacion;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FixPc__ implements EntryPoint {
	private LoginInfo login;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */


	private RootPanel panel_1_1_1;


	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	
	

	/**
	 * This is the entry point method.
	 * @wbp.parser.entryPoint
	 */
	public void onModuleLoad() {
		
	
		panel_1_1_1 = RootPanel.get();
		panel_1_1_1.setSize(String.valueOf(Window.getClientWidth())+"px", String.valueOf(Window.getClientHeight())+"px");
		
		
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Inicio de Sesion");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
	
		
		
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				
			}
		});

		
		final Anchor signInLink = new Anchor("Sign In");
		signInLink.setHTML("");
		
		
		final HorizontalPanel loginPanel = new HorizontalPanel();
		loginPanel.setVisible(false);
		loginPanel.add(signInLink);
		panel_1_1_1.add(loginPanel,100,150);
		
		MenuBar menuBar = new MenuBar(false);
		menuBar.setAnimationEnabled(true);
		menuBar.setAutoOpen(true);
		panel_1_1_1.add(menuBar, 0, 0);
		menuBar.setSize(String.valueOf(Window.getClientWidth())+"px", "33px");
		
		MenuItem mntmBienvenido = new MenuItem("Bienvenido", false, (Command) null);
		menuBar.addItem(mntmBienvenido);
		
		Button btnProductosEnReparacin = new Button("Productos en reparaci\u00F3n");
		btnProductosEnReparacin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				panel_1_1_1.clear();
				ProductosReparacion reparacion = new ProductosReparacion();
				reparacion.onModuleLoad();
				
			}
		});
		panel_1_1_1.add(btnProductosEnReparacin, (Window.getClientWidth()/2), (Window.getClientHeight()/2));
		
		Button btnIngresarReparacin = new Button("Ingresar reparaci\u00F3n");
		btnIngresarReparacin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				panel_1_1_1.clear();
				Ingresar_Reparacion ingr = new Ingresar_Reparacion ();
				ingr.onModuleLoad();
			}
		});
		panel_1_1_1.add(btnIngresarReparacin, (Window.getClientWidth()/2), (Window.getClientHeight()/2)+60);
		btnIngresarReparacin.setSize("157px", "28px");
		
		Button btnModificarDatos = new Button("Modificar datos");
		btnModificarDatos.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				panel_1_1_1.clear();
				ModificarDatos modificar = new ModificarDatos();
				modificar.onModuleLoad();
				
			}
		});
		panel_1_1_1.add(btnModificarDatos, (Window.getClientWidth()/2), (Window.getClientHeight()/2)-60);
		btnModificarDatos.setSize("157px", "28px");
		
		
		
		greetingService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>(){

			@Override
			public void onFailure(Throwable caught) {

				
			}

			@Override
			public void onSuccess(LoginInfo result) {
				login = result;
				if (login.isLoggedIn())
				{
					
				}else
					
				{	
					signInLink.setHref(login.getLoginUrl());
					signInLink.setText("Inicie sesion con una cuenta Google");
					
					dialogVPanel.add(signInLink);
					dialogVPanel.add(closeButton);
					dialogBox.setWidget(dialogVPanel);
					dialogBox.center();
					dialogBox.setVisible(true);
					closeButton.setFocus(true);
			    
			    
				
					
				}
			}
			});

	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void loadLogin(RootPanel panel) {
		 
	}
}
