package br.com.springboot.rest_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import br.com.springboot.rest_api.model.Usuario;
import br.com.springboot.rest_api.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController //intercepta os dados da aplicacão
public class GreetingsController {
	
	@Autowired // IC/CD ou CDI - Injeção de dependências
	private UsuarioRepository usuarioRepository;
	
	
    /**
     *
     * @param /name the name to greet
     * @return greeting text
    */
    /*
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    } */
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuarioRepository.save(usuario); //grava no banco de dados
    	
    	return "Olá Mundo " + nome + "!";
    }
    
    @GetMapping(value = "listatodos") //Primeiro método de API
    @ResponseBody //retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listausuario() {
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); //executa a cosnulta no banco de dados
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //retorna a lista em JSON
		
	}
    
    @PostMapping(value = "salvar") //mapeia a url
    @ResponseBody //descricao da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ //recebe os dados para salvar
    	Usuario user = usuarioRepository.save(usuario);

    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){

        if((usuario.getId()) == 0){
            return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
        }

    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "delete") 
    @ResponseBody 
    public ResponseEntity<String> delete(@RequestParam Long idUser){ 
    	usuarioRepository.deleteById(idUser);
    	
    	return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscaruserid")
    @ResponseBody 
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name = "idUser") Long idUser){ 
    	Usuario user = usuarioRepository.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @GetMapping(value = "buscarpornome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){
        List<Usuario> user = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

        return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    }

}
