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

public class AboutUsActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca_de_nosotros);

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
                finish();
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
                finishAffinity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
