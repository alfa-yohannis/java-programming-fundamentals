package edu.pradita;

import java.awt.Component;
import java.awt.Container;

public class Util {

	public static Component getComponentById(Container container, String id) {

		for (Component component : container.getComponents()) {
			if (component.getName().equals(id)) {
				return component;
			}
			if (component instanceof Container) {
				Util.getComponentById((Container) component, id);
			}
		}

		return null;
	}
}
