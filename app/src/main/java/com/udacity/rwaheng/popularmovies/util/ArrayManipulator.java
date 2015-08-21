package com.udacity.rwaheng.popularmovies.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rwaheng on 8/20/2015.
 */
public class ArrayManipulator {

    public static int[] convertToIntArray(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    public static List<Integer> convertToIntegerList(int []integers)
    {
        ArrayList<Integer> arraylist = new ArrayList<Integer>(integers.length);

        for (int i=0; i < integers.length; i++)
        {
            // ret[i] = integers.get(i).intValue();
            arraylist.add(integers[i]);
        }

        //Log.v("ArrayList", ""  + arraylist.size());
        return arraylist;
    }
}
