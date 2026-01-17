package tutorial.clazz;

public class Initialize {
    private int i;

    public Initialize() {
        System.out.println("Initialize");
    }

    public Initialize(int i) {
        super();
        this.i = i;
        System.out.println("Initialize i = " + i);
    }

    /**
     * The Java compiler copies initializer blocks into every constructor. Therefore, this approach can be used
     * to share a block of code between multiple constructors.
     *
     * This code is executed before every constructor.
     */
    {
        System.out.println("Initializer block");
    }


    static {
        System.out.println("This is a static block");
    }

    public static void main(String[] args) {
        Initialize initialize = new Initialize();

    }
}
