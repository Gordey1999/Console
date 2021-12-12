package com.gordey.console.lib;

import java.util.Comparator;

public class ObjectRenderComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        return (int)(o1.getDepth() - o2.getDepth());
    }
}
