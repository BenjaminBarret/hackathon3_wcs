package fr.wcs.winnewshackathon3;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddVideoActivity extends AppCompatActivity {

    private EditText mEditTitreArticle;
    private EditText mEditUrlArticle;

    private Button mCaptureVid;
    private Button mPosterArticle;

    private Spinner mTagArticle;

    private String mTag;
    private String titreString;
    private String urlArticleString;

    VideoView mVideoView;

    FirebaseDatabase database;
    DatabaseReference myRef;
    StorageReference mStorageRef;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    private String mUserID;

    Uri videoUri;

    static final int REQUEST_VIDEO_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);

        mEditTitreArticle = findViewById(R.id.editText_titre_video);
        mEditUrlArticle = findViewById(R.id.editText_url_article);
        mCaptureVid = findViewById(R.id.button_capture_video);
        mPosterArticle = findViewById(R.id.button_poster_article);

        mTagArticle = findViewById(R.id.spinner_tag_video);

        mVideoView = findViewById(R.id.videoView_preview);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mUserID = mCurrentUser.getUid();

        Singleton singleton = Singleton.getsIntance();
        final ContributorModel contributor = singleton.getUser();


        database = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        myRef = database.getReference("video");


        //mTag = mTagArticle.getSelectedItem().toString().toLowerCase();

        mCaptureVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakeVideoIntent();
            }
        });

        mPosterArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditTitreArticle.getText().toString().isEmpty()
                        && !mEditUrlArticle.getText().toString().isEmpty()
                        && videoUri != null
                        && !mTagArticle.getSelectedItem().toString().equals("..")) {

                    StorageReference riverRef = mStorageRef.child("video").child(videoUri.getLastPathSegment());

                    riverRef.putFile(videoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            String url = downloadUrl.toString();
                                // TODO : ENVOI MODELE VIDEO

                            ArticleModel article = new ArticleModel(url,
                                    mTagArticle.getSelectedItem().toString(),
                                    mEditUrlArticle.getText().toString(),
                                    mEditTitreArticle.getText().toString(),
                                    contributor.getFirstname(),
                                    contributor.getLastname(),
                                    contributor.getEmployer());


                            myRef.push().setValue(article);

                            Toast.makeText(AddVideoActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddVideoActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    // ------------------- PRENDRE VIDEO ET PREVIEW VIDEO -----------------------------------------

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = intent.getData();
            mVideoView.setVideoURI(videoUri);
            mVideoView.setMediaController(new MediaController(this));
            mVideoView.requestFocus();
            mVideoView.start();
        }
    }

}
