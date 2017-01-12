package com.oneg.whsquared.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * @author Jaiprabhu This generic method will return response type of
 *         status,messages and user validated records.
 */
@Component
public class Util<T> {

	static String fileLocation = "/var/uploadeFiles";

	public ResponseType<T> response(String status, String message, T data) {
		return new ResponseType<T>(status, message, data);
	}

	public String uploadFileWithBase64String(String base64File) {
		try {
			if (base64File == null) {
				return null;
			}
			File dir = new File(fileLocation);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String ext = "png";
			byte[] data = org.apache.commons.codec.binary.Base64.decodeBase64(base64File);
			String fileName = UUID.randomUUID().toString() + '.' + ext;
			try (OutputStream stream = new FileOutputStream(fileLocation + '/' + fileName)) {
				stream.write(data);
			}
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String uploadFile(String base64File) {
		try {
			if (base64File == null) {
				return null;
			}
			File dir = new File(fileLocation);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String[] fileData = base64File.split(",");
			// it is a real file path
			if (fileData.length == 1) {
				return base64File;
			}
			String contentType = fileData[0].split(":")[1].replace(";base64", "");
			String type = contentType.split("/")[1];
			String ext = "";
			switch (type) {
			case "jpeg":
				ext = "jpg";
				break;
			case "png":
				ext = "png";
				break;
			default:
				ext = type;
			}
			byte[] data = org.apache.commons.codec.binary.Base64.decodeBase64(fileData[1]);
			String fileName = UUID.randomUUID().toString() + '.' + ext;
			try (OutputStream stream = new FileOutputStream(fileLocation + '/' + fileName)) {
				stream.write(data);
			}
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void writeFileInResponse(HttpServletResponse response, String fileName) {
		File file = new File(fileLocation + "/" + fileName);
		if (file.exists()) {
			try {
				OutputStream os = response.getOutputStream();
				byte[] buf = new byte[8192];
				InputStream is = new FileInputStream(file);
				int c = 0;
				while ((c = is.read(buf, 0, buf.length)) > 0) {
					os.write(buf, 0, c);
					os.flush();
				}
				os.close();
				is.close();
			} catch (IOException e) {
				// e.printStackTrace();
				System.out.println("Not found");
			}
		}
	}

	public boolean deleteFile(String fileUri) {
		if (fileUri == null || fileUri.isEmpty()) {
			return false;
		}
		File file = new File(fileLocation + "/" + fileUri);
		return file.delete();
	}
}
