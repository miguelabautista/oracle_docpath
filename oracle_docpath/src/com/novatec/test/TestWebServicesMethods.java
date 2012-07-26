package com.novatec.test;

import java.util.List;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

import www.novatec.com.ve.dto.ConsultaDTO;
import www.novatec.com.ve.dto.ConsultaReclamosDTO;
import www.novatec.com.ve.dto.DpFolderDTO;
import www.novatec.com.ve.dto.DpIndexDTO;
import www.novatec.com.ve.query.Consulta;

public class TestWebServicesMethods {

	Consulta consulta = Consulta.getInstance();

   

    
    @Test
    public void folderPadres() {
        List<DpFolderDTO> folderPadres = consulta.folderPadres(null);
        for (DpFolderDTO dpFolderDTO : folderPadres) {
            System.out.println("idFolder " + dpFolderDTO.getIdFolder() + " idParent " + dpFolderDTO.getIdParent()
                    + " tooltip " + dpFolderDTO.getToolTip() + " descripcion " + dpFolderDTO.getDescFolder());
        }
    }

    @Ignore
    @Test
    public void testUserDomain() {
        String nameUser = "miguel.pena";
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
        System.out.println(user + " " + domain);
    }

    
    @Test
    public void testConsultaFolder() {
        List<DpFolderDTO> consultaFolder = consulta.consultaFolder("admin@");
        for (DpFolderDTO dpFolderDTO : consultaFolder) {
            System.out.println("idFolder " + dpFolderDTO.getIdFolder() + " idParent " + dpFolderDTO.getIdParent()
                    + " tooltip " + dpFolderDTO.getToolTip() + " descripcion " + dpFolderDTO.getDescFolder());
        }
    }

    
    @Test
    public void testConsultaIndex() {
        List<DpIndexDTO> consultaIndex = consulta.consultaIndex("1");
        for (DpIndexDTO dp : consultaIndex) {
            System.out.println("title: " + dp.getTitle() + " id: " + dp.getIdIdx());
        }
    }
    
    @Test
    public void testConsultas() {
        ConsultaDTO consultaDTO = ConsultaDTO.getIntance();
        consultaDTO.setAnio("11");
        consultaDTO.setMes("12");
        consultaDTO.setNumeroCuenta("5400-8632-1526-0601");
        consultaDTO.setIdDirectorio("1000000002");
        consultaDTO.setIdType("1");
        List<ConsultaDTO> consulta1 = consulta.consulta(consultaDTO);
        for (ConsultaDTO dto1 : consulta1) {
            System.out.println("ano " + dto1.getAnio() + " mes " + dto1.getMes() + " nombre " + dto1.getNombre()
                    + " cuenta " + dto1.getNumeroCuenta() + " tipo de documento " + dto1.getTipoDocumento() + " directorio "
                    + dto1.getDirectorio());
        }

    }
    
    @Test
    public void testConsultaReclamos() {
        ConsultaReclamosDTO consultaDTO = ConsultaReclamosDTO.getInstance();
        consultaDTO.setIdType("2");
        consultaDTO.setNumAfiliado("234");

        List<ConsultaReclamosDTO> consultaReclamos = consulta.consultaReclamos("",consultaDTO);

        for (ConsultaReclamosDTO consultaReclamosDTO : consultaReclamos) {
            System.out.println(consultaReclamosDTO.getLenBlob());
        }
    }
    
    @Test
    public void testConsultaWebService(){
        ConsultaDTO consultaDTO = ConsultaDTO.getIntance();
        consultaDTO.setAnio("11");
        consultaDTO.setMes("12");
        consultaDTO.setNumeroCuenta("5400-8632-1526-0601");
        consultaDTO.setIdDirectorio("1000000002");
        consultaDTO.setIdType("1");
        List<ConsultaDTO> con = consulta.consultaWebServices(consultaDTO);
        for (ConsultaDTO consultaDTO1 : con) {
            System.out.println(consultaDTO1.getMes() + " " + consultaDTO1.getNumeroCuenta());
        }
    }

}
