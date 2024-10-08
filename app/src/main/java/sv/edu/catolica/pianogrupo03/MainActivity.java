package sv.edu.catolica.pianogrupo03;

// Para audio
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;


// Manejar eventos táctiles
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
// Cambiar colores según estado
import android.content.res.ColorStateList;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private SoundPool soundPool;
    private int sRe;
    private int sMi;
    private int sFa;
    private int sSol;
    private int sLa;
    private int sSi;
    private int sDoOct;
    private Button bDo, bRe, bMi, bFa, bSol, bLa, bSi, bDoOct;


    @SuppressLint("ObsoleteSdkInt")
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

        int sDo = soundPool.load(this, R.raw.do_, 1);
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

    @SuppressLint("ClickableViewAccessibility")
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getTitle().toString()) {
            case "Piano Tradicional":
                Toast.makeText(this, "Piano Tradicional Seleccionado", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Cierra la actividad actual
                return true;

            case "Piano Infantil de la Selva":
                Toast.makeText(this, "Piano Infantil de la Selva Seleccionado", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, AnimalesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case "Piano de Instrumentos musicales":
                Toast.makeText(this, "Piano de Instrumentos Musicales", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, piano_musical.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case "Acerca de Nosotros":
                Toast.makeText(this, "Acerca de Nosotros Seleccionado", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, about_us.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case "Salir":
                finishAffinity(); // Cierra todas las actividades
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}