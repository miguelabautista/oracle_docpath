package www.novatec.com.ve.dto;

/*
 * Clase para manejar los datos de los indices 
 */

final public class DpIndexDTO {
	
	private String idIdx;
	private String title;
	private static DpIndexDTO myIntance;
	
	/*
	 * Constructor privado para implementar el metodo singleton
	 */
	private DpIndexDTO(){}
	
	public static DpIndexDTO getIntance(){
		myIntance =  new DpIndexDTO();
		return myIntance;
	}
	
	public String getIdIdx() {
		return idIdx;
	}
	public void setIdIdx(final String idIdx) {
		this.idIdx = idIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}

}
