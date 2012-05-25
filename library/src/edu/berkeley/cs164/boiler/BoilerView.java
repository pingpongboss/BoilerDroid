package edu.berkeley.cs164.boiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import edu.berkeley.cs164.boiler.iter.$GeneratorFactory;
import edu.berkeley.cs164.boiler.util.BoilerUtils;

public class BoilerView extends $ {
	public BoilerView(Activity link, $GeneratorFactory<? extends View> factory) {
		super(link, factory);
	}

	public Drawable background() {
		return get().getBackground();
	}

	public $ background(final Drawable d) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				View view = (View) object;
				view.setBackgroundDrawable(d);
			}
		});
		return this;
	}

	public $ background(final int resid) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				View view = (View) object;
				view.setBackgroundResource(resid);
			}
		});

		return this;
	}

	public $ backgroundColor(final int color) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				View view = (View) object;
				view.setBackgroundColor(color);
			}
		});

		return this;
	}

	public $ backgroundColor(String color) {
		try {
			int colorInt = BoilerUtils.colorStringToInt(color);
			return backgroundColor(colorInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public $ click() {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				View view = (View) object;
				view.performClick();
			}
		});

		return this;
	}

	public $ click(final String methodName) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, final Activity link) {
				View view = (View) object;
				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						try {
							Method method = null;
							try {
								method = link.getClass().getMethod(methodName);
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								try {
									method = link.getClass().getMethod(
											methodName, View.class);
								} catch (SecurityException e1) {
									e1.printStackTrace();
								} catch (NoSuchMethodException e1) {
									e1.printStackTrace();
								}
							}

							if (method != null) {
								if (method.getParameterTypes().length == 0) {
									method.invoke(link);
								} else {
									method.invoke(link, v);
								}
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

	public $ click(final View.OnClickListener listener) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				View view = (View) object;
				view.setOnClickListener(listener);
			}
		});

		return this;
	}

	public View get() {
		return (View) super.get();
	}

	public int height() {
		return get().getHeight();
	}

	public $ height(final int height) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				View view = (View) object;
				LayoutParams params = view.getLayoutParams();
				params.height = height;
				view.setLayoutParams(params);
			}
		});

		return this;
	}

	public $ height(String special) {
		String upper = special.toUpperCase();
		if ("FILL".equals(upper)) {
			return height(LayoutParams.FILL_PARENT);
		} else if ("WRAP".equals(upper)) {
			return height(LayoutParams.WRAP_CONTENT);
		}

		return this;
	}

	public $ hide() {
		return visible(false);
	}

	public $ padding(final int left, final int top, final int right,
			final int bottom) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				View view = (View) object;
				view.setPadding(left, top, right, bottom);
			}
		});

		return this;
	}

	public int paddingbottom() {
		return get().getPaddingBottom();

	}

	public int paddingleft() {
		return get().getPaddingLeft();

	}

	public int paddingright() {
		return get().getPaddingRight();

	}

	public int paddingtop() {
		return get().getPaddingTop();

	}

	public $ show() {
		return visible(true);
	}

	public int visible() {
		return get().getVisibility();
	}

	public $ visible(boolean visible) {
		return visible(visible ? View.VISIBLE : View.GONE);
	}

	public $ visible(final int visible) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				TextView textView = (TextView) object;
				textView.setVisibility(visible);
			}
		});
		return this;
	}

	public $ visible(final String text) {
		String input = text.toUpperCase();
		int visibleInt = View.VISIBLE;
		if (input.equals("VISIBLE")) {
			visibleInt = View.VISIBLE;
		} else if (input.equals("INVISIBLE")) {
			visibleInt = View.INVISIBLE;
		} else if (input.equals("GONE")) {
			visibleInt = View.GONE;
		} else {
			return this;
		}
		return visible(visibleInt);
	}

	public int width() {
		return get().getWidth();
	}

	public $ width(final int width) {
		BoilerUtils.forEach(factory, link, new BoilerUtils.$Action<Object>() {
			public void $(Object object, Activity link) {
				View view = (View) object;
				LayoutParams params = view.getLayoutParams();
				params.width = width;
				view.setLayoutParams(params);
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
}
