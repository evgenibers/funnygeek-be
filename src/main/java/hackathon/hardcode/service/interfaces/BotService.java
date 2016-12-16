package hackathon.hardcode.service.interfaces;

/**
 * Bot service.
 * 
 * @author Evgeni Bokhanov
 */
public interface BotService {

	String getCrowdSourceAnswer(String query);

	String getChatterBotAnswer(String query, String locale, String botType);

	void addCrowdSourceAnswer(String question, String answer);

	Integer getNumberOfSession();
	
}
