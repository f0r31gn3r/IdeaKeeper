package lv.javaguru.java3.core.commands.users;

/**
 * Created by Anna on 20.11.2015.
 */
import lv.javaguru.java3.core.commands.DomainCommandResult;

public class DeleteUserResult implements DomainCommandResult{

    private int result;

    public int getResult() {
        return result;
    }

    public DeleteUserResult(int result) {
        this.result = result;
    }



}