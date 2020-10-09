import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;
    public static void main(String[] args) {
        Frog frog = new Frog();

        List<FrogCommand> commands = new ArrayList<>();
        int curCommand = -1;

        System.out.println("Введите команду:");
        System.out.println("+N - прыгни на N шагов направо");
        System.out.println("-N - прыгни на N шагов налево");
        System.out.println("<< - Undo (отмени последнюю команду)");
        System.out.println(">> - Redo (повтори отменённую команду)");
        System.out.println("!! - повтори последнюю команду");
        System.out.println("0 - выход\n");

        printField(frog.getPosition());
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("0")) break;

            switch (input) {
                case "<<":
                    if (curCommand < 0) {
                        System.out.println("Нечего отменять!");
                    } else {
                        commands.get(curCommand).undoAction();
                        curCommand--;
                    }
                    break;
                case ">>":
                    if (curCommand == commands.size() - 1) {
                        System.out.println("Нет отменённых команд!");
                    } else {
                        curCommand++;
                        commands.get(curCommand).doAction();
                    }
                    break;
                case "!!":
                    if (curCommand == -1) {
                        System.out.println("Еще нет команд");
                    } else {
                        if (commands.get(curCommand).doAction()) {
                            commands.add(commands.get(curCommand));
                            curCommand++;
                        } else {
                            System.out.println("Прыжок слишком большой");
                        }
                    }
                    break;
                default:
                    if ((input.charAt(0) != '-')
                            && ((input.charAt(0) != '+'))) {
                        System.out.println("Неправильная команда");
                    } else {
                        int steps = Integer.valueOf(input.substring(1));
                        if (curCommand != commands.size() - 1) {
                            for (int i = curCommand + 1; i < commands.size(); i++) {
                                commands.remove(i);
                            }
                        }
                        FrogCommand cmd;
                        if (input.charAt(0) == '-') {
                            cmd = FrogCommands.jumpLeftCommand(frog, steps);
                        } else {
                            cmd = FrogCommands.jumpRightCommand(frog, steps);
                        }
                        if (cmd.doAction()) {
                            curCommand++;
                            commands.add(cmd);
                        } else {
                            System.out.println("Прыжок слишком большой");
                        }
                    }
                    break;
            }
            printField(frog.getPosition());
        }
    }

    public static void printField(int currentPosition) {
        for (int i = MIN_POSITION; i <= MAX_POSITION; i++) {
            if (i == currentPosition) {
                System.out.print('X');
            } else {
                System.out.print('_');
            }
        }
        System.out.println();
    }
}
