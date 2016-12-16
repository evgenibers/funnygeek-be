package hackathon.hardcode.controller;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hackathon.hardcode.service.interfaces.BotService;

/**
 * Bot controller.
 * 
 * @author Evgeni Bokhanov
 */
@CrossOrigin
@RestController
public class BotController {

	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired private BotService botService;
	
	@RequestMapping(value = "/crowdsource/answer", method = RequestMethod.GET)
	HashMap<String, String> getCrowdSourceAnswer(
    @RequestParam(value="q") String query) {
        LOGGER.debug("getCrowdSourceAnswer: query = {}", query);
        final String answer = botService.getCrowdSourceAnswer(query);
        LOGGER.debug("getCrowdSourceAnswer: answer = {}", answer);
		return new HashMap<String, String>(){{ put("answer", answer); }};
    }
	
	@RequestMapping(value = "/crowdsource/answer", method = RequestMethod.POST)
	void addCrowdSourceAnswer(
    @RequestParam(value="q") String question,
    @RequestParam(value="a") String answer) {
        LOGGER.debug("getCrowdSourceAnswer: question = {}, answer = {}", question, answer);
        botService.addCrowdSourceAnswer(question, answer);
    }
		
	@RequestMapping(value = "/{botType}/answer", method = RequestMethod.GET)
	HashMap<String, String> getChatterBotAnswer(
    @PathVariable String botType,
    @RequestParam(value="q") String query,
    @RequestParam(value="l") String locale) {
        LOGGER.debug("getChatterBotAnswer: query = {}, locale = {}, botType = {}", query, locale.toLowerCase(), botType);
        final String answer = botService.getChatterBotAnswer(query, locale.toLowerCase(), botType);
        LOGGER.debug("getChatterBotAnswer: answer = {}", answer);
		return new HashMap<String, String>(){{ put("answer", answer); }};
    }
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	HashMap<String, String> getStatus() {
        LOGGER.debug("getStatus:");
        final String number = botService.getNumberOfSession().toString();
        return new HashMap<String, String>(){{ put("status", "ok"); put("sessions", number); }};
    }
			
}