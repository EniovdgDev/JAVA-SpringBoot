package enio.vieira.api;

import enio.vieira.api.medico.DadosCadastroMedico;
import enio.vieira.api.medico.Medico;
import enio.vieira.api.medico.MedicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 Usa-se o requestMapping para dizer qual a rota desse controller
 e usa-se o Rest controller para dizer que é um controller de uma api rest
 */
@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired // serve para injetar dependencias
    private MedicoRepository repository; // serve para comunicar com o banco

    //tipo de requisição(Post) - @reqBody serve para dizer que o dado vem do body da request
    @PostMapping
    @Transactional // escrever no banco
    public void cadastrarMedico(@RequestBody DadosCadastroMedico dados){
        //Receber o DTO e transforma no Medico usando o contrutor
        repository.save(new Medico(dados));
    }

}
