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

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int sDo, sRe, sMi, sFa, sSol, sLa, sSi, sDoOct;
    private Button bDo, bRe, bMi, bFa, bSol, bLa, bSi, bDoOct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        sDo = soundPool.load(this, R.raw.do_, 1);
        sRe = soundPool.load(this, R.raw.re_, 1);
        sMi = soundPool.load(this, R.raw.mi_, 1);
        sFa = soundPool.load(this, R.raw.fa_, 1);
        sSol = soundPool.load(this, R.raw.sol_, 1);
        sLa = soundPool.load(this, R.raw.la_, 1);
        sSi = soundPool.load(this, R.raw.si_, 1);
        sDoOct = soundPool.load(this, R.raw.do_octave, 1);

        bDo = findViewById(R.id.bDo);
        bRe = findViewById(R.id.bRe);
        bMi = findViewById(R.id.bMi);
        bFa = findViewById(R.id.bFa);
        bSol = findViewById(R.id.bSol);
        bLa = findViewById(R.id.bLa);
        bSi = findViewById(R.id.bSi);
        bDoOct = findViewById(R.id.bDoOct);

        teclaPresionada(bDo, sDo);
        teclaPresionada(bRe, sRe);
        teclaPresionada(bMi, sMi);
        teclaPresionada(bFa, sFa);
        teclaPresionada(bSol, sSol);
        teclaPresionada(bLa, sLa);
        teclaPresionada(bSi, sSi);
        teclaPresionada(bDoOct, sDoOct);
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