// Time Complexity : O(1)
// Space Complexity :O(N)
// Did this code successfully run on Leetcode : yes


class LRUCache {
    private static class ListNode {
        int key;
        int value;
        ListNode prev;
        ListNode next;
        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, ListNode> map;
    private ListNode head;
    private ListNode tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        ListNode nodeRef = map.get(key);
        int value = nodeRef.value;
        removeListNode(nodeRef);
        addToHead(nodeRef);
        return value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            ListNode nodeRef = map.get(key);
            nodeRef.value = value;
            removeListNode(nodeRef);
            addToHead(nodeRef);
        } else {
            //fresh
            if(this.capacity == map.size()) {
                ListNode lru = this.tail.prev;
                removeListNode(lru);
                map.remove(lru.key);
            }
            ListNode newListNode = new ListNode(key, value);
            map.put(key, newListNode);
            addToHead(newListNode);
        }
    }


    private void removeListNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
}