package br.com.ot4.proposta.carteira;

public class CarteiraResponse {
	
	private String resultado;

    private String id;

    public CarteiraResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public CarteiraResponse(Carteira carteira) {
    	this.id = carteira.getId().toString();
    	this.resultado = carteira.getTipoCarteira().name();
		
	}

	public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

}
