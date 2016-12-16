package hackathon.hardcode.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

import hackathon.hardcode.model.ChatSession;
import hackathon.hardcode.model.CrowdSourceDTO;
import hackathon.hardcode.mybatis.BotMapper;
import hackathon.hardcode.service.interfaces.BotService;

/**
 * Bot service implementation.
 * 
 * @author Evgeni Bokhanov
 */
@Service
@Transactional
public class BotServiceImpl implements BotService {

	private static final List<ChatSession> sessions = new ArrayList<>();
	
	@Autowired private BotMapper botMapper;

	@Override
	public String getCrowdSourceAnswer(String query) {
		try {		
			List<CrowdSourceDTO> data = botMapper.getData();
			
			for(CrowdSourceDTO d: data) {
				d.setK(findTanimotoByWords(query, d.getStimulus()));
			}
	
			double max = Double.NEGATIVE_INFINITY;
			int maxIndex = 0;
			for (int i=0; i<data.size(); i++) {
				CrowdSourceDTO dto = data.get(i);
				if (dto.getK() > max) {
					max = dto.getK();
					maxIndex = i;
				}
			}
		   
			return data.get(maxIndex).getAnswer();
		} catch (Exception e) {
			e.printStackTrace();
			return "Sorry, I don't understand you. Can you reword it?";
		}
	}
		
	private Double findTanimotoByWords(String s1, String s2) {
				
		String[] words1 = preProcess(s1).split("\\s+");
		String[] words2 = preProcess(s2).split("\\s+");
		
		Integer a = words1.length;
		Integer b = words2.length;
		Integer c = 0;
		
		for(int i=0; i<words1.length; i++) {
			for(int j=0; j<words2.length; j++) {
				
				if(words1[i].equals(words2[j])) {
					c++;
					words2[j]=null;
					break;
				}
			}
		}
		
		Double k = ((double)c) / (double)(a + b - c);
		return k;		
	}
	
	private String preProcess(String str) {
		String s = str.toLowerCase();
		return s.replaceAll("[^a-zA-Z0-9 ]", "");
	}
	
	private ChatterBotSession findSession(ChatterBotType botType, String locale) {
		for(ChatSession s: sessions) {
			if(s.getBotType().equals(botType) && s.getLocale().equals(locale)) {
				return s.getSession();
			}
		}
		return null;
	}

	@Override
	public String getChatterBotAnswer(String query, String locale, String botType) {
		try {
			ChatterBotType type;
			
			switch (botType) {
				case "cleverbot": type = ChatterBotType.CLEVERBOT; break;
				case "pandorabot": type = ChatterBotType.PANDORABOTS; break;
				//case "jabberwackybot": type = ChatterBotType.JABBERWACKY; break;
				default: type = ChatterBotType.CLEVERBOT; break;
			}
			
			ChatterBotSession existingSession = findSession(type, locale);
			if(null == existingSession) {
				ChatterBotFactory factory = new ChatterBotFactory();
		        ChatterBot cleverBot = type.equals(ChatterBotType.PANDORABOTS)
		        	? factory.create(type, "b0dafd24ee35a477") : factory.create(type);
		        ChatterBotSession cleverBotSession = cleverBot.createSession(new Locale(locale));
		        
		        sessions.add(new ChatSession(type, locale, cleverBotSession));
		        return cleverBotSession.think(query);
			} else {
		        return existingSession.think(query);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			return "Sorry, I don't understand you. Can you reword it?";
		}
	}

	@Override
	public void addCrowdSourceAnswer(String question, String answer) {
		botMapper.addCrowdSourceAnswer(question, answer);		
	}

	@Override
	public Integer getNumberOfSession() {
		return sessions.size();
	}
			
}
