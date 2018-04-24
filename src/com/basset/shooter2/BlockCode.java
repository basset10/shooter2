package com.basset.shooter2;

import com.osreboot.ridhvl.HvlMath;

public class BlockCode {

	private static char key;
	private static int value[];

	public BlockCode(char keyArg, int valueArg) {

		value = new int[0];

		key = keyArg;
		value[0] = valueArg;

	}

	public BlockCode(char keyArg, int[] valuesArg) {
		
		value = valuesArg;
		key = keyArg;
		
	}


	public static char getKey() {

		return key;

	}

	public static int getValue() {
		
		
		return value[HvlMath.randomInt(value.length)];

	}


}
