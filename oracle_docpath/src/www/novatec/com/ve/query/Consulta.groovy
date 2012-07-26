package www.novatec.com.ve.query

import java.sql.Connection
import java.sql.DriverManager
import javax.sql.DataSource
import groovy.sql.Sql
import oracle.jdbc.driver.OracleTypes
import www.novatec.com.ve.dto.*



class Consulta {
    
    static def driver = oracle.jdbc.driver.OracleDriver	
	
	static FileRead direccion = new FileRead()	
	
	static Connection conn = DriverManager.getConnection(direccion.getUrl());
		
	static Sql sql = new Sql(conn)
	
    
    private Consulta(){  
		   
    }
    
    private static class SingletonHolder {

        private static final Consulta consulta = new Consulta();
    }
    
    public static Consulta getInstance() {
        return SingletonHolder.consulta;
    }
    
    static List<DpFolderDTO> folderPadres(String url){
        def lista = []
        
        List<Integer> idPadres = []
        
        def query1 = """SELECT ID_PARENT AS id_parent
		FROM DP_DOC_FOLDERS
		WHERE  DELETED='N' 
		GROUP BY ID_PARENT"""
        
        sql.eachRow(query1){
            idPadres << it.ID_PARENT
        }        
        
        DpFolderDTO folder = null 
        
        idPadres.each{ id ->       
            sql.eachRow("""SELECT
		ID_FOLDER AS id_folder, 
		ID_PARENT AS id_parent,
		DESC_FOLDER AS desc_folder,
		TOOLTIP AS tooltip
		FROM DP_DOC_FOLDERS
		WHERE DELETED='N'
		AND ID_FOLDER=${id}"""){
                folder = new DpFolderDTO()
            
                folder.setIdFolder(it.ID_FOLDER as int)
                folder.setIdParent(it.ID_PARENT as int)
                folder.setDescFolder(it.DESC_FOLDER)
                folder.setToolTip(it.TOOLTIP)
            
                lista << folder
            }
        }
        return lista       
    }

    static List<DpFolderDTO> consultaFolder(String nameUser){
        def lista = []
        
        String user = null;
        String domain = null;
        Scanner scan = new Scanner(nameUser);
        scan.useDelimiter("@");
        while (scan.hasNext()) {
            try {
                user = scan.next();
            } catch (Exception e) {
            }
            if (!scan.hasNext("@")) {
                try {
                    domain = scan.next();
                } catch (Exception e) {
                }
            }
        }
        def query = null
        
        DpFolderDTO folder = null
        String agregar = null
        if(domain != null){
            query = """SELECT DISTINCT f.ID_FOLDER AS id_folder, f.ID_PARENT AS id_parent,
		f.DESC_FOLDER AS desc_folder, f.TOOLTIP AS tooltip 
		FROM DP_USERS u INNER JOIN DP_USER_ROLES r ON u.ID_USER=r.ID_USER
		INNER JOIN DP_PERMISSIONS_FOLDERS p ON p.ID_ROLE=r.ID_ROLE
		INNER JOIN DP_DOC_FOLDERS f ON f.ID_FOLDER = p.ID_FOLDER 
		WHERE f.DELETED='N'
		AND u.DELETED='N' 
		AND u.name= """ + "'" +user + "' " + "AND u.domain= " + "'" + domain + "' " + 
                " ORDER BY f.DESC_FOLDER ASC "
        }else{
            query = """SELECT DISTINCT f.ID_FOLDER AS id_folder, f.ID_PARENT AS id_parent,
		f.DESC_FOLDER AS desc_folder, f.TOOLTIP AS tooltip 
		FROM DP_USERS u INNER JOIN DP_USER_ROLES r ON u.ID_USER=r.ID_USER
		INNER JOIN DP_PERMISSIONS_FOLDERS p ON p.ID_ROLE=r.ID_ROLE
		INNER JOIN DP_DOC_FOLDERS f ON f.ID_FOLDER = p.ID_FOLDER 
		WHERE f.DELETED='N'
		AND u.DELETED='N' 
		AND u.name= """ + "'" +user + "' " + "ORDER BY f.DESC_FOLDER ASC "
        }
        println query
        sql.eachRow(query){
            folder = new DpFolderDTO()
            
            folder.setIdFolder(it.ID_FOLDER as int)
            folder.setIdParent(it.ID_PARENT as int)
            folder.setDescFolder(it.DESC_FOLDER)
            folder.setToolTip(it.TOOLTIP)
            
            lista << folder           
        }
        return lista
    }
    
