package edu.berkeley.cs164.boiler.util;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import edu.berkeley.cs164.boiler.iter.$GeneratorFactory;

public class BoilerUtils {
	public static View getRootView(Object link) {
		if (link instanceof Activity) {
			Activity activity = (Activity) link;
			return ((ViewGroup) activity.findViewById(android.R.id.content))
					.getChildAt(0);
		}

		return null;
	}

	public static View getParent(View element) {
		return (View) element.getParent();
	}

	public static <T> void forEach($GeneratorFactory<? extends T> factory,
			Activity link, $Action<T> action) {
		Generator<? extends T> generator = factory.createGenerator();
		for (T object : generator) {
			try {
				action.$(object, link);
			} catch (ClassCastException ex) {
			}
		}
	}

	public static int colorStringToInt(String color) throws Exception {
		if ("".equals(color))
			throw new Exception("Color [" + color + "] is not a valid color.");

		int colorInt = -1;
		try {
			if (color.charAt(0) == '#') {
				if (color.length() == 7) {
					colorInt = Color.rgb(
							Integer.parseInt(color.substring(1, 3), 16),
							Integer.parseInt(color.substring(3, 5), 16),
							Integer.parseInt(color.substring(5, 7), 16));
				} else if (color.length() == 9) {
					colorInt = Color.argb(
							Integer.parseInt(color.substring(1, 3), 16),
							Integer.parseInt(color.substring(3, 5), 16),
							Integer.parseInt(color.substring(5, 7), 16),
							Integer.parseInt(color.substring(7, 9), 16));
				}
			} else {
				colorInt = Color.class.getDeclaredField(color.toUpperCase())
						.getInt(null);
			}
		} catch (Exception ex) {
			throw new Exception("Color [" + color + "] is not a valid color.",
					ex);
		}

		return colorInt;
	}

	public interface $Action<T> {
		void $(T object, Activity link);
	}
}
