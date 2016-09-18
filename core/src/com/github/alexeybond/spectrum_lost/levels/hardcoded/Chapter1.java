package com.github.alexeybond.spectrum_lost.levels.hardcoded;


import static com.github.alexeybond.spectrum_lost.model.util.Direction.*;

/**
 *
 */
public class Chapter1 extends Source {
    {
        L(9,8,
                O(1,6, "mirror", DN_RT),
                O(2,6, "mirror", DN_LF),
                O(6,6, "mirror", DN_RT),
                O(7,6, "mirror", DN_LF),
                O(2,4, "emitter", UP),
                O(6,4, "emitter", UP),
                O(2,2, "mirror", DN_RT),
                O(3,2, "mirror", DN_LF),
                O(5,2, "mirror", DN_RT),
                O(6,2, "mirror", DN_LF),
                O(1,1, "mirror", UP_RT),
                O(2,1, "mirror", UP_LF),
                O(3,1, "mirror", UP_RT),
                O(4,1, "mixer", UP),
                O(5,1, "mirror", UP_LF),
                O(6,1, "mirror", UP_RT),
                O(7,1, "mirror", UP_LF),
                O(4,6, "expector")
                // TODO: Walls
                // TODO: Not solved initial state
                );
        L(8,6,
                O(4,4, "mixer"),
                O(4,1, "switch"),
                O(4,0, "mirror"),
                O(3,0, "emitter"),
                O(3,4, "prism"),
                O(2,3, "emitter"),
                O(5,4, "prism"),
                O(6,3, "emitter")
                );
    }
}
