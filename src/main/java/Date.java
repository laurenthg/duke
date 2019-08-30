import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.zip.DataFormatException;

public class Date {
    private GregorianCalendar d;

    public Date(GregorianCalendar d){
        this.d = d;
    }

    @Override
    public String toString(){
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        fmt.setCalendar(this.d);
        String dateFormatted = fmt.format(this.d.getTime());
        return  dateFormatted; //no need secondes and time zone
    }
}
