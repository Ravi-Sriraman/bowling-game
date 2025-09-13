package ai.ravi.bowling.game;

public class Game {

    private final int[] rolls = new int[21];
    private int current = 0;

    public void roll(int pins) {
        rolls[current++] += pins;
    }

    public int score() {
        int score = 0;
        int firstInFrame = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(firstInFrame)) {
                score += 10 + nextTwoBallForStrike(firstInFrame);
                firstInFrame += 1;
            } else if (isSpare(firstInFrame)) {
                score += 10 + nextBallForSpare(firstInFrame);
                firstInFrame += 2;
            } else {
                score += towBallsInFrame(firstInFrame);
                firstInFrame += 2;
            }
        }
        return score;
    }

    private int towBallsInFrame(int firstInFrame) {
        return rolls[firstInFrame] + rolls[firstInFrame + 1];
    }

    private int nextBallForSpare(int firstInFrame) {
        return rolls[firstInFrame + 2];
    }

    private int nextTwoBallForStrike(int firstInFrame) {
        return rolls[firstInFrame + 1] + nextBallForSpare(firstInFrame);
    }

    private boolean isStrike(int firstInFrame) {
        return rolls[firstInFrame] == 10;
    }

    private boolean isSpare(int firstInFrame) {
        return towBallsInFrame(firstInFrame) == 10;
    }
}
