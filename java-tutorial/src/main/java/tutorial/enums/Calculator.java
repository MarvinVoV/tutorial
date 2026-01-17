package tutorial.enums;

public enum Calculator implements Operate {
    ADD {
        @Override
        public int execute(int a, int b) {
            return a + b;
        }
    },
    SUB {
        @Override
        public int execute(int a, int b) {
            return a - b;
        }
    },
    MUL {
        @Override
        public int execute(int a, int b) {
            return a * b;
        }
    };

    public static void main(String[] args) {
        System.out.println(Calculator.ADD.execute(1, 2));
    }

}
