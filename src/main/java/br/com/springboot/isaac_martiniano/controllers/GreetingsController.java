package br.com.springboot.isaac_martiniano.controllers;

import java.util.List;

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

import br.com.springboot.isaac_martiniano.model.Usuario;
import br.com.springboot.isaac_martiniano.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	
	@Autowired /*Injeção de dependencia*/
	private UsuarioRepository usuarioRepository;
	
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
//    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public String greetingText(@PathVariable String name) {
//        return "Minha 1ª aplicação com Sprong Boot " + name + "!";
//    }
//    
//    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public String retornaOlaMudo(@PathVariable String nome) {
//    	
//    	Usuario usuario = new Usuario();
//    	usuario.setNome(nome);
//    	
//    	usuarioRepository.save(usuario);
//    	
//    	return "Olá mundo " + nome;
//    }
    
    @GetMapping(value = "listartodos") /*PRIMEIRO MÉTODO DE API*/
    @ResponseBody /*RETORNA OS DADOS PARA O CORPO DA RESPOSTA*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); /*EXECUTA A CONSULTA NO BANCO DE DADOS*/
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*RETORNAR A LISTA EM JSON*/
    }
    
    @PostMapping(value = "salvar") /*Mapeia a url*/
    @ResponseBody /*Descrição da Resposta*/
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario) { /*Recebe os dados para salvar*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    
    @DeleteMapping(value = "delete") /*Mapeia a url*/
    @ResponseBody /*Descrição da Resposta*/
    public ResponseEntity<String> delete (@RequestParam Long iduser) { /*Recebe os dados para deletar*/
    	
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("User deletado com sucesso!", HttpStatus.CREATED);
    }
    
    @GetMapping(value = "buscaruserid") /*Mapeia a url*/
    @ResponseBody /*Descrição da Resposta*/
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "iduser") Long iduser) { /*Recebe os dados para consultar*/
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "atualizar") /*Mapeia a url*/
    @ResponseBody /*Descrição da Resposta*/
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario) { /*Recebe os dados para consultar*/
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarPorNome") /*Mapeia a url*/
    @ResponseBody /*Descrição da Resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "name") String name) { /*Recebe os dados para consultar*/
    	
     List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.CREATED);
    }
    
    
}
