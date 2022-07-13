package p04_InterfaceSegregation.p02_identity.interfaces;

public interface RegistrationValidation {

    boolean getRequireUniqueEmail();
    int getMinRequiredPasswordLength();
    int getMaxRequiredPasswordLength();
}
