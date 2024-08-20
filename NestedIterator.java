// Time Complexity : O(N)
// Space Complexity :O(d), where d is the depth of recursion
// Did this code successfully run on Leetcode : yes


public class NestedIterator implements Iterator<Integer> {

    private List<NestedInteger> nestedList;
    private Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.q = new LinkedList<>();
        dfs(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }

    private void dfs(List<NestedInteger> nestedList) {
        //base
        //logic
        for(NestedInteger ni: nestedList) {
            if(ni.isInteger()) {
                q.add(ni.getInteger());
            } else {
                dfs(ni.getList());
            }
        }
    }
}