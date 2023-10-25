package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    private final int LIST_LEN = 3;

    private List<Integer> computer = new ArrayList<>();
    private int strike;
    private int ball;

    Game() {
        setRandomNumbers();
    }

    public int getStrike() {
        return strike;
    }

    /**
     * 랜덤 숫자를 생성해 리스트 형태로 반환하는 함수
     */
    public void setRandomNumbers() {
        computer.clear();
        while (computer.size() < LIST_LEN) { //3자리의 자연수
            int randomNumber = Randoms.pickNumberInRange(1, 9); //1부터 9까지의 숫자로 구성
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber); //중복 불가
            }
        }
    }

    /**
     * 사용자의 입력 리스트 바탕으로 스트라이크/볼 개수를 배열에 저장해 반환하는 함수 같은 수가 같은 자리에 있으면 strike 같은 수가 다른 자리에 있으면 ball
     */
    public void count(List<Integer> user) {
        strike = 0;
        ball = 0;
        for (int i = 0; i < LIST_LEN; i++) {
            if (Objects.equals(computer.get(i), user.get(i))) {
                strike++;
            } else {
                if (computer.contains(user.get(i))) {
                    ball++;
                }
            }
        }
    }

    /**
     * 스트라이크/볼 개수를 바탕으로 결과 출력 둘 다 개수가 0일 경우 낫싱 출력
     */
    public String getResultString() {

        StringBuilder sb = new StringBuilder();
        if (ball != 0) {
            sb.append(ball + "볼 ");
        }
        if (strike != 0) {
            sb.append(strike + "스트라이크 ");
        }
        if (ball == 0 && strike == 0) {
            sb.append("낫싱");
        }

        return sb.toString();
    }

    /**
     * 게임이 끝난 경우 재시작/종료 구 입력은 1과 2 중 하나의 수
     */
    public boolean checkRestart(String input) {
        //문자를 두 개 이상 입력
        if (input.length() != 1) {
            throw new IllegalArgumentException("1이나 2 하나만 공백없이 입력해야 합니다.");
        }

        if (input.charAt(0) - '0' == 1) { //재시작
            return true;
        } else if (input.charAt(0) - '0' == 2) { //종료
            return false;
        } else { //입력이 1이나 2가 아닌 경우
            throw new IllegalArgumentException("1이나 2를 입력해야 합니다.");
        }
    }
}
