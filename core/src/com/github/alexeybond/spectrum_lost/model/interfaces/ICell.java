package com.github.alexeybond.spectrum_lost.model.interfaces;

import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

import java.util.Map;

/**
 *
 */
public interface ICell {
    /** X coordinate of this cell */
    int x();

    /** Y coordinate of this cell */
    int y();

    /**
     * Get the ray that WILL BE EMITTED BY THIS CELL NEXT FRAME in given direction.
     */
    Ray emission(final Direction dir);

    /**
     * Get the ray that IS RECEIVED BY THIS CELL THIS FRAME from given direction.
     *
     * DO NOT MUTATE RETURNED RAY.
     */
    Ray receive(final Direction dir);

    /**
     * Get current direction of this cell.
     */
    Direction direction();

    /**
     * Set direction of this cell.
     */
    void setDirection(final Direction direction);

    /**
     * Get current state object attached to this cell.
     */
    Object state();

    /**
     * Attach state object to this cell.
     */
    void setState(final Object state);

    /**
     * Get type of object placed in this cell.
     */
    ICellType type();

    /**
     * Set type of object placed in this cell.
     */
    void setType(final ICellType type);

    /**
     * Get a view that should be used to draw this cell. The cell is responsible for keeping consistence between its
     * type and view.
     */
    ICellView getView();

    /**
     * Get attribute of this cell. Will delegate call to {@link ICellType#getAttribute(String)} if cell has no own
     * attribute.
     */
    Object getAttribute(final String name);

    /**
     * Get own attributes of this cell.
     */
    Map<String, Object> getOwnAttributes();

    /**
     * Get the grid this cell belongs to.
     */
    IGrid grid();
}
