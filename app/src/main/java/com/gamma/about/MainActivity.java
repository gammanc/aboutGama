package com.gamma.about;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fa_share;
    Context myContext;
    Uri imgUri;
    String sharetext ="Emerson Nolasco - Ingeniería Informáctica" +
            "\n Github: https://github.com/gammanc/" +
            "\n Instagram: https://www.instagram.com/gamalielgram/" +
            "\n Correo: 00215316@uca.edu.sv" +
            "\n Teléfono: 7***-***9";
    TextView powered;
    String imgPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCircular();
        setLinks();

        myContext = getApplicationContext();
        fa_share = findViewById(R.id.fashare);
        fa_share.setOnClickListener(share);

        imgPath = createImage(R.drawable.profile);
    }

    View.OnClickListener share = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sharePicture();
        }
    };

    /*
    * Habilita los TextView para actuar como enlaces
    * */
    private void setLinks(){
        TextView txtinsta = (TextView) findViewById(R.id.instagram);
        TextView txtgit = (TextView) findViewById(R.id.github);
        TextView txtmail = (TextView) findViewById(R.id.mail);
        TextView txtphone = (TextView) findViewById(R.id.phone);
        txtinsta.setMovementMethod(LinkMovementMethod.getInstance());
        txtgit.setMovementMethod(LinkMovementMethod.getInstance());
        txtmail.setMovementMethod(LinkMovementMethod.getInstance());
        txtphone.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*
    * Da formato circular a la foto de perfil
    * */
    private void setCircular(){
        ImageView img = (ImageView) findViewById(R.id.profile);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        RoundedBitmapDrawable r = RoundedBitmapDrawableFactory.create(getResources(), b);
        r.setCircular(true);
        img.setImageDrawable(r);
    }

    /*
    * Crea una copia de la imagen de perfil en la memoria del teléfono
    * */
    private String createImage(int resource){
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),resource);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/profile.jpg";
        OutputStream out = null;
        File file=new File(path);
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getPath();
    }

    /*
    * Comparte la imagen con las aplicaciones disponibles
    * */
    public  void sharePicture(){
        //Se obtiene la URI de imagen con formato content:/, apto para compartir
        //con otras apps sin problemas de permisos
        imgUri = FileProvider.getUriForFile(myContext, "com.gamma.fileprovider", new File(imgPath));

        //Se comparte la imagen como normalmente
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, sharetext);

        //Se permite al intent permiso temporal de lectura para que otras apps puedan acceder
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "Compartir"));
    }

}
