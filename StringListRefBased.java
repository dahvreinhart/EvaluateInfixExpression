public class StringListRefBased implements StringList {
    private StringNode head;
    private StringNode tail;


    public StringListRefBased() {
        head = null;
        tail = null;
    }


    public boolean isEmpty() {
        return (head == null);
    }


    public int  getLength() {
        int len = 0;

        for (StringNode l = head; l != null; l = l.next) {
            len++;
        }
        
        return len;
    }


    public String removeTail() {
        String result;

        if (head == null) {
            result = null;
        } else if (head == tail) {
            result = tail.item;
            head = tail = null;
        } else {
            StringNode l;
            for (l = head; l.next != tail; l = l.next) { }
            result = l.next.item;
            l.next = null;
            tail = l;
        }
        return result;
    }


    public void insertTail(String item) {
        if (tail == null) {
            head = tail = new StringNode(item);
        } else {
            tail.next = new StringNode(item);
            tail = tail.next;
        }

    }


    public String retrieve(int i) {
        int pos;
        StringNode curr;

        for (pos = 0, curr = head; 
            curr != null && pos < i; curr = curr.next, pos++ ) { }

        if (pos != i || curr == null) {
            return null;
        } else {
            return curr.item;
        }
    }


    private void dumpList() {
        String separator = "";

        for (StringNode n = head; n != null; n = n.next) {
            System.out.print(separator + n.item.toString());
            separator = " ";
        }
        System.out.println();
    }


    public String toString() {
        String result = "";
        String separator = "";

        for (StringNode n = head; n != null; n = n.next) {
            result += separator + n.item.toString();
            separator = " ";
        }

        return result;
    }
}
