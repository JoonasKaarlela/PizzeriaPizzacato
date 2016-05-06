package Pizzacato.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;







import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Utils {
	
	public String generate(int length){
		String code = "";
		for (int i = 0; i < length; i++) {
			code += "" + new Random().nextInt(9);
		}
		return code;
	}
	
	public String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		Date date = new Date();
		String now = dateFormat.format(date);

		return now;
	}
	
	
	public String objectToString(Object obj) throws IOException{
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bytearrayoutputstream);
		objectOutputStream.writeObject(obj);
		objectOutputStream.close();
		String encoded = new String(Base64.encode(bytearrayoutputstream.toByteArray()));
		return encoded;
	}
	
	public Object stringToObject(String encoded) throws IOException, ClassNotFoundException{
		byte[] bytes = Base64.decode(encoded);
	    ObjectInputStream objectinputstream = new ObjectInputStream( new ByteArrayInputStream(bytes) );
	    Object decoded = (Object) objectinputstream.readObject(); 
		return decoded;
	}

}
