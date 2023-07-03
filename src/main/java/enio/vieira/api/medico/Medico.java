package enio.vieira.api.medico;

import enio.vieira.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entidade JPA de medico para mapeamento

@Table(name = "medicos")
@Entity(name = "Medicos")

//usa-se lombok para criar os metodos
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // gera o hash no campo id
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    //Embedded diz que o campo endereço faz parte da mesma tabela dentro do banco de dados
    //Exige @Embeddable na classe endereco
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone= dados.telefone();
        this.especialidade = dados.especialidade();
        //Aqui faço outro construtor e mando os dados do DTO Endereco
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        //precisa usar o if, pois como nao e obrigatorio o JAVA trata como null os campos que nao forem atualziados
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }
}
