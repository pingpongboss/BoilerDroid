package edu.berkeley.cs164.boiler.util;

import java.util.HashSet;
import java.util.Set;

import android.view.View;

public class $Tag {
	public static $Tag fromSelector(String filter) {
		$Tag tag = new $Tag(null, null, null);
		String selectors = filter;
		if (selectors == null || (selectors = selectors.trim()).length() == 0) {
			return tag;
		}

		StringBuilder builder = new StringBuilder();

		selectors = selectors.replaceAll("\\.", " .").replaceAll("#", " #");
		for (String selector : selectors.split(" ")) {
			if (selector.length() != 0) {
				char type = selector.charAt(0);
				String id = selector.substring(1);

				switch (type) {
					case '#':
						if (id.length() > 0)
							tag.id = id;
						break;
					case '.':
						if (id.length() > 0)
							tag.classes.add(id);
						break;
					default:
						builder.append(selector).append(" ");
						break;
				}
			}
		}

		String rest = null;
		if ((rest = builder.toString().trim()).length() > 0) {
			tag.tag = rest;
		}

		return tag;
	}

	public static $Tag normalizeTag(View view) {
		Object object = view.getTag();
		if (object instanceof $Tag) {
			return ($Tag) object;
		} else if (object instanceof String) {
			$Tag tag = $Tag.fromSelector((String) object);
			view.setTag(tag);
			return tag;
		} else {
			$Tag tag = new $Tag(null, null, object);
			view.setTag(tag);
			return tag;
		}
	}

	public String id;
	public Set<String> classes;
	public Object tag;

	public $Tag(String id, Set<String> klass, Object tag) {
		super();
		this.id = id == null ? "" : id;
		this.classes = klass == null ? new HashSet<String>() : klass;
		this.tag = tag;
	}

	public boolean matches(String filter) {
		$Tag selectors = fromSelector(filter);

		boolean matched = false;

		// check id
		if (selectors.id.length() > 0) {
			if (selectors.id.equals(id)) {
				matched = true;
			} else {
				return false;
			}
		}

		// check class
		for (String other : selectors.classes) {
			if (classes.contains(other)) {
				matched = true;
			} else {
				return false;
			}
		}

		return matched;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (id.length() > 0) {
			builder.append("#" + id);
		}

		for (String k : classes) {
			builder.append("." + k);
		}
		return builder.toString();
	}
}