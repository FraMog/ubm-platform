package it.ubmplatform.feedback;

import java.util.ArrayList;

public interface FeedbackInterface {
	
	boolean queryInserisciFeedback(Feedback toInsert);
	boolean queryModificaFeedback(Feedback changed);
	ArrayList<Feedback> queryVisualizzaFeedbacks(String emailR);

}
