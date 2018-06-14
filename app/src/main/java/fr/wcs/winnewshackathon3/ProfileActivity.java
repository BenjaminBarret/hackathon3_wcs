package fr.wcs.winnewshackathon3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    private EditText mEditFirstname;
    private EditText mEditLastname;
    private EditText mEditEmployer;
    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mEditFirstname = findViewById(R.id.editText_firstname);
        mEditLastname = findViewById(R.id.editText_lastname);
        mEditEmployer = findViewById(R.id.editText_employer);

        mBtnSave = findViewById(R.id.button_save);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditFirstname.getText().toString().isEmpty()
                        && !mEditLastname.getText().toString().isEmpty()) {



                }
            }
        });
    }
}
