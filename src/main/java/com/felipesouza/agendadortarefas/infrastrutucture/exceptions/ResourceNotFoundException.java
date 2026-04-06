package com.felipesouza.agendadortarefas.infrastrutucture.exceptions;

//Extende a RunTimeException pois é com ela que criamos exceções não verificadas para tratarmos de acordo com nossa lógica
public class ResourceNotFoundException extends RuntimeException {
    //Recebe uma mensagem
    public ResourceNotFoundException(String message) {
        super(message);
    }

    //Recebe uma mensagem e um Throwable
    public ResourceNotFoundException(String message, Throwable throwable) { super(message, throwable); }
}
