package com.basset.shooter2;

import com.osreboot.ridhvl.HvlMath;

public class EnemyCode {

	private char key;
	private int value[];

	public EnemyCode(char keyArg, int valueArg) {
		value = new int[1];

		key = keyArg;
		value[0] = valueArg;
	}

	public EnemyCode(char keyArg, int[] valuesArg) {
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
