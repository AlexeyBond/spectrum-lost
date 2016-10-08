package com.github.alexeybond.spectrum_lost.levels.hardcoded;

import static com.github.alexeybond.spectrum_lost.model.util.Direction.*;

/**
 *
 */
public class Chapter4 extends Source {
    {
        L(7,5,
                O(1,4, "expector:r"),
                O(3,4, "expector:g"),
                O(5,4, "expector:b"),

                O(3,2, "prism", UP_RT),
                O(2,1, "emitter", UP_RT),
                O(3,1, "emitter", RT),

                O(5,1, "mirror", UP_LF),
                O(5,3, "mirror", DN_LF),
                O(1,3, "mirror", UP_RT),

                W(0,4), W(2,4), W(4,4), W(6,4),
                W(0,3), W(0,2), W(1,2), W(2,2), W(4,2), W(6,2), W(6,1),
                W(1,1), W(1,0), W(2,0), W(3,0), W(4,0), W(5,0), W(6,0), W(6,3)
                );
    }
}
