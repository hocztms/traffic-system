package trafficsystem.utils;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import trafficsystem.security.entity.MyUserDetails;

@Component
public class AuthUtils {


    public MyUserDetails getContextUserDetails(){
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
