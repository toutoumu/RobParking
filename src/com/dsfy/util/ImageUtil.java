package com.dsfy.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理类
 * 1.图片缩放
 * 
 * 使用此类是会提示: Access restriction: The type JPEGImageEncoder is not accessible due to restriction on required library C:\Java\jre1.6.0_07\lib\rt.jar
 * import com.sun.image.codec.jpeg.JPEGCodec;
 * import com.sun.image.codec.jpeg.JPEGEncodeParam;
 * import com.sun.image.codec.jpeg.JPEGImageEncoder;
 * 此时解决办法：
 * 将Eclipse默认把这些受访问限制的API设成了ERROR。
 * 只要把Windows->Preferences->Java->Complicer->Errors/Warnings
 * 里面的Deprecated and restricted API
 * 中的Forbidden references(access rules)选为Warning就可以编译通过。
 * @author toutoumu
 *
 */
public class ImageUtil {
	/**
	 * 图片缩放,可设置图片质量
	 * @param originalFile 原始图片字节数组
	 * @param resizedFile 缩放后的图片
	 * @param newWidth 新的图片的宽度
	 * @param quality 质量(0-1之间)
	 * @throws IOException 异常
	 */
	public static void resize(byte[] originalFile, File resizedFile, int newWidth, float quality) throws IOException {
		ImageIcon ii = new ImageIcon(originalFile);
		resize(ii, resizedFile, newWidth, quality);
	}

	/**
	 * 图片缩放,可设置图片质量
	 * @param originalFile 原始图片
	 * @param resizedFile 缩放后的图片
	 * @param newWidth 新的图片的宽度
	 * @param quality 质量(0-1之间)
	 * @throws IOException 异常
	 */
	public static void resize(File originalFile, File resizedFile, int newWidth, float quality) throws IOException {
		ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
		resize(ii, resizedFile, newWidth, quality);
	} // Example usage

	private static void resize(ImageIcon ii, File resizedFile, int newWidth, float quality)
			throws FileNotFoundException, IOException {
		if (quality > 1) {
			throw new IllegalArgumentException("Quality has to be between 0 and 1");
		}
		Image i = ii.getImage();
		Image resizedImage = null;

		int iWidth = i.getWidth(null);
		int iHeight = i.getHeight(null);

		if (iWidth > iHeight) {
			resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);
		} else {
			resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);
		}

		// This code ensures that all the pixels in the image are loaded.
		Image temp = new ImageIcon(resizedImage).getImage();

		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null),
				BufferedImage.TYPE_INT_RGB);

		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();

		// Clear background and paint the image.
		g.setColor(Color.white);
		g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
		g.drawImage(temp, 0, 0, null);
		g.dispose();

		// Soften.
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0,
				softenFactor, 0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImage = cOp.filter(bufferedImage, null);

		// Write the jpeg to a file.
		FileOutputStream out = new FileOutputStream(resizedFile);

		// Encodes image as a JPEG data stream
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);

		param.setQuality(quality, true);

		encoder.setJPEGEncodeParam(param);
		encoder.encode(bufferedImage);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("adsf".lastIndexOf("."));
		System.out.println("abc".substring("abc".lastIndexOf(".") + 1));
		if (true) {
			int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
			System.out.println(System.currentTimeMillis() + ":" + mobile_code);
			return;
		}
		@SuppressWarnings("unused")
		File originalImage = new File("C:\\1207.gif");
		resize(originalImage, new File("c:\\1207-0.jpg"), 150, 0.7f);
		resize(originalImage, new File("c:\\1207-1.jpg"), 150, 1f);
	}
}
