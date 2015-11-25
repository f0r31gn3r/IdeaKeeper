package lv.javaguru.java3.core.dto.idea;

import lv.javaguru.java3.core.domain.user.User;
import lv.javaguru.java3.core.dto.user.UserDTO;

/**
 * Created by Anna on 22.11.2015.
 */
public class IdeaDTOBuilder {

    private Long ideaId;

    private String title;

    private String description;

    private User user;

    private UserDTO userDTO;

    private Long userId;

    public static IdeaDTOBuilder createIdeaDTO() {
        return new IdeaDTOBuilder();
    }

    public IdeaDTO build() {
        IdeaDTO idea = new IdeaDTO();

        idea.setIdeaId(ideaId);
        idea.setTitle(title);
        idea.setDescription(description);
        idea.setUserId(userId);
        idea.setUserDTO(userDTO);

        return idea;
    }

    public IdeaDTOBuilder withId(Long id) {
        this.ideaId = id;
        return this;
    }

    public IdeaDTOBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public IdeaDTOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }


    public IdeaDTOBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public IdeaDTOBuilder withUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
        return this;
    }


}
