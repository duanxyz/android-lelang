package com.example.lelangonline.ui.deposit.transfer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.CursorLoader;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.ActivityTransferBinding;
import com.example.lelangonline.models.banks.Banks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TransferActivity extends DaggerAppCompatActivity {

    private ActivityTransferBinding binding;
    private TransferViewModel viewModel;

    @Inject
    ViewModelProviderFactory factory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transfer);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, factory).get(TransferViewModel.class);
        binding.setViewModel(viewModel);
        Banks banks = getIntent().getExtras().getParcelable("bank");
        binding.setBank(banks);
        viewModel.setOpenAtm();
        viewModel.setOpenInternet();
        viewModel.setOpenMobile();
        viewModel.setUpload();
        observeObservers();
        clipBoard();
        upload();
    }

    private void upload() {
        binding.choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = { "Ambil Gambar", "Pilih Dari Galery","Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(TransferActivity.this);
                builder.setTitle("Tambah Foto");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Ambil Gambar"))
                        {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                            startActivityForResult(intent, 1);
                        }
                        else if (options[item].equals("Pilih Dari Galery"))
                        {
                            Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, 2);
                        }
                        else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    private void clipBoard() {
        binding.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label",binding.accountNumber.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Berhasil disalin", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void observeObservers() {
        viewModel.getCloseItem().observe(this, close -> {
            if (close)
                finish();
        });

        viewModel.getOpenAtm().observe(this, aBoolean -> {
            if (aBoolean){
                binding.textAtm.setVisibility(View.VISIBLE);
            }else {
                binding.textAtm.setVisibility(View.GONE);
            }
        });

        viewModel.getOpenInternet().observe(this, aBoolean -> {
            if (aBoolean){
                binding.textInternet.setVisibility(View.VISIBLE);
            }else {
                binding.textInternet.setVisibility(View.GONE);
            }
        });

        viewModel.getOpenMobile().observe(this, aBoolean -> {
            if (aBoolean){
                binding.textMobile.setVisibility(View.VISIBLE);
            }else {
                binding.textMobile.setVisibility(View.GONE);
            }
        });

        viewModel.getOpenUpload().observe(this, aBoolean -> {
            if (aBoolean){
                binding.tansferLayout.setVisibility(View.GONE);
                binding.uploadLayout.setVisibility(View.VISIBLE);
            }else {
                binding.tansferLayout.setVisibility(View.VISIBLE);
                binding.uploadLayout.setVisibility(View.GONE);
            }
        });
        
        viewModel.getStatus().observe(this, dataStatus -> {
            switch (dataStatus){
                case LOADED:
                    dialogSuccess();
                    break;
                case ERROR:
                    dialogError();
            }
        });
    }

    private void dialogError() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Gangguan");
        alertDialogBuilder
                .setMessage("Proses upload bukti transaksi gagal")
                .setCancelable(false)
                .setPositiveButton("Keluar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        TransferActivity.this.finish();
                    }
                })
                .setNegativeButton("Upload Ulang",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void dialogSuccess() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Catatan");
        alertDialogBuilder
                .setMessage("Terima Kasih, telah melakukan pengisian saldo.\n" +
                        "Proses pengisian saldo akan dilakukan 1x24 jam.")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        TransferActivity.this.finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    binding.imgProof.setImageBitmap(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath+"");
                binding.imgProof.setImageBitmap(thumbnail);
                Uri selectedImagePath = data.getData();
                File file = new File(getRealPathFromURI(selectedImage));
                viewModel.setBitmap(file);
            }
        }
    }

    private String getRealPathFromURI(Uri selectedImage) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, selectedImage, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}