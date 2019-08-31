package org.seniors.rest.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.seniors.model.Atividade;
import org.seniors.model.ConsultaMedica;
import org.seniors.model.Medicacao;
import org.seniors.model.Relatorio;
import org.springframework.stereotype.Component;

@Component
@Path("/relatorio")
public class RelatorioRestService extends GenericRequest {

    @GET
    @Path("{tipo}/{userid}/{datainicio}/{datafinal}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Relatorio> getConsultasInJSON(@PathParam("tipo") String tipo, 
    		@PathParam("userid") String id_usuario,
    		@PathParam("datainicio") String dataInicio, //Thu Jul 09 2015 00:00:00 GMT-0300 (BRT)
    		@PathParam("datafinal") String dataFinal //Fri Jul 10 2015 00:00:00 GMT-0300 (BRT)
    		) {
        try {
        	List<Relatorio> rels = new ArrayList();
        	// Atividades
        	List<Atividade> conAtividade = controllerFacade.getAtividadeController().listAtividadesPorData(id_usuario, dataInicio, dataFinal);
        	for (Atividade at : conAtividade) {
        		String user = controllerFacade.getSeniorsUserController().searchById(at.getId_usuario()).getNomeCompleto();
				Relatorio r = new Relatorio("Atividade Fisica", at.getDataInicial().toString(), at.getDescricao(), at.getStatus(), user);
				rels.add(r);
			}
        	// ConsultasMedicas
        	List<ConsultaMedica> conConsulta = controllerFacade.getConsultaMedicaController().listConsultasMedicasPorData(id_usuario, dataInicio, dataFinal);
        	for (ConsultaMedica at : conConsulta) {
        		String user = controllerFacade.getSeniorsUserController().searchById(at.getId_usuario()).getNomeCompleto();
				Relatorio r = new Relatorio("Consulta Médica", at.getData().toString(), at.getDescricao(), at.getStatus(), user);
				rels.add(r);
			}
        	// Medicacao
        	List<Medicacao> conMedicacao = controllerFacade.getMedicacaoController().listMedicacoesPorData(id_usuario, dataInicio, dataFinal);
        	for (Medicacao at : conMedicacao) {
        		String user = controllerFacade.getSeniorsUserController().searchById(at.getId_usuario()).getNomeCompleto();
				Relatorio r = new Relatorio("Medicação", at.getDataInicial().toString(), at.getNome(), at.getStatus(), user);
				rels.add(r);
			}
			return rels;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

}
