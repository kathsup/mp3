package com.musica.mp3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class MP3 implements Screen{

    private Texture play, pause, stop;
    private float btnWidth, btnHeight;
    private SpriteBatch batch;
    private Vector2 posBtnplay, posBtnpause, posBtnstop;
    private main reproductor;
    
    public MP3(main reproductor) {  // Recibe el objeto 'main' al crearse
        this.reproductor = reproductor;  // Guardar la referencia
    }
    
    @Override
    public void show() {
    batch = new SpriteBatch(); 
    play = new Texture("play.jpg");
    pause = new Texture("pause.jpg");
    stop = new Texture("stop.jpg");
    
        posBtnplay = new Vector2(400, 160);
        posBtnpause = new Vector2(500, 160);
        posBtnstop = new Vector2(300, 160);
    
        btnWidth = 50;  
        btnHeight = 50; 
    }

    @Override
    public void render(float f) {
    ScreenUtils.clear(1, 1, 1, 1);    
    batch.begin();
    
    batch.draw(play, posBtnplay.x, posBtnplay.y, btnWidth, btnHeight);
    batch.draw(pause, posBtnpause.x, posBtnpause.y, btnWidth, btnHeight);
    batch.draw(stop, posBtnstop.x, posBtnstop.y, btnWidth, btnHeight);
    batch.end();
    
    if (Gdx.input.isTouched()) {
        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        touchPos.y = Gdx.graphics.getHeight() - touchPos.y;

        
            if (isButtonPressed(touchPos, posBtnplay, btnWidth, btnHeight)) {
                System.out.println("Botón Play presionado");
                reproductor.musica.play();
                
               
            } else if (isButtonPressed(touchPos, posBtnpause, btnWidth, btnHeight)) {
                System.out.println("Botón Pause presionado");
                reproductor.musica.pause();
                
            } else if (isButtonPressed(touchPos, posBtnstop, btnWidth, btnHeight)) {
                System.out.println("Botón Stop presionado");
                reproductor.musica.play();
                reproductor.musica.stop(); 
                
            }
    }
    }

   
    
    private boolean isButtonPressed(Vector2 touchPos, Vector2 buttonPos, float buttonWidth, float buttonHeight) {
        return touchPos.x > buttonPos.x && touchPos.x < buttonPos.x + buttonWidth
                && touchPos.y > buttonPos.y && touchPos.y < buttonPos.y + buttonHeight;
    }
    
    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        pause.dispose();
        play.dispose();
        stop.dispose();
        
    }
    
}
