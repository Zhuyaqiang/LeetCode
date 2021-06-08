package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class NestedInteger {
    NestedInteger[] vals;
    int val;
    public NestedInteger(NestedInteger[] vals) {
        this.vals = vals;
    }
    public NestedInteger(int val) {
        this.val = val;
    }
    public NestedInteger(int val, NestedInteger[] vals) {
        this.val = val;
        this.vals = vals;
    }
    public boolean isInteger() {
        return vals.length == 0;
    }
    public Integer getInteger() {
        if (vals.length == 0) {
            return val;
        }
        return null;
    }
    public List<NestedInteger> getList() {
        if (vals.length == 0) {
            return null;
        }
        return Arrays.asList(vals);
    }
}

public class NestedIterator implements Iterator<Integer> {
    List<Integer> vals;
    Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        vals = new ArrayList<>();
        dfs(vals, nestedList);
        iterator = vals.iterator();
    }

    private void dfs(List<Integer> vals, List<NestedInteger> nestedList) {
        if (nestedList.size() == 0) {
            return;
        }
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                vals.add(nestedInteger.getInteger());
            } else {
                dfs(vals, nestedInteger.getList());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }
}
