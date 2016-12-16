package hackathon.hardcode.model;

/**
 * Identifier.
 * 
 * @author Evgeni Bokhanov
 */
public class Identifier {

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
	@Override
	public String toString() {
		return "Identifier [id=" + id + "]";
	}
    
}
