package enio.vieira.api.medico;

import enio.vieira.api.endereco.DadosEndereco;

//Especialidade é um enum com 4 coisas pre definidas no escopo | DadosEndereco é outro DTO(um record).
public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
