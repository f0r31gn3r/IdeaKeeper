package lv.javaguru.java3.core.services.ideas;

/**
 * Created by Anna on 28.10.2015.
 */
public interface IdeaValidator {

    void validate(String title, String description, Long userId);

}
