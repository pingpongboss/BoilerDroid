package edu.berkeley.cs164.boiler.iter;

import java.util.LinkedList;
import java.util.Queue;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import edu.berkeley.cs164.boiler.util.$Utils;
import edu.berkeley.cs164.boiler.util.Generator;

public class $LayoutGenerator extends Generator<Object> implements
		$ResettableIterable<Object> {
	Activity activity;

	public $LayoutGenerator(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void run() throws Exception {
		Queue<View> queue = new LinkedList<View>();
		queue.add($Utils.getRootView(activity));

		View view = null;
		while ((view = queue.poll()) != null) {
			yield(view);

			if (view instanceof ViewGroup) {
				ViewGroup group = (ViewGroup) view;
				for (int i = 0; i < group.getChildCount(); i++) {
					queue.add(group.getChildAt(i));
				}
			}
		}
	}

	@Override
	public $ResettableIterable<Object> reset() {
		return new $LayoutGenerator(activity);
	}
}
