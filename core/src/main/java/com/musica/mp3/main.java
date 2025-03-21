package com.musica.mp3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class main extends Game {
    //private SpriteBatch batch;
    //private Texture image;
    public Music musica; 
    public boolean sonidoActivado = true;
    

    @Override
    public void create() {
        //batch = new SpriteBatch();
        //image = new Texture("libgdx.png");
        setScreen(new MP3(this));
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
        /*batch.begin();
        batch.draw(image, 140, 210);
        batch.end();*/
        super.render();
    }

    @Override
    public void dispose() {
       /* batch.dispose();
        image.dispose();*/
       super.dispose();
       if (musica != null) {
            musica.dispose();
        }
    }
}
