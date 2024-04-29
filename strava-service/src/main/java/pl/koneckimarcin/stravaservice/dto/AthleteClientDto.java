package pl.koneckimarcin.stravaservice.dto;

public class AthleteClientDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AthleteClientDto{" +
                "id=" + id +
                '}';
    }
}
