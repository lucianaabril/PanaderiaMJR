package excepciones;

public class TicketInvalidoException extends Exception{
    private int codigoError;

    public TicketInvalidoException(int codigoError){
        super();
        this.codigoError = codigoError;
    }

    @Override
    public String getMessage() {
        String message = "";
        switch(codigoError){
            case 1:
                message = "El DNI del vendedor no coincide con ninguno registrado, TICKET INVALIDO";
                break;
            case 2:
                message = "La venta registrada en el ticket excede el stock disponible, TICKET INVALIDO ";
                break;
        }
        return message;
    }
}
