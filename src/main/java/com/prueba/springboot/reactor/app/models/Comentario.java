package com.prueba.springboot.reactor.app.models;

import java.util.ArrayList;
import java.util.List;

public class Comentario {

	private List<String> comentario;

	
	

	public Comentario() {
		this.comentario = new ArrayList<>();
	}

	public void setComentario(String comentario) {
		this.comentario.add(comentario);
	}

	@Override
	public String toString() {
		return "Comentario [comentario=" + comentario + "]";
	}
	
	
	
}
