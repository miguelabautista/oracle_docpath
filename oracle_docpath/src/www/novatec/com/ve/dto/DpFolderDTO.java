package www.novatec.com.ve.dto;

/*
 * Clase para la construcion del arbol de directorio por permisos de
 * usuarios
 */

final public class DpFolderDTO {
	
	/*
	 * variables privadas para la consulta
	 */
	
	private int idFolder;
	private int idParent;
	private String descFolder;
	private String toolTip;
	
	private static DpFolderDTO myIntance;
	
	/*
	 * Constructor privado para implementar 
	 * metodo singleton
	 */
	private DpFolderDTO(){}
	
	public static DpFolderDTO  getIntance(){
		myIntance =  new DpFolderDTO();
		return myIntance;
	}
	
	/*
	 * Getters y Setters
	 */
	public int getIdFolder() {
		return idFolder;
	}
	public void setIdFolder(final int idFolder) {
		this.idFolder = idFolder;
	}
	public int getIdParent() {
		return idParent;
	}
	public void setIdParent(final int idParent) {
		this.idParent = idParent;
	}
	public String getDescFolder() {
		return descFolder;
	}
	public void setDescFolder(final String descFolder) {
		this.descFolder = descFolder;
	}
	public String getToolTip() {
		return toolTip;
	}
	public void setToolTip(final String toolTip) {
		this.toolTip = toolTip;
	}
	
	

}
