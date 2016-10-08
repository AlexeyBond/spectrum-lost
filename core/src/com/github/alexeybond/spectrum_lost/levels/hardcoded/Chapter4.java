package com.github.alexeybond.spectrum_lost.levels.hardcoded;

import static com.github.alexeybond.spectrum_lost.model.util.Direction.*;

/**
 *
 */
public class Chapter4 extends Source {
    {
        L(9,8,
                O(3,6, "expector:b"),
                O(3,4, "multiplier", UP),
                O(3,2, "switch", UP),
                O(3,1, "mirror", UP_RT),

                O(4,4, "hmirror", UP_LF),
                O(4,3, "hmirror", UP_RT),
                O(4,2, "shifter", UP),
                O(4,1, "mirror", UP),

                O(5,3, "prism", DN_LF),
                O(5,2, "mirror", UP_LF),

                O(6,6, "emitter", LF),
                O(6,4, "prism", LF),

                O(7,5, "mirror", LF),
                O(7,4, "emitter", LF),

                O(2,4, "hmirror", LF),
                O(2,3, "mirror", UP_RT),

                O(1,4, "emitter", RT),
                O(1,2, "emitter", RT)
                );
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
