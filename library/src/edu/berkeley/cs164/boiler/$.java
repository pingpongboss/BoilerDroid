package edu.berkeley.cs164.boiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import edu.berkeley.cs164.boiler.iter.$ResettableIterable;
import edu.berkeley.cs164.boiler.iter.$SingleGenerator;
import edu.berkeley.cs164.boiler.util.$ArrayAdapter;
import edu.berkeley.cs164.boiler.util.$Utils;
import edu.berkeley.cs164.boiler.util.EachCallback;

public class $ {
	public static $ from(Activity link, Activity wrapped) {
		return new $(link, new $SingleGenerator<Object>(wrapped));
	}

	public static $ from(Activity link, Class<?> wrapped) {
		return new $(link, new $SingleGenerator<Object>(wrapped));
	}

	public static $ from(Activity link, int id) {
		View view = $Utils.getRootView(link).findViewById(id);

		return from(link, new $SingleGenerator<Object>(view));
	}

	public static $ from(Activity link, String filter) {
		return from(link, $Utils.filter(link, filter));
	}

	public static $ from(Activity link, View wrapped) {
		return new $(link, new $SingleGenerator<Object>(wrapped));
	}

	private static $ from(Activity link, $ResettableIterable<Object> items) {
		return new $(link, items);
	}

	protected Activity link;
	protected $ResettableIterable<Object> items;

	private static WeakHashMap<Object, Map<String, Object>> dataMap = new WeakHashMap<Object, Map<String, Object>>();

	protected $(Activity link, $ResettableIterable<Object> list) {
		this.link = link;
		this.items = list;
	}

	@SuppressWarnings("unchecked")
	public $ add(Collection<Object> items) {
		Object object = get();
		if (object instanceof ListView) {
			ListAdapter adapter = ((ListView) object).getAdapter();
			if (adapter instanceof ArrayAdapter<?>) {
				ArrayAdapter<Object> arrayAdapter = ((ArrayAdapter<Object>) adapter);
				for (Object item : items) {
					arrayAdapter.add(item);
				}
			}
		}
		return this;
	}

	public $ add(final Object item) {
		return add(new Object[] { item });
	}

	public $ add(Object[] items) {
		return add(Arrays.asList(items));
	}

	public Drawable background() {
		Object object = get();
		if (object instanceof View) {
			return ((View) object).getBackground();
		}
		return null;
	}

