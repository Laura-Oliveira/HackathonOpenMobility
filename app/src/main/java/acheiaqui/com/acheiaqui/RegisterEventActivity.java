package acheiaqui.com.acheiaqui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

import static acheiaqui.com.acheiaqui.R.id.profile_name_shop;

public class RegisterEventActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private ImageView ivPicture;
    private Button btnAcessCamera;
    Bitmap bitmap;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    private static final int CAMERA_REQUEST_CODE = 1;

    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);


        //Pega a referencia do Storage do firebase
        storageReference = FirebaseStorage.getInstance().getReference();

        //Acessa a camera do smartphone ao clicar no botão
        btnAcessCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void nextPageButton(View view)
    {

        @SuppressLint("WrongViewCast") EditText edName = (EditText) findViewById(profile_name_shop);
        EditText edInfo = (EditText) findViewById(R.id.shop_info);
        @SuppressLint("WrongViewCast") EditText edReferencePoint = findViewById(R.id.profile_reference_point);

        String name = edName.getText().toString();
        String info = edInfo.getText().toString();
        String referencePoint = edReferencePoint.getText().toString();


        if(name.length()!=0 && info.length()!=0 && referencePoint.length()!=0){

            Intent intent = new Intent(RegisterEventActivity.this, RegisterLocationActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("info", info);
            intent.putExtra("referencedPoint", referencePoint);
            intent.putExtra("picture", baos.toByteArray());

            startActivity(intent);
        } else {
            Toast.makeText(this, "É preciso preencher todos os campos", Toast.LENGTH_SHORT).show();
        }

    }
}