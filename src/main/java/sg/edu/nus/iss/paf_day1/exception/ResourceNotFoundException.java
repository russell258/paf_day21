package sg.edu.nus.iss.paf_day1.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }

}
