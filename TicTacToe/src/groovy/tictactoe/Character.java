package tictactoe;

/**
 * User: Hunter
 * Date: 8/17/13
 */
public enum Character
{
    X("x"),
    O("o");

    private final String name;

    Character(String c)
    {
        name = c;
    }

    public String toString()
    {
        return name;
    }

}
