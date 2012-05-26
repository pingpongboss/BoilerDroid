package edu.berkeley.cs164.boiler.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class $ArrayAdapter extends ArrayAdapter<Object> {
	static class $ViewHolder {
		Map<String, View> elements;
	}

	LayoutInflater mInflater;

	int mTextViewResourceId;

	public $ArrayAdapter(Context context, int textViewResourceId,
			List<Object> objects) {
		super(context, textViewResourceId, objects);

		mInflater = LayoutInflater.from(context);
		mTextViewResourceId = textViewResourceId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		$ViewHolder holder;
		Object object = getItem(position);

		if (convertView == null) {
			convertView = mInflater.inflate(mTextViewResourceId, parent, false);
			holder = new $ViewHolder();

			holder.elements = getMapOfCorrespondingElements(convertView, object);

			convertView.setTag(holder);
		} else {
			holder = ($ViewHolder) convertView.getTag();
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
				Log.d("Boiler", "Make sure your data model fields are public");
			}
		}

		return convertView;
	}

	private Map<String, View> getMapOfCorrespondingElements(View view,
			Object object) {
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
		fillMap(view, ids, mapping);
		return mapping;
	}

	private void fillMap(View view, List<String> ids, Map<String, View> mapping) {
		if (ids.isEmpty())
			return;

		$Tag tag = $Tag.normalizeTag(view);
		for (String klass : tag.classes) {
			if (ids.contains(klass)) {
				mapping.put(klass, view);
				ids.remove(klass);
			}
		}

		if (view instanceof ViewGroup) {
			ViewGroup group = (ViewGroup) view;
			for (int i = 0; i < group.getChildCount(); i++) {
				View v = group.getChildAt(i);
				fillMap(v, ids, mapping);
			}
		}
	}
}
