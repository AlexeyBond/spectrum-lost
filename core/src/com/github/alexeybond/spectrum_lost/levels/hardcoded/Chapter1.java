package com.github.alexeybond.spectrum_lost.levels.hardcoded;


import static com.github.alexeybond.spectrum_lost.model.util.Direction.*;

/**
 *
 */
public class Chapter1 extends Source {
    {
        L(9,8,// C1L1
                O(1,6, "mirror", DN_RT),
                O(2,6, "mirror", DN_LF),
                O(6,6, "mirror", DN_RT),
                O(7,6, "mirror", DN_LF),
                O(2,4, "emitter", UP),
                O(6,4, "emitter", UP),
                O(2,2, "mirror", DN_RT),
                O(3,2, "mirror", UP_LF),
                O(5,2, "mirror", UP_RT),
                O(6,2, "mirror", DN_LF),
                O(1,1, "mirror", UP_RT),
                O(2,1, "mirror", UP_LF),
                O(3,1, "mirror", UP_RT),
                O(4,1, "mixer", UP),
                O(5,1, "mirror", UP_LF),
                O(6,1, "mirror", UP_RT),
                O(7,1, "mirror", UP_LF),
                O(4,6, "expector"),
                W(3,6), W(3,7), W(3,5), W(3,4), W(3,3),
                W(2,3),
                W(5,6), W(5,7), W(5,5), W(5,4), W(5,3),
                W(6,3),
                W(0,0), W(1,0), W(2,0), W(3,0), W(4,0), W(5,0), W(6,0), W(7,0), W(8,0),
                W(0,7), W(1,7), W(2,7), W(6,7), W(7,7), W(8,7),
                W(0,1), W(0,2), W(0,3), W(0,4), W(0,5), W(0,6),
                W(8,1), W(8,2), W(8,3), W(8,4), W(8,5), W(8,6)
                );
        L(9,6,// C1L1.1
                O(1,2, "mirror", UP_RT),
                O(1,4, "mirror", DN_RT),
                O(2,1, "mirror", UP_RT),
                O(2,2, "mirror", DN_RT),
                O(2,3, "prism", DN),
                O(2,4, "mirror", DN_LF),
                O(3,1, "emitter", LF),
                O(3,2, "prism", RT),
                O(3,5, "expector"),

                O(4,2, "mixer", UP),
                O(4,4, "prism", UP),

                O(7,2, "mirror", UP_LF),
                O(7,4, "mirror", DN_LF),
                O(6,1, "mirror", UP_LF),
                O(6,2, "mirror", DN_LF),
                O(6,3, "prism", DN),
                O(6,4, "mirror", DN_RT),
                O(5,1, "emitter", RT),
                O(5,2, "prism", LF),
                O(5,5, "expector"),

                W(4,1), W(3,4), W(5,4),
                W(4,5), W(0,5), W(1,5), W(2,5), W(6,5), W(7,5), W(8,5),
                W(1,0), W(2,0), W(3,0), W(4,0), W(5,0), W(6,0), W(7,0),
                W(0,1), W(1,1), W(7,1), W(8,1),
                W(0,2), W(0,3), W(0,4),
                W(8,2), W(8,3), W(8,4)
                );
        L(5,5,// C1L1.2
                O(1,3, "expector"),
                O(1,2, "mirror", UP_RT),
                O(2,1, "mirror", RT),
                O(2,2, "switch", UP),
                O(2,3, "mirror", DN_RT),
                O(3,1, "emitter", LF),
                O(3,3, "emitter", LF),

                W(0,0), W(0,1), W(0,2), W(0,3), W(0,4),
                W(4,0), W(4,1), W(4,2), W(4,3), W(4,4),
                W(1,1), W(3,2),
                W(1,0), W(2,0), W(3,0),
                W(1,4), W(2,4), W(3,4)
                );
        L(6,5,// C1L1.5
                O(1,3, "emitter", DN),
                O(1,2, "mirror", UP),
                O(2,2, "switch", LF),
                O(2,1, "expector"),
                O(3,1, "mirror", RT),
                O(3,2, "switch", UP),
                O(2,3, "emitter", RT),
                O(3,3, "mirror", LF),
                O(4,1, "emitter", LF),

                W(0,4), W(1,4), W(2,4), W(3,4), W(4,4),
                W(1,0), W(2,0), W(3,0), W(4,0), W(5,0),
                W(0,1), W(1,1), W(5,1),
                W(0,2), W(0,3),
                W(5,2), W(4,2), W(4,3)
                );
        L(8,7,// C1L2
                O(1,1, "expector"),
                O(2,4, "expector"),

                O(1,3, "mirror", UP_RT),
                O(2,2, "prism", DN_LF),
                O(2,1, "mirror", UP_LF),
                O(3,3, "prism", LF),
                O(4,1, "mixer", UP),
                O(4,3, "switch", UP),
                O(4,5, "emitter", DN),
                O(5,1, "mirror", UP),
                O(5,2, "mirror", RT),
                O(6,2, "mirror", UP_LF),
                O(6,3, "mirror", DN_LF),

                W(3,6), W(4,6), W(5,6),
                W(1,5), W(2,5), W(3,5), W(5,5),
                W(0,4), W(1,4), W(5,4), W(6,4), W(7,4),
                W(0,0), W(1,0), W(2,0), W(3,0), W(4,0), W(5,0), W(6,0),
                W(6,1), W(7,1),
                W(0,1), W(0,2), W(0,3),
                W(7,2), W(7,3),
                W(3,2)
                );
        L(5,3,
                O(1,1, "emitter", DN_RT),
                O(2,1, "hmirror", LF),
                O(2,0, "expector"),
                O(2,2, "expector"),
                O(3,0, "mirror", UP_LF),
                O(3,1, "mirror", UP_LF),
                O(3,2, "mirror", DN_LF),

                W(0,0), W(1,0), W(0,2), W(1,2), W(0,1),
                W(4,0), W(4,1), W(4,2)
        );
        L(5,3,
                O(1,1, "emitter", DN_RT),
                O(2,1, "hmirror", LF),
                O(2,0, "expector"),
                O(2,2, "expector"),
                O(3,1, "mirror", UP_LF),

                W(0,0), W(1,0), W(0,2), W(1,2), W(0,1),
                W(4,0), W(4,1), W(4,2), W(3,0), W(3,2)
        );
        L(5,3,
                O(1,1, "emitter", DN_RT),
                O(2,1, "hmirror", LF),
                O(2,0, "expector"),
                O(2,2, "expector"),
                O(4,1, "expector"),
                O(3,1, "hmirror", UP_LF),

                W(0,0), W(1,0), W(0,2), W(1,2), W(0,1),
                W(3,0), W(3,2)
        );
        L(9,5,
                O(1,2, "emitter", RT),
                O(2,2, "hmirror", LF),
                O(3,2, "hmirror", LF),
                O(4,2, "hmirror", LF),
                O(5,2, "hmirror", LF),
                O(6,2, "hmirror", LF),
                O(7,2, "hmirror", LF),
                O(8,2, "expector"),

                O(1,1, "emitter", RT),
                O(2,1, "hmirror", LF),
                O(3,1, "hmirror", LF),
                O(4,1, "hmirror", LF),
                O(5,1, "hmirror", LF),
                O(6,1, "hmirror", LF),
                O(7,1, "hmirror", LF),
                O(8,1, "expector"),

                O(1,3, "emitter", RT),
                O(2,3, "hmirror", LF),
                O(3,3, "hmirror", LF),
                O(4,3, "hmirror", LF),
                O(5,3, "hmirror", LF),
                O(6,3, "hmirror", LF),
                O(7,3, "hmirror", LF),
                O(8,3, "expector"),

                W(0,0), W(1,0), W(2,0), W(3,0), W(4,0), W(5,0), W(6,0), W(7,0), W(8,0), W(9,0),
                W(0,4), W(1,4), W(2,4), W(3,4), W(4,4), W(5,4), W(6,4), W(7,4), W(8,4), W(9,4),

                W(0,1), W(0,2), W(0,3)
        );
        L(9,5,
                O(1,2, "emitter", RT),
                O(2,2, "hmirror", LF),
                O(3,2, "prism", RT),
                O(4,2, "hmirror", RT),
                O(5,2, "hmirror", LF),
                O(6,2, "hmirror", LF),
                O(7,2, "hmirror", LF),
                O(8,2, "expector"),

                O(1,1, "emitter", RT),
                O(2,1, "hmirror", LF),
                O(3,1, "prism", RT),
                O(4,1, "hmirror", LF),
                O(5,1, "hmirror", LF),
                O(6,1, "hmirror", LF),
                O(7,1, "hmirror", LF),
                O(8,1, "expector"),

                O(1,3, "emitter", RT),
                O(2,3, "hmirror", LF),
                O(3,3, "prism", RT),
                O(4,3, "hmirror", LF),
                O(5,3, "hmirror", LF),
                O(6,3, "hmirror", LF),
                O(7,3, "hmirror", LF),
                O(8,3, "expector"),

                W(0,0), W(1,0), W(2,0), W(3,0), W(4,0), W(5,0), W(6,0), W(7,0), W(8,0), W(9,0),
                W(0,4), W(1,4), W(2,4), W(3,4), W(4,4), W(5,4), W(6,4), W(7,4), W(8,4), W(9,4),

                W(0,1), W(0,2), W(0,3)
        );
        L(4,8,
                O(0,2, "expector"),
                O(2,6, "expector"),

                O(1,2, "hmirror", UP_LF),
                O(1,1, "mirror", UP),
                O(2,1, "mirror", UP),
                O(2,3, "emitter", DN),
                O(2,2, "switch", UP),

                O(1,5, "mirror", UP_RT),
                O(1,6, "mirror", DN_RT),
                O(2,5, "emitter", LF),

                W(0,0), W(0,1), W(0,3), W(0,4), W(0,5), W(0,6), W(0,7),
                W(3,0), W(3,1), W(3,2), W(3,3), W(3,4), W(3,5), W(3,6), W(3,7),
                W(1,0), W(2,0), W(1,7), W(2,7),
                W(2,4)
        );
        L(8,7,
                O(1,2, "emitter", RT),
                O(2,2, "switch", LF),
                O(3,2, "hmirror", DN_LF),
                O(2,1, "mirror", UP_RT),
                O(3,1, "mirror", UP_LF),
                O(3,4, "mirror", DN_LF),
                O(4,4, "hmirror", UP_RT),
                O(4,5, "hmirror", DN_RT),
                O(5,5, "mirror", DN_LF),
                O(5,4, "switch", RT),

                O(5,1, "emitter", UP),
                O(5,2, "mirror", DN_RT),

                O(6,4, "emitter", LF),

                O(2,4, "expector"),
                O(2,5, "expector"),
                O(6,2, "expector"),

                W(5,3), W(6,3), W(2,3), W(1,3), W(0,3), W(7,3),
                W(1,4), W(1,5), W(1,6), W(2,6), W(3,6), W(4,6), W(5,6), W(6,6), W(6,5), W(7,5), W(7,4),

                W(0,2), W(0,1), W(1,1), W(1,0), W(2,0), W(3,0), W(4,0), W(4,1), W(5,0), W(6,0), W(6,1),
                W(7,1), W(7,2)
        );
    }
}
