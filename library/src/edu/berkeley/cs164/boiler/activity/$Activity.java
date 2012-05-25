package edu.berkeley.cs164.boiler.activity;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import edu.berkeley.cs164.boiler.$;

/*
 * $-sign madness!!!
 */
public class $Activity extends Activity implements $able {
	public $ $(int id) {
		return $.from(this, id);
	}

	public $ $(String klass) {
		return $.from(this, klass);
	}

	public $ $(View wrapped) {
		return $.from(this, wrapped);
	}

	public $ $(TextView wrapped) {
		return $.from(this, wrapped);
	}

	public $ $(ListView wrapped) {
		return $.from(this, wrapped);
	}

	public $ $(Activity wrapped) {
		return $.from(this, wrapped);
	}

	public $ $(Class<?> wrapped) {
		return $.from(this, wrapped);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		unbundleIntent();
	}

	private void unbundleIntent() {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Set<String> keys = extras.keySet();
			for (String key : keys) {
				Object value = extras.get(key);
				$(this).data(key, value);
			}
		}
	}
}
