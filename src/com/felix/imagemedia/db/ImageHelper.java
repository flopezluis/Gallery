/**
 * 
 */
package com.felix.imagemedia.db;

import java.util.ArrayList;


import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;

/**
 * 
 * 
 * @author flopez
 *
 */
public class ImageHelper {

	/**
	 * It returns the ImageMediaStore for the images in the phone.
	 * @param activity
	 * @param where
	 * @return
	 */
	public static ArrayList<ImageMediaStore> getImageMedias(Activity activity, String where) {
		ArrayList<ImageMediaStore> ids = new ArrayList<ImageMediaStore>();
		String [] projI={ MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        // 	Now we create the cursor pointing to the external image store
        Cursor cursor = activity.managedQuery( MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        		projI,   // Which columns to return
        		where,   // WHERE clause; which rows to return (all rows)
        		null,	 // WHERE clause selection arguments (none)
        		null);   // Order-by clause (ascending by name)
        
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
        int column_path = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    	while (cursor.moveToNext()) {
    		ImageMediaStore mediaImage = new ImageMediaStore(cursor.getInt(column_index), cursor.getString(column_path));
    		ids.add(mediaImage);
    	}
        cursor.close();
        activity = null;
		return ids;
	}
}