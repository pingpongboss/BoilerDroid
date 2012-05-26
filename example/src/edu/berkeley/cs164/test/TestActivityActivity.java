package edu.berkeley.cs164.test;

import android.os.Bundle;
import edu.berkeley.cs164.boiler.$;
import edu.berkeley.cs164.boiler.activity.$Activity;

public class TestActivityActivity extends $Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity_activity);

		$(R.id.text1).text(
				$(this).data("test").toString()
						+ $(this).data("test2").toString());
		$ $ = $(this);
		$(R.id.text2).text(
				String.valueOf((Integer) $.data("int1")
						+ (Integer) $.data("int2")));
	}
}
