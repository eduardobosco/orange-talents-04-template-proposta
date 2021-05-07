package br.com.ot4.proposta.proposta.Analise;

public class AnalisePropostaResponse {

	private String documento;
    private String nome;
    private RetornoAnalise resultadoSolicitacao;
    private Long idProposta;

    public AnalisePropostaResponse(String documento,String nome, RetornoAnalise resultadoSolicitacao, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public RetornoAnalise getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "AnaliseRestricaoResponse{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", resultadoSolicitacao=" + resultadoSolicitacao +
                ", idProposta=" + idProposta +
                '}';
    }
}
