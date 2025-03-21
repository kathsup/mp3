package com.musica.mp3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class main extends Game {
   
    public Music musica; 
    public boolean sonidoActivado = true;
    GestionLista gestionLista;
    
    

    @Override
    public void create() {
       gestionLista = new GestionLista(); 
        setScreen(new MP3(this));
        
        gestionLista.agregarCancion("Cancion1", "Random", 3.5f, "imagen1.jpg", "Fondo");
        
        
        musica = Gdx.audio.newMusic(Gdx.files.internal("musica.mp3"));
         musica.setLooping(true);
        musica.setVolume(0.5f);
        
         if (sonidoActivado) {
            musica.play();
        }
    }

    
    public void alternarSonido() {
        sonidoActivado = !sonidoActivado;

        
        if (sonidoActivado) {
            musica.play();
        } else {
            musica.pause(); 
        }
    }
    
    
    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        super.render();
    }

    @Override
    public void dispose() {
       super.dispose();
       if (musica != null) {
            musica.dispose();
        }
    }
}
