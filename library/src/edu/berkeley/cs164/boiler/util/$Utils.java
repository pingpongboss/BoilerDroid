package edu.berkeley.cs164.boiler.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import edu.berkeley.cs164.boiler.iter.$FilterGenerator;
import edu.berkeley.cs164.boiler.iter.$FilterGenerator.$Filter;
import edu.berkeley.cs164.boiler.iter.$LayoutGenerator;
import edu.berkeley.cs164.boiler.iter.$ResettableIterable;

public class $Utils {
	public static View getRootView(Activity activity) {
		return ((ViewGroup) activity.findViewById(android.R.id.content))
				.getChildAt(0);
	}

	public static View getParent(View element) {
		return (View) element.getParent();
	}

	public static <T> void forEach($ResettableIterable<T> items, Activity link,
			$Action<T> action) {
		items = items.reset();

		int i = 0;
		for (T item : items) {
			action.$(item, link, i++);
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

	public static Object callMethod(String methodName, Object receiver,
			Object... args) {
		try {
			Method method = null;
			try {
				method = receiver.getClass().getMethod(methodName);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				try {
					method = receiver.getClass().getMethod(methodName,
							View.class);
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				}
			}

			if (method != null) {
				if (method.getParameterTypes().length == 0) {
					return method.invoke(receiver);
				} else {
					return method.invoke(receiver, args);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static $ResettableIterable<Object> filter(
			$ResettableIterable<Object> items, final String filter) {
		return new $FilterGenerator<Object>(items.reset(), new $Filter<Object>() {

			@Override
			public boolean $(Object object) {
				if (object instanceof View) {
					$Tag viewTag = $Tag.normalizeTag((View) object);

					return viewTag.matches(filter);
				}
				return false;
			}

		});
	}

	public static $ResettableIterable<Object> filter(Activity link,
			String filter) {
		return filter(new $LayoutGenerator(link), filter);
	}

	public interface $Action<T> {
		void $(T object, Activity link, int index);
	}
}
