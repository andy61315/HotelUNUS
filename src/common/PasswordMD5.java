package common;

import java.security.MessageDigest;

public class PasswordMD5 {
	 public String getRandomPassword(int pwdLength) {
		    int z;
		    StringBuilder sb = new StringBuilder();
		    int i;
		    for (i = 0; i < pwdLength; i++) {
		      z = (int) ((Math.random() * 7) % 3);
		 
		      if (z == 1) { // 放數字
		        sb.append((int) ((Math.random() * 10) + 48));
		      } else if (z == 2) { // 放大寫英文
		        sb.append((char) (((Math.random() * 26) + 65)));
		      } else {// 放小寫英文
		        sb.append(((char) ((Math.random() * 26) + 97)));
		      }
		    }
		    return sb.toString();
		  }
	 
		public static String string2MD5(String inStr) {
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				e.printStackTrace();
			}
			char[] charArray = inStr.toCharArray();
			byte[] byteArray = new byte[charArray.length];

			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		}
	 
		public static String convertMD5(String inStr) {

			char[] a = inStr.toCharArray();
			for (int i = 0; i < a.length; i++) {
				a[i] = (char) (a[i] ^ 't');
			}
			String s = new String(a);
			return s;

		}	
	
	public static void main(String[] args) {
		PasswordMD5 passwordMD5 = new PasswordMD5();
		
		String pwd = passwordMD5.getRandomPassword(10);
		System.out.println(pwd);
		System.out.println(passwordMD5.string2MD5(pwd));
		String convertPwd = convertMD5(pwd);
		System.out.println(convertMD5(pwd));
		System.out.println(convertMD5(convertMD5(pwd)));
		System.out.println(convertMD5(convertPwd));
		
		
	}
	
}
