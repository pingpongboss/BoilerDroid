package edu.berkeley.cs164.boiler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import edu.berkeley.cs164.boiler.iter.$GeneratorFactory;
import edu.berkeley.cs164.boiler.util.BoilerUtils;

public class BoilerListView extends BoilerView {

	public BoilerListView(Activity link,
			$GeneratorFactory<? extends View> factory) {
		super(link, factory);
	}

	public static class CustomAdapter extends ArrayAdapter<Object> {
		static class ViewHolder {
			Map<String, View> elements;
		}

		LayoutInflater mInflater;

		int mTextViewResourceId;

		public CustomAdapter(Context context, int textViewResourceId,
				List<Object> objects) {
			super(context, textViewResourceId, objects);

			mInflater = LayoutInflater.from(context);
			mTextViewResourceId = textViewResourceId;
		}

		@SuppressWarnings("unchecked")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			Object object = getItem(position);

			if (convertView == null) {
				convertView = mInflater.inflate(mTextViewResourceId, parent,
						false);
				holder = new ViewHolder();

				holder.elements = parseElements(convertView, object);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			for (String id : holder.elements.keySet()) {
				View view = holder.elements.get(id);

				Object field = null;
				if (object instanceof Map) {
					field = ((Map<String, Object>) object).get(id);
				} else {
					try {
						field = object.getClass().getField(id).get(object);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
				}

				if (field != null) {
					if (view instanceof ImageView) {
						((ImageView) view).setImageDrawable((Drawable) field);
					} else if (view instanceof TextView) {
						((TextView) view).setText(field.toString());
					}
				} else {
					Log.d("Boiler",
							"Make sure your data model fields are public");
				}
			}

			return convertView;
		}

		private void parseElements(View view, List<String> ids,
				Map<String, View> mapping) {
			if (ids.isEmpty())
				return;

			Object tag = view.getTag();
			if (tag != null) {
				String id = tag.toString();
				if (ids.contains(id)) {
					mapping.put(id, view);
					ids.remove(id);
				}
			}

			if (view instanceof ViewGroup) {
				ViewGroup group = (ViewGroup) view;
				for (int i = 0; i < group.getChildCount(); i++) {
					View v = group.getChildAt(i);
					parseElements(v, ids, mapping);
				}
			}
		}

		private Map<String, View> parseElements(View convertView, Object object) {
			List<String> ids = new ArrayList<String>();
			if (object instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) object;
				ids.addAll(map.keySet());
			} else {
				Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					ids.add(field.getName());
				}
			}

			Map<String, View> mapping = new HashMap<String, View>();
			parseElements(convertView, ids, mapping);
			return mapping;
		}
	}

	protected ArrayAdapter<Object> listAdapter;
	protected ArrayList<Object> itemList;

	public $ add(Collection<Object> items) {
		itemList.addAll(items);
		return this;
	}

	public $ add(Object[] items) {
		return add(Arrays.asList(items));
	}

	public $ add(Object item) {
		itemList.add(item);
		return this;
	}

	public $ clickItem(final int position) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				ListView listView = (ListView) object;
				listView.performItemClick(listView, position, listView
						.getAdapter().getItemId(position));
			}
		});

		return this;
	}

	public $ clickItem(final OnItemClickListener listener) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				ListView listView = (ListView) object;
				listView.setOnItemClickListener(listener);
			}
		});

		return this;
	}

	public $ clickItem(final String methodName) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, final Activity link) {
				ListView listView = (ListView) object;
				listView.setOnItemClickListener(new OnItemClickListener() {

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
		});

		return this;
	}

	public ListView get() {
		View view = super.get();
		if (view instanceof ListView) {
			return (ListView) view;
		}
		return null;
	}

	public $ setup(int itemLayout, Collection<Object> items) {
		ListView listView = get();
		if (listView != null) {
			itemList = new ArrayList<Object>(items);
			listAdapter = new CustomAdapter((Activity) link, itemLayout,
					itemList);
			listView.setAdapter(listAdapter);
			return this;
		}
		return null;
	}

	public $ setup(int itemLayout, Object[] items) {
		return setup(itemLayout, Arrays.asList(items));
	}
}
