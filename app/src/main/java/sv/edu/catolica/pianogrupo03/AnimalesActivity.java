package sv.edu.catolica.pianogrupo03;
// Para audio
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import android.os.Build;
import android.os.Bundle;

// Manejar eventos táctiles
import android.view.MotionEvent;
// Cambiar colores según estado
import android.content.res.ColorStateList;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class AnimalesActivity extends AppCompatActivity{
    private SoundPool soundPool;
    private int svaca;
    private int scabra;
    private int scaballo;
    private int sperro;
    private int slobo;
    private int sburro;
    private int selefante;
    private Button bDo, bRe, bMi, bFa, bSol, bLa, bSi, bDoOct;
    private Button bVaca,bPerro,bCabra,bLobo,bBurro,bCaballo,bElefeante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piano_animales);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        //piano animales teclas
        svaca = soundPool.load(this, R.raw.vaca, 1);
        sperro = soundPool.load(this, R.raw.perro, 1);
        selefante = soundPool.load(this, R.raw.elefante, 1);
        sburro = soundPool.load(this, R.raw.burro, 1);
        scabra = soundPool.load(this, R.raw.cabra, 1);
        scaballo = soundPool.load(this, R.raw.caballo, 1);
        slobo = soundPool.load(this, R.raw.lobo, 1);

        bVaca = findViewById(R.id.Vaca);
        bPerro = findViewById(R.id.Perro);
        bElefeante = findViewById(R.id.Elefante);
        bBurro = findViewById(R.id.Burro);
        bCabra = findViewById(R.id.Cabra);
        bCaballo = findViewById(R.id.Caballo);
        bLobo = findViewById(R.id.Lobo);

        teclaPresionada(bVaca, svaca);
        teclaPresionada(bPerro, sperro);
        teclaPresionada(bElefeante, selefante);
        teclaPresionada(bBurro, sburro);
        teclaPresionada(bCabra, scabra);
        teclaPresionada(bCaballo, scaballo);
        teclaPresionada(bLobo, slobo);
    }

    private void teclaPresionada(Button btn, int sonidoId){
        btn.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Al presionar: gris
                    btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.tecla_press)));
                    soundPool.play(sonidoId,1,1,0,0,1);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    // Al soltar: blanco
                    btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    break;
            }
            return true;
        });
    }

}