    static List<DpIndexDTO> consultaIndex(String idType){
        List<DpIndexDTO> dpIndexDTOs = new ArrayList<DpIndexDTO>();
        
        DpIndexDTO dpIndexDTO
        //DESC_FOLDER
        dpIndexDTO = DpIndexDTO.getIntance()
        dpIndexDTO.setIdIdx("DESC_FOLDER");
        dpIndexDTO.setTitle("Carpeta");
        dpIndexDTOs.add(dpIndexDTO);
        //DESC_TYPE
        dpIndexDTO = DpIndexDTO.getIntance();
        dpIndexDTO.setIdIdx("DESC_TYPE");
        dpIndexDTO.setTitle("Tipo de Documento");
        dpIndexDTOs.add(dpIndexDTO);
                
        sql.eachRow("""SELECT TITLE AS titulo, ID_IDX AS id 
		    FROM DP_INDEX WHERE ID_TYPE=${idType} AND VISIBLE='1' 
                    ORDER BY ALIAS ASC"""){
            dpIndexDTO = DpIndexDTO.getIntance();
            dpIndexDTO.setIdIdx("dpidx"+it.id);
            dpIndexDTO.setTitle(it.titulo);
            dpIndexDTOs.add(dpIndexDTO);
        }
        return dpIndexDTOs
    }
    
    //TODO Terminar
    static List<ConsultaDTO> consulta(ConsultaDTO consultaDatosDTO){
      
       List<ConsultaDTO> consultaDTOs = new ArrayList<ConsultaDTO>();
        
        def cod_genDoc = []		
		
		
		sql.eachRow("SELECT datos.COD_GENDOC AS cod_gendoc FROM N_DP_GENDOCS_"
			+ consultaDatosDTO.getIdType() + " datos " +
					"WHERE datos.ID_FOLDER = " + consultaDatosDTO.getIdDirectorio() +
					" AND datos.NUMCTA = '" + consultaDatosDTO.getNumeroCuenta() +
					"' AND datos.MES1 = '" + consultaDatosDTO.getMes() + "' " +
					" AND datos.ANO1 = '" + consultaDatosDTO.getAnio() + "'"){
			cod_genDoc << it.COD_GENDOC
		}
					
		ConsultaDTO consultaDTO;
					
		cod_genDoc.each{ valor ->
            sql.eachRow("SELECT datos.COD_GENDOC AS cod_gendoc " +
                        ",datos.DESC_FOLDER AS desc_folder " +
                        ",datos.ID_FOLDER AS id_folder " +
                        ",datos.NUMCTA AS numero_cuenta " +
                        ",datos.ANO1 AS anio " +
                        ",datos.MES1 AS mes " +
                        ",datos.COD_BANCO AS cod_banco " +
                        ",types.DESC_TYPE AS desc_type " +
                        ",datos.NOMBRE AS nombre " +
                        ",datos.LEN_BLOB AS len " +
                        " FROM V_DP_GENDOCS_" + consultaDatosDTO.getIdType() + " datos " +
                        " INNER JOIN DP_DOC_TYPES types ON datos.ID_TYPE=types.ID_TYPE WHERE datos.COD_GENDOC = " + valor + " AND datos.DELETED = 'N' "){
                consultaDTO = ConsultaDTO.getIntance();
                consultaDTO.setNombre(it.nombre);
                consultaDTO.setAnio(it.anio);
                consultaDTO.setBanco(it.cod_banco);
                consultaDTO.setDirectorio(it.desc_folder);
                consultaDTO.setIdDirectorio(it.id_folder as String);
                consultaDTO.setMes(it.mes);
                consultaDTO.setNumeroCuenta(it.numero_cuenta);
                consultaDTO.setTipoDocumento(it.desc_type);
                consultaDTO.setCodGenDoc(it.cod_gendoc as String);
                consultaDTO.setLenBlob(it.len as String);
                consultaDTOs.add(consultaDTO)
            }
            
        }
		return consultaDTOs
    }  
	static List<ConsultaReclamosDTO> consultaReclamos(String url,ConsultaReclamosDTO consultaDatosDTO){
		
		
		ConsultaReclamosDTO consultaDTO;
		List<ConsultaReclamosDTO> consultaDTOs = new ArrayList<ConsultaReclamosDTO>();
		def lista = []
		
		def numTarjeta = consultaDatosDTO.getNumTarjeta()? " AND datos.NUMT = " + "'" + consultaDatosDTO.getNumTarjeta() + "' ":""
		def numAfiliado = consultaDatosDTO.getNumAfiliado()?" AND datos.NUMAFI = " + "'" +consultaDatosDTO.getNumAfiliado()+ "' ":""
		def numAutorizacion = consultaDatosDTO.getNumAutorizacion()? " AND datos.NUMAUTO = " + "'"+consultaDatosDTO.getNumAutorizacion()+ "' " : ""
		def trace = consultaDatosDTO.getTrace()?" AND datos.TRACE = " + "'"+consultaDatosDTO.getTrace()+ "'" :""
		def fechaTransaccion = consultaDatosDTO.getFechaTransaccion()? " AND datos.FECHATRAN = " + "'"+consultaDatosDTO.getFechaTransaccion()  + "' ":""
		def monto = consultaDatosDTO.getMontoTransaccion()? " AND datos.MONTO = " + "'"+consultaDatosDTO.getMontoTransaccion()+"' ":""
		def fechaCreacion = consultaDatosDTO.getFechaCreacionRegistro()?" AND datos.FECHAREGISTRO = " + "'"+consultaDatosDTO.getFechaCreacionRegistro()+ "'":""
		def idDirectorio = consultaDatosDTO.getIdDirectorio()?" AND datos.ID_FOLDER = " + "'"+ consultaDatosDTO.getIdDirectorio() + "'":""
			
		def query = "SELECT datos.COD_GENDOC AS cod_gendoc " +
		"FROM N_DP_GENDOCS_" + consultaDatosDTO.getIdType() + " datos " +
		"WHERE datos.ID_TYPE = 2 " + idDirectorio + numTarjeta +
		numAfiliado + numAutorizacion +	trace + fechaTransaccion + monto + fechaCreacion
		
		sql.eachRow(query){ valor ->
			lista << valor.COD_GENDOC
		}
		
		lista.each{ valor ->
			sql.eachRow("SELECT " +
		"datos.COD_GENDOC AS cod_gendoc" +
		",datos.DESC_FOLDER AS desc_folder" +
		",datos.ID_FOLDER AS id_folder" +
		",datos.NUMT AS numero_tarjeta" +
		",datos.NUMAFI AS numero_afiliado" +
		",datos.NUMAUTO AS numero_autorizacion" +
		",datos.TRACE AS trace" +
		",datos.FECHATRAN AS fecha_transaccion" +
		",datos.MONTO AS monto" +
		",datos.FECHAREGISTRO AS fecha_registro" +
		",types.DESC_TYPE AS desc_type" +
		",datos.LEN_BLOB AS len " +
		"FROM V_DP_GENDOCS_2" + " datos " +
		"INNER JOIN DP_DOC_TYPES types " +
		"ON datos.ID_TYPE=types.ID_TYPE " +
		"WHERE datos.COD_GENDOC =  " + valor +
		"AND datos.DELETED = 'N' "){
				consultaDTO = ConsultaReclamosDTO.getInstance();
					consultaDTO.setNumTarjeta(String.valueOf(it.numero_tarjeta))
					consultaDTO.setNumAfiliado(it.numero_afiliado)
					consultaDTO.setNumAutorizacion(it.numero_autorizacion)
					consultaDTO.setTrace(it.trace)
					consultaDTO.setFechaTransaccion(it.fecha_transaccion)
					consultaDTO.setMontoTransaccion(it.monto)
					consultaDTO.setFechaCreacionRegistro(it.fecha_registro)
					consultaDTO.setLenBlob(it.len as String)
					consultaDTO.setCodGenDoc(it.cod_gendoc as String)
					consultaDTO.setDirectorio(it.desc_folder)
					consultaDTO.setIdDirectorio(it.id_folder as String)
					consultaDTO.setTipoDocumento(it.desc_type)
					consultaDTOs.add(consultaDTO)
			}
		}
		return consultaDTOs
		
	}
	
