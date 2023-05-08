package efub.session.blog.post.exception;

public class NotExistPostException extends RuntimeException{
    public NotExistPostException(){
        super("존재하지 않는 포스트입니다.");
    }
}
