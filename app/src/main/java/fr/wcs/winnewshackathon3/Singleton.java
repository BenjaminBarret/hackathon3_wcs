package fr.wcs.winnewshackathon3;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Singleton {

    private static Singleton sIntance = null;

    private ContributorModel mUser = null;

    private ArrayList<ArticleModel> mArticle = null;

    private Singleton() {
        loadUser();
        loadArrayList();
    }

    public static Singleton getsIntance() {
        if (sIntance == null) {
            sIntance = new Singleton();
        }
        return sIntance;
    }


    public void loadUser() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            final String userId = user.getUid();
            DatabaseReference userRef = database.getReference("contributors").child(userId);
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mUser = dataSnapshot.getValue(ContributorModel.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void loadArrayList() {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("video");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mArticle = new ArrayList<>();
                for (DataSnapshot articleSnap : dataSnapshot.getChildren()) {
                    ArticleModel article = articleSnap.getValue(ArticleModel.class);
                    mArticle.add(article);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ContributorModel getUser() {
        return mUser;
    }

    public ArrayList<ArticleModel> getArrayList() {
        return mArticle;
    }

}
