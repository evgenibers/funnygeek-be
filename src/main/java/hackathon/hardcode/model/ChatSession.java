package hackathon.hardcode.model;

import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

/**
 * Bot chat session.
 * 
 * @author Evgeni Bokhanov
 */
public class ChatSession {

	private ChatterBotType botType;
	private String locale;
	private ChatterBotSession session;
		
	public ChatSession() {}
		
	public ChatSession(ChatterBotType botType, String locale, ChatterBotSession session) {
		this.botType = botType;
		this.locale = locale;
		this.session = session;
	}

	public ChatterBotType getBotType() { return botType; }
	public void setBotType(ChatterBotType botType) { this.botType = botType; }
	public String getLocale() { return locale; }
	public void setLocale(String locale) { this.locale = locale; }
	public ChatterBotSession getSession() { return session; }
	public void setSession(ChatterBotSession session) { this.session = session; }
	
	@Override
	public String toString() {
		return "ChatSession [botType=" + botType + ", locale=" + locale + ", session=" + session + "]";
	}
		
}
