package com.br.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import com.google.zxing.BarcodeFormat;
import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

public class Barcode {
	private String name;
	
	
	public static String decodeCode(MultipartFile img) throws IOException, NotFoundException, FormatException, ChecksumException {
	    Map<DecodeHintType, Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(
	            DecodeHintType.class);
	    tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
	    tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS,
	            EnumSet.allOf(BarcodeFormat.class));
	    tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
		BufferedImage image = ImageIO.read(img.getInputStream());
		// BufferedImage cropedImage = image.getSubimage(0, 0, 914, 400);
	//    BinaryBitmap bitmap = convertImageToBinaryBitmap(cropedImage);
	    LuminanceSource source = new BufferedImageLuminanceSource(image);
	     BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	    MultiFormatReader reader = new MultiFormatReader();  
	   // Bitmap resize = Bitmap.createScaledBitmap(srcBitmap, dstWidth,dstHeight,false);
	    Result result = reader.decode(bitmap,tmpHintsMap);
	    return result.getText();
	}
	
	/*public static void main(final String[] args) throws Exception {
	    final File imageFile = new File("bar3.jpg");
	    final ITesseract instance = new Tesseract();
	    instance.setTessVariable("tessedit_char_whitelist", "0123456789");
	    final String result = instance.doOCR(imageFile);
	    System.out.println(result);
	}
	*/

	 
	 public BinaryBitmap convertImageToBinaryBitmap(BufferedImage image) {
	    int[] pixels = image.getRGB(0, 0,
	                                image.getWidth(), image.getHeight(),
	                                null, 0, image.getWidth());
	    RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(),
	                                                       image.getHeight(),
	                                                       pixels);
	    return new BinaryBitmap(new HybridBinarizer(source));
	  }
}
