/**
 * 
 */
package com.oneg.whsquared.util;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;

/**
 * @author Anbukkani G
 * 
 */
@SuppressWarnings("restriction")
public class ImageUtil {

	public static byte[] Base64ToBytes(String imageString) {
		if (imageString == null) {
			return null;
		}
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decodedBytes = decoder.decodeBuffer(imageString);
			return decodedBytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String bytesToBase64(byte[] encodedBytes) {
		if (encodedBytes == null) {
			return null;
		}
		return new String(Base64.encodeBase64(encodedBytes));
	}
}
