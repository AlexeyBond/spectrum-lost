package com.github.alexeybond.spectrum_lost.levels.hardcoded;

import com.github.alexeybond.spectrum_lost.model.util.Direction;

/**
 *
 */
public class Chapter0 extends Source {
    {
        backwardCompatible = true;
        L(//L-1
                5, 4,
                O(3, 2, EMITTERCELL, Direction.UP),
                O(3, 1, MIRRORCELL, Direction.DN_RT),
                O(1, 1, ANYEXPECTORCELL),
                //
                W(0, 0), W(1, 0), W(2, 0), W(3, 0), W(4, 0),
                W(0, 1), W(4, 1),
                W(0, 2), W(1, 2), W(2, 2), W(4, 2),
                W(0, 3), W(1, 3), W(2, 3), W(3, 3), W(4, 3)
        );
        L(//L0
                5, 5,
                O(1, 3, EMITTERCELL, Direction.RT),
                O(3, 3, MIRRORCELL, Direction.UP_RT),
                O(3, 1, MIRRORCELL, Direction.UP_LF),
                O(1, 1, ANYEXPECTORCELL),
                O(1, 2, WALLCELL),
                W(0, 0), W(0, 1), W(0, 2), W(0, 3), W(0, 4),
                W(4, 0), W(4, 1), W(4, 2), W(4, 3), W(4, 4),
                W(0, 0), W(1, 0), W(2, 0), W(3, 0), W(4, 0),
                W(0, 4), W(1, 4), W(2, 4), W(3, 4), W(4, 4)
        );
        L(//L1
                8, 6,
                O(1, 4, EMITTERCELL, Direction.UP_RT),
                O(4, 1, MIRRORCELL, Direction.UP),
                O(4, 3, MIRRORCELL, Direction.UP_RT),
                O(4, 4, MIRRORCELL, Direction.UP_LF),
                O(6, 3, MIRRORCELL, Direction.UP_LF),
                O(6, 1, ANYEXPECTORCELL),
                O(5, 1, WALLCELL),
                W(0, 2), W(1, 2), W(2, 2), W(2, 1), W(3, 1), W(3, 0), W(4, 0), W(5, 0), W(6, 0), W(7, 0),
                W(0, 5), W(1, 5), W(2, 5), W(3, 5), W(4, 5), W(5, 5), W(5, 4), W(6, 4), W(7, 4),
                W(0, 3), W(0, 4),
                W(7, 1), W(7, 2), W(7, 3)
        );
        L(//L2
                11, 7,
                O(1, 4, EMITTERCELL, Direction.DN),
                O(1, 3, MIRRORCELL, Direction.UP_LF),
                O(1, 5, MIRRORCELL, Direction.UP_RT),
                O(3, 1, MIRRORCELL, Direction.UP_LF),
                O(3, 4, MIRRORCELL, Direction.UP_LF),
                O(6, 1, MIRRORCELL, Direction.UP_LF),
                O(6, 2, MIRRORCELL, Direction.UP_LF),
                O(6, 4, MIRRORCELL, Direction.UP_LF),
                O(7, 1, MIRRORCELL, Direction.UP_LF),
                O(7, 5, MIRRORCELL, Direction.UP_LF),
                O(9, 3, MIRRORCELL, Direction.UP_LF),
                O(8, 2, ANYEXPECTORCELL),
                O(9, 5, PRISMCELL),
                W(1, 0), W(2, 0), W(3, 0), W(4, 0), W(5, 0), W(6, 0), W(7, 0), W(8, 0), W(9, 0),
                W(0, 6), W(1, 6), W(2, 6), W(3, 6), W(4, 6), W(5, 6), W(6, 6), W(7, 6), W(8, 6), W(9, 6),
                W(1, 1), W(1, 2), W(0, 2), W(0, 3), W(0, 4), W(0, 5),
                W(9, 1), W(10, 1), W(10, 2), W(10, 3), W(10, 4), W(10, 5),
                W(8, 4), W(8, 5), W(10, 6)
        );
        L(//L3
                11, 7,
                O(1, 3, EMITTERCELL, Direction.UP_RT),
                //RT mirrors
                O(1, 5, MIRRORCELL, Direction.UP_RT),
                O(7, 5, MIRRORCELL, Direction.UP_RT),
                //Wrong mirrors
                O(3, 1, MIRRORCELL, Direction.UP),
                O(6, 4, MIRRORCELL, Direction.UP),
                O(8, 2, MIRRORCELL, Direction.UP),
                O(9, 3, MIRRORCELL, Direction.LF),
                O(8, 4, MIRRORCELL, Direction.UP),
                O(5, 1, MIRRORCELL, Direction.UP),
                O(3, 3, MIRRORCELL, Direction.RT),
                O(4, 4, MIRRORCELL, Direction.UP),
                //
                O(7, 1, ANYEXPECTORCELL),
                //
                W(2, 0), W(3, 0), W(4, 0), W(5, 0), W(6, 0), W(7, 0),
                W(1, 1), W(2, 1), W(4, 1), W(6, 1), W(8, 1), W(9, 1),
                W(0, 2), W(1, 2), W(9, 2), W(10, 2),
                W(0, 3), W(0, 4), W(0, 5),
                W(10, 3), W(10, 4), W(9, 4), W(9, 5), W(8, 5),
                W(0, 6), W(1, 6), W(2, 6), W(3, 6), W(4, 6), W(5, 6), W(6, 6), W(7, 6), W(8, 6)
        );
        L(//L3.01
                9, 7,
                O(1, 3, EMITTERCELL, Direction.RT),
                O(3, 3, PRISMCELL, Direction.RT),
                O(1, 2, MIRRORCELL, Direction.DN_RT),
                O(1, 4, MIRRORCELL, Direction.UP_RT),
                O(3, 2, MIRRORCELL, Direction.DN_LF),
                O(3, 4, MIRRORCELL, Direction.UP_LF),
                O(5, 1, MIRRORCELL, Direction.DN_LF),
                O(5, 2, MIRRORCELL, Direction.DN_RT),
                O(5, 3, MIRRORCELL, Direction.UP_LF),
                O(5, 4, MIRRORCELL, Direction.UP_RT),
                O(5, 5, MIRRORCELL, Direction.UP_LF),
                O(7, 2, MIRRORCELL, Direction.LF),
                O(7, 4, MIRRORCELL, Direction.LF),
                O(7, 3, ANYEXPECTORCELL),
                //
                W(4, 0), W(5, 0), W(6, 0),
                W(0, 1), W(1, 1), W(2, 1), W(3, 1), W(4, 1), W(6, 1), W(7, 1), W(8, 1),
                W(0, 5), W(1, 5), W(2, 5), W(3, 5), W(4, 5), W(6, 5), W(7, 5), W(8, 5),
                W(4, 6), W(5, 6), W(6, 6),
                W(0, 2), W(0, 3), W(0, 4), W(8, 2), W(8, 3), W(8, 4)
        );
        L(//L3.02
                7, 7,
                O(3, 5, EMITTERCELL, Direction.UP),
                O(3, 4, PRISMCELL, Direction.UP),
                O(3, 3, PRISMCELL, Direction.DN),
                O(2, 3, PRISMCELL, Direction.UP_LF),
                O(4, 3, PRISMCELL, Direction.UP_RT),
                O(1, 1, MIRRORCELL, Direction.DN),
                O(1, 3, MIRRORCELL, Direction.UP_RT),
                O(5, 1, MIRRORCELL, Direction.DN),
                O(5, 3, MIRRORCELL, Direction.UP_LF),
                O(3, 1, ANYEXPECTORCELL),
                O(2, 2, WALLCELL),
                O(4, 2, WALLCELL),
                //
                W(0, 0), W(1, 0), W(2, 0), W(3, 0), W(4, 0), W(5, 0), W(6, 0),
                W(0, 4), W(1, 4), W(2, 4), W(4, 4), W(5, 4), W(6, 4),
                W(2, 5), W(2, 6), W(3, 6), W(4, 6), W(4, 5),
                W(0, 1), W(0, 2), W(0, 3),
                W(6, 1), W(6, 2), W(6, 3)
        );
        L(//L4
                9, 8,
                O(1, 5, EMITTERCELL, Direction.RT),
                O(3, 5, PRISMCELL, Direction.RT),
                O(4, 4, PRISMCELL, Direction.UP),
                O(4, 6, MIRRORCELL, Direction.UP),
                O(7, 5, MIRRORCELL, Direction.UP_LF),
                O(7, 4, MIRRORCELL, Direction.DN_LF),
                O(7, 3, MIRRORCELL, Direction.LF),
                O(6, 2, MIRRORCELL, Direction.DN),
                O(2, 3, MIRRORCELL, Direction.UP),
                O(2, 1, MIRRORCELL, Direction.UP_RT),
                O(4, 1, MIRRORCELL, Direction.UP_LF),
                O(6, 3, ANYEXPECTORCELL),
                //
                W(1, 0), W(2, 0), W(3, 0), W(4, 0), W(5, 0),
                W(5, 2), W(5, 1), W(6, 1), W(7, 1), W(8, 1),
                W(8, 2), W(8, 3), W(8, 4), W(8, 5), W(8, 6),
                W(1, 1), W(1, 2), W(1, 3), W(1, 4), W(0, 4), W(0, 5), W(0, 6),
                W(0, 7), W(1, 7), W(2, 7), W(3, 7), W(4, 7), W(5, 7), W(6, 7), W(7, 7), W(8, 7)
        );
        L(//L5
                5, 6,
                O(1, 4, EMITTERCELL, Direction.RT),
                O(2, 4, PRISMCELL, Direction.RT),
                O(3, 3, PRISMCELL, Direction.UP),
                O(3, 2, PRISMCELL, Direction.UP),
                O(2, 1, PRISMCELL, Direction.UP),
                O(3, 4, MIRRORCELL, Direction.UP_LF),
                O(1, 1, MIRRORCELL, Direction.DN_LF),
                O(1, 3, WALLCELL),
                O(1, 2, ANYEXPECTORCELL),
                //
                W(0, 0), W(1, 0), W(2, 0), W(3, 0), W(3, 1), W(4, 1),
                W(0, 5), W(1, 5), W(2, 5), W(3, 5), W(4, 5),
                W(0, 1), W(0, 2), W(0, 3), W(0, 4), W(0, 5),
                W(4, 2), W(4, 3), W(4, 4)
        );
        L(//L6
                9, 8,
                O(4, 1, ANYEXPECTORCELL),
                O(4, 2, PRISMCELL, Direction.UP),
                O(4, 5, MIRRORCELL, Direction.UP_LF),
                O(4, 6, MIRRORCELL, Direction.UP_RT),
                O(1, 4, MIRRORCELL, Direction.DN_RT),
                O(7, 4, MIRRORCELL, Direction.DN_LF),
                O(3, 5, PRISMCELL, Direction.UP_RT),
                O(5, 5, PRISMCELL, Direction.UP_LF),
                O(3, 6, PRISMCELL, Direction.UP),
                O(5, 6, PRISMCELL, Direction.UP),
                O(2, 6, EMITTERCELL, Direction.UP_RT),
                O(6, 6, EMITTERCELL, Direction.UP_LF),
                O(3, 3, WALLCELL),
                O(5, 3, WALLCELL),
                //
                W(4, 0),
                W(3, 1), W(2, 2), W(1, 3), W(0, 4), W(0, 5),
                W(5, 1), W(6, 2), W(7, 3), W(8, 4), W(8, 5),
                W(1, 5), W(1, 6), W(1, 7),
                W(7, 5), W(7, 6), W(7, 7),
                W(2, 7), W(4, 7), W(6, 7)
        );
        L(//L666
                12, 9,
                O(1, 7, EMITTERCELL, Direction.RT),
                O(10, 7, EMITTERCELL, Direction.UP),
                O(10, 1, ANYEXPECTORCELL),
                O(1, 1, MIRRORCELL, Direction.DN),
                O(1, 2, MIRRORCELL, Direction.UP_RT),
                O(1, 3, MIRRORCELL, Direction.DN_RT),
                O(1, 4, MIRRORCELL, Direction.UP_RT),
                O(1, 6, WALLCELL),

                O(2, 2, PRISMCELL, Direction.DN_LF),
                O(2, 4, PRISMCELL, Direction.UP_LF),
                O(2, 6, WALLCELL),

                O(3, 1, MIRRORCELL, Direction.DN),
                O(3, 3, MIRRORCELL, Direction.UP_RT),
                O(3, 4, MIRRORCELL, Direction.UP_LF),

                O(4, 1, MIRRORCELL, Direction.DN_LF),
                O(4, 3, MIRRORCELL, Direction.DN_LF),
                O(4, 6, PRISMCELL, Direction.UP),
                O(4, 7, MIRRORCELL, Direction.UP_LF),

                O(5, 1, MIRRORCELL, Direction.DN_RT),
                O(5, 3, PRISMCELL, Direction.UP_LF),
                O(5, 4, WALLCELL),

                O(6, 2, WALLCELL),
                O(6, 3, MIRRORCELL, Direction.RT),
                O(6, 4, MIRRORCELL, Direction.RT),
                O(6, 5, MIRRORCELL, Direction.RT),
                O(6, 6, MIRRORCELL, Direction.DN_RT),
                O(6, 7, MIRRORCELL, Direction.UP),

                O(7, 1, MIRRORCELL, Direction.DN_LF),
                O(7, 2, PRISMCELL, Direction.DN),
                O(7, 3, WALLCELL),
                O(7, 4, MIRRORCELL, Direction.UP),
                O(7, 5, MIRRORCELL, Direction.UP),
                O(7, 6, PRISMCELL, Direction.LF),
                O(7, 7, WALLCELL),

                O(8, 1, MIRRORCELL, Direction.DN_LF),
                O(8, 2, PRISMCELL, Direction.UP),
                O(8, 4, PRISMCELL, Direction.UP),
                O(8, 5, MIRRORCELL, Direction.DN_RT),
                O(8, 6, MIRRORCELL, Direction.UP_LF),
                O(8, 7, WALLCELL),

                O(9, 1, WALLCELL),
                O(9, 3, MIRRORCELL, Direction.LF),
                O(9, 4, PRISMCELL, Direction.UP_RT),
                O(9, 6, WALLCELL),
                O(9, 6, WALLCELL),
                O(9, 7, WALLCELL),

                O(10, 1, ANYEXPECTORCELL),
                O(10, 2, WALLCELL),
                O(10, 3, MIRRORCELL, Direction.LF),
                O(10, 4, WALLCELL),
                O(10, 5, MIRRORCELL, Direction.DN_LF),
                O(10, 7, EMITTERCELL, Direction.UP),

                W(0, 0), W(1, 0), W(2, 0), W(3, 0), W(4, 0), W(5, 0), W(6, 0), W(7, 0), W(8, 0), W(9, 0), W(10, 0), W(11, 0),
                W(0, 8), W(1, 8), W(2, 8), W(3, 8), W(4, 8), W(5, 8), W(6, 8), W(7, 8), W(8, 8), W(9, 8), W(10, 8), W(11, 8),

                W(0, 1), W(0, 2), W(0, 3), W(0, 4), W(0, 5), W(0, 6), W(0, 7),
                W(11, 1), W(11, 2), W(11, 3), W(11, 4), W(11, 5), W(11, 6), W(11, 7)
        );
    }
}
