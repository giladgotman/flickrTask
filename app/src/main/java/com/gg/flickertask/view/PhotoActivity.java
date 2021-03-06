package com.gg.flickertask.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.gg.flickertask.R;
import com.gg.flickertask.model.Photo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Full screen photo with inormation
 */
public class PhotoActivity extends AppCompatActivity {
    
    private static final String TAG = PhotoActivity.class.getSimpleName();
    @BindView(R.id.fullscreenPhoto)
    ImageView mFullScreenPhoto;
    @BindView(R.id.owener_name_value_txv)
    TextView mOwnerNameTxv;
    @BindView(R.id.date_value_txv)
    TextView mDateTxv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.setDebug(true);
        ButterKnife.bind(PhotoActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent photoIntent = getIntent();
        if (photoIntent != null) {
            Photo photo = (Photo) photoIntent.getSerializableExtra(MainActivity.PHOTO_OBJECT_KEY);
            toolbar.setTitle(photo.getTitle());
            String photoUrl = photo.getUrlLargeSize();
            if (photoUrl == null) {
                photoUrl = photo.getUrlOriginalSize();
            }
            if (photoUrl == null) {
                photoUrl = photo.getUrlSmallSize();
            }
            if (photoUrl != null) {
                Picasso.with(PhotoActivity.this)
                        .load(photoUrl)
                        .placeholder( R.drawable.progress_animation )
                        .error(R.drawable.default_photo_image)
                        .into(mFullScreenPhoto);

            } else {
                Log.e(TAG, "onCreate: url is null");
            }
            mOwnerNameTxv.setText(photo.getOwenerName());
            mDateTxv.setText(photo.getDateTaken());
            Log.d(TAG, "onCreate: photo title:" + photo.getTitle());

        }
        setSupportActionBar(toolbar);
    }
}
