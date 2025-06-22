package org.personal.quizapplication.services;


import org.personal.quizapplication.dao.QuizStatsDAO;
import org.personal.quizapplication.dao.impl.LeaderboardDAOImpl;
import org.personal.quizapplication.dao.LeaderboardDAO;
import org.personal.quizapplication.model.QuizStatistics;
import org.personal.quizapplication.model.LeaderboardEntry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizService {
	private final LeaderboardDAO leaderboardDAO;

    public QuizService() {
        this.leaderboardDAO = new LeaderboardDAOImpl(); // Instantiate the implementation
    }

    public QuizStatistics getQuizStatistics() {
        return QuizStatsDAO.getStatistics();
    }

    public List<LeaderboardEntry> getLeaderboard() {
			try {
				return leaderboardDAO.getLeaderboardByDifficulty();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			return List.of();
			return new ArrayList<>();
    }
}
