package com.sds.oauth.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Cipher {
	
	public String caesarCipherEncrypt(String plain) {
		   String b64encoded = Base64.getEncoder().encodeToString(plain.getBytes());

		   // Reverse the string
		   String reverse = new StringBuffer(b64encoded).reverse().toString();

		   StringBuilder tmp = new StringBuilder();
		   final int OFFSET = 4;
		   for (int i = 0; i < reverse.length(); i++) {
		      tmp.append((char)(reverse.charAt(i) + OFFSET));
		   }
		   return tmp.toString();
		}
	
		public String caesarCipherDecrypte(String secret) {
		   StringBuilder tmp = new StringBuilder();
		   final int OFFSET = 4;
		   for (int i = 0; i < secret.length(); i++) {
		      tmp.append((char)(secret.charAt(i) - OFFSET));
		   }

		   String reversed = new StringBuffer(tmp.toString()).reverse().toString();
		   String returnValue = null;
			
		   try {
				returnValue = new String(Base64.getDecoder().decode(reversed),"UTF-8");
		   } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		   }
	
		   return returnValue;
		}
}
