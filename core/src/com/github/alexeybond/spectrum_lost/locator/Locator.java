package com.github.alexeybond.spectrum_lost.locator;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellView;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 */
public final class Locator<T> {
    private final String kindName;
    private final Map<String, T> entries;

    private Locator(final String kind) {
        kindName = kind;
        entries = new HashMap<String, T>();
    }

    public T get(final String id) {
        T item = entries.get(id);

        if (item == null) {
            throw new NoSuchElementException("No such "+kindName+" named "+id);
        }

        return item;
    }

    public void set(final String id, final T item) {
        entries.put(id, item);
    }

    public void clear() {
        entries.clear();
    }

    public static final Locator<ICellType> CELL_TYPES = new Locator<ICellType>("cell type");

    public static final Locator<ICellView> CELL_VIEWS = new Locator<ICellView>("cell view");

    public static final Locator<Object> RENDERER_OBJECT = new Locator<Object>("object");
}
