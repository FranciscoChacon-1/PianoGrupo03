package sv.edu.catolica.pianogrupo03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import android.os.Build;
import android.os.Bundle;

// Manejar eventos táctiles
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
// Cambiar colores según estado
import android.content.res.ColorStateList;

import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class InstrumentosActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int sbajo;
    private int sbongos;
    private int sguitarra;
    private int spiano;
    private int stambor;
    private int strompeta;
    private int stuba;
    private int sxilofono;

    private Button bBajo,bBongos,bGuitarra,bPiano,bTambor,bTrompeta,btuba,bXilofono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piano_instrumentos);

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
        //piano instrumentos teclas
        sbajo = soundPool.load(this, R.raw.bajo, 1);
        sbongos = soundPool.load(this, R.raw.bongos, 1);
        sguitarra = soundPool.load(this, R.raw.guitarra, 1);
        spiano = soundPool.load(this, R.raw.piano, 1);
        stambor = soundPool.load(this, R.raw.tambor, 1);
        strompeta = soundPool.load(this, R.raw.trompeta, 1);
        stuba = soundPool.load(this, R.raw.tuba, 1);
        sxilofono = soundPool.load(this, R.raw.xilofono, 1);

        bBajo = findViewById(R.id.bajo);
        bBongos = findViewById(R.id.bongos);
        bGuitarra = findViewById(R.id.guitarra);
        bPiano = findViewById(R.id.piano);
        bTambor = findViewById(R.id.tambor);
        bTrompeta = findViewById(R.id.trompeta);
        btuba = findViewById(R.id.tuba);
        bXilofono = findViewById(R.id.xilofono);

        teclaPresionada(bBajo, sbajo,"Bajo");
        teclaPresionada(bBongos, sbongos,"Bongos");
        teclaPresionada(bGuitarra, sguitarra,"Guitarra");
        teclaPresionada(bPiano, spiano,"Piano");
        teclaPresionada(bTambor, stambor,"Tambor");
        teclaPresionada(bTrompeta, strompeta,"trompeta");
        teclaPresionada(btuba, stuba,"tuba");
        teclaPresionada(bXilofono, sxilofono,"Xilofono");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void teclaPresionada(Button btn, int sonidoId, String mensajeTecla){
        btn.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Al presionar: gris
                    btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.tecla_press)));
                    soundPool.play(sonidoId,1,1,0,0,1);
                    Toast.makeText(getApplicationContext(), mensajeTecla, Toast.LENGTH_SHORT).show();
                    break;
                // Mostrar un Toast con el mensaje de la tecla presionada
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    // Al soltar: blanco
                    btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    break;
                // Mostrar un Toast con el mensaje de la tecla presionada


            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
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
                intent = new Intent(this, InstrumentosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case "Acerca de Nosotros":
                Toast.makeText(this, "Acerca de Nosotros Seleccionado", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, AboutUsActivity.class);
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
