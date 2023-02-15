package es.rodrigo.learning.pmdm.capturafoto;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.rodrigo.learning.pmdm.capturafoto.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private Button btnCapturaFoto;
    private Button btnCapturaFotoyGuardar;
    private ImageView ivFoto;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int CAPTURA_IMAGEN_TAMAÑO_REAL = 2;
    private String mCurrentPhotoPath;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCapturaFoto = findViewById(R.id.btnCapturaFoto);
        btnCapturaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        ivFoto = findViewById(R.id.ivFoto);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    ivFoto.setImageBitmap(bitmap);
                }
                if (result.getResultCode() == RESULT_OK && result.getData() == null) {
                    Toast.makeText(MainActivity.this, "Imagen guardada en: " + mCurrentPhotoPath, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCapturaFotoyGuardar = findViewById(R.id.btnCapturaFotoyGuardar);
        btnCapturaFotoyGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntentAndSave();
            }
        });

        pedirPermisosSiNoSeTienen();
    }



    static String[] PERMISOS = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private void pedirPermisosSiNoSeTienen() {
        if (ActivityCompat.checkSelfPermission(this, PERMISOS[0]) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, PERMISOS[1]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISOS, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            activityResultLauncher.launch(takePictureIntent);

        } else {
            Toast.makeText(this, "No se encuentra ninguna app para captura de foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void galleryAddPic() {
        Intent mediaScanlntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanlntent.setData(contentUri);
        this.sendBroadcast(mediaScanlntent);
    }



    private void dispatchTakePictureIntentAndSave() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Error creando el fichero", Toast.LENGTH_SHORT).show();
                Log.e("Error creando fichero", "dispatchTakePictureIntentAndSave: ", ex);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "es.rodrigo.learning.pmdm.capturafoto.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(photoFile));

//                startActivityForResult(takePictureIntent, CAPTURA_IMAGEN_TAMAÑO_REAL);
                activityResultLauncher.launch(takePictureIntent);
            }
        }
    }

    private File createImageFile() throws IOException {
        try {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";

            File path = Environment.getExternalStorageDirectory();

            File dir = new File(path, getBaseContext().getString(R.string.app_files_dir));

            File parent = dir.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }

            if (!dir.exists()) {
                dir.mkdirs();
            }

            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    dir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = image.getAbsolutePath();
            return image;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            ivFoto.setImageBitmap(imageBitmap);
//        }
//    }

}