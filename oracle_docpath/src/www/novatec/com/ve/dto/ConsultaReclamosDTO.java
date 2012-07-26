package www.novatec.com.ve.dto;

import java.io.Serializable;


final public class ConsultaReclamosDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numTarjeta;
	private String numAfiliado;
	private String numAutorizacion;
	private String trace;
	private String fechaTransaccion;
	private String montoTransaccion;
	private String idDirectorio;
	private String directorio;
	private String idType;
	private byte[] file;
	private String codGenDoc;
	private String tipoDocumento;
	private String lenBlob;
	private String fechaCreacionRegistro;
	
		
	public String getFechaCreacionRegistro() {
		return fechaCreacionRegistro;
	}

	public void setFechaCreacionRegistro(String fechaCreacionRegistro) {
		this.fechaCreacionRegistro = fechaCreacionRegistro;
	}

	private static ConsultaReclamosDTO myInstance;
	
	private ConsultaReclamosDTO(){
		this.numAfiliado="";
		this.numAutorizacion="";
		this.numTarjeta="";
		this.fechaTransaccion="";
		this.trace="";
		this.montoTransaccion="";
		this.idDirectorio="";
		this.idType="";
	}
	
	public static ConsultaReclamosDTO getInstance(){
		myInstance =  new ConsultaReclamosDTO();
		return myInstance;
	}
	
	public String getLenBlob() {
		return lenBlob;
	}

	public void setLenBlob(final String lenBlob) {
		this.lenBlob = lenBlob;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(final String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDirectorio() {
		return directorio;
	}

	public void setDirectorio(final String directorio) {
		this.directorio = directorio;
	}

	public String getCodGenDoc() {
		return codGenDoc;
	}

	public void setCodGenDoc(final String codGenDoc) {
		this.codGenDoc = codGenDoc;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(final byte[] file) {
		this.file = file;
	}

	public String getIdType() {
		return idType;
	}
	public void setIdType(final String idType) {
		this.idType = idType;
	}
	public String getIdDirectorio() {
		return idDirectorio;
	}
	public void setIdDirectorio(final String idDirectorio) {
		this.idDirectorio = idDirectorio;
	}
	public String getTrace() {
		return trace;
	}
	public void setTrace(final String trace) {
		this.trace = trace;
	}
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(final String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public String getMontoTransaccion() {
		return montoTransaccion;
	}
	public void setMontoTransaccion(final String montoTransaccion) {
		this.montoTransaccion = montoTransaccion;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(final String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getNumAfiliado() {
		return numAfiliado;
	}

	public void setNumAfiliado(final String numAfiliado) {
		this.numAfiliado = numAfiliado;
	}

	public String getNumAutorizacion() {
		return numAutorizacion;
	}

	public void setNumAutorizacion(final String numAutorizacion) {
		this.numAutorizacion = numAutorizacion;
	}
	
	
	
	

}
