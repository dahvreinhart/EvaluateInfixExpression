/*Dahv Reinhart V00735279
 *CSC115 Assignment 4: StringStackRefBased and EvalInfix
 *This class creates an implementation of a stack for use in calculating an arithmetic expression
 *using a infix to postfix method.
 *Tests for this class are available in test_output.txt
 */
public class StringStackRefBased implements StringStack {
    private StringNode head;
    private StringNode curr; //global variables
    private StringNode tail;
    
    public StringStackRefBased() {
        head = null;
        tail = null;
        curr = null;
    }
    //This method checks if the head of the stakc is null. if so, the stack must be empty.
    public boolean isEmpty() {
        return (head == null);
    }
    //This method removes an item from the stack and returns that item to the method that celled.
    //if the stack is empty, then an exception is thrown
    public String pop() throws StringStackException {
        if (isEmpty())
            throw new StringStackException("Stack is Empty!");
        String pop = tail.toString();
        for (curr = head; curr != null; curr = curr.next) {
            if (curr.next == tail) {
                break;
            }
            if (curr == tail) {
                tail = null;
                head = null;
                return pop;
            }
        }
        tail = curr;
        return pop;
    }
    //This method pops all the items off the stack to make an empty stack
    public void popAll() {
        head = null;
        tail = null;
    }
    //This method adds an item to the stack at the tail position.
    public void push(String item) throws StringStackException {
        StringNode newNode = new StringNode(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    //This method returns the identity of an item at the tail of the stack. It does not remove this item.
    //if the stack is empty, an exception is thrown.
    public String peek() throws StringStackException {
        if (isEmpty())
            throw new StringStackException("Stack is Empty!");
        String peek = tail.toString();
        return peek;
    }
    //*********************************MAIN METHOD********************************************
    public static void main(String[] args) {
        //TESTS ARE BELOW--
        
        //Test 1: test to see if construction works and if the resulting stack is correctly empty
        StringStack testStack = new StringStackRefBased();
        boolean empty = testStack.isEmpty();
        if (empty == true)
            System.out.println("Test 1: Passed!");
        else
            System.out.println("Test 1: FAILED");
        
        //Test 2: test the push function of the stack and if after a push, the stack is correctly NOT empty
        try {
            testStack.push("hello");
            empty = testStack.isEmpty();
            if (empty == false)
                System.out.println("Test 2: Passed!");
            else
                System.out.println("Test 2: FAILED");
        }
        catch(StringStackException ex1) {
            System.err.println(ex1);
        }
        
        //Test 3: test the peak function and test to see that it returns the string at the top of the stack
        try {
            testStack.push("hello");
            testStack.push("how");
            testStack.push("are");
            testStack.push("you?");
            String peekTest = testStack.peek();
            String expected3 = "you?";
            if (peekTest.equals(expected3))
                System.out.println("Test 3: Passed!");
            else
                System.out.println("Test 3: FAILED");
        }
        catch(StringStackException ex2) {
            System.err.println(ex2);
        }
        
        //Test 4: test the pop function of the stack and test to see if it returns the correct string
        try {
            testStack.pop();
            String test4 = testStack.pop();
            String expected4 = "are";
            if (test4.equals(expected4))
                System.out.println("Test 4: Passed!");
            else
                System.out.println("Test 4: FAILED");
        }
        catch(StringStackException ex3) {
            System.err.println(ex3);
        }
        
        //Test 5: test the functionality of the popAll method. isEmpty should be true after this has been called.
        testStack.popAll();
        empty = testStack.isEmpty();
        if (empty == true)
            System.out.println("Test 5: Passed!");
        else
            System.out.println("Test 5: FAILED");
            
        //Test 6: complex test with multiple pushes and pops to test the correct ordering of the stack
        StringStack testStack2 = new StringStackRefBased();
        try {
            testStack2.push("one");
            testStack2.push("two");
            testStack2.push("three");
            testStack2.push("four");
            testStack2.push("five");
            String peeker = testStack2.peek(); // should be "five"
            testStack2.pop();
            String four = testStack2.pop(); // should be "four"
            String three = testStack2.pop(); // should be "three"
            String two = testStack2.pop(); // should be "two"
            String one = testStack2.pop(); // should be "one"
            boolean empty2 = testStack2.isEmpty(); //should be true at this point
            if (peeker.equals("five") && four.equals("four") && three.equals("three") && two.equals("two") && one.equals("one") && empty2)
                System.out.println("Test 6: Passed!");
            else
                System.out.println("Test 6: FAILED");
        }
        catch(StringStackException ex4) {
            System.err.println(ex4);
        }
    }
}