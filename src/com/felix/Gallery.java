package com.felix;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.felix.imagemedia.ImageViewGrid;
import com.felix.imagemedia.ViewPhotos;
import com.felix.imagemedia.db.ImageHelper;
import com.felix.imagemedia.db.ImageMediaStore;

public class Gallery extends Activity{
	
	public static final int PADDING = 40;
	
	private ArrayList<ImageMediaStore> selectedImgMedia = new ArrayList<ImageMediaStore>();
	
    ArrayList<ImageMediaStore> ids;
    ArrayList<ImageMediaStore> originPaths = new ArrayList<ImageMediaStore>();
	private GridView g;
    private ImageViewGrid adapter = null;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_browser);
        g = (GridView) findViewById(R.id.PhoneImageGrid);
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        
		// Set the adapter to our custom adapter (below)
        ids = ImageHelper.getImageMedias(this, "");
        
        adapter = new ImageViewGrid(this, ids, (width >> 1) - PADDING, null);
		g.setAdapter(adapter);
		g.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Intent viewPhoto = new Intent (Gallery.this, ViewPhotos.class);
				viewPhoto.putExtra("image", ids.get(position).getNormalizePath());
				startActivity(viewPhoto);
				return false;
			}
			
			
		});
		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				if (!selectedImgMedia.contains(ids.get(position))) {
					Intent viewPhoto = new Intent (Gallery.this, ViewPhotos.class);
					viewPhoto.putExtra("image", ids.get(position).getPath());
					startActivity(viewPhoto);
				}
			}
		});
    }

	@Override
	protected void onDestroy() {
		selectedImgMedia.clear();
		selectedImgMedia = null;
		originPaths.clear();
		originPaths = null;
		ids.clear();
		ids = null;
		g.destroyDrawingCache();
		g = null;
		
		super.onDestroy();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
}