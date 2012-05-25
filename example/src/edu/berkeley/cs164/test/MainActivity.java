package edu.berkeley.cs164.test;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import edu.berkeley.cs164.boiler.activity.$Activity;

public class MainActivity extends $Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		$(R.id.button1).click("button1");
		$(R.id.button2).click("button2");
		$(R.id.button3).click("button3");
		$(R.id.button4).click("button4");

		$(R.id.text).width("fill").click("textClick");

		$(R.id.e).click(new OnClickListener() {

			@Override
			public void onClick(View v) {
				$("button").width("fill");
			}
		});
	}

	public void textClick() {
		Log.d("Boiler", "clicked");
	}

	public boolean textLongClick(View v) {
		Log.d("Boiler", v.getId() + " long clicked");
		return true;
	}

	public void button1() {
		$("status").text("clicked b1").textColor(Color.BLUE).hide();
	}

	public void button2() {
		$("status").text("clicked b2").textColor("red").show();
	}

	public void button3() {
		$("status").text("Lauch test: BoilerListView").textColor("#ff00ff")
				.visible(true);
		$(TestListActivity.class).start();
	}

	public void button4() {
		$("status").text("Launch test: BoilerActivity").textColor(Color.YELLOW)
				.visible(View.GONE);
		String bundleItem2 = "test2";
		int bundleItem3 = 41;

		$(TestActivityActivity.class).data("test", "Test")
				.data("test2", bundleItem2).data("int1", bundleItem3)
				.data("int2", 1).start();
	}

}