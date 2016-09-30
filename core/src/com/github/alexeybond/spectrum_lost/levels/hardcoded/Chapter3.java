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
        L(7,7,
                O(3,5, "emitter", LF),
                O(0,5, "expector:none"),
                O(4,5, "mirror", DN_LF),
                O(3,4, "mirror", UP_RT),
                O(2,1, "expector"),
                O(3,2, "prism", LF),
                O(4,2, "mirror", UP_LF),
                O(5,4, "mirror", DN_LF),
                O(4,1, "mirror", UP_RT),
                O(5,1, "mirror", UP_LF),
                O(5,3, "fader"),
                O(1,5, "fader"),
                O(2,5, "fader"),

                W(0,6), W(1,6), W(2,6), W(3,6), W(4,6), W(5,6), W(5,5),
                W(0,4), W(1,3), W(2,2),
                W(3,1), W(3,0),
                W(4,0), W(5,0), W(6,0),
                W(6,1), W(6,2), W(6,3), W(6,4), W(6,5)
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
        L(10,9,
                O(3,4, "emitter", DN),
                O(5,5, "emitter", DN),
                O(3,6, "prism", UP),
                O(3,7, "mixer", LF),
                O(1,7, "mirror", DN_RT),
                O(1,1, "mirror", UP_RT),
                O(8,1, "mirror", UP_LF),
                O(6,1, "mixer", UP),
                O(6,2, "mixer", LF),
                O(6,3, "mirror", DN_LF),
                O(6,5, "prism", RT),
                O(7,5, "shifter", UP),
                O(8,5, "mirror", UP_LF),
                O(7,4, "prism", DN_RT),
                O(7,3, "shifter", RT),
                O(7,2, "mirror", UP_LF),
                O(7,6, "mirror", LF),
                O(6,7, "prism", UP_LF),
                O(5,7, "shifter", UP),
                O(4,7, "shifter", UP),
                O(1,4, "shifter", RT),
                O(3,5, "fader"),
                O(7,1, "fader"),
                O(8,7, "mirror", DN_LF),

                O(3,3, "prism", DN),
                O(4,3, "shifter", UP),
                O(5,3, "prism", DN),

                O(2,2, "expector"),
                O(3,2, "expector:none"),
                O(2,0, "expector"),
                O(5,2, "expector:none"),

                W(2,3), W(2,4), W(2,5), W(2,6),
                W(4,4), W(4,5), W(4,6), W(5,6), W(6,6), W(6,4),

                W(0,0), W(1,0), W(3,0), W(4,0), W(5,0), W(6,0), W(7,0), W(8,0), W(9,0),
                W(0,8), W(1,8), W(2,8), W(3,8), W(4,8), W(5,8), W(6,8), W(7,8), W(8,8), W(9,8),

                W(0,1), W(0,2), W(0,3), W(0,4), W(0,5), W(0,6), W(0,7),
                W(9,1), W(9,2), W(9,3), W(9,4), W(9,5), W(9,6), W(9,7)
        );
        L(9,12,
                // Part I
                O(4,1, "mixer", UP),
                O(4,2, "prism", UP),
                O(4,4, "shifter", RT),
                O(4,5, "multiplier", UP),
                O(4,7, "switch", DN),

                O(1,2, "mirror", UP_RT),
                O(1,3, "shifter", RT),
                O(1,4, "mirror", UP_RT),
                O(1,5, "shifter", RT),
                O(1,6, "mirror", DN_RT),

                O(2,2, "shifter", UP),
                W(2,3),
                O(2,4, "prism", UP_LF),
                W(2,5),
                O(2,6, "shifter", UP),

                O(3,2, "mirror", UP_LF),
                O(3,4, "shifter", RT),
                O(3,5, "mirror", UP_RT),
                O(3,6, "mirror", DN_LF),

                O(5,2, "mirror", UP_RT),
                O(5,4, "shifter", RT),
                O(5,5, "mirror", DN_LF),
                O(5,6, "mirror", DN_RT),

                O(6,2, "shifter", UP),
                W(6,3),
                O(6,4, "prism", UP_RT),
                W(6,5),
                O(6,6, "shifter", UP),

                O(7,2, "mirror", UP_LF),
                O(7,3, "shifter", RT),
                O(7,4, "mirror", DN_LF),
                O(7,5, "shifter", RT),
                O(7,6, "mirror", DN_LF),

                O(1,1, "emitter", RT),
                O(7,1, "emitter", LF),

                O(2,7, "expector:none"),
                O(6,7, "expector"),

                // Part II
                O(1,7, "emitter", RT),
                O(1,10, "mirror", DN_RT),
                O(2,9, "mirror", UP_RT),
                O(2,10, "switch", LF),
                O(3,9, "prism", RT),
                O(4,8, "prism", DN_RT),
                O(5,10, "mixer", LF),
                O(7,9, "mirror", DN_LF),
                O(7,10, "mirror", DN_LF),

                // Walls
                W(2,8), W(6,8),

                W(0,0), W(1,0), W(2,0), W(3,0), W(4,0), W(5,0), W(6,0), W(7,0), W(8,0),
                W(0,11), W(1,11), W(2,11), W(3,11), W(4,11), W(5,11), W(6,11), W(7,11), W(8,11),

                W(0,1), W(0,2), W(0,3), W(0,4), W(0,5), W(0,6), W(0,7), W(0,8), W(0,9), W(0,10),
                W(8,1), W(8,2), W(8,3), W(8,4), W(8,5), W(8,6), W(8,7), W(8,8), W(8,9), W(8,10)
        );
    }
}
