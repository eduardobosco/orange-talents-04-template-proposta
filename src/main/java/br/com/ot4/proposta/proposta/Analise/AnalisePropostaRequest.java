package br.com.ot4.proposta.proposta.Analise;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ot4.proposta.proposta.Proposta;

public class AnalisePropostaRequest {

	@NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull
    private Long idProposta;
    
    public AnalisePropostaRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}
}
