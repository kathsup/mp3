package com.musica.mp3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class MP3 implements Screen{

    private Texture play, pause, stop;
    private float btnWidth, btnHeight;
    private SpriteBatch batch;
    private Vector2 posBtnplay, posBtnpause, posBtnstop, imagen;
    private main reproductor;
    private Nodo cancionSeleccionada;
    private Texture imagenFondo; 
    private Texture add, remove, select;
    private Vector2 posBtnadd, posBtnremove, posBtnselect;
    private GestionLista gestionLista;
    
    
    private BitmapFont font;
    
    public MP3(main reproductor) { 
        this.reproductor = reproductor; 
        this.gestionLista = reproductor.gestionLista; 
    }
    
    @Override
    public void show() {
    batch = new SpriteBatch(); 
    play = new Texture("play.jpg");
    pause = new Texture("pause.jpg");
    stop = new Texture("stop.jpg");
    imagenFondo = new Texture("imagen1.jpg");
    add = new Texture("add.jpg");
    remove = new Texture("remove.jpg");
    select = new Texture("select.jpg");
    
    
        posBtnplay = new Vector2(400, 160);
        posBtnpause = new Vector2(500, 160);
        posBtnstop = new Vector2(300, 160);
        imagen = new Vector2(325,300);
        posBtnadd = new Vector2(700,300);
        posBtnremove = new Vector2(700,400);
        posBtnselect = new Vector2(700,500);
        
    
        btnWidth = 50;  
        btnHeight = 50; 
        
        font = new BitmapFont();
        
        cancionSeleccionada = reproductor.gestionLista.seleccionarCancion("Cancion1");
        if (cancionSeleccionada != null && cancionSeleccionada.imagenRuta != null) {
            cancionSeleccionada.imagen = new Texture("imagen1.jpg");
        }
        
        
    }

    @Override
    public void render(float f) {
    ScreenUtils.clear(1, 1, 1, 1);    
    batch.begin();
    
    batch.draw(play, posBtnplay.x, posBtnplay.y, btnWidth, btnHeight);
    batch.draw(pause, posBtnpause.x, posBtnpause.y, btnWidth, btnHeight);
    batch.draw(stop, posBtnstop.x, posBtnstop.y, btnWidth, btnHeight);
    batch.draw(imagenFondo, imagen.x, imagen.y, 200, 200);
    batch.draw(add, posBtnadd.x, posBtnadd.y, btnWidth, btnHeight);
    batch.draw(remove, posBtnremove.x, posBtnremove.y, btnWidth, btnHeight);
    batch.draw(select, posBtnselect.x, posBtnselect.y, btnWidth, btnHeight);
    
    if (cancionSeleccionada != null && cancionSeleccionada.imagen != null) {
            batch.draw(cancionSeleccionada.imagen, 100, 100, 200, 200);
        }
    
    
    
    batch.end();
    
    if (Gdx.input.isTouched()) {
        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        touchPos.y = Gdx.graphics.getHeight() - touchPos.y;

        
            if (isButtonPressed(touchPos, posBtnplay, btnWidth, btnHeight)) {
                
                reproductor.musica.play();
                
               
            } else if (isButtonPressed(touchPos, posBtnpause, btnWidth, btnHeight)) {
                
                reproductor.musica.pause();
                
            } else if (isButtonPressed(touchPos, posBtnstop, btnWidth, btnHeight)) {
                
                
                reproductor.musica.stop(); 
                
            }else if (isButtonPressed(touchPos, posBtnadd, btnWidth, btnHeight)) {
                agregarCancion();
            }
            else if (isButtonPressed(touchPos, posBtnselect, btnWidth, btnHeight)) {
              
            }
    }
    }

  
    
    private void agregarCancion() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar una canción");
        
        
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de audio", "mp3", "wav"));
        
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String rutaArchivo = archivo.getAbsolutePath();
            
            
            String nombre = archivo.getName();
            String artista = "Desconocido"; 
            float duracion = obtenerDuracion(archivo);
            String imagenRuta = "ruta_a_imagen"; 
            String genero = "Desconocido";

           
            gestionLista.agregarCancion(nombre, artista, duracion, imagenRuta, genero);
        }
    }
   
    private float obtenerDuracion(File archivo) {
        
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(archivo));
            long microseconds = clip.getMicrosecondLength();
            return microseconds / 1000000f; 
        } catch (Exception e) {
            e.printStackTrace();
            return 0f; 
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
        add.dispose();
        remove.dispose();
        select.dispose();
        if(cancionSeleccionada != null && cancionSeleccionada.imagen != null){
            cancionSeleccionada.imagen.dispose();
        }
        imagenFondo.dispose();
        
    }
    
}
