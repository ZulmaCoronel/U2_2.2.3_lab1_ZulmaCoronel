package mx.edu.ittepic.u2_lab1_zulmacoronel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Consultar extends AppCompatActivity {
    Button btnConsultar, btnRegresar;
    EditText IdCliente;
    TextView txtNombre, txtDomicilio, txtColonia;
    BasedeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);


        IdCliente = findViewById(R.id.idCliente);

        txtNombre = findViewById(R.id.nombre);
        txtDomicilio = findViewById(R.id.domicilio);
        txtColonia = findViewById(R.id.colonia);

        btnConsultar = findViewById(R.id.btnconsultar);
        btnRegresar = findViewById(R.id.btnregresar);

        bd = new BasedeDatos(this,"BASE", null,1);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultarClientes();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void ConsultarClientes(){
        Insertar a = new Insertar();
        try{
            if(a.vacio(IdCliente)){
                Toast.makeText(this, "Introduzca id del cliente", Toast.LENGTH_SHORT).show();
            }
            else{
                SQLiteDatabase databse = bd.getReadableDatabase();
                String SQL = "SELECT NOMBRE, DOMICILIO, COLONIA FROM CLIENTE WHERE IDCLIENTE="+a.cad(IdCliente);

                Cursor c = databse.rawQuery(SQL,null);

                if(c.moveToFirst()){
                    txtNombre.setText("NOMBRE: "+c.getString(0));
                    txtDomicilio.setText("DOMICILIO: "+c.getString(1));
                    txtColonia.setText("COLONIA: "+c.getString(2));
                }
                else{
                    Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();
                }
            }

        }   catch (SQLiteException e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

}

