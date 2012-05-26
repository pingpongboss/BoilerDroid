package edu.berkeley.cs164.boiler.activity;

import android.app.Activity;
import android.view.View;
import edu.berkeley.cs164.boiler.$;

public interface $able {
	public $ $(int id);

	public $ $(String filter);

	public $ $(View wrapped);

	public $ $(Activity wrapped);

	public $ $(Class<?> wrapped);
}
