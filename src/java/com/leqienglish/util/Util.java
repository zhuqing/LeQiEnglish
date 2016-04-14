package com.leqienglish.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.leqienglish.entity.TitleTip;

public class Util {
	public static List<TitleTip> listTitleTip;
	public static String activityEmail;
	public static String findPassword;
	private static long time = 0;
	public static String projectName = "duoduoroom";
	public static Map<String, String> userMap = new HashMap<String, String>();

	/**
	 * 创建全局唯一的Id
	 * @return
	 */
	public synchronized static long getID() {

		long temp = System.currentTimeMillis();
		if (temp == time) {
			time += 1;
		} else {
			time = temp;
		}

		return time;

	}

	

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * 创建密匙
	 * 
	 * @param algorithm
	 *            加密算法,可用 DES,DESede,Blowfish
	 * @return SecretKey 秘密（对称）密钥
	 */
	public static SecretKey createSecretKey(String algorithm) {
		// 声明KeyGenerator对象
		KeyGenerator keygen;
		// 声明 密钥对象
		SecretKey deskey = null;
		try {
			// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
			keygen = KeyGenerator.getInstance(algorithm);
			// 生成一个密钥
			deskey = keygen.generateKey();
			// keygen.
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 返回密匙
		return deskey;
	}

	public static String decryptByDES(String sInfo) {
		// 定义 加密算法,
		String k = "1055d3e698d289f2af8663725127bd4b";
		String Algorithm = "DES";

		// key.
		// 加密随机数生成器 (RNG)
		SecureRandom sr = new SecureRandom();
		byte[] cipherByte = null;
		try {
			SecretKey key = Util.toKey(k, Algorithm);
			// 得到加密/解密器
			Cipher c1 = Cipher.getInstance(Algorithm);
			// 用指定的密钥和模式初始化Cipher对象
			c1.init(Cipher.DECRYPT_MODE, key, sr);
			// 对要解密的内容进行编码处理
			cipherByte = c1.doFinal(base64StrToByteArray(sInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return byte2hex(cipherByte);
		return new String(cipherByte);
	}

	/**
	 * 将base64编码后的密钥字符串转换成密钥对象
	 * 
	 * @param key
	 *            密钥字符串
	 * @param algorithm
	 *            加密算法
	 * @return 返回密钥对象
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	private static SecretKey toKey(String key, String algorithm)
			throws Exception {
		// SecretKey secretKey = new SecretKeySpec(base64StrToByteArray(key),
		// algorithm);
		DESKeySpec dks = new DESKeySpec(hex2byte(key));
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
	}

	/**
	 * 将base64码转换成字节数组
	 * 
	 * @param base64Str
	 *            base64码
	 * @return 返回转换后的字节数组
	 */
	public static byte[] base64StrToByteArray(String base64Str) {
		char[] dataArr = new char[base64Str.length()];
		base64Str.getChars(0, base64Str.length(), dataArr, 0);
		return decode(dataArr);
	}

	/**
	 * 将一个base64字符数组解码成一个字节数组
	 * 
	 * @param data
	 *            base64字符数组
	 * @return 返回解码以后的字节数组
	 */
	private static byte[] decode(char[] data) {
		int len = ((data.length + 3) / 4) * 3;
		if (data.length > 0 && data[data.length - 1] == '=')
			--len;
		if (data.length > 1 && data[data.length - 2] == '=')
			--len;
		byte[] out = new byte[len];
		int shift = 0;
		int accum = 0;
		int index = 0;
		for (int ix = 0; ix < data.length; ix++) {
			int value = codes[data[ix] & 0xFF];
			if (value >= 0) {
				accum <<= 6;
				shift += 6;
				accum |= value;
				if (shift >= 8) {
					shift -= 8;
					out[index++] = (byte) ((accum >> shift) & 0xff);
				}
			}
		}
		if (index != out.length)
			throw new Error("miscalculated data length!");
		return out;
	}

	/**
	 * 初始化base64字符集表
	 */
	static private byte[] codes = new byte[256];

	/**
	 * 将二进制转化为16进制字符串
	 * 
	 * @param b
	 *            二进制字节数组
	 * @return String
	 */
	public String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * 十六进制字符串转化为2进制
	 * 
	 * @param hex
	 * @return
	 */
	private static byte[] hex2byte(String hex) {
		byte[] ret = new byte[8];
		byte[] tmp = hex.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

}
