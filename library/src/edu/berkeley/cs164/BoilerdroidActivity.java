package edu.berkeley.cs164;

import android.os.Bundle;
import edu.berkeley.cs164.boiler.activity.$Activity;
import edu.berkeley.cs164.boiler.util.$Tag;

public class BoilerdroidActivity extends $Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		$Tag.fromSelector(".status");

		$(".status").textColor("red").filter(".large").textStyle("italic");
		$(".large").padding(50, 0, 0, 0);
	}
}