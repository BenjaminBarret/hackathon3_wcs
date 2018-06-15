package fr.wcs.winnewshackathon3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooseActivity extends AppCompatActivity {

    private ImageView mReporter;
    private ImageView mInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);



        mReporter = findViewById(R.id.imageView_reporter);
        mInfos = findViewById(R.id.imageView_news);

        mReporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddVideo = new Intent(ChooseActivity.this,MainActivity.class);
                startActivity(goToAddVideo);
            }
        });

        mInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNews = new Intent(ChooseActivity.this,NewsActivity.class);
                startActivity(goToNews);
            }
        });

    }
}
