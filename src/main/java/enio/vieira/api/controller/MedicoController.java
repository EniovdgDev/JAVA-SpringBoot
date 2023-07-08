package enio.vieira.api.controller;

import enio.vieira.api.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados); //salva medico na variavel com os dados vindo da request
        repository.save(medico); //salva no banco e gera um id
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); // gera uma uri com o id do medico
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico)); //retornar 201 + json com dados do registro + cabeçalho location com uri
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
         var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
         return ResponseEntity.ok(page); // ok devolve 200 e passo objeto de paginacao com os dados dos medicos
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // criar um novo DTO para mostrar o medico atualziado
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){ // retorno padronizado
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build(); // retorna 204, pois e uma funcao delete
    }

}
