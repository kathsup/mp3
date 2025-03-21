package com.musica.mp3;

import java.util.ArrayList;

public class GestionLista {
    private Nodo cabeza;  

    public GestionLista() {
        cabeza = null; 
    }

    
    public void agregarCancion(String nombre, String artista, float duracion, String imagenRuta, String genero) {
        Nodo nuevoNodo = new Nodo(nombre, artista, duracion, imagenRuta, genero);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;  
            }
            temp.siguiente = nuevoNodo; 
        }
    }

    
    public void eliminarCancion(String nombre) {
        if (cabeza == null) return;  

        if (cabeza.nombre.equals(nombre)) {  
            cabeza = cabeza.siguiente;
            return;
        }

        Nodo temp = cabeza;
        while (temp.siguiente != null && !temp.siguiente.nombre.equals(nombre)) {
            temp = temp.siguiente;  
        }

        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;  
        }
    }

    
    public Nodo seleccionarCancion(String nombre) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.nombre.equals(nombre)) {
                return temp;  
            }
            temp = temp.siguiente;
        }
        return null;  
    }

    
    public void mostrarLista() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println("Nombre: " + temp.nombre + ", Artista: " + temp.artista);
            temp = temp.siguiente;
        }
    }

    
    public void liberarRecursos() {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.imagen != null) {
                temp.imagen.dispose();
            }
            temp = temp.siguiente;
        }
    }
    
    public ArrayList<String[]> obtenerLista() {
        ArrayList<String[]> listaCanciones = new ArrayList<>();
        Nodo temp = cabeza;
        while (temp != null) {
            String[] datosCancion = {
                temp.nombre,
                temp.artista,
                String.valueOf(temp.duracion),
                temp.genero
            };
            listaCanciones.add(datosCancion);
            temp = temp.siguiente;
        }
        return listaCanciones; 
    }
}
