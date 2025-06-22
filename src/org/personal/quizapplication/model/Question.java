package org.personal.quizapplication.model;

/**
 * Represents a question in the quiz.
 * This class contains the question text, options, correct option, and difficulty level.
 */
public class Question {
    
    private int id;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;
    private String difficulty;

    /**
     * Constructor to create a new instance of Question with specified values.
     * 
     * @param id the ID of the question
     * @param questionText the text of the question
     * @param option1 the first option for the question
     * @param option2 the second option for the question
     * @param option3 the third option for the question
     * @param option4 the fourth option for the question
     * @param correctOption the correct answer option
     * @param difficulty the difficulty level of the question (e.g., Easy, Medium, Hard)
     */
    public Question(Integer id, String questionText, String option1, String option2, String option3, 
                    String option4, String correctOption, String difficulty) {
        this.id = id;
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.difficulty = difficulty;
    }

    // Getters and Setters

    /**
     * Gets the ID of the question.
     * 
     * @return the question ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the question.
     * 
     * @param id the question ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the text of the question.
     * 
     * @return the question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the text of the question.
     * 
     * @param questionText the question text
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Gets the first option of the question.
     * 
     * @return the first option
     */
    public String getOption1() {
        return option1;
    }

    /**
     * Sets the first option of the question.
     * 
     * @param option1 the first option
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * Gets the second option of the question.
     * 
     * @return the second option
     */
    public String getOption2() {
        return option2;
    }

    /**
     * Sets the second option of the question.
     * 
     * @param option2 the second option
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * Gets the third option of the question.
     * 
     * @return the third option
     */
    public String getOption3() {
        return option3;
    }

    /**
     * Sets the third option of the question.
     * 
     * @param option3 the third option
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * Gets the fourth option of the question.
     * 
     * @return the fourth option
     */
    public String getOption4() {
        return option4;
    }

    /**
     * Sets the fourth option of the question.
     * 
     * @param option4 the fourth option
     */
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    /**
     * Gets the correct option of the question.
     * 
     * @return the correct option
     */
    public String getCorrectOption() {
        return correctOption;
    }

    /**
     * Sets the correct option of the question.
     * 
     * @param correctOption the correct option
     */
    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    /**
     * Gets the difficulty level of the question.
     * 
     * @return the difficulty level (e.g., Easy, Medium, Hard)
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level of the question.
     * 
     * @param difficulty the difficulty level (e.g., Easy, Medium, Hard)
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Checks if the selected option is correct.
     * 
     * @param selectedOption the option selected by the user
     * @return true if the selected option matches the correct option, false otherwise
     */
    public boolean isCorrectAnswer(String selectedOption) {
        return correctOption.equalsIgnoreCase(selectedOption);
    }
}
