package Server;

public class Protocol {
    final int INITIAL = 0;
    final int INTHELOOP = 1;

    //Database d = new Database();

    protected int state = INITIAL;

    public String getOutput(String fromPlayer){
        if (state == INITIAL){
            state = INTHELOOP;
            return "hej och hå";

        }
        else if (state == INTHELOOP){
            //return d.(fromPlayer);
        }
        return "nope didnt happen";
    }
}
