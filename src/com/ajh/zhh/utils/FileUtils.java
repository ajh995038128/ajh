package com.ajh.zhh.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ajh.zhh.endecode.Coder;

public class FileUtils {
	/**
	 * @author benjamin 2013年7月18日 这个类是根据图片的地址将其转换成base64码以及将其转化回来的类
	 * @throws IOException
	 */
	public static String encode(String path) throws IOException {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(path);
		int len = 0;
		byte[] buffer = new byte[512];
		while ((len = fis.read(buffer)) != -1) {
			bao.write(buffer, 0, len);
		}
		fis.close();
		byte[] byteData = bao.toByteArray();
		bao.close();
		String encodeedStr = null;
		try {
			encodeedStr = Coder.encryptBASE64(byteData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return encodeedStr;
	}

	public static byte[] decodeToByteArray(String encodeStr) {
		try {
			return Coder.decryptBASE64(encodeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File decodeToFile(String encodeStr, String filename)
			throws IOException {
		final byte[] buffer = decodeToByteArray(encodeStr);
		File file = new File(filename);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(buffer, 0, buffer.length);
		fos.close();
		return null;

	}
}
