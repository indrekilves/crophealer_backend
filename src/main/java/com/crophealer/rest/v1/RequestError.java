package com.crophealer.rest.v1;

public enum RequestError
{
    // User
    E0001("User name is missing."), E0002("User name exists."), E0003("Password is missing."), E0004(
            "Password is too weak."), E0005("Email is missing."), E0006("Email format is faulty."),

    // Message
    E0007("SenderID is missing."), E0008("ReceiverID is missing."), E0009("Subject is missing."), E0010(
            "Sender is unknown."), E0011("Receiver is unknown."),

    // Role / User / Security
    E0012("Role is unknown.");

    private final String value;

    private RequestError( final String value )
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return getValue();
    }
}
