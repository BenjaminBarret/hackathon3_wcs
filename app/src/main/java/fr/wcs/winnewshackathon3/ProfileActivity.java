package fr.wcs.winnewshackathon3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class ProfileActivity extends AppCompatActivity {

    private EditText mEditFirstname;
    private EditText mEditLastname;
    private EditText mEditEmployer;
    private Button mBtnSave;

    private String mUserID;

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mEditFirstname = findViewById(R.id.editText_firstname);
        mEditLastname = findViewById(R.id.editText_lastname);
        mEditEmployer = findViewById(R.id.editText_employer);

        mBtnSave = findViewById(R.id.button_save);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("contributors");

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        mUserID = mCurrentUser.getUid();

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditFirstname.getText().toString().isEmpty()
                        && !mEditLastname.getText().toString().isEmpty()) {

                    ContributorModel contributor = new ContributorModel();
                    if (!mEditEmployer.getText().toString().isEmpty()) {
                        contributor = new ContributorModel(mEditFirstname.getText().toString(),mEditLastname.getText().toString(),mEditEmployer.getText().toString());
                    } else {
                        contributor = new ContributorModel(mEditFirstname.getText().toString(),mEditLastname.getText().toString(),"undefined");
                    }

                    myRef.child(mUserID).setValue(contributor);
                    startActivity(new Intent(ProfileActivity.this,AddVideoActivity.class));

                }
            }
        });
    }
}
