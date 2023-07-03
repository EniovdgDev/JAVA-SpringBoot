package enio.vieira.api.medico;

import enio.vieira.api.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//Especialidade é um enum com 4 coisas pre definidas no escopo | DadosEndereco é outro DTO(um record).
public record DadosCadastroMedico(

        @NotBlank   // Não pode ser nulo/vazio
        String nome,
        @NotBlank
        @Email      // Formato de email
        String email,

        @NotBlank
        String telefone, //Novo campo
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // Regex para ser 4 a 7 digitos
        String crm,
        @NotNull    //Não pode ser nulo , pois é um enum
        Especialidade especialidade,
        @NotNull
        @Valid // validar DTO que possui bean validation dentro
        DadosEndereco endereco) {
}
