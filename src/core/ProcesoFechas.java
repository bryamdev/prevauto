package core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcesoFechas {
	
	public static Date convertToDate(String fecha) {
		
		Date fechaDate = null;
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			fechaDate = formateador.parse(fecha);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return fechaDate;
		
	}
	
	public static int diferenciaDias(Date fecha1, Date fecha2) {
		
		int dias = (int) ((fecha2.getTime()-fecha1.getTime())/86400000);
		
		return dias;
	}

}
