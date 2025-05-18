import java.util.*;

public class ProblemQueue {

    public static void remAllQueue(Queue<Object> queue, Object item) {

	    Queue<Object> temp = new LLQueue<Object>();

        while (!queue.isEmpty()) {

            Object front = queue.remove();
            if (!front.equals(item)) {

                temp.insert(front);
            }
        }
        while (!temp.isEmpty()) {

            queue.insert(temp.remove());
        }
    }
    public static void main(String[] args) {

        Queue<Object> queue1 = new LLQueue<Object>();
        queue1.insert(1);
        queue1.insert(2);
        queue1.insert(3);
        queue1.insert(4);
        queue1.insert(2);
        queue1.insert(2);

        System.out.println("Original Stack: " + queue1);
        remAllQueue(queue1, 2);
        System.out.println("Modified Stack: " + queue1);  // Should print: [1, 3, 4]
    }
}