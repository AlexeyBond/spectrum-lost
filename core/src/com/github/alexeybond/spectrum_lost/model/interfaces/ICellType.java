package com.github.alexeybond.spectrum_lost.model.interfaces;

/**
 * Represents a type of objects that may be placed in a cell.
 */
public interface ICellType {
    /**
     * Called by a grid when object of this type is placed in a cell.
     */
    void init(final ICell cell);

    /**
     * Called by a grid when object of this type is removed from cell.
     */
    void leave(final ICell cell);

    /**
     * Called by grid in every simulation frame for each cell where object of this type exists.
     */
    void update(final ICell cell);

    /**
     * String identifying this type of object.
     */
    String id();

    /**
     * Get attribute of cell type.
     */
    Object getAttribute(final String name);
}
