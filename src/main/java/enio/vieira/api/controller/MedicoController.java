package enio.vieira.api.controller;

import enio.vieira.api.medico.DadosCadastroMedico;
import enio.vieira.api.medico.DadosListagemMedico;
import enio.vieira.api.medico.Medico;
import enio.vieira.api.medico.MedicoRepository;

import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DadosListagemMedico> listar(){

        return repository.findAll().stream().map(DadosListagemMedico::new).toList(); // transforma medico em DadosListagemMedico
    }

}
