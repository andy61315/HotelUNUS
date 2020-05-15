package common;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;
 
/**
 * 
 * @author Andy.Chen
 * @mail Chenjunjun.ZJ@gmail.com
 *
 */
public class InputStreamUtils {
	
	final static int BUFFER_SIZE = 4096;
	
	/**
	 * 将InputStream转换成String
	 * @param in InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String InputStreamTOString(InputStream in) throws Exception{
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while((count = in.read(data,0,BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		
		data = null;
		return new String(outStream.toByteArray(),"ISO-8859-1");
	}
	
	/**
	 * 将InputStream转换成某种字符编码的String
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
         public static String InputStreamTOString(InputStream in,String encoding) throws Exception{
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while((count = in.read(data,0,BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		
		data = null;
		return new String(outStream.toByteArray(),"ISO-8859-1");
	}
    
    /**
     * 将String转换成InputStream
     * @param in
     * @return
     * @throws Exception
     */
    public static InputStream StringTOInputStream(String in) throws Exception{
    	
    	ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("ISO-8859-1"));
    	return is;
    }
    
    /**
     * 将InputStream转换成byte数组
     * @param in InputStream
     * @return byte[]
     * @throws IOException
     */
    public static byte[] InputStreamTOByte(Part part) throws IOException{
    	InputStream in = part.getInputStream();
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int count;
		while((count = in.read(buffer)) != -1) {
			outStream.write(buffer, 0, count);
		}
		outStream.close();
		in.close();
		
		return outStream.toByteArray();
    }
    
    /**
     * 将byte数组转换成InputStream
     * @param in
     * @return
     * @throws Exception
     */
    public static InputStream byteTOInputStream(byte[] in) throws Exception{
    	
    	ByteArrayInputStream is = new ByteArrayInputStream(in);
    	return is;
    }
    
    /**
     * 将byte数组转换成String
     * @param in
     * @return
     * @throws Exception
     */
    public static String byteTOString(byte[] in) throws Exception{
    	
    	InputStream is = byteTOInputStream(in);
    	return InputStreamTOString(is);
    }
 
}
