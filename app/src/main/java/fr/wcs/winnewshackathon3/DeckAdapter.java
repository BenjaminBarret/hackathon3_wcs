package fr.wcs.winnewshackathon3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;


import java.util.List;

public class DeckAdapter extends ArrayAdapter<Pair<String, String>> {


    private final int mResource;

    DeckAdapter(@NonNull Context context, @NonNull List<Pair<String, String>> objects) {
        super(context, R.layout.item, objects);
        mResource = R.layout.item;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final RelativeLayout layout;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (RelativeLayout) inflater.inflate(mResource, parent, false);
        } else
            layout = (RelativeLayout) convertView;

        Pair item = getItem(position);
        if (item != null) {


            VideoView videoView = (VideoView) layout.findViewById(R.id.item_video);
            videoView.setVideoPath(String.valueOf(item.second));
        }
        return layout;
    }

}
