package Pizzacato.model;
import java.util.Random;

public class Utils {
	public String generate(int length){
		String code = "";
		for (int i = 0; i < length; i++) {
			code += "" + new Random().nextInt(0 - 9 + 1);
		}
		return code;
	}
}
