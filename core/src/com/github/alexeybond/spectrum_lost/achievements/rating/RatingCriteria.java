package com.github.alexeybond.spectrum_lost.achievements.rating;

/**
 *
 * <pre>
 *     {
 *         "$strEq": "UP",
 *         "var": "grid/3;5/direction"
 *     }
 * </pre>
 */
public class RatingCriteria {
    private Double $lt = null;
    private Double $gt = null;
    private Double $numEq = null;
    private Double $numNEq = null;

    private String $strEq = null;
    private String $strNEq = null;

    private String var = null;

    private RatingCriteria[] $and = null;
    private RatingCriteria[] $or = null;

    private static IRatingVariable getNestedVar(final IRatingVariable variable, final String path) {
        IRatingVariable v = variable;

        for (String step : path.split("/")) {
            v = v.getV(step);
        }

        return v;
    }

    public boolean meet(final IRatingVariable variable) {
        IRatingVariable checkVar = variable;

        if (var != null) checkVar = getNestedVar(checkVar, var);

        boolean meet = true;

        if (null != $lt) meet = meet && checkVar.getN() < $lt;
        if (null != $gt) meet = meet && checkVar.getN() > $gt;
        if (null != $numEq) meet = meet && checkVar.getN() == $numEq;
        if (null != $numNEq) meet = meet && checkVar.getN() != $numNEq;

        if (null != $strEq) meet = meet && checkVar.getS().equals($strEq);
        if (null != $strNEq) meet = meet && !checkVar.getS().equals($strNEq);

        if (null != $and) {
            for (RatingCriteria rc : $and) {
                meet = meet && rc.meet(checkVar);
            }
        }

        if (null != $or) {
            boolean orMeet = false;

            for (RatingCriteria rc : $or) {
                if (rc.meet(checkVar)) {
                    orMeet = true;
                    break;
                }
            }

            meet = meet && orMeet;
        }

        return meet;
    }
}
