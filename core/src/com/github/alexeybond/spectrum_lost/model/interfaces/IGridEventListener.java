package com.github.alexeybond.spectrum_lost.model.interfaces;

/**
 *
 */
public interface IGridEventListener {
    void onEvent(ICell cell, String eventName, Object arg);
}
