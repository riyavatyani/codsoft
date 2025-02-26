import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    List<String> options;
    int correctAnswer;

    public Question(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class Quiz {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = createQuestions();
        int score = 0;
        List<Boolean> results = new ArrayList<>(); 

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.question);
            for (int j = 0; j < currentQuestion.options.size(); j++) {
                System.out.println((j + 1) + ". " + currentQuestion.options.get(j));
            }

            final int questionIndex = i; 
            final int[] userAnswer = {-1}; 

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (userAnswer[0] == -1) {
                        System.out.println("\nTime's up!");
                        results.add(false); 
                        printAnswer(questions.get(questionIndex).correctAnswer);
                        timer.cancel();

                    }
                }
            }, 10000); 

            System.out.print("Your answer (1-" + currentQuestion.options.size() + "): ");
            if (scanner.hasNextInt()) {
                userAnswer[0] = scanner.nextInt();

                if (userAnswer[0] >= 1 && userAnswer[0] <= currentQuestion.options.size()) {
                    if (userAnswer[0] - 1 == currentQuestion.correctAnswer) {
                        score++;
                        results.add(true);
                        System.out.println("Correct!");
                    } else {
                        results.add(false);
                        System.out.println("Incorrect.");
                        printAnswer(currentQuestion.correctAnswer);
                    }
                } else {
                    System.out.println("Invalid input. Incorrect.");
                    results.add(false);
                    printAnswer(currentQuestion.correctAnswer);
                }
            } else {
                System.out.println("Invalid input. Incorrect.");
                results.add(false);
                printAnswer(currentQuestion.correctAnswer);
                scanner.next(); 
            }
            timer.cancel();
        }

        displayResults(score, questions.size(), results);
        scanner.close();
    }

    public static List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Which is red fruit?",
                List.of("apple", "orange", "Banana", "mango"), 0));
        questions.add(new Question("Which planet is known as the Red Planet?",
                List.of("Earth", "Mars", "Jupiter", "Venus"), 1));
        questions.add(new Question("What is 2 + 2?",
                List.of("3", "4", "5", "6"), 1));
        return questions;
    }

    public static void displayResults(int score, int totalQuestions, List<Boolean> results) {
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Your Score: " + score + "/" + totalQuestions);
        System.out.println("Correct/Incorrect Answers:");
        for(int i = 0; i < results.size(); i++){
            System.out.println("Question " + (i + 1) + ": " + (results.get(i) ? "Correct" : "Incorrect"));
        }
    }

    public static void printAnswer(int correctAnswerIndex){
        System.out.println("Correct Answer: " + (correctAnswerIndex + 1));
    }
}