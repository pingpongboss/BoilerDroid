package edu.berkeley.cs164.boiler.activity;

import java.util.Set;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import edu.berkeley.cs164.boiler.$;

public class BoilerListActivity extends ListActivity implements $able {
	public $ $(int id) {
		return $.from(this, id);
	}

	public $ $(String filter) {
		return $.from(this, filter);
	}

	public $ $(View wrap) {
		return $.from(this, wrap);
	}

	public $ $(Activity wrap) {
		return $.from(this, wrap);
	}

	public $ $(Class<?> wrap) {
		return $.from(this, wrap);
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
