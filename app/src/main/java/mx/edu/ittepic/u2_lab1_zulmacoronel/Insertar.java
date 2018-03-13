package mx.edu.ittepic.u2_lab1_zulmacoronel;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends AppCompatActivity {

    Button btnInsertar, btnRegresar;
    EditText txtNombre, txtDomicilio, txtColonia;
    BasedeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        txtNombre = findViewById(R.id.nombre);
        txtColonia = findViewById(R.id.colonia);
        txtDomicilio = findViewById(R.id.domicilio);

        btnInsertar = findViewById(R.id.btninsertar);
        btnRegresar = findViewById(R.id.btnregresar);

        bd = new BasedeDatos(this,"BASE", null,1);


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnInsertarCliente(txtNombre,txtDomicilio,txtColonia);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void fnInsertarCliente(EditText nom, EditText dom, EditText col) {
        try {
            if(vacio(nom) || vacio(dom) || vacio(col)){
                Toast.makeText(this, "Uno o mas campos no han sido completados", Toast.LENGTH_SHORT).show();
            }
            else{
                SQLiteDatabase database = bd.getReadableDatabase();
                String SQL = "INSERT INTO CLIENTE VALUES(" +
                        "NULL," +
                        "'"+cad(nom).toUpperCase()+"'," +
                        "'"+cad(dom).toUpperCase()+"'," +
                        "'"+cad(col).toUpperCase()+"')";

                database.execSQL(SQL);
                Toast.makeText(this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
            }


        } catch (SQLiteException e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }
    public boolean vacio(EditText edt){
        if(cad(edt).isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    public String cad(EditText edt){
        return edt.getText().toString();
    }
}

