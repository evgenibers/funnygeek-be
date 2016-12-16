package hackathon.hardcode.mybatis;

import java.util.List;

import hackathon.hardcode.model.CrowdSourceDTO;

/**
 * Bot mapper.
 * 
 * @author Evgeni Bokhanov
 */
public interface BotMapper {

	Long getDataCount();
	
	List<CrowdSourceDTO> getData();

	void addCrowdSourceAnswer(String question, String answer);

}
