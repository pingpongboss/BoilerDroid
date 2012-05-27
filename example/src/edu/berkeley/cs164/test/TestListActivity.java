package edu.berkeley.cs164.test;

import java.util.HashMap;
import java.util.Map;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import edu.berkeley.cs164.boiler.activity.BoilerListActivity;

public class TestListActivity extends BoilerListActivity {
	private Object[] listObjects;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();

		ListView lv = getListView();

		Map<String, Object> a = new HashMap<String, Object>();
		Map<String, Object> b = new HashMap<String, Object>();
		Map<String, Object> c = new HashMap<String, Object>();

		a.put("image", getResources().getDrawable(R.drawable.a));
		a.put("title", "A");
		a.put("description", "Hey");
		b.put("image", getResources().getDrawable(R.drawable.b));
		b.put("title", "B");
		b.put("description", "Bee!");
		c.put("image", getResources().getDrawable(R.drawable.c));
		c.put("title", "C");
		c.put("description", "See?");

		DataModel d = new DataModel(getResources().getDrawable(R.drawable.d),
				"D", "Dee");
		DataModel e = new DataModel(getResources().getDrawable(R.drawable.e),
				"E", "Eek!");

		listObjects = new Object[] { a, b, c, d, e };
		$(lv).setup(R.layout.list_item, listObjects).clickItem("clickList");
	}

	@SuppressWarnings("unchecked")
	public void clickList(int position) {
		Object clickedObject = listObjects[position];
		if (clickedObject instanceof Map) {
			Toast.makeText(
					this,
					((Map<String, Object>) clickedObject).get("title")
							.toString(), Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, ((DataModel) clickedObject).title,
					Toast.LENGTH_SHORT).show();
		}
	}

	public class DataModel {
		public Drawable image;
		public String title;
		public String description;

		public DataModel(Drawable image, String title, String description) {
			super();
			this.image = image;
			this.title = title;
			this.description = description;
		}

	}
}
