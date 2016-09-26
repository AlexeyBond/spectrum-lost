package com.github.alexeybond.spectrum_lost.levels.hardcoded;

import static com.github.alexeybond.spectrum_lost.model.util.Direction.*;

/**
 *
 */
public class Chapter3 extends Source {
    {
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

                W(4,2), W(4,4)
                );
        L(7,7, // C3L020
                O(0,0, "expector"),
                O(0,3, "fader"),
                O(0,2, "fader"),
                O(0,4, "mirror", DN_RT),
                O(1,4, "mixer", LF),
                O(1,3, "mirror", UP_RT),
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
                O(5,1, "mirror", UP_RT)
                );
        L(5,5,
                O(2,0, "emitter", UP),
                O(2,1, "prism", UP),
                O(2,2, "fader", RT),
                O(1,2, "fader", UP_RT),
                O(3,2, "fader", UP_LF),
                O(2,3, "fader", RT),
                O(2,4, "fader", RT));
    }
}
