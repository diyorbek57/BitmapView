package com.b12.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ThirdActivity extends AppCompatActivity {

    TextInputLayout toEmailAddressEdt, subjectEmailAddressEdt, messageEdt;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        toEmailAddressEdt = findViewById(R.id.edt_email_to);
        subjectEmailAddressEdt = findViewById(R.id.edt_email_Subject);
        messageEdt = findViewById(R.id.edt_email_message);
        button=findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

    }

    private void backToHomeActivity() {
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToHomeActivity();
    }

    private void sendMail() {
        String recipientList = toEmailAddressEdt.getEditText().getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = subjectEmailAddressEdt.getEditText().getText().toString();
        String message = messageEdt.getEditText().getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}