class StringNode {
    String  item;
    StringNode next;

    StringNode(String item) {
        this.item = item;
        this.next = null;
    }

    public String toString() {
        return item;
    }
}
