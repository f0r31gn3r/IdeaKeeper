package lv.javaguru.java3.core.dto.idea;

import lv.javaguru.java3.core.dto.user.UserDTO;

import java.io.Serializable;

/**
 * Created by Anna on 22.11.2015.
 */
public class IdeaDTO implements Serializable {

    private Long ideaId;

    private String title;

    private String description;

    private UserDTO userDTO;

    private Long userId;

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
