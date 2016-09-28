package com.github.alexeybond.spectrum_lost.levels.hardcoded;

import static com.github.alexeybond.spectrum_lost.model.util.Direction.*;

/**
 *
 */
public class Chapter3 extends Source {
    {
        L(5,4,
                O(0,1, "expector"),
                O(0,2, "expector:none"),
                O(1,2, "prism", LF),
                O(2,2, "emitter", LF),
                O(3,2, "mirror", LF),
                O(3,1, "mirror", LF),

                W(0,0), W(1,0), W(2,0), W(3,0), W(4,0),
                W(0,3), W(1,3), W(2,3), W(3,3), W(4,3),
                W(4,2), W(4,1)
                );
        L(6,6, // C3L010
                O(3,1, "emitter", UP),
                O(3,2, "prism", UP),
                O(3,3, "mirror", DN_RT),
                O(4,3, "prism", RT),
                O(5,2, "expector"),
                O(2,3, "shifter", UP),
                O(2,4, "shifter", UP),
                O(1,3, "mirror", RT),
                O(1,4, "mirror", RT),
                O(3,4, "mirror", LF),

                W(4,2), W(4,4),

                W(0,5), W(1,5), W(2,5), W(3,5), W(4,5), W(5,5),
                W(0,4), W(0,3), W(0,2),
                W(1,2), W(2,2), W(2,1), W(2,0),
                W(3,0), W(4,0), W(4,1), W(4,2),
                W(5,1), W(5,3), W(5,4)
                );
        L(7,7, // C3L020
                O(1,0, "expector"),
                O(0,3, "fader"),
                O(0,2, "fader"),
                O(0,4, "mirror", DN_RT),
                O(1,4, "mixer", LF),
                O(1,3, "mirror", DN_RT),
                O(1,5, "mirror", DN_RT),
                O(2,3, "prism", UP_LF),
                O(3,1, "emitter", UP),
                O(3,2, "prism", UP),
                O(3,4, "mirror", DN_RT),
                O(3,5, "shifter", UP),
                O(4,3, "prism", UP_RT),
                O(4,4, "fader"),
                O(6,3, "fader"),
                O(6,2, "fader"),
                O(6,1, "mirror", UP_LF),
                O(6,4, "mirror", DN_LF),
                O(5,3, "mirror", DN_LF),
                O(5,5, "mirror", DN_LF),
                O(5,1, "mirror", UP_RT),

                O(0,1, "switch", UP),
                O(1,1, "switch", DN),

                W(0,6), W(1,6), W(2,6), W(3,6), W(4,6), W(5,6), W(6,6),
                W(0,5), W(6,5),
                W(2,2), W(2,1), W(4,2), W(4,1),
                W(2,0), W(3,0), W(4,0), W(5,0), W(6,0),
                W(0,0)
                );
    }
}
