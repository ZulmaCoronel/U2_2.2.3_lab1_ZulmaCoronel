package mx.edu.ittepic.u2_lab1_zulmacoronel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {

    Button btnModificar, btnRegresar;
    EditText txtNombre, txtDomicilio, txtColonia, txtidClientes;
    BasedeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtNombre = findViewById(R.id.nombre);
        txtColonia = findViewById(R.id.colonia);
        txtDomicilio = findViewById(R.id.domicilio);
        txtidClientes = findViewById(R.id.idCliente);
        btnModificar = findViewById(R.id.btnmodificar);
        btnRegresar = findViewById(R.id.btnregresar);

        bd = new BasedeDatos(this,"BASE", null,1);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar(txtNombre,txtDomicilio,txtColonia,txtidClientes);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Modificar(EditText nom, EditText dom, EditText col, EditText id){
        Insertar a = new Insertar();
        try{
            if(a.vacio(nom) || a.vacio(dom) || a.vacio(col) || a.vacio(id)){
                Toast.makeText(Modificar.this, "Uno o mas no se han completado", Toast.LENGTH_SHORT).show();
            }
            else{
                if(!idFound(txtidClientes)){
                    Toast.makeText(this, "El id no se ha registrado", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase database = bd.getWritableDatabase();
                    String SQL ="UPDATE CLIENTE " +
                            "SET NOMBRE='"+a.cad(nom)+"', " +
                            "DOMICILIO='"+a.cad(dom)+"', " +
                            "COLONIA='"+a.cad(col)+"'"+
                            "WHERE IDCLIENTE="+a.cad(id);
                    database.execSQL(SQL);
                    Toast.makeText(Modificar.this, "REGISTRO MODIFICADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (SQLiteException e){
            Toast.makeText(Modificar.this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean idFound(EditText edt){
        try{
            SQLiteDatabase databse = bd.getReadableDatabase();
            String SQL = "SELECT IDCLIENTE FROM CLIENTE WHERE IDCLIENTE="+edt.getText().toString();

            Cursor c = databse.rawQuery(SQL,null);

            if(c.moveToFirst()){
                return true;
            }
            else{
                return false;
            }

        }catch(SQLiteException e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
