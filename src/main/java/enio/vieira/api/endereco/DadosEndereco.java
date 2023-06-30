package enio.vieira.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//foi criado isolado pois o endereco pode ser compartilhado com pacientes
public record DadosEndereco(

        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep ,
        // Os dois abaixo nao sao obrigatorios
        String complemento,
        String numero) {
}
