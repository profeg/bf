import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Проф on 10.02.2015.
 * Brainfuck language interpreter
 * used memory
 * char[] arr = chars[30000]
 * used operators
 * >    shift pointer right     pointer++
 * <    shift pointer left      pointer--
 * +    increment pointer       arr[pointer]++
 * -    decrement pointer       arr[pointer]--
 * [    begin loop              while (arr[pointer]) {
 * ]    end loop                }
 * .    print pointer           sout arr[pointer]
 * ,    enter from input string arr[pointer] = getchar
 */
public class Model {

    private static final int STACK_LENGHT = 30000;
    private static short[] arr = new short[STACK_LENGHT];


    //private static String strCommand = "----[---->+<]>++.";
    private static String strCommand = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
    //private static String strCommand = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]++++++++++++++++++++++++++++++++++++++++++++.[-]++++++++++++++++++++++++++++++++.[-]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-]+++++++++++++++++++++++++++++++++.[-]++++++++++.[-]";

    public static void main(String[] args) {
        execute(strCommand);
    }

    public static void execute(String strCommand) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] cmd_stack = strCommand.toCharArray();
        int cmd_pointer = 0;    //command pointer
        int pointer = 0;        //memory pointer
        ArrayList<Integer> queue = new ArrayList<Integer>();

        System.out.println("===== Output =====");

        while (cmd_pointer < cmd_stack.length) {
            switch (cmd_stack[cmd_pointer]) {
                case '+':
                    if ((arr[pointer]+1) > 255) arr[pointer] = 0;
                    else arr[pointer]++;
                    cmd_pointer++;
                    break;
                case '-':
                    if ((arr[pointer]-1) < 0) arr[pointer] = 255;
                    else arr[pointer]--;
                    cmd_pointer++;
                    break;
                case '>':
                    pointer++;
                    cmd_pointer++;
                    break;
                case '<':
                    pointer--;
                    cmd_pointer++;
                    break;
                case '.':
                    System.out.print((char) arr[pointer]);
                    cmd_pointer++;
                    break;
                case ',':
                    try {
                        arr[pointer] = (short) Integer.parseInt(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cmd_pointer++;
                    break;
                case '[':
                    queue.add(cmd_pointer);
                    cmd_pointer++;
                    break;
                case ']':
                    if ((arr[pointer]) > 0) cmd_pointer = queue.remove(queue.size()-1);
                    else cmd_pointer++;
                    break;
                default:
                    cmd_pointer++;
                    break;

            }
        }
        System.out.println();
        System.out.println("===== Final memory dump =====");
        for (int i = 0; i < 10; i++) {
            System.out.println("arr[" + i + "] " + arr[i]);
        }


    }
}
