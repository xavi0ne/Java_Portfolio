public class ProblemStack  {
    
    
    // FOR REVERSE ORDER 
    public static void remAllStack(Stack<Object> stack, Object item) {
        Queue<Object> tempQueue = new LLQueue<>();

        // Push only the elements that should remain
        while (!stack.isEmpty()) {
            Object top = stack.pop();
            if (!top.equals(item)) {  // Only keep elements that are NOT equal to item
                tempQueue.insert(top);
            }
        }

        // Restore stack with remaining elements in the correct order
        while (!tempQueue.isEmpty()) {
            stack.push(tempQueue.remove());
        }
    }

    public static void main(String[] args) {
        Stack<Object> stack = new LLStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(2);
        stack.push(4);

        System.out.println("Original Stack: " + stack);
        remAllStack(stack, 2);
        System.out.println("Modified Stack: " + stack);  // Should print: [1, 3, 4]
    }
}
   
