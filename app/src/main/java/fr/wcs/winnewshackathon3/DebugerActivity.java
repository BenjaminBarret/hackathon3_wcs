package fr.wcs.winnewshackathon3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class DebugerActivity extends AppCompatActivity {
private String mAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debuger);
        TextView tvAdress = findViewById(R.id.tv_adress);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setHint("Adresse");


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
// TODO: Get info about the selected place.

                TextView adressSelected = findViewById(R.id.tv_adress);
                mAdress = place.getAddress().toString();

                adressSelected.setText(place.getAddress());
                String lat = String.valueOf(place.getLatLng().latitude);
                String lng = String.valueOf(place.getLatLng().longitude);
                Toast.makeText(DebugerActivity.this, "Coordonnées GPS:"+ " " +"lattitude:"+" " + lat  + " / " +"longitude:"+" " + lng, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Status status) {

                Toast.makeText(DebugerActivity.this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
// TODO: Handle the error.

            }
        });

    }

}
