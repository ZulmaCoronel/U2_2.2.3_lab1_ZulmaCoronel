package mx.edu.ittepic.u2_lab1_zulmacoronel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btnInsertar,btnModificar,btnConsultar,btnRregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        btnInsertar = findViewById(R.id.btninsertar);
        btnModificar=findViewById(R.id.btnmodificar);
        btnConsultar = findViewById(R.id.btnconsultar);
        btnRregresar = findViewById(R.id.btnregresar);


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insert = new Intent(Menu.this,Insertar.class);
                startActivity(insert);
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(Menu.this,Modificar.class);
                startActivity(update);
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent select = new Intent(Menu.this,Consultar.class);
                startActivity(select);
            }
        });

        btnRregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
