package com.crophealer.security;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.crophealer.domain.Company;
import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.Field;
import com.crophealer.domain.Message;
import com.crophealer.rest.v1.RequestError;
import com.crophealer.rest.v1.UserResource;
import com.crophealer.utils.EmailValidator;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUsersesByUsernameEquals" })
public class Users
{

    /**
     */
    private String username;

    /**
     */
    private String password;

    /**
     */
    private Boolean enabled;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usr")
    private final Set < DiagnosedProblem > diagnosedProblems = new HashSet < DiagnosedProblem >();

    /**
     */
    private String email;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date expirationDate;

    /**
     */
    private String phone;

    @Override
    public String toString()
    {
        return "User [username=" + username + ", expirationDate=" + expirationDate + "]";
    }

    public void setPassword( String password )
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode( password );
    }

    public static RequestError validateUserResource( UserResource ur )
    {
        if ( ur.getUsername().isEmpty() )
            return RequestError.E0001;
        if ( doesUsernameExists( ur.getUsername() ) )
            return RequestError.E0002;
        if ( ur.getPassword().isEmpty() )
            return RequestError.E0003;
        if ( isPasswordToWeak( ur.getPassword() ) )
            return RequestError.E0004;
        if ( ur.getEmail().isEmpty() )
            return RequestError.E0005;
        if ( isBadEmailFormat( ur.getEmail() ) )
            return RequestError.E0006;
        return null;
    }

    private static boolean doesUsernameExists( String un )
    {
        TypedQuery < Users > result = Users.findUsersesByUsernameEquals( un );
        List < Users > userList = result.getResultList();
        return userList.isEmpty() ? false : true;
    }

    private static boolean isPasswordToWeak( String pw )
    {
        // TODO: isPasswordToWeak isn't implemented
        return false;
    }

    private static boolean isBadEmailFormat( String email2 )
    {
        // TODO: isEmailFormatFaulty isn't implemented
        EmailValidator eValidator = new EmailValidator();
        return !eValidator.validate( email2 );
    }

    public static Users createFromResource( UserResource ur )
    {
        if ( ur == null )
            return null;
        Users u = new Users();
        u.setUsername( ur.getUsername() );
        u.setPassword( ur.getPassword() );
        u.setEmail( ur.getEmail() );
        u.setPhone( ur.getPhone() );
        u.setExpirationDate( getTrialPeriodEndDate() );
        u.setEnabled( true );
        u.persist();

        // TODO: Send verification email.
        return u;
    }

    private static Date getTrialPeriodEndDate()
    {
        int trialPeriodLength = 30;
        Calendar c = Calendar.getInstance();
        c.setTime( new Date() );
        c.add( Calendar.DATE, trialPeriodLength );
        return c.getTime();
    }

    public void updateFromResource( UserResource ur )
    {
        if ( ur == null )
            return;
        if ( ur.getPassword() != null )
            this.setPassword( ur.getPassword() );
        if ( ur.getEmail() != null )
            this.setEmail( ur.getEmail() );
        if ( ur.getPhone() != null )
            this.setPhone( ur.getPhone() );
        this.persist();
    }

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private final Set < Message > sentMessages = new HashSet < Message >();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private final Set < Message > receivedMessages = new HashSet < Message >();

    /**
     */
    @ManyToOne
    private Company company;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private final Set < Field > fields = new HashSet < Field >();

}