    static List<ConsultaDTO> consultaWebServices(ConsultaDTO consultaDatosDTO){
        
        
        ConsultaDTO consultaDTO;
        List<ConsultaDTO> consultaDTOs = new ArrayList<ConsultaDTO>();
        def lista = []
        
        def query = "SELECT datos.COD_GENDOC AS cod_gendoc " +
        "FROM N_DP_GENDOCS_" + consultaDatosDTO.getIdType() + " datos " + 
        "WHERE datos.NUMCTA = " + "'" + consultaDatosDTO.getNumeroCuenta() + "' " + 
        " AND datos.MES1 = " + consultaDatosDTO.getMes() + " AND datos.ANO1 = " + consultaDatosDTO.getAnio()
        
        sql.eachRow(query){ valor ->
            lista << valor.COD_GENDOC
        }
        
        lista.each{ valor ->
            sql.eachRow("SELECT " +
        "datos.COD_GENDOC AS cod_gendoc" +
        ",datos.DESC_FOLDER AS desc_folder" +
        ",datos.ID_FOLDER AS id_folder" +
        ",datos.NUMCTA AS numero_cuenta" +
        ",datos.ANO1 AS anio" +
        ",datos.MES1 AS mes" +
        ",datos.COD_BANCO AS cod_banco" +
        ",types.DESC_TYPE AS desc_type" +
        ",datos.NOMBRE AS nombre" +
        ",datos.LEN_BLOB AS len " +
        "FROM V_DP_GENDOCS_" + consultaDatosDTO.getIdType()+ " datos " +
        "INNER JOIN DP_DOC_TYPES types " +
        "ON datos.ID_TYPE=types.ID_TYPE " +
        "WHERE datos.COD_GENDOC =  " + valor +
        "AND datos.DELETED = 'N' "){
                consultaDTO = ConsultaDTO.getIntance();
                consultaDTO.setNombre(it.nombre);
                consultaDTO.setAnio(it.anio);
                consultaDTO.setBanco(it.cod_banco);
                consultaDTO.setDirectorio(it.desc_folder);
                consultaDTO.setIdDirectorio(it.id_folder as String);
                consultaDTO.setMes(it.mes);
                consultaDTO.setNumeroCuenta(it.numero_cuenta);
                consultaDTO.setTipoDocumento(it.desc_type);
                consultaDTO.setCodGenDoc(it.cod_gendoc as String);
                consultaDTO.setLenBlob(it.len as String);
                consultaDTOs.add(consultaDTO)
            }
        }
        return consultaDTOs
    }	            
}
      

    
    


