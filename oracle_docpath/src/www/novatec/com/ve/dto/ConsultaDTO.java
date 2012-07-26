package www.novatec.com.ve.dto;

import java.io.Serializable;

final public class ConsultaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static transient ConsultaDTO myIntance;
	
	/*
	 * varibles privadas para la consulta principal
	 * 
	 * argumendtos de busqueda 
	 * 
	 * *numero de cuenta
	 * *anio
	 * *mes
	 * *idType
	 * *id directorio
	 * 
	 * datos de retorno
	 * *numero de cuenta
	 * *mes
	 * *anio
	 * *directorio
	 * *tipo de documento
	 * *banco
	 * *codGenDoc
	 */
	
	private String numeroCuenta;
	private String anio;
	private String mes;
	private String banco;
	private String nombre;
	private String directorio;
	private String idDirectorio;
	private String tipoDocumento;
	private String codGenDoc;
	private String idType;
	private String lenBlob;
	
	/*
	 * Constructos privado para implemetar metodo singleton
	 */
	private ConsultaDTO(){
		
	}
	
	/*
	 * getters y setters
	 */
	
	public String getIdDirectorio() {
		return idDirectorio;
	}

	public void setIdDirectorio(final String idDirectorio) {
		this.idDirectorio = idDirectorio;
	}

	public String getLenBlob() {
		return lenBlob;
	}

	public void setLenBlob(final String lenBlob) {
		this.lenBlob = lenBlob;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(final String idType) {
		this.idType = idType;
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


	public static ConsultaDTO getIntance(){
		myIntance = new ConsultaDTO();
		return myIntance;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(final String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(final String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(final String mes) {
		this.mes = mes;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(final String banco) {
		this.banco = banco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(final String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	

}
