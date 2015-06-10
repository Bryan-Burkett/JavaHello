package com.ibm.bluemix.informix;

import java.io.Serializable;
import java.util.Random;

public class StringType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final int maxSize = 1024;
	public static final int maxKeySize = 127;
	
	private static final String CHAR_AND_NUMBER_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	
	public static String getRandomKey(Random randomizer, int maxLength, boolean isRelational) {
		StringBuffer keyName = new StringBuffer();
		int length = 0;
		while(length <= 0) {
			length = randomizer.nextInt(maxLength);
		}
		for(int i=0; i< length; i++){
			char ch;
			if(isRelational && i == 0) {
				int number = randomizer.nextInt(CHAR_LIST.length() - 1) + 1;
				ch = CHAR_LIST.charAt(number);
			} else {
				int number = randomizer.nextInt(CHAR_AND_NUMBER_LIST.length() - 1) + 1;
				ch = CHAR_AND_NUMBER_LIST.charAt(number);
			}
			keyName.append(ch);
		}
		
		if(keyName.length() == 0 && isRelational) { 
			int number = randomizer.nextInt(CHAR_LIST.length() - 1) + 1;
			char ch = CHAR_LIST.charAt(number);
			keyName.append(ch);
		}

		return keyName.toString();
	}	
}