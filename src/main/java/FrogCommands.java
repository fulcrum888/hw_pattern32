public class FrogCommands {
    public static FrogCommand jumpRightCommand (Frog frog, int steps) {
        return new FrogCommand() {
            @Override
            public boolean doAction() {
                boolean result;
                result = frog.jump(steps);
                return result;
            }

            @Override
            public boolean undoAction() {
                boolean result;
                result = frog.jump(-steps);
                return result;
            }
        };
    }

    public static FrogCommand jumpLeftCommand(Frog frog, int steps) {
        return new FrogCommand() {
            @Override
            public boolean doAction() {
                boolean result;
                result = frog.jump(-steps);
                return result;
            }

            @Override
            public boolean undoAction() {
                boolean result;
                result = frog.jump(steps);
                return result;
            }
        };
    }

}
