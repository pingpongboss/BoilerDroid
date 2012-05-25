package edu.berkeley.cs164.boiler;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import edu.berkeley.cs164.boiler.iter.$GeneratorFactory;
import edu.berkeley.cs164.boiler.util.BoilerUtils;

public class BoilerTextView extends BoilerView {

	public BoilerTextView(Activity link,
			$GeneratorFactory<? extends View> factory) {
		super(link, factory);
	}

	public $ backgroundColor(final int color) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				textView.setBackgroundColor(color);
			}
		});

		return this;
	}

	public $ font(final String path) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				Typeface type = Typeface.createFromAsset(
						((ContextWrapper) link).getAssets(), path);
				textView.setTypeface(type);
			}
		});

		return this;
	}

	public TextView get() {
		View view = super.get();
		if (view instanceof TextView) {
			return (TextView) view;
		}
		return null;
	}

	public $ hint(final int resid) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				textView.setHint(resid);
			}
		});
		return this;
	}

	public $ hint(final String hint) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				textView.setHint(hint);
			}
		});

		return this;
	}

	public String text() {
		TextView textView = get();
		if (textView != null)
			return textView.getText().toString();
		return null;
	}

	public $ text(final String text) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				textView.setText(text);
			}
		});

		return this;
	}

	public int textColor() {
		TextView textView = get();
		if (textView != null)
			return textView.getCurrentTextColor();
		return -1;
	}

	public $ textColor(final ColorStateList colorList) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				textView.setTextColor(colorList);
			}
		});

		return this;
	}

	public $ textColor(final int color) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				textView.setTextColor(color);
			}
		});

		return this;
	}

	public $ textColor(String color) {
		try {
			int colorInt = BoilerUtils.colorStringToInt(color);
			return textColor(colorInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public float textSize() {
		TextView textView = get();
		if (textView != null)
			return textView.getTextSize();
		return -1;
	}

	public $ textSize(final float size) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				textView.setTextSize(size);
			}
		});

		return this;
	}

	public $ textStyle(final String text) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				String input = text.toUpperCase();
				int tf = 0;
				if (input == "BOLD") {
					tf = Typeface.BOLD;
				}
				if (input == "ITALIC") {
					tf = Typeface.ITALIC;
				}
				if (input == "BOLD_ITALIC") {
					tf = Typeface.BOLD_ITALIC;
				}
				if (input == "NORMAL") {
					tf = Typeface.NORMAL;
				} else {
					throw new IllegalArgumentException();
				}
				textView.setTypeface(null, tf);
			}
		});
		return this;
	}
}
