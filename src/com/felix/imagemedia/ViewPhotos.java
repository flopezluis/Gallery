package com.felix.imagemedia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.felix.R;
import com.felix.R.id;
import com.felix.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ViewPhotos extends Activity implements OnClickListener{
	
	Button select;
	ImageView photo;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewphotos);
                        
        select = (Button) findViewById(R.id.ViewPhotos_select);
        select.setOnClickListener(this);        
        photo = (ImageView)findViewById(R.id.ViewPhotos_Image);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
        	String s = extras.getString("image");
        	Bitmap preview;
        	BitmapFactory.Options options = new BitmapFactory.Options();
    		options.inSampleSize = 4;
    		try {
    			preview = BitmapFactory.decodeStream(new FileInputStream(s), null, options);
    			photo.setImageBitmap(preview);
    		} catch (FileNotFoundException e) {
    		}
        }
        
    }

	@Override
	protected void onDestroy() {
		select = null;
		super.onDestroy();
	}
    
	/**
	 * 
	 */
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ViewPhotos_select:
				finish();
				break;
		default:
			break;
		}
	}

}