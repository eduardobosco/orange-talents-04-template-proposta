package br.com.ot4.proposta.cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.ot4.proposta.proposta.Proposta;

public class SolicitacaoCartaoResponse {
	
	@NotBlank
    private String id;

    @NotNull
    @Positive
    private Long idProposta;


    public SolicitacaoCartaoResponse(String id, Long idProposta) {
        this.id = id;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(id, proposta);
    }


}