	public $ background(final Drawable drawable) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.setBackgroundDrawable(drawable);
				}
			}
		});

		return this;
	}

	public $ background(final int resid) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.setBackgroundResource(resid);
				}
			}
		});

		return this;
	}

	public $ backgroundColor(final int color) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.setBackgroundColor(color);
				}
			}
		});

		return this;
	}

	public $ backgroundColor(String color) {
		try {
			int colorInt = $Utils.colorStringToInt(color);
			return backgroundColor(colorInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public $ click() {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.performClick();
				}
			}
		});

		return this;
	}

	public $ click(final OnClickListener listener) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.setOnClickListener(listener);
				}
			}
		});

		return this;
	}

	public $ click(final String methodName) {
		return click(new OnClickListener() {

			@Override
			public void onClick(View v) {
				$Utils.callMethod(methodName, link, v);
			}
		});
	}

	public $ clickItem(final int position) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof ListView) {
					ListView listView = (ListView) object;
					listView.performItemClick(listView, position, listView
							.getAdapter().getItemId(position));
				}
			}
		});

		return this;
	}

	public $ clickItem(final OnItemClickListener listener) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof ListView) {
					ListView listView = (ListView) object;
					listView.setOnItemClickListener(listener);
				}
			}
		});

		return this;
	}

	public $ clickItem(final String methodName) {
		return clickItem(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long id) {
				try {
					Method method = null;
					try {
						method = link.getClass().getMethod(methodName,
								int.class);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}

					if (method != null) {
						method.invoke(link, position);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected Map<String, Object> data() {
		Object key = get();
		if (key == null) {
			return null;
		}
		Map<String, Object> data = dataMap.get(key);
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		return data;
	}

	public Object data(String name) {
		return data().get(name);
	}

	public $ data(final String name, final Object value) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {

			@Override
			public void $(Object object, Activity link, int index) {
				Map<String, Object> data = data();
				data.put(name, value);
				$.dataMap.put(object, data);
			}

		});

		return this;
	}

	public $ font(final String familyName, final int style) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof TextView) {
					TextView textView = (TextView) object;
					Typeface type = Typeface.create(familyName, style);
					textView.setTypeface(type);
				}
			}
		});

		return this;
	}

	public Object get() {
		items = items.reset();
		Iterator<Object> iter = items.iterator();
		if (iter.hasNext()) {
			return iter.next();
		}
		return null;
	}

	protected View getRootView() {
		return ((ViewGroup) link.findViewById(android.R.id.content))
				.getChildAt(0);
	}

	public int height() {
		Object object = get();
		if (object instanceof View) {
			return ((View) object).getHeight();
		}
		return 0;
	}

	public $ height(final int height) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					LayoutParams params = view.getLayoutParams();
					params.height = height;
					view.setLayoutParams(params);
				}
			}
		});

		return this;
	}

	public $ height(String special) {
		String upper = special.toUpperCase();
		if ("FILL".equals(upper) || "FILLPARENT".equals(upper)
				|| "FILL_PARENT".equals(upper) || "MATCHPARENT".equals(upper)
				|| "MATCH_PARENT".equals(upper)) {
			return height(LayoutParams.FILL_PARENT);
		} else if ("WRAP".equals(upper) || "WRAPCONTENT".equals(upper)
				|| "WRAP_CONTENT".equals(upper)) {
			return height(LayoutParams.WRAP_CONTENT);
		}

		return this;
	}

	public $ hide() {
		return visible(false);
	}

	public $ hint(final int resid) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof TextView) {
					TextView textView = (TextView) object;
					textView.setHint(resid);
				}
			}
		});

		return this;
	}

	public $ hint(final String hint) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof TextView) {
					TextView textView = (TextView) object;
					textView.setHint(hint);
				}
			}
		});

		return this;
	}

	public $ longClick() {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.performLongClick();
				}
			}
		});

		return this;
	}

	public $ longClick(final OnLongClickListener listener) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.setOnLongClickListener(listener);
				}
			}
		});

		return this;
	}

	public $ longClick(final String methodName) {
		return longClick(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				$Utils.callMethod(methodName, link, v);

				return false;
			}
		});
	}

	public $ padding(int all) {
		return padding(all, all, all, all);
	}

	public $ padding(int leftright, int topbottom) {
		return padding(leftright, topbottom, leftright, topbottom);
	}

	public $ padding(int leftright, int top, int bottom) {
		return padding(leftright, top, leftright, bottom);
	}

	public $ padding(final int left, final int top, final int right,
			final int bottom) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.setPadding(left, top, right, bottom);
				}
			}
		});

		return this;
	}

	public int paddingbottom() {
		Object object = get();
		if (object instanceof View) {
			return ((View) object).getPaddingBottom();
		}
		return 0;
	}

	public int paddingleft() {
		Object object = get();
		if (object instanceof View) {
			return ((View) object).getPaddingLeft();
		}
		return 0;
	}

	public int paddingright() {
		Object object = get();
		if (object instanceof View) {
			return ((View) object).getPaddingRight();
		}
		return 0;
	}

	public int paddingtop() {
		Object object = get();
		if (object instanceof View) {
			return ((View) object).getPaddingTop();
		}
		return 0;
	}

	public $ parent() {
		return null;
	}

	public $ root() {
		return null;
	}

	public $ setup(int itemLayout, Collection<Object> list) {
		Object object = get();
		if (object instanceof ListView) {
			ListView listView = (ListView) object;
			ArrayList<Object> itemList = new ArrayList<Object>(list);
			$ArrayAdapter listAdapter = new $ArrayAdapter(link, itemLayout,
					itemList);
			listView.setAdapter(listAdapter);

			return this;
		}
		return null;
	}

	public $ setup(int itemLayout, Object[] list) {
		return setup(itemLayout, Arrays.asList(list));
	}

	public $ show() {
		return visible(true);
	}

	public void start() {
		Object wrapped = get();
		// need check because we allow both Class and Activity
		if (wrapped instanceof Class<?>) {
			Intent in = new Intent(link, (Class<?>) get());
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
			link.startActivity(in);
		}
	}

	public String text() {
		Object object = get();
		if (object instanceof TextView) {
			return ((TextView) object).getText().toString();
		}
		return null;
	}

	public $ text(final String text) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof TextView) {
					TextView textView = (TextView) object;
					textView.setText(text);
				}
			}
		});

		return this;
	}

	public int textColor() {
		Object object = get();
		if (object instanceof TextView) {
			return ((TextView) object).getCurrentTextColor();
		}
		return 0;
	}

	public $ textColor(final int color) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof TextView) {
					TextView textView = (TextView) object;
					textView.setTextColor(color);
				}
			}
		});

		return this;
	}

	public $ textColor(String color) {
		try {
			int colorInt = $Utils.colorStringToInt(color);
			return textColor(colorInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public float textSize() {
		Object object = get();
		if (object instanceof TextView) {
			return ((TextView) object).getTextSize();
		}
		return 0;
	}

	public $ textSize(final float size) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof TextView) {
					TextView textView = (TextView) object;
					textView.setTextSize(size);
				}
			}
		});

		return this;
	}

	public $ textStyle(final String text) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof TextView) {
					TextView textView = (TextView) object;
					String upper = text.toUpperCase();
					if ("BOLD".equals(upper)) {
						textView.setTypeface(null, Typeface.BOLD);
					} else if ("ITALIC".equals(upper)
							|| "ITALICS".equals(upper)) {
						textView.setTypeface(null, Typeface.ITALIC);
					} else if ("BOLD_ITALIC".equals(upper)
							|| "BOLDITALIC".equals(upper)
							|| "BOLDITALICS".equals(upper)) {
						textView.setTypeface(null, Typeface.BOLD_ITALIC);
					} else if ("NORMAL".equals(upper)) {
						textView.setTypeface(null, Typeface.NORMAL);
					}
				}
			}
		});
		return this;
	}

	public int visible() {
		Object object = get();
		if (object instanceof View) {
			return ((View) object).getVisibility();
		}
		return View.GONE;
	}

	public $ visible(boolean visible) {
		return visible(visible ? View.VISIBLE : View.GONE);
	}

	public $ visible(final int visible) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					view.setVisibility(visible);
				}
			}
		});
		return this;
	}

	public $ visible(String text) {
		String upper = text.toUpperCase();
		if ("VISIBLE".equals(upper) || "SHOW".equals(upper)
				|| "DISPLAY".equals(upper)) {
			return visible(View.VISIBLE);
		} else if ("INVISIBLE".equals(upper)) {
			return visible(View.INVISIBLE);
		} else if ("GONE".equals(upper) || "HIDE".equals(upper)) {
			return visible(View.GONE);
		}

		return this;
	}

	public int width() {
		Object object = get();
		if (object instanceof View) {
			return ((View) object).getWidth();
		}
		return 0;
	}

	public $ width(final int width) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			public void $(Object object, Activity link, int index) {
				if (object instanceof View) {
					View view = (View) object;
					LayoutParams params = view.getLayoutParams();
					params.width = width;
					view.setLayoutParams(params);
				}
			}
		});

		return this;
	}

	public $ width(String special) {
		String upper = special.toUpperCase();
		if ("FILL".equals(upper)) {
			return width(LayoutParams.FILL_PARENT);
		} else if ("WRAP".equals(upper)) {
			return width(LayoutParams.WRAP_CONTENT);
		}

		return this;
	}

	public $ filter(String filter) {
		items = $Utils.filter(items, filter);
		return this;
	}

	public <T> $ each(final EachCallback<T> callback) {
		$Utils.forEach(items, link, new $Utils.$Action<Object>() {
			@SuppressWarnings("unchecked")
			public void $(Object object, Activity link, int index) {
				try {
					callback.$(index, (T) object);
				} catch (ClassCastException ex) {
					ex.printStackTrace();
				}
			}
		});
		return this;
	}
}
