package enio.vieira.api.endereco;

//foi criado isolado pois o endereco pode ser compartilhado com pacientes
public record DadosEndereco(String logradouro, String bairro, String cidade, String uf,String cep ,String complemento, String numero) {
}
