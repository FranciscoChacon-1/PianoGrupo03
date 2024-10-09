package sv.edu.catolica.pianogrupo03;
// Para audio
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


public class AnimalesActivity extends AppCompatActivity{
    private SoundPool soundPool;
    private int svaca;
    private int scabra;
    private int scaballo;
    private int sperro;
    private int slobo;
    private int sburro;
    private int selefante;
    private int sserpiente;
    private Button bVaca,bPerro,bCabra,bLobo,bBurro,bCaballo,bElefeante,bSerpiente;

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
        sserpiente = soundPool.load(this, R.raw.serpiente, 1);

        bVaca = findViewById(R.id.Vaca);
        bPerro = findViewById(R.id.Perro);
        bElefeante = findViewById(R.id.Elefante);
        bBurro = findViewById(R.id.Burro);
        bCabra = findViewById(R.id.Cabra);
        bCaballo = findViewById(R.id.Caballo);
        bLobo = findViewById(R.id.Lobo);
        bSerpiente = findViewById(R.id.Serpiente);

        teclaPresionada(bVaca, svaca);
        teclaPresionada(bPerro, sperro);
        teclaPresionada(bElefeante, selefante);
        teclaPresionada(bBurro, sburro);
        teclaPresionada(bCabra, scabra);
        teclaPresionada(bCaballo, scaballo);
        teclaPresionada(bLobo, slobo);
        teclaPresionada(bSerpiente, sserpiente);
    }

    private void teclaPresionada(Button btn, int sonidoId){
        // Guardar el color de fondo original del botón
        final ColorStateList originalColor = btn.getBackgroundTintList();

        // Obtener el texto del botón para usarlo en el Toast
        String buttonText = btn.getText().toString();

        btn.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Al presionar: gris
                    btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.tecla_press)));

                    // Mostrar el Toast con el nombre del animal
                    Toast.makeText(this, "Sonido de " + buttonText, Toast.LENGTH_SHORT).show();

                    // Reproducir el sonido asociado al botón
                    soundPool.play(sonidoId,1,1,0,0,1);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    // Al soltar, restaurar el color original
                    btn.setBackgroundTintList(originalColor);
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
