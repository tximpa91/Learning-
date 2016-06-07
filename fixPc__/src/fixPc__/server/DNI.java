package fixPc__.server;

public class DNI {
	public static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	public static boolean checkDNI (String DNI)
	{
		
		int dni = Integer.valueOf(DNI.substring(0, 7));
		String letra = Character.toString(NIF_STRING_ASOCIATION.charAt(dni % 23));
		
		if(DNI.endsWith(letra))
			return true;
		else
			return false;
		
		
				
	
	}

}
