package efub.session.blog.account.exception;

public class NotExistAccountException extends RuntimeException{
    public NotExistAccountException() {
        super("존재하지 않는 계정입니다.");
    }
}
