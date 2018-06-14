package fr.wcs.winnewshackathon3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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



    }
}
