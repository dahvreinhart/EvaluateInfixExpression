public interface StringStack {
    /**
     * Returns true if there are no strings in the stack; false
     * otherwise.
     * @return true or false as described
     */
    public boolean isEmpty();

    /**
     * If the stack contains at least one string, then the string
     * at the top of the stack is removed, and the value contained
     * returned to the caller. Otherwise if the stack was empty
     * at the start of the method, then a StringStackException must
     * be thrown.
     *
     * @return the value of the String at the top of the stack.
     */
    public String pop() throws StringStackException;

    /**
     * Clears the stack such that is contains no elements (i.e.,
     * isEmpty() on the stack will be true after this call completes).
     */
    public void popAll();

    /**
     * Places the item onto the top of the stack. However, if there
     * is no room in which to place this item, then the method
     * must instead thrown a StringStackException.
     */
    public void push(String item) throws StringStackException;


    /**
     * If the stack contains at least one string, then the value of
     * the String at the top of the stack is returned to the caller.
     * Otherwise if the stack was empty at the start of the method, 
     * then a StringStackException must be thrown. This method
     * does not modify the stack's contents.
     *
     * @return the value of the String at the top of the stack.
     */
    public String peek() throws StringStackException;
}
