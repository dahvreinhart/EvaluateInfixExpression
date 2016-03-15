/**
 * Note that this particular interface for StringList does not
 * specify the use of any exceptions. Error cases are handled
 * usually by return null values from relevant methods.
 */

public interface StringList {
    /** 
     * Examines the StringList; if there are no items  returns
     * true, otherwise false.
     *
     * @return whether or not the list contains Strings.
     */
    public boolean isEmpty();

    /** 
     * Either retrieves or computes the number of Strings
     * currently stored in the list
     *
     * @return number greater than or equal to 0 corresponding
     * to number of items in the list.
     */ 
    public int getLength();

    /** 
     * If the list has at least one String, then the
     * String at the end of the list (the tail) is removed.
     * If there are no Strings in the list, then the value returned
     * is null.
     *
     * @return a String object corresponding the the String
     * at the tail of the list before removal; possibly null.
     */
    public String removeTail();

    /**                                                       
     * Accepts a String to be added to the end of the   
     * list (i.e., after the current ail.)                    
     * @param item String to be placed at the end of the list.         
     */ 
    public void insertTail(String item);

    /**                                                       
     * Takes an integer value indicate that the ith String
     * in the list is to be returned. When i is 0, the first  
     * String is returned, when i is 1, the second is   
     * returned, etc. If there is no such String i, then null         
     * is returned. The list is not modified by this operation.
     *
     * @param i indicates the number of the item from the start of          
     * of the list which will be the String returned    
     * @return contents of the ith item in the list; possibly null          
     */                                                       
    public String retrieve(int i);
}
