package janis.core.commands;

public class VoidResult implements DomainCommandResult {

    public static final VoidResult INSTANCE = new VoidResult();

    private VoidResult() {

    }

}
