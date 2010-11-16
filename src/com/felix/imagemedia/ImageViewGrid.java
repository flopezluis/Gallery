package com.felix.imagemedia;

import java.util.ArrayList;

import com.felix.imagemedia.db.ImageMediaStore;

import android.content.Context;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * 
 * This class show in each element of the grid a thumbnail.
 * 
 * @author flopez
 */
public class ImageViewGrid extends BaseAdapter {
	private Context mContext;
	private ArrayList<ImageMediaStore> ids;
	private int size = 0;
	private ImageMediaStore imgMedia;
	/**
	 * 
	 * @param c
	 * @param ids
	 * @param size
	 * @param selectedIds
	 */
	public ImageViewGrid(Context c, ArrayList<ImageMediaStore> ids, int size, ArrayList<ImageMediaStore> selectedIds) {
		this.ids =ids;
		this.size = size;
		mContext = c;
		
	}

	public int getCount() {
		return ids.size();
	}

	public Object getItem(int position) {
		return null;
	}
	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView i = null;
		if (convertView == null) {
			i = new ImageView(mContext);
			i.setScaleType(ImageView.ScaleType.FIT_XY);
			i.setLayoutParams(new GridView.LayoutParams(size, size));
		} else {
			i = (ImageView) convertView;
		}
		imgMedia = ids.get(position);
		Log.d("Load thumbanil","for the image " + imgMedia.getImageId());
		i.setImageBitmap(MediaStore.Images.Thumbnails.getThumbnail(mContext.getContentResolver(), 
					imgMedia.getImageId(),Thumbnails.MICRO_KIND, null));
		return i;
	}
}
