package edu.berkeley.cs164.boiler.activity;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import edu.berkeley.cs164.boiler.$;

public interface $able {
	public $ $(int id);

	public $ $(String klass);

	public $ $(View wrapped);

	public $ $(TextView wrapped);

	public $ $(ListView wrapped);

	public $ $(Activity wrapped);

	public $ $(Class<?> wrapped);
}
