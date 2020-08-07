package com.example.lelangonline.ui.withdraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.lelangonline.R;
import com.example.lelangonline.ViewModelProviderFactory;
import com.example.lelangonline.databinding.ActivityWithdrawBinding;
import com.example.lelangonline.databinding.UploadImageBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class WithdrawActivity extends DaggerAppCompatActivity {

    private WithdrawViewModel viewModel;
    private ActivityWithdrawBinding binding ;
    private Dialog dialog ;
    private AutoCompleteTextView completeTextView;
    private UploadImageBinding imageBinding;
    String[] bankList = {"BNI","BCA"};

    @Inject
    ViewModelProviderFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdraw);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, factory).get(WithdrawViewModel.class);
        binding.setViewModel(viewModel);
        observeObservers();
        initCustomDialog();
        onClick();
    }

    private void onClick() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        imageBinding.addBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setBank(imageBinding.autoComplete.getText(), imageBinding.accountNumber.getText(), imageBinding.receiver.getText());
                viewModel.setBank(imageBinding.accountNumber.getText());
                binding.layoutNominal.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
    }

    private void initCustomDialog() {
        dialog = new Dialog(WithdrawActivity.this, R.style.Theme_Dialog);
        Window window = dialog.getWindow();
        imageBinding = DataBindingUtil.inflate(LayoutInflater.from(WithdrawActivity.this),
                R.layout.upload_image, null, false);
        dialog.setContentView(imageBinding.getRoot());
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, bankList);
        completeTextView = imageBinding.autoComplete;
        completeTextView.setThreshold(1);
        completeTextView.setAdapter(adapter);

    }

    @SuppressLint("SetTextI18n")
    private void observeObservers() {
        viewModel.getCloseItem().observe(this, close -> {
            if (close)
                finish();
        });

        viewModel.getNominal().observe(this, integer -> {
            if (integer > 0){
                NumberFormat formatter = new DecimalFormat("#,###");
                binding.txtNominal.setText(String.format("Rp. %s", formatter.format(integer)));
            } else {
                binding.txtNominal.setText("Rp. 0");
            }
        });

        viewModel.getBank().observe(this, banks -> {
            if (banks != null){
                binding.bankAndNumber.setText(banks.getBankName()+"|"+banks.getAccountNumber());
            }
        });
    }



}
