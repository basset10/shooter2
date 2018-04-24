package com.basset.shooter2;

import com.osreboot.ridhvl.HvlMath;

public class BlockCode {

	private char key;
	private int value[];

	public BlockCode(char keyArg, int valueArg) {
		value = new int[1];

		key = keyArg;
		value[0] = valueArg;
	}

	public BlockCode(char keyArg, int[] valuesArg) {
		value = valuesArg;
		key = keyArg;
	}


	public char getKey() {
		return key;
	}

	public int getValue() {
		return value[HvlMath.randomInt(value.length)];
	}


}
