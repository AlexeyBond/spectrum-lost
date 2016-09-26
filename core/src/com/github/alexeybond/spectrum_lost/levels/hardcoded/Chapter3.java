package com.github.alexeybond.spectrum_lost.levels.hardcoded;

import static com.github.alexeybond.spectrum_lost.model.util.Direction.*;

/**
 *
 */
public class Chapter3 extends Source {
    {
        L(6,6,
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
        L(5,5,
                O(2,0, "emitter", UP),
                O(2,1, "prism", UP),
                O(2,2, "shifter", RT),
                O(1,2, "shifter", UP_RT),
                O(3,2, "shifter", UP_LF),
                O(2,3, "shifter", RT),
                O(2,4, "shifter", RT));
    }
}
