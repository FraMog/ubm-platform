package it.ubmplatform.feedback;

import java.util.ArrayList;

public interface FeedbackInterface {
	
	boolean queryInserisciFeedback(Feedback toInsert);
	Feedback queryOttieniFeedbackDaModificare(String emailP, String emailR);
	boolean queryModificaFeedback(Feedback changed);
	ArrayList<Feedback> queryVisualizzaFeedbacks(String emailR);

}
