package pl.koneckimarcin.usersservice.user.external;

public class Athlete {

    private Long id;

    private String firstName;

    private String lastName;

    private boolean isAssignedToUser = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAssignedToUser() {
        return isAssignedToUser;
    }

    public void setAssignedToUser(boolean assignedToUser) {
        isAssignedToUser = assignedToUser;
    }
}
