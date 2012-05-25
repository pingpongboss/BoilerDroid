package edu.berkeley.cs164.boiler.iter;

import java.util.LinkedList;
import java.util.Queue;

import android.view.View;
import android.view.ViewGroup;
import edu.berkeley.cs164.boiler.util.Generator;

public class $LayoutGenerator extends Generator<View> {
	View root;
	String klass;

	public static $GeneratorFactory<View> getFactory(final View root,
			final String klass) {
		return new $GeneratorFactory<View>() {

			@Override
			public Generator<View> createGenerator() {
				return new $LayoutGenerator(root, klass);
			}

		};
	}

	public $LayoutGenerator(View root, String klass) {
		this.root = root;
		this.klass = klass;
	}

	@Override
	protected void run() throws Exception {
		Queue<View> queue = new LinkedList<View>();
		queue.add(root);

		View view = null;
		while ((view = queue.poll()) != null) {
			if (klass.equals(view.getTag())) {
				yield(view);
			}

			if (view instanceof ViewGroup) {
				ViewGroup group = (ViewGroup) view;
				for (int i = 0; i < group.getChildCount(); i++) {
					queue.add(group.getChildAt(i));
				}
			}
		}
	}
}
