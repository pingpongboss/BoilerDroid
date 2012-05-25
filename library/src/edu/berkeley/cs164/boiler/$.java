package edu.berkeley.cs164.boiler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import edu.berkeley.cs164.boiler.iter.$GeneratorFactory;
import edu.berkeley.cs164.boiler.iter.$LayoutGenerator;
import edu.berkeley.cs164.boiler.iter.$SingleGenerator;
import edu.berkeley.cs164.boiler.util.BoilerUtils;

public class $ {
	protected Activity link;
	protected $GeneratorFactory<? extends Object> factory;

	private static WeakHashMap<Object, Map<String, Object>> dataMap = new WeakHashMap<Object, Map<String, Object>>();

	public static $ from(Activity link, int id) {
		View view = BoilerUtils.getRootView(link).findViewById(id);
		$GeneratorFactory<View> factory = $SingleGenerator.getFactory(view);
		return from(link, view, factory);
	}

	public static $ from(Activity link, String klass) {
		View view = $LayoutGenerator
				.getFactory(BoilerUtils.getRootView(link), klass)
				.createGenerator().get();
		$GeneratorFactory<View> factory = $LayoutGenerator.getFactory(
				BoilerUtils.getRootView(link), klass);
		return from(link, view, factory);
	}

	private static $ from(Activity link, View view,
			$GeneratorFactory<View> factory) {
		if (view instanceof ListView) {
			return new BoilerListView(link, factory);
		} else if (view instanceof TextView) {
			return new BoilerTextView(link, factory);
		} else {
			return new BoilerView(link, factory);
		}
	}

	public static $ from(Activity link, View wrapped) {
		return new BoilerView(link, $SingleGenerator.getFactory(wrapped));
	}

	public static $ from(Activity link, TextView wrapped) {
		return new BoilerTextView(link, $SingleGenerator.getFactory(wrapped));
	}

	public static $ from(Activity link, ListView wrapped) {
		return new BoilerListView(link, $SingleGenerator.getFactory(wrapped));
	}

	public static $ from(Activity link, Activity wrapped) {
		return new BoilerActivity(link, $SingleGenerator.getFactory(wrapped));
	}

	public static $ from(Activity link, Class<?> wrapped) {
		return new BoilerActivity(link, $SingleGenerator.getFactory(wrapped));
	}

	protected $(Activity link, $GeneratorFactory<? extends Object> factory) {
		this.link = link;
		this.factory = factory;
	}

	public $ add(Collection<Object> items) {
		return null;
	}

	public $ add(Object item) {
		return null;
	}

	public $ add(Object[] items) {
		return null;
	}

	public $ addItem(Object item) {
		return null;
	}

	public Drawable background() {
		return null;
	}

	public $ background(Drawable d) {
		return null;
	}

	public $ background(int resid) {
		return null;
	}

	public $ backgroundColor(int color) {
		return null;
	}

	public $ backgroundColor(String color) {
		return null;
	}

	public $ click() {
		return null;
	}

	public $ click(OnClickListener listener) {
		return null;
	}

	public $ click(String methodName) {
		return null;
	}

	public $ clickItem(String methodName) {
		return null;
	}

	public $ clickItem(int position) {
		return null;
	}

	public $ clickItem(OnItemClickListener listener) {
		return null;
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
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {

			@Override
			public void $(Object object, Activity link) {
				Map<String, Object> data = data();
				data.put(name, value);
				$.dataMap.put(object, data);
			}

		});

		return this;
	}

	public $ font(String path) {
		return null;
	}

	public Object get() {
		return factory.createGenerator().get();
	}

	protected View getRootView() {
		return ((ViewGroup) link.findViewById(android.R.id.content))
				.getChildAt(0);
	}

	public int height() {
		return 0;
	}

	public $ height(int height) {
		return null;
	}

	public $ height(String special) {
		return null;
	}

	public $ hide() {
		return null;
	}

	public $ hint(int resid) {
		return null;
	}

	public $ hint(String hint) {
		return null;
	}

	public $ longClick() {
		return null;
	}

	public $ longClick(OnClickListener listener) {
		return null;
	}

	public $ longClick(String methodName) {
		return null;
	}

	public $ padding(int left, int top, int right, int bottom) {
		return null;
	}

	public int paddingbottom() {
		return 0;
	}

	public int paddingleft() {
		return 0;
	}

	public int paddingright() {
		return 0;
	}

	public int paddingtop() {
		return 0;
	}

	public $ parent() {
		return null;
	}

	public $ root() {
		return null;
	}

	public $ setup(int itemLayout, Collection<Object> items) {
		return null;
	}

	public $ setup(int itemLayout, Object[] items) {
		return null;
	}

	public $ show() {
		return null;
	}

	public void start() {
	}

	public String text() {
		return null;
	}

	public $ text(String text) {
		return null;
	}

	public int textColor() {
		return 0;
	}

	public $ textColor(ColorStateList colorList) {
		return null;
	}

	public $ textColor(int color) {
		return null;
	}

	public $ textColor(String color) {
		return null;
	}

	public float textSize() {
		return 0;
	}

	public $ textSize(float size) {
		return null;
	}

	public $ textStyle(String text) {
		return null;
	}

	public int visible() {
		return 0;
	}

	public $ visible(boolean visible) {
		return null;
	}

	public $ visible(int visible) {
		return null;
	}

	public $ visible(String text) {
		return null;
	}

	public int width() {
		return 0;
	}

	public $ width(int width) {
		return null;
	}

	public $ width(String special) {
		return null;
	}
}
