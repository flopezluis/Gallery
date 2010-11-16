/**
 * 
 */
package com.felix.imagemedia.db;

/**
 * 
 * Represents a imagemedia.
 * @author flopez
 *
 */
public class ImageMediaStore {

	private String path;
	private int image_id;
	
	/**
	 * 
	 * @param image_id
	 * @param path
	 */
	public ImageMediaStore(int image_id, String path) {
		this.image_id = image_id;
		this.path = path;
	}

	/**
	 * It gets the id of the real image.
	 * @return
	 */
	public int getImageId() {
		return image_id;
	}

	/**
	 * @return the real path of an image.
	 */
	public String getNormalizePath() {
		return path.replaceFirst("/mnt", "");
	}
	
	/**
	 * @return the path of the thumbnail.
	 */
	public String getPath() {
		return path;
	}
}
