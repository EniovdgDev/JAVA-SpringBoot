package enio.vieira.api.controller;

import enio.vieira.api.medico.*;

import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Usa-se o requestMapping para dizer qual a rota desse controller
 e usa-se o Rest controller para dizer que é um controller de uma api rest
 */
@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired // serve para injetar dependencias
    private MedicoRepository repository; // serve para comunicar com o banco

    @PostMapping //tipo de requisição(Post) - @reqBody serve para dizer que o dado vem do body da request
    @Transactional // escrever no banco
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados){ // @Valid para ver se o DTO veio correto
        //Receber o DTO e transforma no Medico usando o contrutor
        repository.save(new Medico(dados));
    }


    @GetMapping //tipo de requisição(get)
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){ // passando o padrao
        return repository.findAll(paginacao).map(DadosListagemMedico::new); // transforma medico em DadosListagemMedico
        // Na url pode-se fazer requests = ?size=10&page=1 // size e quantos registros vem, page é qual pagina estou. Vai sobreescrever o padrao
        // Para fazer o sort o parametro via url é = sort=variavel  || ainda pode usar = sort=variavel,desc ou sort=variavel,asc para crescente ou decrescente
    }


    @PutMapping //tipo de requisição(put)
    @Transactional // escrever no banco
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id()); // salvo o medico do banco na variavel
        medico.atualizarInformacoes(dados); // vejo os campos que vieram e faço as trocas
        //Após mudar a entidade, o spring faz um update no banco automaticamente.
    }

    @DeleteMapping("/{id}")//parametro que vem da url
    @Transactional
    public void excluir(@PathVariable Long id){ //anotacao para pegar valor da url
        var medico = repository.getReferenceById(id);
        medico.excluir(); // vai definir ativo como false
    }


}
