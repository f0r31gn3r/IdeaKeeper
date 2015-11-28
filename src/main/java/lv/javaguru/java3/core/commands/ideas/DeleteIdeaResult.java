package lv.javaguru.java3.core.commands.ideas;

import lv.javaguru.java3.core.commands.DomainCommandResult;

/**
 * Created by Anna on 27.11.2015.
 */
public class DeleteIdeaResult implements DomainCommandResult {

    private int result;

    public int getResult() {
        return result;
    }

    public DeleteIdeaResult(int result) {
        this.result = result;
    }



}