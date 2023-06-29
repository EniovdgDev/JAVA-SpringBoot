package enio.vieira.api;

import enio.vieira.api.medico.DadosCadastroMedico;
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

    //tipo de requisição(Post) - @reqBody serve para dizer que o dado vem do body da request
    @PostMapping
    public void cadastrarMedico(@RequestBody DadosCadastroMedico dados){

    }

}
