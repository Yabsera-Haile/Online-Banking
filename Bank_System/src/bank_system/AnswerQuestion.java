package bank_system;

public class AnswerQuestion {
    private String question, answer;
    protected long accountno;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAccountno(long accountno) {
        this.accountno = accountno;
    }

    public long getAccountno() {
        return accountno;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}