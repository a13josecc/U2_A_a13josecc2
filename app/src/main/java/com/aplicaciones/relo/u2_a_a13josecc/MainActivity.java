package com.aplicaciones.relo.u2_a_a13josecc;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static com.aplicaciones.relo.u2_a_a13josecc.R.string.Leon;
import static com.aplicaciones.relo.u2_a_a13josecc.R.string.toast2;
import static com.aplicaciones.relo.u2_a_a13josecc.R.string.toast3;
import static com.aplicaciones.relo.u2_a_a13josecc.R.string.toast4;


public class MainActivity extends Activity {

    private EditText textoEntrada;
    private CheckBox checkB;
    private Button boton;
    private TextView textoSalida;
    private RadioButton btRojo;
    private RadioButton btAzul;
    private Spinner desplegable;
    private ImageView faroF;
    private Switch btSwitch;
    private Chronometer crono;
    private int tempoDestruccion=15;
    private Chronometer contar;
    private Switch onOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        textoEntrada=(EditText) findViewById(R.id.ptTexto);
        checkB=(CheckBox) findViewById(R.id.cvClear);
        boton= (Button) findViewById(R.id.btAddClear);
        textoSalida=(TextView) findViewById(R.id.ptEscrito);
        btRojo=(RadioButton) findViewById(R.id.rbRojo);
        btAzul=(RadioButton) findViewById(R.id.rbAzul);
        desplegable= (Spinner) findViewById(R.id.spinnerProvincias);
        faroF= (ImageView) findViewById(R.id.faro);
        crono= (Chronometer) findViewById(R.id.cronometro);
        onOff = (Switch) findViewById(R.id.idSwitch);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkB.isChecked()) {
                    textoSalida.setText("");
                } else {
                    textoSalida.setText(textoSalida.getText().toString()+" "+textoEntrada.getText().toString());
                    textoEntrada.setText("");
                }
            }
        });

        desplegable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Toast toast1;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equalsIgnoreCase(getResources().getString(Leon))) {
                    toast1 = Toast.makeText(getBaseContext(), toast3, Toast.LENGTH_SHORT);
                    toast1.show();
                } else {
                    toast1 = Toast.makeText(getBaseContext(), toast2, Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    crono.setBase(SystemClock.elapsedRealtime());
                    crono.start();
                }

                else {
                    crono.stop();
                    crono.setBase(SystemClock.elapsedRealtime());
                }
            }
        });

        crono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long tempoPasado = SystemClock.elapsedRealtime()
                        - chronometer.getBase();
                int tempoSeg = (int) tempoPasado / 1000;
                if (tempoSeg == tempoDestruccion) {
                    finish();
                }
            }
        });

    }

    public void controlarCheck(View v){

        switch (v.getId()){
            case R.id.rbRojo:
                textoSalida.setTextColor(getResources().getColor(R.color.rojo));
                break;
            case R.id.rbAzul:
                textoSalida.setTextColor(getResources().getColor(R.color.azul));
                break;
        }
    }
    public void clickFoto(View view){

       Toast toastF = Toast.makeText(getBaseContext(), toast4, Toast.LENGTH_SHORT);
       toastF.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
