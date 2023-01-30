package com.prueba.springboot.reactor.app;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prueba.springboot.reactor.app.models.Comentario;
import com.prueba.springboot.reactor.app.models.Usuario;
import com.prueba.springboot.reactor.app.models.usuarioComentario;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxExtensionsKt;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class PruebaApplication implements CommandLineRunner{

	private static final Logger log= LoggerFactory.getLogger(PruebaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		pruebas();		
		
	}
	
	public void pruebas() {

		List<Usuario> listausu= new ArrayList<>();
		listausu.add(new Usuario("david","hidalgo avila"));
		listausu.add(new Usuario("jenifer","azabache garcia"));
		
		Mono<List<Usuario>> x =Flux.fromIterable(listausu).collectList();
		
		x.subscribe(e-> {
			e.forEach(pl-> log.info(pl.toString()));
		});
		//Flux<String> nom = Flux.fromIterable(listausu).map(e -> e.getNombre().toUpperCase()
				//.concat(" ").concat(e.getApellido()).toUpperCase());
		
		//nom.subscribe(e-> log.info(e));
	}
	
	public void pruebas2() {
		
		List<String> lista = new ArrayList<>();
		lista.add("david hidalgo avila");
		lista.add("jenifer azabache garcia");
		
		Flux<String> listapru = Flux.fromIterable(lista);
		
		Flux<String> variable = Flux.just("david hidalgo avila","jenifer azabache garcia");
		
		Flux<Usuario> datos = listapru
				.map(e -> new Usuario(e.split(" ")[0].toUpperCase(),
						e.split(" ")[1].toUpperCase().concat(" ").concat(e.split(" ")[2].toUpperCase())))
				.filter(lamda -> lamda.getNombre().toLowerCase().equals("david"))
				.doOnNext(elemento -> System.out.println(elemento.getNombre().concat(" ").concat(elemento.getApellido())));
		
				
		
		datos.subscribe(elemento -> log.info(elemento.toString()),
				null,
				new Runnable() {
					public void run() {
						log.info("Esta completo!!!!");
					}
				});
		
	}
	
	public void pruebas3() {

		Flux<String> nombres = Flux.just("Andres Guzman","Pedro Fulano","Diego Sultano","Juan Mengano","Bruce Willis","Bruce Lee");
		 
		Flux<Usuario> usuarios = nombres.map(nombelemento -> new Usuario(nombelemento.split(" ")[0],nombelemento.split(" ")[1]));
		
		Mono<Comentario> comentarioUsuario = Mono.fromCallable(()->{
			Comentario x = new Comentario();
			x.setComentario("Hi");
			x.setComentario("Hello");
			x.setComentario("Bye");
			return x;
		});
		
		
		usuarios.flatMap(usu -> comentarioUsuario.map(comen -> new usuarioComentario(usu, comen)))
		.subscribe(usucomen -> log.info(usucomen.toString()));
	}

}
