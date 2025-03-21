package com.musica.mp3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Nodo {
     String nombre;
    String artista;
    float duracion;
    String imagenRuta;
    String genero;
    Texture imagen;  // La imagen
    Nodo siguiente;

    public Nodo(String nombre, String artista, float duracion, String imagenRuta, String genero) {
        this.nombre = nombre;
        this.artista = artista;
        this.duracion = duracion;
        this.imagenRuta = imagenRuta;
        this.genero = genero;
        this.siguiente = null;
    }
}
