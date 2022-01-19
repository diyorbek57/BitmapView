package com.b12.lesson.activitys;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.b12.lesson.Hashtag;
import com.b12.lesson.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditTextActivity extends AppCompatActivity {


    TextInputLayout editText;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        inits();
    }

    private void editAndDisplayText() {
        String text = editText.getEditText().getText().toString();
        SpannableString hashtags = new SpannableString(text);
        setSpanComment(hashtags, getSpans(text));
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        textView.setText(hashtags);
    }

    private void inits() {
        editText = findViewById(R.id.edt_not_edited);
        button = findViewById(R.id.btn_send_entered_text);
        textView = findViewById(R.id.tv_edited_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAndDisplayText();
            }
        });

    }

    public ArrayList<int[]> getSpans(String text) {
        ArrayList<int[]> spans = new ArrayList<int[]>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int[] currentSpan = new int[2];
            currentSpan[0] = matcher.start();
            currentSpan[1] = matcher.end();
            spans.add(currentSpan);
        }
        return spans;
    }

    private void setSpanComment(SpannableString hashtagContent, ArrayList<int[]> hashtagSpans) {
        for (int i = 0; i < hashtagSpans.size(); i++) {
            int[] span = hashtagSpans.get(i);
            int hashTagStart = span[0];
            int hashTagEnd = span[1];
            hashtagContent.setSpan(new Hashtag(this), hashTagStart, hashTagEnd, 0);

        }

    }
}