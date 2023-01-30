package com.prueba.springboot.reactor.app.models;

public class usuarioComentario {

	private Usuario usuario;
	
	private Comentario comentario;

	public usuarioComentario(Usuario usuario, Comentario comentario) {
		super();
		this.usuario = usuario;
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "usuarioComentario [usuario/=" + usuario + ", comentario/=" + comentario + "]";
	}
	
	
}
