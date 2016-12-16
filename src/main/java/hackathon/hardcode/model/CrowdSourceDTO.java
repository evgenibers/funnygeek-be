package hackathon.hardcode.model;

/**
 * Crowd source data.
 * 
 * @author Evgeni Bokhanov
 */
public class CrowdSourceDTO extends Identifier {

	private String stimulus;
	private String answer;
	private Double k;
		
	public CrowdSourceDTO() {}
	
	public CrowdSourceDTO(String stimulus, String answer) {
		this.stimulus = stimulus;
		this.answer = answer;
	}
	
	public String getStimulus() { return stimulus; }
	public void setStimulus(String stimulus) { this.stimulus = stimulus; }
	public String getAnswer() { return answer; }
	public void setAnswer(String answer) { this.answer = answer; }
	public Double getK() { return k; }
	public void setK(Double k) { this.k = k; }
	
	@Override
	public String toString() {
		return "CrowdSourceDTO [stimulus=" + stimulus + ", answer=" + answer + ", k=" + k + "]";
	}
		
}
