package controller.db;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.imageio.ImageIO;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * Deal with image and byte array.
 * 
 * @author JGroup
 *
 */
public class ImageUtils {
	
	/**
	 * Convert image to input stream.
	 * 
	 * @param image Image file.
	 * @return InputStream.
	 */
	public static InputStream imageToByte(Image image) {
		if (image != null) {
			BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				ImageIO.write(bImage, "png", bos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] res = bos.toByteArray();
			ByteArrayInputStream bis = new ByteArrayInputStream(res);
			return bis;
		}
		return null;
	}
	
	/**
	 * Convert blob to image.
	 * 
	 * @param blob Blob.
	 * @return Image file.
	 */
	public static Image byteToImage(Blob blob) {
		InputStream is;
		try {
			if (blob != null) {
				is = blob.getBinaryStream();
				BufferedImage bImage = ImageIO.read(is);
				Image image = SwingFXUtils.toFXImage(bImage, null);
				return image;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;  
	}
	
}
