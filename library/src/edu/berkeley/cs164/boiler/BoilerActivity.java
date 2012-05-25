package edu.berkeley.cs164.boiler;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import edu.berkeley.cs164.boiler.iter.$GeneratorFactory;

public class BoilerActivity extends $ {

	public BoilerActivity(Activity link, $GeneratorFactory<?> factory) {
		super(link, factory);
	}

	public void start() {
		Activity activity = (Activity) link;
		Object wrapped = get();
		// need check because we allow both Class and Activity
		if (wrapped instanceof Class<?>) {
			Intent in = new Intent(activity, (Class<?>) get());
			Map<String, Object> data = data();
			for (String key : data.keySet()) {
				Object value = data.get(key);
				if (value instanceof String) {
					in.putExtra(key, (String) value);
				} else if (value instanceof Boolean) {
					in.putExtra(key, (Boolean) value);
				} else if (value instanceof Boolean[]) {
					in.putExtra(key, (Boolean[]) value);
				} else if (value instanceof String[]) {
					in.putExtra(key, (String[]) value);
				} else if (value instanceof Byte) {
					in.putExtra(key, (Byte) value);
				} else if (value instanceof Byte[]) {
					in.putExtra(key, (Byte[]) value);
				} else if (value instanceof Character) {
					in.putExtra(key, (Character) value);
				} else if (value instanceof Character[]) {
					in.putExtra(key, (Character[]) value);
				} else if (value instanceof Integer) {
					in.putExtra(key, (Integer) value);
				} else if (value instanceof Integer[]) {
					in.putExtra(key, (Integer[]) value);
				} else if (value instanceof Double) {
					in.putExtra(key, (Double) value);
				} else if (value instanceof Double[]) {
					in.putExtra(key, (Double[]) value);
				} else if (value instanceof Float) {
					in.putExtra(key, (Float) value);
				} else if (value instanceof Float[]) {
					in.putExtra(key, (Float[]) value);
				} else if (value instanceof Long) {
					in.putExtra(key, (Long) value);
				} else if (value instanceof Long[]) {
					in.putExtra(key, (Long[]) value);
				}
			}
			activity.startActivity(in);
		}
	}
}
